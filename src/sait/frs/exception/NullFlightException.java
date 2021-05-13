package sait.frs.exception;

/**
 * This exception is thrown when a flight object is null.
 */
public class NullFlightException extends Exception
{
	/**
	 * Default constructor.
	 */
	public NullFlightException()
	{
		super("Flight is missing.");
	}
}
