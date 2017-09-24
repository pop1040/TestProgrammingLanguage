package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PBoolean;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class TokenEvalIf extends Token implements Evaluatable, ScopableToken {
	
	Evaluatable conditional;
	Token executedToken;
	ElseBlock elseBlock;
	
	public TokenEvalIf(Evaluatable conditional, Token executedToken) {
		this.conditional=conditional;
		this.executedToken=executedToken;
	}
	public TokenEvalIf(Evaluatable conditional, Token executedToken, Token elseToken) {
		this.conditional=conditional;
		this.executedToken=executedToken;
		elseBlock = new ElseBlock(elseToken);
	}

	@Override
	public ArrayList<Token> getTokens() {
		//if(executedToken instanceof Subroutine) ((Subroutine) executedToken).getTokens();
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
		if(((PBoolean)values.get(0)).value){
			stack.stack.add(new FunctionStack.FunctionInstance(this));
		}else if(elseBlock != null){
			stack.stack.add(new FunctionStack.FunctionInstance(elseBlock));
		}
	}
	
	
	public static class ElseBlock implements ScopableToken{
		
		Token executedToken;
		
		public ElseBlock(Token token) {
			executedToken = token;
		}
		
		@Override
		public ArrayList<Token> getTokens() {
			return new ArrayList<Token>(Arrays.asList(executedToken));
		}

		@Override
		public boolean isAccessibilityBarrier() {
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		return "TokenEvalIf[conditional=" + conditional + "]";
	}
	
}
