package net.pop1040.ProgrammingLanguage.Tokens.Operations.Boolean;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Types.PBoolean;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class EvalNot implements Evaluatable {
	
	Evaluatable a;

	public EvalNot(Evaluatable a) {
		this.a = a;
	}

	@Override
	public PClass getEvaluatedType() {
		return PBoolean.pClass;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(evaluated.size()==0)return a;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return new PBoolean(!((PBoolean)evaluated.get(0)).value);
	}
	
	@Override
	public String toString() {
		return "EvalNot[evaluated=" + a + "]";
	}
	
}
