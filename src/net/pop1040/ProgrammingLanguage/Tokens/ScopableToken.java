package net.pop1040.ProgrammingLanguage.Tokens;

import java.util.ArrayList;


/**
 * Bad name I know.<br>
 * <br>
 * This interface should be implemented by any token that has multiple
 * internal tokens that should be stepped through individually. It's
 * called ScopableToken because it creates a new "subscope" on the stack
 * (that is any new local variables will go out of scope when this
 * subroutine exits)
 * 
 * @author pop1040
 *
 */
public interface ScopableToken {
	
	public ArrayList<Token> getTokens();
	
	public boolean isAccessibilityBarrier();
	
}
