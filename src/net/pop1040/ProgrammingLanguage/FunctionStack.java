package net.pop1040.ProgrammingLanguage;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import net.pop1040.ProgrammingLanguage.Tokens.Evaluatable;
import net.pop1040.ProgrammingLanguage.Tokens.Function;
import net.pop1040.ProgrammingLanguage.Tokens.ScopableToken;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

/**
 * Function stack manager class. Contains a linked list of FunctionInstances
 * in order of depth, the last being the 
 * @author thomas
 *
 */
public class FunctionStack {
	/**
	 * Contains local data to a scope level
	 * @author thomas
	 *
	 */
	public static class FunctionInstance{
		public FunctionInstance(ScopableToken function){
			this.function=function;
		}
		public FunctionInstance(ScopableToken function, PObject methodObject){
			this.function=function;
			this.methodObject=methodObject;
		}
		//local state stuff
		ScopableToken function;
		PObject methodObject;
		PGeneric returnValue;
		boolean returnState=false;
		public boolean firstLoop=false;
		public boolean toLoop=false;
		ArrayList<PGeneric> localVariables = new ArrayList<PGeneric>();
		HashMap<String, PGeneric> localVariableMap = new HashMap<String, PGeneric>();
		ArrayList<String> localVariableNames = new ArrayList<String>();
		
		//instruction pointer stuff
		public LinkedList<EvalTreeLayer> evaluationTree = new LinkedList<EvalTreeLayer>();
		int execIndex = 0;
		
		public static class EvalTreeLayer{
			public Evaluatable obj;
			public ArrayList<PGeneric> paramaters;
			EvalTreeLayer(Evaluatable par){
				this.obj = par;
				paramaters = new ArrayList<PGeneric>();
			}
		}
		
		
		public FunctionInstance addVariable(String name, PGeneric instance){
			localVariableMap.put(name, instance);
			localVariables.add(instance);
			localVariableNames.add(name);
			return this;
		}
		public void replaceVariable(String name, PGeneric instance){
			
		}
		public PGeneric getVariable(String name){
			return localVariableMap.get(name);
		}
		public PObject getMethodObject(){
			return methodObject;
		}
		public ScopableToken getFunction() {
			return function;
		}
		public void setReturnValue(PGeneric returnValue) {
			this.returnValue=returnValue;
			this.returnState=true;
		}
		public PGeneric getReturnVariable() {
			return returnValue;
		}
		public void clearReturnValue() {
			returnState=false;
			returnValue=null;
		}
		public boolean getReturnState() {
			return returnState;
		}
	}

	public PGeneric getVariable(String variableName, boolean crossAccessiblityBoundery) {
		Iterator<FunctionInstance> iter =  stack.descendingIterator();
		while(iter.hasNext()){
			FunctionInstance inst = iter.next();
			if(inst.localVariableMap.containsKey(variableName))return inst.localVariableMap.get(variableName);
			if(inst.function.isAccessibilityBarrier() && !crossAccessiblityBoundery)break;
		}
		return null;
	}

	public boolean replaceVariable(String variableName, PGeneric newVal, boolean crossAccessiblityBoundery) {
		Iterator<FunctionInstance> iter =  stack.descendingIterator();
		while(iter.hasNext()){
			FunctionInstance inst = iter.next();
			if(inst.localVariableMap.containsKey(variableName)){
				try{
					inst.localVariables.set(inst.localVariables.indexOf(inst.localVariableMap.get(variableName)), newVal);
				}catch(IndexOutOfBoundsException e){inst.localVariables.add(newVal);}
				inst.localVariableMap.put(variableName, newVal);
				return true;
			}
			if(inst.function.isAccessibilityBarrier() && !crossAccessiblityBoundery)break;
		}
		return false;
	}
	
	//public HashMap<ScopableToken,ArrayList<PGeneric>> functionParamaterLists = new HashMap<ScopableToken,ArrayList<PGeneric>>();
	//public HashMap<FunctionInstance,ArrayList<PGeneric>> functionParamaterLists = new HashMap<FunctionInstance,ArrayList<PGeneric>>();
	
	public LinkedList<FunctionInstance> stack = new LinkedList<FunctionInstance>();

	public FunctionStack(Function start) {
		stack.add(new FunctionInstance(start));
	}

	public void dumpState(PrintStream out) {
		out.println("-Stack state:");
		for(int i=0; i<stack.size(); i++){
			FunctionInstance inst = stack.get(i);
			out.println(" -Layer " + i + ":");
			out.println("  -subroutine: " + inst.function);
			out.println("  -return state: " + inst.returnState);
			out.println("  -return variable: " + inst.returnValue);
			out.println("  -values:");
			for(int j=0; j<inst.localVariableNames.size(); j++)out.println("   -name:" + inst.localVariableNames.get(j) + ", value:" + inst.localVariableMap.get(inst.localVariableNames.get(j)));
		}
	}


}
