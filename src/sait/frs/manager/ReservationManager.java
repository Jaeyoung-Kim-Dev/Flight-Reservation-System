package sait.frs.manager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import sait.frs.exception.*;
import sait.frs.problemdomain.*;

/**
 * This class is responsible for managing reservations
 * 
 * @author Jaeyoung Kim
 *
 */
public class ReservationManager
{
	private ArrayList<Reservation> reservations;

	/**
	 * Default constructor for ReservationManager.
	 */
	public ReservationManager()
	{

	}

	/**
	 * Makes a reservation.
	 * 
	 * @param flight      Flight to book reservation for.
	 * @param name        Name of person (cannot be null or empty).
	 * @param citizenship Citizenship of person (cannot be null or empty).
	 * @return Created reservation instance.
	 * @throws NullFlightException         Thrown if flight is null.
	 * @throws NoMoreSeatsException        Thrown if flight is booked up.
	 * @throws InvalidNameException        Thrown if name is null or empty.
	 * @throws InvalidCitizenshipException Thrown if citizenship is null or empty.
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws NullFlightException, NoMoreSeatsException, InvalidNameException, InvalidCitizenshipException
	{
		populateFromBinary();
		
		int bookedSeats = 0;
		
		//check and count already booked fight
		for (int i = 0; i < reservations.size(); i++)
		{
			if (reservations.get(i).getFlightCode().equals(flight.getCode()) && reservations.get(i).isActive())
			{
				bookedSeats++;
			}
		}
		
		if (flight == null)
			throw new NullFlightException();
		if (getAvailableSeats(flight) - bookedSeats <= 0)
			throw new NoMoreSeatsException();
		if (name == null || name.isEmpty())
			throw new InvalidNameException();
		if (citizenship == null || citizenship.isEmpty())
			throw new InvalidCitizenshipException();

		String rCode = generateReservationCode(flight);
		Reservation newReservation = new Reservation(rCode, flight.getCode(), flight.getAirline(), name, citizenship,
				flight.getCostPerSeat(), true);
		reservations.add(newReservation);
		persist();
		return newReservation;
	}

	/**
	 * Finds reservations containing either reservation code or airline.
	 * 
	 * @param code    Reservation code to search for.
	 * @param airline Airline to search for.
	 * @param name    Travelers name to search for.
	 * @return Any matching Reservation objects.
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name)
	{
		populateFromBinary();

		ArrayList<Reservation> reservationSearch = new ArrayList<Reservation>();
		boolean found = false;

		for (int i = 0; i < reservations.size(); i++)
		{
			if (code.isEmpty())
			{
				found = true;
			} else if (reservations.get(i).getCode().contains(code))
			{
				found = true;
			}

			if (!airline.isEmpty() && found && !reservations.get(i).getAirline().contains(airline))
			{
				found = false;
			}

			if (!name.isEmpty() && found && !reservations.get(i).getName().contains(name))
			{
				found = false;
			}

			if (found)
			{
				Reservation reservationMatch = reservations.get(i);
				reservationSearch.add(reservationMatch);
			}
		}

		return reservationSearch;
	}

	/**
	 * Finds reservation with the exact reservation code.
	 * 
	 * @param code Reservation code.
	 * @return Reservation object or null if none found.
	 */
	public Reservation findReservationByCode(String code)
	{
		populateFromBinary();
		boolean found = false;

		Reservation reservationByCode = null;

		for (int i = 0; i < reservations.size() && !found; i++)
		{
			if (reservations.get(i).getCode().equals(code))
			{
				reservationByCode = reservations.get(i);
				found = true;
			}
		}
		return reservationByCode;

	}

	/**
	 * Saves records in memory to hard drive.
	 */
	public void persist()
	{
		try
		{
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
				path = "res" + File.separator + "reservations.bin";
			} else
			{
				path = path.substring(0, lastSlashPos + 1);
				path += "res" + File.separator + "reservations.bin";
			}

			File rf = new File(path);

			if (rf.exists())
				;
			{
				rf.delete();
			}

			RandomAccessFile rFile = new RandomAccessFile(rf, "rw");

			rFile.seek(0);

			for (int i = 0; i < reservations.size(); i++)
			{
				rFile.writeUTF(reservations.get(i).getCode());
				rFile.writeUTF(reservations.get(i).getFlightCode());
				rFile.writeUTF(reservations.get(i).getAirline());
				rFile.writeUTF(reservations.get(i).getName());
				rFile.writeUTF(reservations.get(i).getCitizenship());
				rFile.writeDouble(reservations.get(i).getCost());
				rFile.writeBoolean(reservations.get(i).isActive());
			}

			rFile.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the number of available seats for a flight.
	 * 
	 * @param flight Flight instance.
	 * @return Number of available seats.
	 */
	private int getAvailableSeats(Flight flight)
	{
		return flight.getSeats();
	}

	/**
	 * Generate Reservation Code with Flight object and return the code.
	 * 
	 * @param flight Flight instance.
	 * @return Reservation code.
	 */
	private String generateReservationCode(Flight flight)
	{
		String rCode = null;
		char letter;
		int randomNum;

		letter = flight.isDomestic() ? 'D' : 'I';
		randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
		rCode = letter + Integer.toString(randomNum);

		return rCode;
	}

	/**
	 * Populates reservations with Reservation objects from Random Access File.
	 * 
	 */
	private void populateFromBinary()
	{
		boolean endOfFile = false;
		String path;
		reservations = new ArrayList<Reservation>();

		path = System.getProperty("java.class.path");
		int slashPos = 0;
		int lastSlashPos = 0;
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = path.indexOf(File.separator, slashPos + 1);
		}

		if (lastSlashPos == 0)
		{
			path = "res" + File.separator + "reservations.bin";
		} else
		{
			path = path.substring(0, lastSlashPos + 1);
			path += "res" + File.separator + "reservations.bin";
		}

		RandomAccessFile rFile;

		try
		{
			rFile = new RandomAccessFile(path, "rw");
			rFile.seek(0);
			while (!endOfFile)
			{
				try
				{
					reservations.add(new Reservation(rFile.readUTF(), rFile.readUTF(), rFile.readUTF(), rFile.readUTF(),
							rFile.readUTF(), rFile.readDouble(), rFile.readBoolean()));
				} catch (IOException e)
				{
					endOfFile = true;
				} catch (NullFlightException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (InvalidNameException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (InvalidCitizenshipException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
			rFile.close();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
