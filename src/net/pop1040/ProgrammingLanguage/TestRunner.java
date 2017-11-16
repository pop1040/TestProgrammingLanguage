package net.pop1040.ProgrammingLanguage;

//import java.io.BufferedOutputStream;
//import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.io.PrintWriter;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.Tokens.*;
import net.pop1040.ProgrammingLanguage.Tokens.Operations.compare.*;
import net.pop1040.ProgrammingLanguage.Tokens.Operations.math.*;
import net.pop1040.ProgrammingLanguage.Tokens.Operations.Boolean.*;
import net.pop1040.ProgrammingLanguage.Types.*;

public class TestRunner {

	public static void main(String[] args) throws FileNotFoundException {
		/*
		PrintStream dummyStream = new PrintStream(new OutputStream(){
		    public void write(int b) {
		        // NO-OP
		    }
		});

		System.setOut(dummyStream);*/
		System.setErr(System.out); //because errors in the middle of the log are annoying
		
		PClass simpleClass = new PClass("TestClass");
		PClass car = new PClass("Car");
		PClass linkedList = new PClass("LinkedList");
		PClass linkedListElement = new PClass("LinkedList.Element");
		
		car.fieldNames.add("wheelSize");
		
		linkedList.fieldNames.add("headNode");

		linkedListElement.fieldNames.add("element");
		linkedListElement.fieldNames.add("next");
		
		ExecutionEngine engine = new ExecutionEngine(simpleClass);
		engine.addClass(car);
		engine.addClass(linkedList);
		engine.addClass(linkedListElement);
		
		Subroutine main = new Subroutine();
		Subroutine square = new Subroutine();
		Subroutine sub = new Subroutine();
		
		Subroutine evalYes = new Subroutine();
		Subroutine evalNo  = new Subroutine();
		
		Subroutine fibonacci = new Subroutine();
		
		Subroutine loop = new Subroutine();
		
		Subroutine testFunc1 = new Subroutine();
		Subroutine testFunc2 = new Subroutine();
		
		Subroutine carConstructor = new Subroutine();
		
		Constructor constructor;
		car.addConstructor(constructor = new Constructor(carConstructor, car).addArgument("wheelSize", PInteger.pClass));
		carConstructor.tokens.add(new TokenSetVarValue(new VariableReference("wheelSize", PInteger.pClass, new EvalThisReference(constructor)), new EvalGetVariable(new VariableReference("wheelSize", PInteger.pClass))));
		
		
		simpleClass.addFunction(new Function("main", main, PClass.pVoid));
		
		simpleClass.addFunction(new Function("square", square, PInteger.pClass).addArgument("x", PInteger.pClass));
		square.tokens.add(new TokenReturn(new EvalMultiplyInteger(new EvalGetVariable(new VariableReference("x", PInteger.pClass)), new EvalGetVariable(new VariableReference("x", PInteger.pClass)))));
		
		simpleClass.addFunction(new Function("fibonacci", fibonacci, PInteger.pClass).addArgument("n", PInteger.pClass));
		fibonacci.tokens.add(new TokenEvalIf(new EvalLessThanOrEqual(new EvalGetVariable(new VariableReference("n", PInteger.pClass)), new EvalConstant(new PInteger(0))), new TokenReturn(new EvalConstant(new PInteger(0)))));
		fibonacci.tokens.add(new TokenEvalIf(new EvalEqual(new EvalGetVariable(new VariableReference("n", PInteger.pClass)), new EvalConstant(new PInteger(1))), new TokenReturn(new EvalConstant(new PInteger(1)))));
		fibonacci.tokens.add(new TokenReturn(new EvalAddInteger(new EvalInvokeFunction(new FunctionReference("fibonacci", "TestClass", PInteger.pClass, PInteger.pClass.typeName), engine.classes, new EvalSubtractInteger(new EvalGetVariable(new VariableReference("n", PInteger.pClass)), new EvalConstant(new PInteger(1)))), new EvalInvokeFunction(new FunctionReference("fibonacci", "TestClass", PInteger.pClass, PInteger.pClass.typeName), Arrays.asList(simpleClass), new EvalSubtractInteger(new EvalGetVariable(new VariableReference("n", PInteger.pClass)), new EvalConstant(new PInteger(2)))))));
		
		simpleClass.addFunction(new Function("test", testFunc1, PInteger.pClass));
		testFunc1.tokens.add(new TokenReturn(new EvalConstant(new PInteger(7))));
		simpleClass.addFunction(new Function("test", testFunc2, PInteger.pClass).addArgument("x", PInteger.pClass));
		testFunc2.tokens.add(new TokenReturn(new EvalGetVariable(new VariableReference("x", PInteger.pClass))));
		
		{
			linkedList.addConstructor(new Constructor(new Subroutine(), linkedList));
			
			Subroutine size = new Subroutine();
			Method sizeMethod = new Method("size", size, linkedList, PInteger.pClass);
			linkedList.addMethod(sizeMethod);
			size.tokens.add(new TokenDeclareLocalVariable("currentHead", linkedListElement, new EvalGetVariable(new VariableReference("headNode", linkedListElement, new EvalThisReference(sizeMethod)))));
			//size.tokens.add(new TokenEvalIf(new EvalEqual(new EvalGetVariable(new VariableReference("currentHead", linkedListElement)), new EvalConstant(null)), new TokenReturn(new EvalConstant(new PInteger(0)))));
			size.tokens.add(new TokenDeclareLocalVariable("counter", PInteger.pClass, new EvalConstant(new PInteger(0))));
			{
				Subroutine whileLoop = new Subroutine();
				//size.tokens.add(new TokenEvalWhile(new EvalNotEqual(new EvalGetVariable(new VariableReference("next", linkedListElement, new EvalGetVariable(new VariableReference("currentHead", linkedListElement)))), new EvalConstant(null)), whileLoop));
				//whileLoop.tokens.add(new TokenSetVarValue(new VariableReference("currentHead", linkedListElement), new EvalGetVariable(new VariableReference("next", linkedListElement, new EvalGetVariable(new VariableReference("currentHead", linkedListElement))))));
				//whileLoop.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalAddInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(1)))));
				size.tokens.add(new TokenEvalWhile(new EvalNotEqual(new EvalGetVariable(new VariableReference("currentHead", linkedListElement)), new EvalConstant(null)), whileLoop));
				whileLoop.tokens.add(new TokenSetVarValue(new VariableReference("currentHead", linkedListElement), new EvalGetVariable(new VariableReference("next", linkedListElement, new EvalGetVariable(new VariableReference("currentHead", linkedListElement))))));
				whileLoop.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalAddInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(1)))));
			}
			size.tokens.add(new TokenReturn(new EvalGetVariable(new VariableReference("counter", PInteger.pClass))));
			
			Subroutine getLast = new Subroutine();
			Method getLastMethod = new Method("getLast", getLast, linkedList, PInteger.pClass);
			linkedList.addMethod(getLastMethod);
			getLast.tokens.add(new TokenDeclareLocalVariable("currentHead", linkedListElement, new EvalGetVariable(new VariableReference("headNode", linkedListElement, new EvalThisReference(sizeMethod)))));
			getLast.tokens.add(new TokenEvalIf(new EvalEqual(new EvalGetVariable(new VariableReference("currentHead", linkedListElement)), new EvalConstant(null)), new TokenReturn(new EvalConstant(null))));
			getLast.tokens.add(new TokenEvalWhile(new EvalNotEqual(new EvalGetVariable(new VariableReference("next", linkedListElement, new EvalGetVariable(new VariableReference("currentHead", linkedListElement)))), new EvalConstant(null)), new TokenSetVarValue(new VariableReference("currentHead", linkedListElement), new EvalGetVariable(new VariableReference("next", linkedListElement, new EvalGetVariable(new VariableReference("currentHead", linkedListElement)))))));
			getLast.tokens.add(new TokenReturn(new EvalGetVariable(new VariableReference("currentHead", linkedListElement))));
			
		}
		
		
		evalYes.tokens.add(new TokenSetVarValue(new VariableReference("result", PChar.pClass), new EvalConstant(new PChar('T'))));
		evalNo.tokens.add(new TokenSetVarValue(new VariableReference("result", PChar.pClass), new EvalConstant(new PChar('F'))));
		
		main.tokens.add(new TokenDeclareLocalVariable("x", PInteger.pClass, new EvalConstant(new PInteger(7))));
		main.tokens.add(new TokenDeclareLocalVariable("global t", PInteger.pClass, new EvalConstant(new PInteger(-1))));
		main.tokens.add(new TokenDeclareLocalVariable("result", PChar.pClass, new EvalConstant(new PChar('U'))));
		main.tokens.add(sub);
		sub.tokens.add(new TokenDeclareLocalVariable("y", PInteger.pClass, new EvalConstant(new PInteger(9))));
		sub.tokens.add(new TokenDeclareLocalVariable("t", PInteger.pClass, new EvalConstant(new PInteger(44))));
		sub.tokens.add(new TokenSetVarValue(new VariableReference("global t", PInteger.pClass), new EvalGetVariable(new VariableReference("t", PInteger.pClass))));
		sub.tokens.add(new TokenSetVarValue(new VariableReference("x", PInteger.pClass), new EvalConstant(new PInteger(27))));
		main.tokens.add(new TokenDeclareLocalVariable("z", PInteger.pClass, new EvalConstant(new PInteger(11))));
		main.tokens.add(new TokenSetVarValue(new VariableReference("x", PInteger.pClass), new EvalConstant(new PInteger(25))));
		main.tokens.add(new TokenDeclareLocalVariable("t", PInteger.pClass, new EvalAddInteger(new EvalConstant(new PInteger(2)), new EvalGetVariable(new VariableReference("global t", PInteger.pClass)))));
		main.tokens.add(new TokenDeclareLocalVariable("t squared", PInteger.pClass, new EvalInvokeFunction(new FunctionReference("square", "TestClass", PInteger.pClass, PInteger.pClass.typeName), engine.classes, new EvalGetVariable(new VariableReference("t", PInteger.pClass)))));
		main.tokens.add(new TokenDeclareLocalVariable("conditional", PBoolean.pClass, new EvalNot(new EvalGreaterThan(new EvalGetVariable(new VariableReference("t squared", PInteger.pClass)), new EvalGetVariable(new VariableReference("t", PInteger.pClass))))));
		main.tokens.add(new TokenEvalIf(new EvalGetVariable(new VariableReference("conditional", PBoolean.pClass)), evalYes, evalNo));
		//main.tokens.add(new TokenDeclareLocalVariable("fib12", PInteger.pClass, new EvalInvokeFunction(new FunctionReference("fibonacci", "TestClass", PInteger.pClass, PInteger.pClass.typeName), engine.classes, new EvalConstant(new PInteger(25)))));
		main.tokens.add(new TokenDeclareLocalVariable("test1", PInteger.pClass, new EvalInvokeFunction(new FunctionReference("test", "TestClass", PInteger.pClass), engine.classes)));
		main.tokens.add(new TokenDeclareLocalVariable("test2", PInteger.pClass, new EvalInvokeFunction(new FunctionReference("test", "TestClass", PInteger.pClass, PInteger.pClass.typeName), engine.classes, new EvalConstant(new PInteger(3)))));
		main.tokens.add(new TokenDeclareLocalVariable("Power", PDouble.pClass, new EvalInvokeFunction(new FunctionReference("pow", PDouble.pClass, "Math", PDouble.pClass.typeName, PDouble.pClass.typeName), engine.classes, new EvalConstant(new PDouble(5)), new EvalConstant(new PDouble(5)))));
		
		main.tokens.add(new TokenDeclareLocalVariable("counter", PInteger.pClass, new EvalConstant(new PInteger(1))));
		main.tokens.add(new TokenEvalWhile(new EvalNotEqual(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(10))), loop));
		loop.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalAddInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(1)))));
		
		main.tokens.add(new TokenDeclareLocalVariable("carInstance", car, new EvalInvokeFunction(new FunctionReference(car, PInteger.pClass.typeName), engine.classes, new EvalConstant(new PInteger(113)))));
		
		//main.tokens.add(new Subroutine());
		main.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalConstant(new PInteger(0))));
		main.tokens.add(new TokenDeclareLocalVariable("test array", PArray.pClass, new EvalNewArray(new EvalConstant(new PInteger(16)), PInteger.pClass)));
		{
			Subroutine whileLoop = new Subroutine();
			main.tokens.add(new TokenEvalWhile(new EvalLessThan(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalGetVariable(new VariableReference("length", PInteger.pClass, new EvalGetVariable(new VariableReference("test array", PArray.pClass))))), whileLoop));
			whileLoop.tokens.add(new TokenSetVarValue(new VariableReference(PInteger.pClass, new EvalGetVariable(new VariableReference("test array", PArray.pClass)), new EvalGetVariable(new VariableReference("counter", PInteger.pClass))), new EvalMultiplyInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalGetVariable(new VariableReference("counter", PInteger.pClass)))));
			whileLoop.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalAddInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(1)))));
		}
		

		main.tokens.add(new EvalInvokeFunction(new FunctionReference("exit", null, "System", PInteger.pClass.typeName), engine.classes, new EvalConstant(new PInteger(1))));
		main.tokens.add(new TokenSetVarValue(new VariableReference("x", PInteger.pClass), new EvalConstant(new PInteger(99999))));
		//File logFile = new File("log.txt");
		//System.out.println(logFile.getAbsolutePath());
		
		//main.tokens.add(new TokenDeclareLocalVariable("a", PInteger.pClass, new EvalConstant(new PInteger(0))));
		//main.tokens.add(new TokenEvalWhile(new EvalNotEqual(new EvalGetVariable(new VariableReference("a", PInteger.pClass)), new EvalConstant(new PInteger(10))), new TokenSetVarValue(new VariableReference("a", PInteger.pClass),  new EvalAddInteger(new EvalGetVariable(new VariableReference("a", PInteger.pClass)), new EvalConstant(new PInteger(1))))));
		//main.tokens.add(new EvalInvokeFunction(new FunctionReference("println", "System", PClass.pVoid, PInteger.pClass.typeName), engine.classes, new EvalGetVariable(new VariableReference("a", PInteger.pClass))));
		//main.tokens.add(new TokenEvalWhile(new EvalConstant(new PBoolean(true)), new Subroutine()));
		
		
		//PrintStream fileOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFile), 67108864));
		
		//engine.initProgramData();
		engine.start("main");
		long counter = 1;
		
		//engine.dumpState(fileOut);
		
		long time = System.currentTimeMillis();
		while(!engine.instructionPointer.halted){
			//System.out.println("<----------------- Step " + counter + " ----------------> (Took " + (System.currentTimeMillis()-time) + " milliseconds)");
			engine.step();
			//if(counter%100==0){
			//	System.out.println("Step " + counter);
				//fileOut.flush();
			//}
			//if(counter==7127){
			//if(counter%10000==0){
			//if(counter > 3699200){
			//fileOut.println("<----------------- Step " + counter + " ---------------->");
			//engine.dumpState(fileOut);
			//fileOut.flush();
			//if(counter == 3699222){
			//if(counter == 135){
			//	System.out.println("<--------------Done! Took " + (System.currentTimeMillis()-time) + " milliseconds-------------->");
				System.out.println("<----------------- Step " + counter + " ---------------->");
				if(engine.instructionPointer.halted)System.out.println("<--------------Done! Took " + (System.currentTimeMillis()-time) + " milliseconds (exit code = " + engine.exitCode + ")-------------->");
				engine.dumpState(System.out);
			//}
			counter++;
		}
		
		//fileOut.flush();
		//fileOut.close();
	}

}
