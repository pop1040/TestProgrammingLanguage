package net.pop1040.ProgrammingLanguage.Tokens.Operations.math;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;
import net.pop1040.ProgrammingLanguage.Types.PShort;

public class EvalModulusShort implements Evaluatable{
	
	Evaluatable a, b;
	public PClass returnType = PShort.pClass;
	
	public EvalModulusShort(Evaluatable a, Evaluatable b) {
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
		return new PShort((short) (((PPrimitive)evaluated.get(0)).getShortValue() % ((PPrimitive)evaluated.get(1)).getShortValue()));
	}

}
