package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PBoolean;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class TokenEvalWhile extends Token implements Evaluatable, ScopableToken, ILoopable{


	Evaluatable conditional;
	Token executedToken;
	
	public TokenEvalWhile(Evaluatable conditional, Token executedToken) {
		this.conditional=conditional;
		this.executedToken=executedToken;
	}
	
	
	@Override
	public ArrayList<Token> getTokens() {
		return new ArrayList<Token>(Arrays.asList(executedToken));
	}

	@Override
	public boolean isAccessibilityBarrier() {
		return false;
	}

	@Override
	public PClass getEvaluatedType() {
		return null;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(evaluated.size() == 0)return conditional;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return null;
	}

	@Override
	public void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values) {
		FunctionInstance funcInst = stack.stack.getLast();
		if(((PBoolean)values.get(0)).value){
			funcInst.toLoop=true;
			stack.stack.add(new FunctionStack.FunctionInstance(this));
		}else funcInst.toLoop=false;
	}

	@Override
	public boolean shouldLoop(FunctionStack stack) {
		FunctionInstance funcInst = stack.stack.getLast();
		if(funcInst.toLoop){
			funcInst.toLoop=false;
			return true;
		}
		return false;
	}

}
