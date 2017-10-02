package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.List;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Tokens.FunctionReference.Type;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

public class EvalInvokeFunction implements Invokable{
	
	public FunctionReference function;
	public Function funcInstance;
	//public FunctionInstance instFrame;
	public IIntrinsicFunction iFuncInstance;
	
	ArrayList<Evaluatable> arguments = new ArrayList<Evaluatable>();
	
	public EvalInvokeFunction(FunctionReference functionRef, List<PClass> classes, Evaluatable ... arguments) {
		this.function = functionRef;
		//if(functionRef.mode == Type.INTRINSIC){
			
		//}else{
			//System.out.println("Function Ref (mode != INTRINSIC), arguments (len = " + arguments.length + "):");
			//for(int i=0; i<arguments.length; i++)System.out.println(" -" + arguments[i]);
		for(Evaluatable iter : arguments)this.arguments.add(iter);
		PClass clazz = null;
		for(PClass iter : classes)if(iter.typeName.equals(functionRef.className)){ //TODO yay for linear search
			clazz = iter;
			break;
		}
		if(functionRef.mode == Type.FUNCTION){
			//funcInstance = clazz.functionMap.get(functionRef.functionName);
			funcInstance = clazz.getFunction(functionRef.functionName, functionRef.arguments);
		}else if(functionRef.mode == Type.METHOD){ //I was going to just use else but this is better for the future
			funcInstance = clazz.getMethod(functionRef.functionName, functionRef.arguments);
			//funcInstance = clazz.methodMap.get(functionRef.functionName);
		}else if(functionRef.mode == Type.INTRINSIC){
			iFuncInstance = clazz.getIntrinsicFunction(functionRef.functionName, functionRef.arguments);
		}
		//}
	}

	@Override
	public PClass getEvaluatedType() {
		return function.returnType;
	}

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//if(function.mode != Type.INTRINSIC){
		if(function.mode == Type.METHOD){
			if(evaluated.size() == 0)return function.object;
			if(evaluated.size()>arguments.size())return null;
			return arguments.get(evaluated.size()-1);
		}
		if(evaluated.size()>=arguments.size())return null;
		return arguments.get(evaluated.size());
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//System.out.println("returning " + stack.stack.getLast().getReturnVariable());
		if(function.mode == Type.INTRINSIC)return iFuncInstance.getReturnValue(stack, evaluated);
		PGeneric retVal = stack.stack.getLast().getReturnVariable();
		stack.stack.getLast().clearReturnValue();
		return retVal;
		
	}

	@Override
	public ArrayList<Token> getTokens() {
		if(function.mode != Type.INTRINSIC){
			return funcInstance.getTokens();
		}
		return new ArrayList<Token>();
	}

	@Override
	public boolean isAccessibilityBarrier() {
		return true;
	}

	@Override
	public boolean canReturnYet(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		if(function.mode != Type.INTRINSIC){
			return funcInstance.canReturnYet(stack, evaluated);
		}
		return true;
	}

	@Override
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated) {
		//instFrame = inst;
		if(function.mode != Type.INTRINSIC){  //methods receive the this reference as the first element in evaluated
			funcInstance.setup(stack, inst, evaluated);
		}
	}
	
	@Override
	public String toString() {
		String ret = "EvalInvokeFunction[" + (function.mode == Type.INTRINSIC?"IntrinsicFunction = " + function.functionName + ", class=" + function.className:"Function = " + funcInstance.toString()) + ", Arguments={";
		for(int i=0; i<arguments.size(); i++)ret = ret + "Arg" + i + "=" + arguments.get(i) + (i==arguments.size()-1?"":", ");
		return ret + "}]";
	}

}
