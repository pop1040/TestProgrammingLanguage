package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class Subroutine extends Token implements ScopableToken{
	
	public ArrayList<Token> tokens = new ArrayList<Token>();
	
	public Subroutine(){
		
	}

	@Override
	public ArrayList<Token> getTokens() {
		return tokens;
	}

	@Override
	public void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values) {
		stack.stack.add(new FunctionStack.FunctionInstance(this));
		//instructionPointer.executionIndex.add(Integer.valueOf(0));
	}
	
	@Override
	public String toString() {
		return "Subroutine[" + super.toString() + "]";
	}

	@Override
	public boolean isAccessibilityBarrier() {
		return false;
	}
	
	
	
	
}
