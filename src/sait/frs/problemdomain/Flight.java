package sait.frs.problemdomain;

/**
 * Represents a Flight.
 */
public class Flight
{
	private String code;
	private String airline;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	/**
	 * User-defined constructor for Flight.
	 * 
	 * @param code        - Flight code
	 * @param from        - From airport
	 * @param to          - To airport
	 * @param weekday     - Weekday
	 * @param time        - Time of departure
	 * @param seats       - Number of seats available
	 * @param costPerSeat - Cost per seat
	 */
	public Flight(String code, String from,
			String to, String weekday, String time, int seats,
			double costPerSeat)
	{
		this.code = code;
		parseCode(code);
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Gets the flight code.
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Gets the airline.
	 * 
	 * @return the airlineName
	 */
	public String getAirline()
	{
		return airline;
	}

	/**
	 * Gets the originating airport.
	 * 
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * Gets the destination airport.
	 * 
	 * @return the to
	 */
	public String getTo()
	{
		return to;
	}

	/**
	 * Gets the day of the week the flight leaves.
	 * 
	 * @return the weekday
	 */
	public String getWeekday()
	{
		return weekday;
	}

	/**
	 * Gets the time the flight leaves (in 24 hour format).
	 * 
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * Gets the number of seats on the flight.
	 * 
	 * @return the seats
	 */
	public int getSeats()
	{
		return seats;
	}

	/**
	 * Gets the cost per seat on the flight.
	 * 
	 * @return the costPerSeat
	 */
	public double getCostPerSeat()
	{
		return costPerSeat;
	}

	/**
	 * Is this a domestic flight? *
	 * 
	 * @return True if flight is domestic.
	 */
	public boolean isDomestic()
	{
		boolean domestic = false;

		if (this.from.substring(0, 1).equals("Y") && this.to.substring(0, 1).equals("Y"))
		{
			domestic = true;
		}
		return domestic;
	}

	/**
	 * Parse Airline using flight code.
	 * 
	 * @param code	Flight code.
	 */
	private void parseCode(String code)
	{
		String airLineCode = code.substring(0, 2);

		switch (airLineCode)
		{
		case "OA":
			this.airline = "Otto Airlines";
			break;
		case "CA":
			this.airline = "Conned Air";
			break;
		case "TB":
			this.airline = "Try a Bus Airways";
			break;
		case "VA":
			this.airline = "Vertical Airway";
		}
	}

	/**
	 * Gets the human readable representation of a flight.
	 * 
	 * @return Flight information.
	 */
	@Override
	public String toString()
	{
		return code + ", From: " + from + ", To: " + to + ", Day: " + weekday + ", Cost: " + costPerSeat;
	}

}
