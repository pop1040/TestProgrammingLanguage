package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public abstract class Token {
	
	public abstract void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values);
	
}
