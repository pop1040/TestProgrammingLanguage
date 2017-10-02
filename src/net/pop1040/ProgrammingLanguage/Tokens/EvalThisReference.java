package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class EvalThisReference implements Evaluatable {
	
	public Method method;
	
	public EvalThisReference(Method method) {
		this.method=method;
	}

	@Override
	public PClass getEvaluatedType() {
		return method.getPClass();
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return method.getObject(stack);
	}

}
