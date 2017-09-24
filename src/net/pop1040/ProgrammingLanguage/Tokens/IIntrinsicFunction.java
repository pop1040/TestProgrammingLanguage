package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public interface IIntrinsicFunction {

	PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated);

}
