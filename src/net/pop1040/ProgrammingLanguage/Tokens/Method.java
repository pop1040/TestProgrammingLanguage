package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Iterator;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

public class Method extends Function {
	
	protected PClass hostClass;
	
	public Method(String name, Token executedToken, PClass hostClass, PClass returnType){
		super(name, executedToken, returnType);
		this.hostClass = hostClass;
	}

	public PClass getPClass() {
		return hostClass;
	}

	public PObject getObject(FunctionStack stack) {
		Iterator<FunctionInstance> iter = stack.stack.descendingIterator();
		while(iter.hasNext()){
			FunctionInstance inst = iter.next();
			if(inst.getFunction() instanceof Method)return inst.getMethodObject();
		}
		throw new NullPointerException("reached end of stack, no function found cannot return");
	}
	
	public Method addArgument(String name, PClass type){
		argumentNames.add(name);
		argumentTypes.add(type);
		return this;
	}
	
	@Override
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated) {
		FunctionStack.FunctionInstance functionInstance = new FunctionStack.FunctionInstance(this, (PObject) evaluated.get(0));
		stack.stack.add(functionInstance); //much better
		
		for(int i=1; i<evaluated.size(); i++)functionInstance.addVariable(argumentNames.get(i), evaluated.get(i));
	}
	
	@Override
	public String toString() {
		return "Method[name=" + name + ", class=" + hostClass + ", ReturnType=" + returnType + "]";
	}

}
