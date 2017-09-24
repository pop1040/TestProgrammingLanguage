package net.pop1040.ProgrammingLanguage.Tokens.Operations.Boolean;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Types.PBoolean;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class EvalXOr implements Evaluatable {
	
	Evaluatable a, b;

	public EvalXOr(Evaluatable a, Evaluatable b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public PClass getEvaluatedType() {
		return PBoolean.pClass;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(evaluated.size()==0)return a;
		if(evaluated.size()==1)return b;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return new PBoolean(((PBoolean)evaluated.get(0)).value ^ ((PBoolean)evaluated.get(1)).value);
	}

}
