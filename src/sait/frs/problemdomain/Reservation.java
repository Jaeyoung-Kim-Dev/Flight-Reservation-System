package sait.frs.problemdomain;

import sait.frs.exception.*;

/**
 * Represents a reservation.
 */
public class Reservation
{
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;

	/**
	 * User-defined constructor for Reservation.
	 * 
	 * @param code        - Existing reservation code.
	 * @param flightCode  - Flight reservation is for.
	 * @param airline     - Airline reservation is for.z
	 * @param name        - Name reservation is for.
	 * @param citizenship - Travelers citizenship.
	 * @param cost        - Cost per seat on the flight
	 * @param active      - Is the reservation active?
	 * @throws NullFlightException         - Thrown if flight is null.
	 * @throws InvalidNameException        - Thrown if name is null or empty.
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty.
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) throws NullFlightException, InvalidNameException, InvalidCitizenshipException
	{
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * Gets the reservation code.
	 * 
	 * @return Reservation code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Gets the Flight code.
	 * 
	 * @return the flightCode
	 */
	public String getFlightCode()
	{
		return flightCode;
	}

	/**
	 * Gets the airline of the flight.
	 * 
	 * @return the airline
	 */
	public String getAirline()
	{
		return airline;
	}

	/**
	 * Gets the name of the traveler.
	 * 
	 * @return Traveler's name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the travelers citizenship.
	 * 
	 * @return Traveler's citizenship.
	 */
	public String getCitizenship()
	{
		return citizenship;
	}

	/**
	 * Gets the cost per seat on the flight.
	 * 
	 * @return the cost
	 */
	public double getCost()
	{
		return cost;
	}

	/**
	 * Gets if the reservation is active.
	 * 
	 * @return True if reservation is active.
	 */
	public boolean isActive()
	{
		return active;

	}

	/**
	 * Sets the name of the traveler.
	 * 
	 * @param name - New name of traveler.
	 * @throws InvalidNameException - Thrown if name is null or empty.
	 */
	public void setName(String name) throws InvalidNameException
	{
		if (name != null)
			this.name = name;
		else
			throw new InvalidNameException();
	}

	/**
	 * Sets the citizenship of the traveler.
	 * 
	 * @param citizenship - Travelers citizenship.
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty.
	 */
	public void setCitizenship(String citizenship) throws InvalidCitizenshipException
	{
		if (citizenship != null)
			this.citizenship = citizenship;
		else
			throw new InvalidCitizenshipException();
	}

	/**
	 * Sets if the reservation is active or inactive.
	 * 
	 * @param active - True meaning reservation is active.
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * Gets a human readable representation of the Reservation.
	 * 
	 * @return The reservation code.
	 */
	@Override
	public String toString()
	{
		return code;
	}

}
