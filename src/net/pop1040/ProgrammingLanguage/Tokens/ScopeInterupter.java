package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.FunctionStack;

public interface ScopeInterupter {
	
	//public boolean shouldBreak(FunctionStack stack, ArrayList<ScopableToken> originialState);
	
	public int getBreakDepth(FunctionStack stack);
	
}
