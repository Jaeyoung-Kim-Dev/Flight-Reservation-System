package sait.frs.exception;

/** 
 * This exception is thrown when the travelers name is missing.
 */
public class InvalidNameException extends Exception
{
	/**
	 * Default constructor.
	 */
	public InvalidNameException()
	{
		super("Name is missing.");
	}
}
