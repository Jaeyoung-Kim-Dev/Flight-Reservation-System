package sait.frs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frs.exception.*;
import sait.frs.manager.*;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase
{
	/**
	 * Instance of Flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of Reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Flight TextField.
	 */
	private JTextField flightTF;

	/**
	 * AirLine TextField.
	 */
	private JTextField airlineTF;

	/**
	 * Day TextField.
	 */
	private JTextField dayTF;

	/**
	 * Time TextField.
	 */
	private JTextField timeTF;

	/**
	 * Cost TextField.
	 */
	private JTextField costTF;

	/**
	 * Name TextField.
	 */
	private JTextField nameTF;

	/**
	 * Citizenship TextField.
	 */
	private JTextField citizenTF;

	/**
	 * From ComboBox.
	 */
	private JComboBox<String> fromCB;

	/**
	 * To ComboBox.
	 */
	private JComboBox<String> toCB;

	/**
	 * Day ComboBox.
	 */
	private JComboBox<String> dayCB;

	/**
	 * To enable or disable the MyListSelectionListener.
	 */
	private boolean listnerActive = true;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Manager object
	 */
	public FlightsTab(FlightManager flightManager)
	{

		this.flightManager = flightManager;
		reservationManager = new ReservationManager();
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel()
	{
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 60, 20));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);

		return panel;
	}

	/**
	 * Creates the east panel.
	 * 
	 * @return JPanel that goes in east.
	 */
	private JPanel createEastPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		JPanel eastNorth = createEastNorthPanel();
		panel.add(eastNorth, BorderLayout.NORTH);

		JPanel eastWest = createEastWestPanel();
		panel.add(eastWest, BorderLayout.WEST);

		JPanel eastEast = createEastEastPanel();
		panel.add(eastEast, BorderLayout.EAST);

		JPanel eastSouth = createEastSouthPanel();
		panel.add(eastSouth, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the north panel in the east panel.
	 * 
	 * @return JPanel that goes in the north panel in east.
	 */
	private JPanel createEastNorthPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));

		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the west panel in the east panel.
	 * 
	 * @return JPanel that goes in the west panel in east.
	 */
	private JPanel createEastWestPanel()
	{
		JPanel panel = new JPanel();

		JLabel flightLbl = new JLabel("Flight: ", SwingConstants.RIGHT);
		JLabel airlineLbl = new JLabel("AirLine: ", SwingConstants.RIGHT);
		JLabel dayLbl = new JLabel("Day: ", SwingConstants.RIGHT);
		JLabel timeLbl = new JLabel("Time: ", SwingConstants.RIGHT);
		JLabel costLbl = new JLabel("Cost: ", SwingConstants.RIGHT);
		JLabel nameLbl = new JLabel("Name: ", SwingConstants.RIGHT);
		JLabel citizenLbl = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		panel.setLayout(new GridLayout(7, 1));

		panel.add(flightLbl);
		panel.add(airlineLbl);
		panel.add(dayLbl);
		panel.add(timeLbl);
		panel.add(costLbl);
		panel.add(nameLbl);
		panel.add(citizenLbl);

		return panel;
	}

	/**
	 * Creates the east panel in the east panel.
	 * 
	 * @return JPanel that goes in the east panel in east.
	 */
	private JPanel createEastEastPanel()
	{
		JPanel panel = new JPanel();

		flightTF = new JTextField(12);
		airlineTF = new JTextField(12);
		dayTF = new JTextField(12);
		timeTF = new JTextField(12);
		costTF = new JTextField(12);
		nameTF = new JTextField(12);
		citizenTF = new JTextField(12);

		flightTF.setEditable(false);
		airlineTF.setEditable(false);
		dayTF.setEditable(false);
		timeTF.setEditable(false);
		costTF.setEditable(false);

		panel.setLayout(new GridLayout(7, 1));

		panel.add(flightTF);
		panel.add(airlineTF);
		panel.add(dayTF);
		panel.add(timeTF);
		panel.add(costTF);
		panel.add(nameTF);
		panel.add(citizenTF);

		return panel;
	}

	/**
	 * Creates the south panel in the east panel.
	 * 
	 * @return JPanel that goes in the south panel in east.
	 */
	private JPanel createEastSouthPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

		JButton reserve = new JButton("Reserve");

		panel.setLayout(new GridLayout(1, 1));
		reserve.addActionListener(new ReserveListener());
		panel.add(reserve);

		return panel;
	}

	/**
	 * Creates the south panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		JPanel southNorth = createSouthNorthPanel();
		panel.add(southNorth, BorderLayout.NORTH);

		JPanel southWest = createSouthWestPanel();
		panel.add(southWest, BorderLayout.WEST);

		JPanel southCenter = createSouthCenterPanel();
		panel.add(southCenter, BorderLayout.CENTER);

		JPanel southSouth = createSouthSouthPanel();
		panel.add(southSouth, BorderLayout.SOUTH);

		return panel;

	}

	/**
	 * Creates the north panel in the south panel.
	 * 
	 * @return JPanel that goes in the north panel in south.
	 */
	private JPanel createSouthNorthPanel()
	{
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the east panel in the south panel.
	 * 
	 * @return JPanel that goes in the east panel in south.
	 */
	private JPanel createSouthWestPanel()
	{
		JPanel panel = new JPanel();

		JLabel fromLbl = new JLabel("From: ", SwingConstants.RIGHT);
		JLabel toLbl = new JLabel("To: ", SwingConstants.RIGHT);
		JLabel dayLbl = new JLabel("Day: ", SwingConstants.RIGHT);

		panel.setLayout(new GridLayout(3, 1));

		panel.add(fromLbl);
		panel.add(toLbl);
		panel.add(dayLbl);

		return panel;
	}

	/**
	 * Creates the east panel in the south panel.
	 * 
	 * @return JPanel that goes in the east panel in south.
	 */
	private JPanel createSouthCenterPanel()
	{
		JPanel panel = new JPanel();

		String[] day =
		{ FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_SUNDAY, FlightManager.WEEKDAY_MONDAY,
				FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY, FlightManager.WEEKDAY_THURSDAY,
				FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY };

		fromCB = new JComboBox<>(flightManager.getAirports().toArray(new String[flightManager.getAirports().size()]));
		toCB = new JComboBox<>(flightManager.getAirports().toArray(new String[flightManager.getAirports().size()]));
		dayCB = new JComboBox<>(day);

		panel.setLayout(new GridLayout(3, 1));

		panel.add(fromCB);
		panel.add(toCB);
		panel.add(dayCB);

		return panel;
	}

	/**
	 * Creates the east panel in the south panel.
	 * 
	 * @return JPanel that goes in the east panel in south.
	 */
	private JPanel createSouthSouthPanel()
	{
		JPanel panel = new JPanel();

		JButton findFlights = new JButton("Find Flights");

		panel.setLayout(new GridLayout(1, 1));

		findFlights.addActionListener(new FindFlightListener());
		panel.add(findFlights);

		return panel;
	}

	/**
	 * Inner action listener class that listens for the flights list to be clicked.
	 * 
	 * @author Jaeyoung Kim
	 * @version March 22, 2020
	 */
	private class MyListSelectionListener implements ListSelectionListener
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (listnerActive)
			{
				flightTF.setText(flightsList.getSelectedValue().getCode());
				airlineTF.setText(flightsList.getSelectedValue().getAirline());
				dayTF.setText(flightsList.getSelectedValue().getWeekday());
				timeTF.setText(flightsList.getSelectedValue().getTime());
				costTF.setText("$" + flightsList.getSelectedValue().getCostPerSeat());
			}
		}
	}

	/**
	 * Inner action listener class that listens for the Reserve button to be
	 * clicked.
	 * 
	 * @author Jaeyoung Kim
	 * @version March 22, 2020
	 */
	private class ReserveListener implements ActionListener
	{
		/**
		 * Called when user clicks the Reserve button.
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Reservation reservation = reservationManager.makeReservation(flightsList.getSelectedValue(),
						(String) nameTF.getText(), (String) citizenTF.getText());
				String result = String.format("Reservation Created. Your code is %s.", reservation.getCode());
				JOptionPane.showMessageDialog(null, result);
			} catch (NullFlightException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (NoMoreSeatsException e1)
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
	}

	/**
	 * Inner action listener class that listens for the Find Flights button to be
	 * clicked.
	 * 
	 * @author Jaeyoung Kim
	 * @version March 22, 2020
	 */
	private class FindFlightListener implements ActionListener
	{
		/**
		 * Called when user clicks the Find Flights button.
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			listnerActive = false;
			flightsModel.removeAllElements();

			flightTF.setText("");
			airlineTF.setText("");
			dayTF.setText("");
			timeTF.setText("");
			costTF.setText("");
			nameTF.setText("");
			citizenTF.setText("");

			String fromVal = (String) fromCB.getSelectedItem();
			String toVal = (String) toCB.getSelectedItem();
			String dayVal = (String) dayCB.getSelectedItem();

			ArrayList<Flight> flights = flightManager.findFlights(fromVal, toVal, dayVal);

			for (int i = 0; i < flights.size(); i++)
			{
				flightsModel.addElement(flights.get(i));
			}

			listnerActive = true;
		}
	}
}