package net.pop1040.ProgrammingLanguage.Tokens.Operations.math;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PInteger;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

public class EvalMultiplyInteger implements Evaluatable{
	
	Evaluatable a, b;
	public PClass returnType = PInteger.pClass;
	
	public EvalMultiplyInteger(Evaluatable a, Evaluatable b) {
		this.a=a;
		this.b=b;
	}
	

	@Override
	public PClass getEvaluatedType() {
		return returnType;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//System.out.println("[" + this + "] get next eval (evaluated size = " + evaluated.size() + ")");
		if(evaluated.size()==0)return a;
		if(evaluated.size()==1)return b;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//System.out.println("[" + this + "] get return value (evaluated size = " + evaluated.size() + ")");
		//System.out.println("a: " + evaluated.get(0));
		//System.out.println("a: " + evaluated.get(1));
		return new PInteger(((PPrimitive)evaluated.get(0)).getIntValue() * ((PPrimitive)evaluated.get(1)).getIntValue());
	}
	

	@Override
	public String toString() {
		return "EvalMultiplyInteger[EvalA=" + a + ", EvalB=" + b + "]";
	}
	
}
