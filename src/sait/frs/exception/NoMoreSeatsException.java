package sait.frs.exception;

/**
 * This exception is thrown when a flight has no more seats.
 */
public class NoMoreSeatsException extends Exception
{
	/**
	 * Default constructor.
	 */
	public NoMoreSeatsException()
	{
		super("No more seats available.");
	}
}
