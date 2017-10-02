package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class Function implements Invokable{
	
	Token executedToken;
	PClass returnType;
	
	String name;
	
	ArrayList<String> argumentNames  = new ArrayList<String>();
	ArrayList<PClass> argumentTypes  = new ArrayList<PClass>();
	//ArrayList<Evaluatable> arguments = new ArrayList<Evaluatable>();
	
	public Function(String name, Token executedToken, PClass returnType){
		this.name=name;
		this.executedToken=executedToken;
		this.returnType=returnType;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<Token> getTokens() {
		//if(executedToken instanceof Subroutine) ((Subroutine) executedToken).getTokens();
		return new ArrayList<Token>(Arrays.asList(executedToken));
	}
	
	public String[] getArgumentNames(){
		return argumentNames.toArray(new String[argumentNames.size()]);
	}
	
	public String[] getArgumentTypeNames(){
		String[] names = new String[argumentTypes.size()];
		for(int i=0; i<names.length; i++)names[i]=argumentTypes.get(i).typeName;
		return names;
	}

	@Override
	public PClass getEvaluatedType() {
		return returnType;
	}
	

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//if(evaluated.size()>=arguments.size())return null;
		//return arguments.get(evaluated.size());
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//return evaluated.get(evaluated.size()-1);
		return stack.stack.getLast().getReturnVariable();
	}
	
	public Function addArgument(String name, PClass type){
		argumentNames.add(name);
		argumentTypes.add(type);
		return this;
	}
	

	
	@Override
	public String toString() {
		return "Function[name=" + name + ", ReturnType=" + returnType + "]";
	}

	@Override
	public boolean canReturnYet(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return stack.stack.getLast().getReturnState();
	}

	@Override
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated) {
		FunctionStack.FunctionInstance functionInstance;
		stack.stack.add(functionInstance = new FunctionStack.FunctionInstance(this)); //wait why did I set this up like this?
		//System.out.println("evaluated Size = " + evaluated.size());
		for(int i=0; i<evaluated.size(); i++)functionInstance.addVariable(argumentNames.get(i), evaluated.get(i));
	}

	@Override
	public boolean isAccessibilityBarrier() {
		return true;
	}
	
	
}
