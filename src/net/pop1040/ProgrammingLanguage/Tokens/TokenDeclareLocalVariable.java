package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.InstructionExecuter;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class TokenDeclareLocalVariable extends Token implements Evaluatable{
	
	public String variableName;
	public PClass variableType;
	private Evaluatable evaluated;
	

	public TokenDeclareLocalVariable(String variableName, PClass variableType, Evaluatable evaluated) {
		this.variableName=variableName;
		this.variableType=variableType;
		this.evaluated=evaluated;
	}
	
	@Override
	public void run(FunctionStack stack, InstructionExecuter instructionPointer, ArrayList<PGeneric> values) {
		
	}

	@Override
	public PClass getEvaluatedType() {
		return variableType;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//System.out.println("[" + this + "] get next eval (evaluated size = " + evaluated.size() + ")");
		if(evaluated.size()==0)return this.evaluated;
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//if(stack.getVariable(variableName, false) != null)stack.replaceVariable(variableName, evaluated.get(0), false);
		stack.stack.getLast().addVariable(variableName, evaluated.get(0));
		return evaluated.get(0);
		
	}
	
	@Override
	public String toString() {
		//return super.toString();
		return "TokenDeclareLocalVariable[variable=\"" + variableName + "\", variableType=" + variableType.typeName + ", evaluated=" + evaluated + "]";
	}
	
	
}
