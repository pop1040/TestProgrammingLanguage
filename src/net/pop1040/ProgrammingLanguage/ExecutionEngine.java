package net.pop1040.ProgrammingLanguage;

import java.io.PrintStream;
import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PDouble;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PInteger;
import net.pop1040.ProgrammingLanguage.Types.PLong;
import net.pop1040.ProgrammingLanguage.Types.PObject;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

public class ExecutionEngine {
	
	//ArrayList<PObject> objects = new ArrayList<PObject>();
	
	public static ArrayList<PClass> baseClasses = new ArrayList<PClass>();
	static{
		PClass math = new PClass("Math");
		baseClasses.add(math);
		math.addIntrinsicFunction("pow", new String[]{PDouble.pClass.typeName, PDouble.pClass.typeName}, (FunctionStack stack, ArrayList<PGeneric> evaluated)->{
			return new PDouble(Math.pow(((PPrimitive)evaluated.get(0)).getDoubleValue(), ((PPrimitive)evaluated.get(1)).getDoubleValue()));
		});
		math.addIntrinsicFunction("sqrt", new String[]{PDouble.pClass.typeName}, (FunctionStack stack, ArrayList<PGeneric> evaluated)->{
			return new PDouble(Math.sqrt(((PPrimitive)evaluated.get(0)).getDoubleValue()));
		});
		
	}


	public int exitCode = 0;
	
	public ArrayList<PClass> init(ExecutionEngine engine){
		ArrayList<PClass> baseClasses = new ArrayList<PClass>();
		PClass system = new PClass("System");
		baseClasses.add(system);
		system.addIntrinsicFunction("currentTimeMillis", new String[]{}, (FunctionStack stack, ArrayList<PGeneric> evaluated) -> {
			return new PLong(System.currentTimeMillis());
		});
		system.addIntrinsicFunction("nanoTime", new String[]{}, (FunctionStack stack, ArrayList<PGeneric> evaluated) -> {
			return new PLong(System.nanoTime());
		});
		system.addIntrinsicFunction("exit", new String[]{PInteger.pClass.typeName}, (FunctionStack stack, ArrayList<PGeneric> evaluated) -> {
			engine.exit(((PPrimitive)evaluated.get(0)).getIntValue());
			return null;
		});
		return baseClasses;
	}
	private void exit(int code) {
		instructionPointer.haulted=true;
		exitCode = code;
	}
	ArrayList<PClass> classes = new ArrayList<PClass>();
	PClass startingClass;
	FunctionStack stack;
	InstructionExecuter instructionPointer;
	
	
	
	/**
	 * Creates the execution engine with the following token list. Make
	 * sure to call initProgramData before stepping.
	 * @param tokens
	 */
	public ExecutionEngine(PObject startingObject){
		classes.addAll(baseClasses);
		classes.addAll(init(this));
		if(startingObject instanceof PClass){
			startingClass = (PClass) startingObject;
			classes.add((PClass) startingObject);
		}else{
			//objects.add(startingObject);
			classes.add(startingObject.getPClass());
			startingClass = startingObject.getPClass();
		}
		
	}
	
	public void addClass(PClass clazz){
		classes.add(clazz);
	}
	
	public void start(String startingFunction){
		//stack = new FunctionStack(startingClass.getFunctionMap().get(startingFunction));
		stack = new FunctionStack(startingClass.getFunction(startingFunction, null));
		instructionPointer = new InstructionExecuter(stack, this);
	}
	
	public void step(){
		instructionPointer.step();
	}


	public void dumpState(PrintStream out) {
		
		out.println("Current State:");
		stack.dumpState(out);
		instructionPointer.dumpState(out);
		
		
	}
	

}
