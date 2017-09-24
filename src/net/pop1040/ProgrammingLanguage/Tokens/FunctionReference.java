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
	public Evaluatable object;
	public String className;
	public PClass returnType;
	
	public FunctionReference(String functionName, String className, PClass returnType) {
		this.mode = Type.FUNCTION;
		this.functionName = functionName;
		this.className = className;
		this.returnType = returnType;
	}
	
	public FunctionReference(String functionName, Evaluatable object, PClass returnType) {
		this.mode = Type.METHOD;
		this.functionName = functionName;
		this.object = object;
		this.returnType = returnType;
	}
	
	public FunctionReference(String functionName, PClass returnType, String className){
		this.mode = Type.INTRINSIC;
		this.functionName = functionName;
		this.returnType = returnType;
		this.className=className;
	}
}
