package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;
import java.util.Arrays;

import net.pop1040.ProgrammingLanguage.FunctionStack;
import net.pop1040.ProgrammingLanguage.FunctionStack.FunctionInstance;
import net.pop1040.ProgrammingLanguage.Types.PClass;
import net.pop1040.ProgrammingLanguage.Types.PGeneric;
import net.pop1040.ProgrammingLanguage.Types.PObject;

import static net.pop1040.ProgrammingLanguage.Tokens.Function.ArgumentType.ArgumentClass.*;

public class Function implements Invokable{
	
	Token executedToken;
	PClass returnType;
	
	String name;
	
	ArrayList<String> argumentNames  = new ArrayList<String>();
	protected ArrayList<ArgumentType> argumentTypes  = new ArrayList<ArgumentType>();
	//ArrayList<Evaluatable> arguments = new ArrayList<Evaluatable>();
	
	protected static class ArgumentType{
		protected PClass clazz;
		protected int genericIndex;
		public ArgumentClass type;
		
		public ArgumentType(PClass type){
			this.clazz = type;
			this.type = CLASS;
		}
		
		public ArgumentType(int index){
			genericIndex = index;
			this.type = GENERIC;
		}
		
		
		Object getTypename(PObject object){
			return type.getTypename(this, object);
		}
		
		public static enum ArgumentClass{
			CLASS(){
				@Override
				public PClass getTypeClass(ArgumentType type, PObject object) {
					return type.clazz;
				}
			},
			GENERIC(){
				@Override
				public PClass getTypeClass(ArgumentType type, PObject object) {
					return object.generics.get(type.genericIndex);
				}
				
				@Override
				public Object getTypename(ArgumentType type, PObject object) {
					return Integer.valueOf(type.genericIndex);
				}
			};
			
			public Object getTypename(ArgumentType type, PObject object){
				//PClass clazz = this.getTypeClass(type, object);
				//return clazz == null ? type.genericIndex + "" : clazz.typeName;
				return type.clazz.typeName;
			}
			public PClass getTypeClass(ArgumentType type, PObject object){
				return null;
			}
			
		}
	}
	
	public Function(String name, Token executedToken, PClass returnType){
		this.name=name;
		this.executedToken=executedToken;
		this.returnType=returnType;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<Token> getTokens() {
		//if(executedToken instanceof Subroutine) ((Subroutine) executedToken).getTokens();
		return new ArrayList<Token>(Arrays.asList(executedToken));
	}
	
	public String[] getArgumentNames(){
		return argumentNames.toArray(new String[argumentNames.size()]);
	}
	
	public Object[] getArgumentTypeNames(){
		Object[] names = new Object[argumentTypes.size()];
		for(int i=0; i<names.length; i++){
			ArgumentType argType = argumentTypes.get(i);
			names[i]=argType.type.getTypename(argType, null);
		}
		return names;
	}

	@Override
	public PClass getEvaluatedType() {
		return returnType;
	}
	

	@Override
	public Evaluatable getNextEvaluatable(ArrayList<PClass> classes, ArrayList<PGeneric> evaluated) {
		//if(evaluated.size()>=arguments.size())return null;
		//return arguments.get(evaluated.size());
		return null;
	}

	@Override
	public PGeneric getReturnValue(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		//return evaluated.get(evaluated.size()-1);
		return stack.stack.getLast().getReturnVariable();
	}
	
	public Function addArgument(String name, PClass type){
		argumentNames.add(name);
		argumentTypes.add(new ArgumentType(type));
		return this;
	}
	

	
	@Override
	public String toString() {
		return "Function[name=" + name + ", ReturnType=" + returnType + "]";
	}

	@Override
	public boolean canReturnYet(FunctionStack stack, ArrayList<PGeneric> evaluated) {
		return stack.stack.getLast().getReturnState();
	}

	@Override
	public void setup(FunctionStack stack, FunctionInstance inst, ArrayList<PGeneric> evaluated) {
		FunctionStack.FunctionInstance functionInstance;
		stack.stack.add(functionInstance = new FunctionStack.FunctionInstance(this)); //wait why did I set this up like this?
		//System.out.println("evaluated Size = " + evaluated.size());
		for(int i=0; i<evaluated.size(); i++)functionInstance.addVariable(argumentNames.get(i), evaluated.get(i));
	}

	@Override
	public boolean isAccessibilityBarrier() {
		return true;
	}
	
	
}
