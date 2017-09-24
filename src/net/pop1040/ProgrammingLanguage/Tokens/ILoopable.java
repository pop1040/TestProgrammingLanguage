package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.FunctionStack;

public interface ILoopable {
	
	boolean shouldLoop(FunctionStack stack);
	
}
