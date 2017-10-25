package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.Types.PClass;

public class FunctionReference {
	
	public static enum Type{
		FUNCTION,
		METHOD,
		CONSTRUCTOR,
		INTRINSIC
	}
	
	public Type mode;
	public String functionName;
	public Object[] arguments;
	public Evaluatable object;
	public String className;
	public PClass returnType;
	
	public FunctionReference(PClass clazz, Object ... arguments) {
		this.mode = Type.CONSTRUCTOR;
		this.className = clazz.typeName;
		this.returnType = clazz;
		this.arguments = arguments;
	}
	
	public FunctionReference(String functionName, String className, PClass returnType, Object ... arguments) {
		this.mode = Type.FUNCTION;
		this.functionName = functionName;
		this.className = className;
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public FunctionReference(String functionName, Evaluatable object, PClass returnType, Object ... arguments) {
		this.mode = Type.METHOD;
		this.functionName = functionName;
		this.object = object;
		this.returnType = returnType;
		this.arguments = arguments;
	}
	
	public FunctionReference(String functionName, PClass returnType, String className, Object ... arguments){
		this.mode = Type.INTRINSIC;
		this.functionName = functionName;
		this.returnType = returnType;
		this.className=className;
		this.arguments = arguments;
	}
}
