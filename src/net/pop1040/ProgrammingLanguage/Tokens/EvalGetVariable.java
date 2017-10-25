package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PArray;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

import static net.pop1040.ProgrammingLanguage.Tokens.VariableReference.Type.*;

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
		if((variableReference.mode == FIELD || variableReference.mode == ARRAY) && evaluated.size() == 0)return variableReference.object;
		if(variableReference.mode == ARRAY && evaluated.size() == 1)return variableReference.index;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		if(variableReference.mode == FIELD){
			return ((PObject)evaluated.get(0)).fieldMap.get(variableReference.name);
		}
		if(variableReference.mode == ARRAY){
			return ((PArray)evaluated.get(0)).value[((PPrimitive)evaluated.get(1)).getIntValue()];
		}
		return stack.getVariable(variableReference.name, false);
	}
	
	@Override
	public String toString() {
		return "EvalGetVariable[variable=" + variableReference + "]";
	}

}
