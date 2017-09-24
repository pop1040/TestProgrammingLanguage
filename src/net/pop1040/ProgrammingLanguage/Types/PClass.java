package net.pop1040.ProgrammingLanguage.Types;

import java.util.ArrayList;
import java.util.HashMap;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;
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
	public HashMap<String, Function> functionMap = new HashMap<String, Function>();

	public ArrayList<IIntrinsicFunction> intrinsicFunctions = new ArrayList<IIntrinsicFunction>();
	public ArrayList<String> intrinsicFunctionNames = new ArrayList<String>();
	public HashMap<String, IIntrinsicFunction> intrinsicFunctionMap = new HashMap<String, IIntrinsicFunction>();
	
	public ArrayList<Method> methods = new ArrayList<Method>();
	public ArrayList<String> methodNames = new ArrayList<String>();
	public HashMap<String, Method> methodMap = new HashMap<String, Method>();
	public ArrayList<String> fieldNames = new ArrayList<String>();
	
	public Method[] getMethodArray(){
		return getMethods().toArray(new Method[getMethods().size()]);
	}
	
	public ArrayList<Method> getMethods(){
		return methods;
	}
	
	public HashMap<String, Method> getMethodMap(){
		return methodMap;
	}
	
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
	
	public HashMap<String, Function> getFunctionMap(){
		return functionMap;
	}
	
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
	
	public HashMap<String, IIntrinsicFunction> getIntrinsicFunctionMap(){
		return intrinsicFunctionMap;
	}
	
	public ArrayList<String> getIntrinsicFunctionNames(){
		return intrinsicFunctionNames;
	}
	
	public String[] getIntrinsicFunctionNameArray(){
		return getIntrinsicFunctionNames().toArray(new String[getIntrinsicFunctionNames().size()]);
	}






	public void addFunction(String name, Function function) {
		functions.add(function);
		functionMap.put(name, function);
		functionNames.add(name);
	}
	
	public void addIntrinsicFunction(String name, IIntrinsicFunction function) {
		intrinsicFunctions.add(function);
		intrinsicFunctionMap.put(name, function);
		intrinsicFunctionNames.add(name);
	}
	
	
	/**
	 * Gets a function by name, will recursively query the superclass
	 * @param functionName
	 * @return the function mapped by that name in this class or if
	 * that function is not mapped and this class has a superclass,
	 * the result of calling getFunction on that superclass, otherwise
	 * null
	 */
	public Function getFunction(String functionName) {
		Function func = functionMap.get(functionName);
		return func != null ? func : (superClass == null ? null : superClass.getFunction(functionName));
	}
	/**
	 * Gets a method by name, will recursively query the superclass
	 * @param methodName
	 * @return
	 */
	public Method getMethod(String methodName) {
		Method mthd = methodMap.get(methodName);
		return mthd != null ? mthd : (superClass == null ? null : superClass.getMethod(methodName));
	}
	
	/**
	 * Gets a function by name, will recursively query the superclass
	 * @param functionName
	 * @return the function mapped by that name in this class or if
	 * that function is not mapped and this class has a superclass,
	 * the result of calling getFunction on that superclass, otherwise
	 * null
	 */
	public IIntrinsicFunction getIntrinsicFunction(String functionName) {
		IIntrinsicFunction func = intrinsicFunctionMap.get(functionName);
		return func != null ? func : (superClass == null ? null : superClass.getIntrinsicFunction(functionName));
	}
	
	
}
