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
		
		PClass simpleClass = new PClass("TestClass");
		PClass car = new PClass("Car");
		
		ExecutionEngine engine = new ExecutionEngine(simpleClass);
		
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
		
		evalYes.tokens.add(new TokenSetVarValue(new VariableReference("result", PChar.pClass), new EvalConstant(new PChar('T'))));
		evalNo.tokens.add(new TokenSetVarValue(new VariableReference("result", PChar.pClass), new EvalConstant(new PChar('F'))));
		
		loop.tokens.add(new TokenSetVarValue(new VariableReference("counter", PInteger.pClass), new EvalAddInteger(new EvalGetVariable(new VariableReference("counter", PInteger.pClass)), new EvalConstant(new PInteger(1)))));
		
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
		main.tokens.add(new EvalInvokeFunction(new FunctionReference("exit", null, "System", PInteger.pClass.typeName), engine.classes, new EvalConstant(new PInteger(1))));
		main.tokens.add(new TokenSetVarValue(new VariableReference("x", PInteger.pClass), new EvalConstant(new PInteger(99999))));
		//main.tokens.add(new Subroutine());
		
		//File logFile = new File("log.txt");
		//System.out.println(logFile.getAbsolutePath());
		
		//PrintStream fileOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFile), 67108864));
		
		//engine.initProgramData();
		engine.start("main");
		long counter = 1;
		
		//engine.dumpState(fileOut);
		
		long time = System.currentTimeMillis();
		while(!engine.instructionPointer.haulted){
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
				if(engine.instructionPointer.haulted)System.out.println("<--------------Done! Took " + (System.currentTimeMillis()-time) + " milliseconds (exit code = " + engine.exitCode + ")-------------->");
				engine.dumpState(System.out);
			//}
			counter++;
		}
		
		//fileOut.flush();
		//fileOut.close();
	}

}
