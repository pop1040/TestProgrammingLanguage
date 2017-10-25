package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PArray;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

public class EvalNewArray implements Evaluatable{

	Evaluatable size;
	PClass type;
	
	public EvalNewArray(Evaluatable size, PClass type) {
		this.size = size;
		this.type = type;
	}

	@Override
	public PClass getEvaluatedType() {
		return PArray.pClass;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(evaluated.size()==0)return size;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return new PArray(type, ((PPrimitive)evaluated.get(0)).getIntValue());
	}

}
