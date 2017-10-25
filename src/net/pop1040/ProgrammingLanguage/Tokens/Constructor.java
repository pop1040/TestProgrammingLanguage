package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

public class Constructor extends Method {
	
	Token ret = new TokenReturn(new EvalThisReference(this));

	public Constructor(Token executedToken, PClass clazz) {
		super("constructor", executedToken, clazz, clazz);
	}
	
	public Constructor addArgument(String name, PClass type){
		super.addArgument(name, type);
		return this;
	}
	public Method addArgument(String name, int genericTypeIndex){
		super.addArgument(name, genericTypeIndex);
		return this;
	}
	
	@Override
	public ArrayList<Token> getTokens() {
		return new ArrayList<Token>(Arrays.asList(executedToken, ret));
	}
	
	
	@Override
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated) {
		
		PObject object = new PObject(hostClass);
		for(String s : hostClass.fieldNames){
			object.fieldMap.put(s, null);
			object.fields.add(null);
		}
		
		FunctionStack.FunctionInstance functionInstance = new FunctionStack.FunctionInstance(this, object);
		stack.stack.add(functionInstance); //much better
		
		for(int i=0; i<evaluated.size(); i++)functionInstance.addVariable(argumentNames.get(i), evaluated.get(i));
	}
	
	@Override
	public String toString() {
		return "Constructor[class=" + hostClass + ", ReturnType=" + returnType + "]";
	}

}
