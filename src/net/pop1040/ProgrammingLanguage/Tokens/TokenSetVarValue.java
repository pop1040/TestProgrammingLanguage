package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Tokens.VariableReference.Type;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

public class TokenSetVarValue extends Token implements Evaluatable{
	
	public VariableReference variableReference;
	private Evaluatable evaluated;
	

	public TokenSetVarValue(VariableReference variableReference, Evaluatable evaluated) {
		this.variableReference=variableReference;
		this.evaluated=evaluated;
	}
	
	@Override
	public void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values) {
		
	}

	@Override
	public PClass getEvaluatedType() {
		return variableReference.type;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//System.out.println("[" + this + "] get next eval (evaluated size = " + evaluated.size() + ")");
		//if(evaluated.size()==0)return this.evaluated;
		if(variableReference.mode == Type.FIELD){
			if(evaluated.size()==0)return variableReference.object;
			if(evaluated.size()==1)return this.evaluated;
		}else if(evaluated.size()==0)return this.evaluated;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		if(variableReference.mode == Type.FIELD){
			PObject obj = (PObject) evaluated.get(0);
			obj.fields.set(obj.getPClass().fieldNames.indexOf(variableReference.name), evaluated.get(1)); //Can't use the index of the original object because there might be multiple instances of it
			obj.fieldMap.put(variableReference.name, evaluated.get(1));
			//TODO handle object fields
		}else{
			if(stack.getVariable(variableReference.name, false) != null)stack.replaceVariable(variableReference.name, evaluated.get(0), false);
			else throw new NullPointerException("Variable does not exist");
		}
		//else stack.stack.getLast().addVariable(variableName, evaluated.get(0));
		return evaluated.get(variableReference.mode == Type.FIELD?1:0); //I could also do .get(variableReference.mode.ordinal()) but that's too janky for me
		
	}
	
	@Override
	public String toString() {
		return "TokenSetVarVal[ref=" + variableReference + ", evaluated=" + evaluated + "]";
	}
	
	
}
