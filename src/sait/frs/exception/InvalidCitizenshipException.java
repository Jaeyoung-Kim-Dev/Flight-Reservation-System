package sait.frs.exception;

/** 
 * This exception is thrown when citizenship is missing.
 */
public class InvalidCitizenshipException extends Exception
{
	/**
	 * Default constructor.
	 */
	public InvalidCitizenshipException()
	{
		super("Citizenship is missing.");
	}
}
