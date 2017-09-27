package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.Types.PClass;

public class FunctionReference {
	
	public static enum Type{
		FUNCTION,
		METHOD,
		INTRINSIC
	}
	
	public Type mode;
	public String functionName;
	public String[] arguments;
	public Evaluatable object;
	public String className;
	public PClass returnType;
	
	public FunctionReference(String functionName, String className, PClass returnType, String ... arguments) {
		this.mode = Type.FUNCTION;
		this.functionName = functionName;
		this.className = className;
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public FunctionReference(String functionName, Evaluatable object, PClass returnType, String ... arguments) {
		this.mode = Type.METHOD;
		this.functionName = functionName;
		this.object = object;
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public FunctionReference(String functionName, PClass returnType, String className, String ... arguments){
		this.mode = Type.INTRINSIC;
		this.functionName = functionName;
		this.returnType = returnType;
		this.className=className;
		this.arguments = arguments;
	}
}
