package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Tokens.VariableReference.Type;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

public class EvalGetVariable implements Evaluatable {
	
	public VariableReference variableReference;
	
	public EvalGetVariable(VariableReference variableReference) {
		this.variableReference=variableReference;
	}

	@Override
	public PClass getEvaluatedType() {
		return variableReference.type;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		if(variableReference.mode == Type.FIELD && evaluated.size() == 0)return variableReference.object;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		if(variableReference.mode == Type.FIELD){
			return ((PObject)evaluated.get(0)).fieldMap.get(variableReference.name);
			//TODO handle object fields
		}
		return stack.getVariable(variableReference.name, false);
	}
	
	@Override
	public String toString() {
		return "EvalGetVariable[variable=" + variableReference + "]";
	}

}
