package net.pop1040.ProgrammingLanguage.Types;

import java.util.ArrayList;
import java.util.HashMap;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;
import net.pop1040.ProgrammingLanguage.Tokens.Constructor;
import net.pop1040.ProgrammingLanguage.Tokens.Function;
import net.pop1040.ProgrammingLanguage.Tokens.IIntrinsicFunction;
import net.pop1040.ProgrammingLanguage.Tokens.Method;

public class PClass extends PObject {
	
	public static final PClass pClass = new PClass("Class");
	public static final PClass pVoid = new PClass("Void");
	
	static{
		ExecutionEngine.baseClasses.add(pClass);
		ExecutionEngine.baseClasses.add(pVoid);
	}
	
	public String typeName;
	
	public PClass superClass;

	public ArrayList<Function> functions = new ArrayList<Function>();
	public ArrayList<String> functionNames = new ArrayList<String>();
	public HashMap<FuncKey, Function> functionMap = new HashMap<FuncKey, Function>();

	public ArrayList<IIntrinsicFunction> intrinsicFunctions = new ArrayList<IIntrinsicFunction>();
	public ArrayList<String> intrinsicFunctionNames = new ArrayList<String>();
	public HashMap<FuncKey, IIntrinsicFunction> intrinsicFunctionMap = new HashMap<FuncKey, IIntrinsicFunction>();
	
	public ArrayList<Method> methods = new ArrayList<Method>();
	public ArrayList<String> methodNames = new ArrayList<String>();
	public HashMap<FuncKey, Method> methodMap = new HashMap<FuncKey, Method>();
	
	public ArrayList<String> fieldNames = new ArrayList<String>();
	public HashMap<String, PClass> fieldClasses = new HashMap<String, PClass>();
	
	public HashMap<String[], Constructor> constructors = new HashMap<String[], Constructor>();
	
	public Constructor getConstructor(String[] arguments) {
		return constructors.get(arguments);
	}
	
	public Method[] getMethodArray(){
		return getMethods().toArray(new Method[getMethods().size()]);
	}
	
	public ArrayList<Method> getMethods(){
		return methods;
	}
	
	/*public HashMap<String, Method> getMethodMap(){
		return methodMap;
	}*/
	
	public ArrayList<String> getMethodNames(){
		return methodNames;
	}
	
	public String[] getMethodNameArray(){
		return getMethodNames().toArray(new String[getMethodNames().size()]);
	}
	
	public String[] getFieldNameArray(){
		return getFieldNames().toArray(new String[getFieldNames().size()]);
	}
	
	public ArrayList<String> getFieldNames(){
		return fieldNames;
	}
	
	public PClass(String typeName) {
		super(pClass);
		this.typeName=typeName;
	}
	
	@Override
	public String toString() {
		return typeName;
	}
	
	
	
	
	
	

	public Function[] getFunctionArray(){
		return getFunctions().toArray(new Function[getFunctions().size()]);
	}
	
	public ArrayList<Function> getFunctions(){
		return functions;
	}
	
	/*public HashMap<String, Function> getFunctionMap(){
		return functionMap;
	}*/
	
	public ArrayList<String> getFunctionNames(){
		return functionNames;
	}
	
	public String[] getFunctionNameArray(){
		return getFunctionNames().toArray(new String[getFunctionNames().size()]);
	}
	
	@Override
	public PClass getPClass() {
		return pClass;
	}

	
	
	
	public IIntrinsicFunction[] getIntrinsicFunctionArray(){
		return getIntrinsicFunctions().toArray(new IIntrinsicFunction[getIntrinsicFunctions().size()]);
	}
	
	public ArrayList<IIntrinsicFunction> getIntrinsicFunctions(){
		return intrinsicFunctions;
	}
	
	/*public HashMap<String, IIntrinsicFunction> getIntrinsicFunctionMap(){
		return intrinsicFunctionMap;
	}*/
	
	public ArrayList<String> getIntrinsicFunctionNames(){
		return intrinsicFunctionNames;
	}
	
	public String[] getIntrinsicFunctionNameArray(){
		return getIntrinsicFunctionNames().toArray(new String[getIntrinsicFunctionNames().size()]);
	}






	public void addFunction(Function function) {
		functions.add(function);
		functionMap.put(new FuncKey(function.getName(), function.getArgumentTypeNames()), function);
		functionNames.add(function.getName());
	}
	
	public void addIntrinsicFunction(String name, String[] arguments, IIntrinsicFunction function) {
		intrinsicFunctions.add(function);
		intrinsicFunctionMap.put(new FuncKey(name, arguments), function);
		intrinsicFunctionNames.add(name);
	}
	
	
	/**
	 * Gets a function by name, will recursively query the superclass
	 * @param functionName
	 * @param arguments 
	 * @return the function mapped by that name in this class or if
	 * that function is not mapped and this class has a superclass,
	 * the result of calling getFunction on that superclass, otherwise
	 * null
	 */
	public Function getFunction(String functionName, String[] arguments) {
		Function func = functionMap.get(new FuncKey(functionName, arguments));
		return func != null ? func : (superClass == null ? null : superClass.getFunction(functionName, arguments));
	}
	/**
	 * Gets a method by name, will recursively query the superclass
	 * @param methodName
	 * @return
	 */
	public Method getMethod(String methodName, String[] arguments) {
		Method mthd = methodMap.get(new FuncKey(methodName, arguments));
		return mthd != null ? mthd : (superClass == null ? null : superClass.getMethod(methodName, arguments));
	}
	
	/**
	 * Gets a function by name, will recursively query the superclass
	 * @param functionName
	 * @return the function mapped by that name in this class or if
	 * that function is not mapped and this class has a superclass,
	 * the result of calling getFunction on that superclass, otherwise
	 * null
	 */
	public IIntrinsicFunction getIntrinsicFunction(String functionName, String[] arguments) {
		IIntrinsicFunction func = intrinsicFunctionMap.get(new FuncKey(functionName, arguments));
		return func != null ? func : (superClass == null ? null : superClass.getIntrinsicFunction(functionName, arguments));
	}
	
	public static class FuncKey{
		
		String name;
		String[] arguments;
		
		public FuncKey(String name, String[] arguments) {
			this.name = name;
			this.arguments = arguments;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof FuncKey))return false;
			FuncKey key = (FuncKey) obj;
			if(!name.equals(key.name))return false;
			if(arguments==null || arguments.length==0)return key.arguments == null || key.arguments.length == 0;
			if(key.arguments == null)return false;
			if(arguments.length != key.arguments.length)return false;
			for(int i=0; i<arguments.length; i++)if(!arguments[i].equals(key.arguments[i]))return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = name.hashCode();
			
			if(arguments == null)return result;
			
			for( String s : arguments)result = result * prime + s.hashCode();
			
			return result;
		}
		
	}

	public void addConstructor(Constructor constructor) {
		constructors.put(constructor.getArgumentTypeNames(), constructor);
	}
	
	
}
