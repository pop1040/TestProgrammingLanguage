package net.pop1040.ProgrammingLanguage;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PDouble;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;
import net.pop1040.ProgrammingLanguage.Types.PPrimitive;

public class ExecutionEngine {
	
	//ArrayList<PObject> objects = new ArrayList<PObject>();

	public static ArrayList<PClass> baseClasses = new ArrayList<PClass>();
	static{
		PClass math = new PClass("Math");
		baseClasses.add(math);
		math.addIntrinsicFunction("pow", (FunctionStack stack, ArrayList<PGeneric> evaluated)->{
			return new PDouble(Math.pow(((PPrimitive)evaluated.get(0)).getDoubleValue(), ((PPrimitive)evaluated.get(1)).getDoubleValue()));
		});
		math.addIntrinsicFunction("sqrt", (FunctionStack stack, ArrayList<PGeneric> evaluated)->{
			return new PDouble(Math.sqrt(((PPrimitive)evaluated.get(0)).getDoubleValue()));
		});
		
		
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
		if(startingObject instanceof PClass){
			startingClass = (PClass) startingObject;
			classes.add((PClass) startingObject);
		}else{
			//objects.add(startingObject);
			classes.add(startingObject.getPClass());
			startingClass = startingObject.getPClass();
		}
		
	}
	
	
	public void initProgramData(){
		
	}
	
	public void start(String startingFunction){
		stack = new FunctionStack(startingClass.getFunctionMap().get(startingFunction));
		instructionPointer = new InstructionExecuter(stack, this);
	}
	
	public void step(){
		instructionPointer.step();
	}


	public void dumpState() {
		
		System.out.println("Current State:");
		stack.dumpState();
		instructionPointer.dumpState();
		
		
	}
	

}
