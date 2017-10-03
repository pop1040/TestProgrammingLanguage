package net.pop1040.ProgrammingLanguage;

import java.io.PrintStream;
import java.util.ArrayList;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance.EvalTreeLayer;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Tokens.ILoopable;
import net.pop1040.ProgrammingLanguage.Tokens.Invokable;
import net.pop1040.ProgrammingLanguage.Tokens.ScopableToken;
import net.pop1040.ProgrammingLanguage.Tokens.ScopeInterupter;
import net.pop1040.ProgrammingLanguage.Tokens.Token;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class InstructionExecuter {
	
	FunctionStack stack;
	
	//public LinkedList<Integer> executionIndex = new LinkedList<Integer>();
	//LinkedList<Evaluatable> evaluationTree = new LinkedList<Evaluatable>();
	//HashMap<Evaluatable, ArrayList<PGeneric>> evaluationTreeData = new HashMap<Evaluatable, ArrayList<PGeneric>>();
	ExecutionEngine executionEngine;
	
	boolean haulted=false;

	public InstructionExecuter(FunctionStack stack, ExecutionEngine executionEngine) {
		this.stack=stack;
		//executionIndex.add(Integer.valueOf(0));
		this.executionEngine = executionEngine;
	}
	
	///**
	// * 
	// * @return true if the InstructionPointer is evaluating a value rather than executing instructions
	// */
	/*public boolean isEvaluating(){
		return evaluationDepth==0;
	}*/

	public void step() {
		if(haulted)return;
		FunctionInstance cStackInstance = stack.stack.getLast();
		
		int index = cStackInstance.execIndex;
		
		Token currentToken = cStackInstance.function.getTokens().get(index);
		
		if(currentToken instanceof Evaluatable){
			EvalTreeLayer evaluatingObjectLayer = null;
			if(cStackInstance.evaluationTree.isEmpty()){
				evaluatingObjectLayer = new EvalTreeLayer((Evaluatable) currentToken);
				cStackInstance.evaluationTree.add(evaluatingObjectLayer);
			}else evaluatingObjectLayer = cStackInstance.evaluationTree.getLast();
			
			Evaluatable evaluatingObject = evaluatingObjectLayer.obj;
			
			
			Evaluatable nextToProcess = null;
			while(true){
				nextToProcess = evaluatingObject.getNextEvaluatable(executionEngine.classes, evaluatingObjectLayer.paramaters);
				if(nextToProcess == null)break;
				evaluatingObject = nextToProcess;
				cStackInstance.evaluationTree.add(evaluatingObjectLayer = new EvalTreeLayer(evaluatingObject)); //ha
			}
			
			if(evaluatingObject instanceof Invokable){
				//System.out.println("Invokable");
				if(((Invokable) evaluatingObject).canReturnYet(stack, evaluatingObjectLayer.paramaters)){
					//System.out.println("can return (param size = " + evaluatingObjectLayer.paramaters.size() + ")");
					ArrayList<PGeneric> inputs = evaluatingObjectLayer.paramaters;
					if(evaluatingObject != currentToken){
						cStackInstance.evaluationTree.removeLast();
						cStackInstance.evaluationTree.getLast().paramaters.add(evaluatingObject.getReturnValue(stack, inputs)); //TODO probably gonna change this
					}else{
						evaluatingObject.getReturnValue(stack, inputs); //so root level function calls work with intrinsics
						cStackInstance.evaluationTree.removeLast();
					}
				}else{
					//System.out.println("cannot return, setting up (param size = " + evaluatingObjectLayer.paramaters.size() + ")");
					((Invokable) evaluatingObject).setup(stack, cStackInstance, evaluatingObjectLayer.paramaters);
				}
			}else{
				ArrayList<PGeneric> inputs = evaluatingObjectLayer.paramaters;
				if(evaluatingObject instanceof Token)((Token) evaluatingObject).run(stack, this, inputs);
				PGeneric retVal = evaluatingObject.getReturnValue(stack, inputs);
				if(evaluatingObject != currentToken){
					cStackInstance.evaluationTree.removeLast();
					cStackInstance.evaluationTree.getLast().paramaters.add(retVal);
				}else cStackInstance.evaluationTree.removeLast();
			}
			if(evaluatingObject != currentToken)return;
		}else{
			currentToken.run(stack, this, null);
		}
		
		boolean flagIncrement = stack.stack.getLast() != cStackInstance;
		
		cStackInstance = stack.stack.getLast();
		//indexObj = executionIndex.getLast().intValue();
		//index = indexObj.intValue();
		index = cStackInstance.execIndex;
		currentToken = cStackInstance.function.getTokens().get(index);
		
		if(flagIncrement)return;
		
		ArrayList<ScopableToken> originalStackState = new ArrayList<ScopableToken>(stack.stack.size());
		for(FunctionInstance inst : stack.stack)originalStackState.add(inst.function);
		
		
		while(true){
			int removeDepth = 0;
			if(currentToken instanceof ScopeInterupter){
				if(currentToken instanceof Evaluatable){
					if(cStackInstance.evaluationTree.isEmpty())removeDepth = ((ScopeInterupter)currentToken).getBreakDepth(stack);
					else{
						EvalTreeLayer evaluatingObjectLayer = cStackInstance.evaluationTree.getLast();
						if(evaluatingObjectLayer.obj == currentToken && evaluatingObjectLayer.obj.getNextEvaluatable(executionEngine.classes, evaluatingObjectLayer.paramaters) == null)removeDepth = ((ScopeInterupter)currentToken).getBreakDepth(stack);
					}
				}else removeDepth = ((ScopeInterupter)currentToken).getBreakDepth(stack);
				
				//System.out.println("breaking to " + removeDepth + " layers down");
			}
			if(removeDepth == 0){
				if(currentToken instanceof Evaluatable){
					if(cStackInstance.evaluationTree.isEmpty()){
						if(currentToken instanceof ILoopable){
							if(!((ILoopable) currentToken).shouldLoop(stack))index++; //TODO I LEFT OFF HERE
						}else index++;
					}
					else if(cStackInstance.evaluationTree.getLast().obj == currentToken){
						cStackInstance.evaluationTree.clear();
						index++;
					}
				}else index++;
				if(index<cStackInstance.function.getTokens().size())break;
				removeDepth++;
			}

			while(removeDepth>0){
				removeDepth--;
				stack.stack.removeLast();
				if(stack.stack.isEmpty()){
					haulted = true;
					return;
				}
			}

			cStackInstance = stack.stack.getLast();
			index = cStackInstance.execIndex;
			currentToken = cStackInstance.function.getTokens().get(index);
		}
		
		cStackInstance.execIndex = index; // I probably could operate directly on cStackInstance.execIndex now
		
	}

	public void dumpState(PrintStream out) {
		out.println("-CurrentEvaluation:");
		if(!stack.stack.isEmpty()){
			FunctionInstance inst = stack.stack.getLast();
			out.println(" -Current Token:");
			out.println("   -" + inst.function.getTokens().get(inst.execIndex));
			out.println(" -Evaluation Tree:");
			int c=0;
			for(EvalTreeLayer eval : inst.evaluationTree){
				out.println("   -layer " + c++);
				out.println("     -Eval=" + eval.obj);
				out.println("     -Paramaters:");
				for(int i=0; i<eval.paramaters.size(); i++)out.println("       -" + eval.paramaters.get(i));
			}
			
		}
		out.println("-Instruction Stack State:");
		int i=0;
		for(FunctionInstance inst : stack.stack){
			out.println(" -[" + i + "]:" + inst.toString());
			//System.out.println("  -index: " + executionIndex.get(i));
			out.println("  -index: " + inst.execIndex);
			printTokens(inst.function.getTokens(), 2, out);
			i++;
		}
	}
	
	private static void printTokens(ArrayList<Token> list, int offset, PrintStream out){
		for(int i=0; i<offset; i++)out.print(" ");
		out.println("\\elements [length=" + list.size() + "]");
		for(Token token : list){
			for(int i=0; i<offset+1; i++)out.print(" ");
			out.println("-" + token);
			if(token instanceof ScopableToken)printTokens(((ScopableToken) token).getTokens(), offset+2, out);
		}
	}
	
	

}
