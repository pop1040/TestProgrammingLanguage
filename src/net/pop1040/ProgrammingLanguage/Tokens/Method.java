package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PObject;

public class Method extends Function {
	
	
	public Method(String name, Token executedToken, PClass returnType){
		super(name, executedToken, returnType);
	}

	public PObject getObject() {
		//TODO implement this reference backend
		return null;
	}

}
