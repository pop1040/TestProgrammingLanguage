package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public interface Invokable extends Evaluatable, ScopableToken {
	
	public boolean canReturnYet(FunctionStack stack, ArrayList<PGeneric> evaluated);
	
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated);
}
