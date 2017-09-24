package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;

/**
 * Implemented by anything that returns or has a value. Also for specifying
 * iff there is anything that needs to be stepped through to return that value.
 * @author pop1040
 *
 */
public interface Evaluatable {
	
	public PClass getEvaluatedType();
	
	/**
	 * 
	 * @return next subEvaluation or null if all needed evaluations are completed
	 */
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated);
	
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated);
	
	//public void evaluateNext(ArrayList<PGeneric> evaluated);
	
}
