package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class EvalConstant implements Evaluatable {
	
	PGeneric value;
	
	public EvalConstant(PGeneric value) {
		this.value=value;
	}

	@Override
	public PClass getEvaluatedType() {
		return value.getPClass();
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return value;
	}
	
	@Override
	public String toString() {
		return "EvalConst[value=" + value + "]";
	}

}
