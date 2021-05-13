package sait.frs.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import sait.frs.problemdomain.Flight;

/**
 * This class is responsible for managing flights and airports.
 * 
 * @author Jaeyoung Kim
 *
 */
public class FlightManager
{
	/**
	 * Used to search for reservations on any day of the week.
	 */
	public final static String WEEKDAY_ANY = "Any";
	/**
	 * Used to search for reservations on Sunday.
	 */
	public final static String WEEKDAY_SUNDAY = "Sunday";
	/**
	 * Used to search for reservations on Monday.
	 */
	public final static String WEEKDAY_MONDAY = "Monday";
	/**
	 * Used to search for reservations on Tuesday.
	 */
	public final static String WEEKDAY_TUESDAY = "Tuesday";
	/**
	 * Used to search for reservations on Wednesday.
	 */
	public final static String WEEKDAY_WEDNESDAY = "Wednesday";
	/**
	 * Used to search for reservations on Thursday.
	 */
	public final static String WEEKDAY_THURSDAY = "Thursday";
	/**
	 * Used to search for reservations on Friday.
	 */
	public final static String WEEKDAY_FRIDAY = "Friday";
	/**
	 * Used to search for reservations on Saturday.
	 */
	public final static String WEEKDAY_SATURDAY = "Saturday";

	/**
	 * ArrayList for Flights.
	 */
	private ArrayList<Flight> flights;

	/**
	 * ArrayList for Airports.
	 */
	private ArrayList<String> airports;

	/**
	 * Default constructor for FlightManager.
	 */
	public FlightManager()
	{

	}

	/**
	 * Gets all of the airports.
	 * 
	 * @return ArrayList of airports.
	 */
	public ArrayList<String> getAirports()
	{
		populateAirports();
		return airports;
	}

	/**
	 * Gets all of the flights.
	 * 
	 * @return ArrayList of Flight objects.
	 */
	public ArrayList<Flight> getFlights()
	{
		populateFlights();
		return flights;
	}

	/**
	 * Finds a airport using code.
	 * 
	 * @param code	Flight code.
	 * @return Airport
	 */
	public String findAirportByCode(String code)
	{
		return code;
	}

	/**
	 * Finds a flight using code.
	 * 
	 * @param code	Flight code.
	 * @return Flight object or null if code is not found.
	 */
	public Flight findFlightByCode(String code)
	{
		boolean found = false;

		this.getFlights();

		Flight flightsByCode = null;

		for (int i = 0; i < flights.size() && !found; i++)
		{
			if (flights.get(i).getCode().equals(code))
			{
				flightsByCode = flights.get(i);
				found = true;
			}
		}
		return flightsByCode;
	}

	/**
	 * Finds flights going between airports on a specified weekday.
	 * 
	 * @param from    From airport
	 * @param to      To airport
	 * @param weekday Day of week (one of WEEKDAY_* constants). Use WEEKDAY_ANY for
	 *                any day of the week.
	 * @return Any found Flight objects.
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday)
	{
		this.getFlights();

		ArrayList<Flight> flightsSearch = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++)
		{
			boolean validCode = false;
			if (flights.get(i).getCode().substring(0, 2).matches("[A-Z][A-Z]"))
				validCode = true;

			if (validCode && weekday.equals(WEEKDAY_ANY) && flights.get(i).getFrom().equals(from)
					&& flights.get(i).getTo().equals(to))
			{
				flightsSearch.add(flights.get(i)); // Any day
			} else if (validCode && flights.get(i).getFrom().equals(from) && flights.get(i).getTo().equals(to)
					&& flights.get(i).getWeekday().equals(weekday))
			{
				flightsSearch.add(flights.get(i)); // Non-Any day
			}

		}
		return flightsSearch;
	}

	/**
	 * Populates flights from text file or binary file if it exists.
	 * 
	 */
	private void populateFlights()
	{
		flights = new ArrayList<Flight>();

		String path = System.getProperty("java.class.path");
		int slashPos = 0;
		int lastSlashPos = 0;
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = path.indexOf(File.separator, slashPos + 1);
		}

		if (lastSlashPos == 0)
		{
			path = "res" + File.separator + "flights.csv";
		} else
		{
			path = path.substring(0, lastSlashPos + 1);
			path += "res" + File.separator + "flights.csv";
		}

		File savedFile = new File(path);

		Scanner t;
		try
		{
			t = new Scanner(savedFile);
			while (t.hasNextLine())
			{
				String line = t.nextLine();
				String[] flightsInfo = line.split(","); // split a book info line and put values in array.

				Flight flightMatch = new Flight(flightsInfo[0], flightsInfo[1], flightsInfo[2], flightsInfo[3],
						flightsInfo[4], Integer.parseInt(flightsInfo[5]), Double.parseDouble(flightsInfo[6]));
				flights.add(flightMatch);
			}
			t.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Populates airports with the airports from CSV file.
	 * 
	 */
	private void populateAirports()
	{
		airports = new ArrayList<String>();

		String path = System.getProperty("java.class.path");
		int slashPos = 0;
		int lastSlashPos = 0;
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = path.indexOf(File.separator, slashPos + 1);
		}

		if (lastSlashPos == 0)
		{
			path = "res" + File.separator + "airports.csv";
		} else
		{
			path = path.substring(0, lastSlashPos + 1);
			path += "res" + File.separator + "airports.csv";
		}

		File savedFile = new File(path);

		Scanner t;
		try
		{
			t = new Scanner(savedFile);
			while (t.hasNextLine())
			{
				String line = t.nextLine();
				String[] airportsInfo = line.split(","); // split a book info line and put values in array.
				airports.add(airportsInfo[0]);
			}
			t.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
