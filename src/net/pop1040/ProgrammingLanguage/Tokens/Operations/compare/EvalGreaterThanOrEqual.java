package net.pop1040.ProgrammingLanguage.Tokens.Operations.compare;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Types.PBoolean;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PDouble;
import net.pop1040.ProgrammingLanguage.Types.PFloat;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PLong;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

public class EvalGreaterThanOrEqual implements Evaluatable {
	
	Evaluatable a, b;

	public EvalGreaterThanOrEqual(Evaluatable a, Evaluatable b) {
		this.a=a;
		this.b=b;
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
		PPrimitive valA = (PPrimitive) evaluated.get(0);
		PPrimitive valB = (PPrimitive) evaluated.get(1);
		if(valA instanceof PFloat){
			if(valB instanceof PFloat)return new PBoolean(valA.getFloatValue()       >= valB.getFloatValue());
			if(valB instanceof PDouble)return new PBoolean(valA.getFloatValue()      >= valB.getDoubleValue());
			if(valB instanceof PLong)return new PBoolean(valA.getFloatValue()        >= valB.getLongValue());
			if(!(valB instanceof PBoolean))return new PBoolean(valA.getFloatValue()  >= valB.getIntValue());

		}if(valA instanceof PDouble){
			if(valB instanceof PFloat)return new PBoolean(valA.getDoubleValue()      >= valB.getFloatValue());
			if(valB instanceof PDouble)return new PBoolean(valA.getDoubleValue()     >= valB.getDoubleValue());
			if(valB instanceof PLong)return new PBoolean(valA.getDoubleValue()       >= valB.getLongValue());
			if(!(valB instanceof PBoolean))return new PBoolean(valA.getDoubleValue() >= valB.getIntValue());
			
		}if(valA instanceof PLong){
			if(valB instanceof PFloat)return new PBoolean(valA.getLongValue()        >= valB.getFloatValue());
			if(valB instanceof PDouble)return new PBoolean(valA.getLongValue()       >= valB.getDoubleValue());
			if(valB instanceof PLong)return new PBoolean(valA.getLongValue()         >= valB.getLongValue());
			if(!(valB instanceof PBoolean))return new PBoolean(valA.getLongValue()   >= valB.getIntValue());
		}if(!(valA instanceof PBoolean)){
			if(valB instanceof PFloat)return new PBoolean(valA.getIntValue()         >= valB.getFloatValue());
			if(valB instanceof PDouble)return new PBoolean(valA.getIntValue()        >= valB.getDoubleValue());
			if(valB instanceof PLong)return new PBoolean(valA.getIntValue()          >= valB.getLongValue());
			if(!(valB instanceof PBoolean))return new PBoolean(valA.getIntValue()    >= valB.getIntValue());
		}
		return null;
	}
	

	@Override
	public String toString() {
		return "EvalGreaterThanOrEqual[evalA=" + a + ", evalB=" + b + "]";
	}

}
