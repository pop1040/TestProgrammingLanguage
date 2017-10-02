package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Iterator;
import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class TokenReturn extends Token implements ScopeInterupter, Evaluatable{
	
	Evaluatable input;
	
	public TokenReturn(Evaluatable input) {
		this.input=input;
	}
	/*
	@Override
	public boolean shouldBreak(FunctionStack stack, ArrayList<ScopableToken> originialState) {
		//int i;
		//for(i=originialState.size()-1; i>=0; i--)if(originialState.get(i) == boundFunction)break;
		//return stack.stack.size()>i;
	}*/
	public int getBreakDepth(FunctionStack stack) throws NullPointerException{
		Iterator<FunctionInstance> iter = stack.stack.descendingIterator();
		int counter=0;
		while(iter.hasNext()){
			FunctionInstance inst = iter.next();
			counter++;
			if(inst.getFunction() instanceof Function)return counter;
		}
		throw new NullPointerException("reached end of stack, no function found cannot return");
	}

	@Override
	public void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values) {
		
	}

	@Override
	public PClass getEvaluatedType() {
		return null;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(evaluated.isEmpty() && input!=null)return input;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//if(input!=null)stack.functionParamaterLists.get(boundFunction).add(evaluated.get(0)); //TODO predicted serious problem with recursive function calls
		//if(input!=null)stack.functionParamaterLists.get().add(evaluated.get(0));
		if(input!=null){
			Iterator<FunctionInstance> iter = stack.stack.descendingIterator();
			FunctionInstance inst;
			
			boolean firstPassed = false; //since we will pass the function we are breaking before we reach the parent
			
			while(iter.hasNext()){
				inst = iter.next();
				if(firstPassed){
					inst.setReturnValue(evaluated.get(0));
					break;
				}
				if(inst.getFunction() instanceof Function)firstPassed = true;
			}
			//stack.stack.getLast().setReturnValue(evaluated.get(0));
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "TokenReturn[input=[" + input + "]]";
	}

}
