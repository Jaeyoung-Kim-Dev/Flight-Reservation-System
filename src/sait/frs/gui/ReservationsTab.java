package sait.frs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frs.manager.*;
import sait.frs.exception.*;
import sait.frs.problemdomain.*;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase
{
	/**
	 * Instance of Reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of reservations.
	 */
	private JList<Reservation> reservationList;

	private DefaultListModel<Reservation> reservationModel;

	/**
	 * Reservation Code TextField.
	 */
	private JTextField rCodeTF;

	/**
	 * Flight TextField.
	 */
	private JTextField flightTF;

	/**
	 * AirLine TextField.
	 */
	private JTextField rAirlineTF;

	/**
	 * Cost TextField.
	 */
	private JTextField costTF;

	/**
	 * Name TextField.
	 */
	private JTextField rNameTF;

	/**
	 * Citizenship TextField.
	 */
	private JTextField citizenTF;

	/**
	 * Status ComboBox.
	 */
	private JComboBox<String> statusCB;

	/**
	 * Reservation Code Search TextField.
	 */
	private JTextField sCodeTF;

	/**
	 * AirLine Search TextField.
	 */
	private JTextField sAirLineTF;

	/**
	 * Name Search TextField.
	 */
	private JTextField sNameTF;

	/**
	 * To enable or disable the MyListSelectionListener.
	 */
	private boolean listnerActive = true;

	/**
	 * Creates the components for reservations tab.
	 * 
	 * @param reservationManager Manager object
	 */
	public ReservationsTab(ReservationManager reservationManager)
	{
		this.reservationManager = reservationManager;
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

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
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

		reservationModel = new DefaultListModel<>();
		reservationList = new JList<>(reservationModel);

		// User can only select one item at a time.
		reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationList);

		reservationList.addListSelectionListener(new MyListSelectionListener());

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

		JLabel codeLbl = new JLabel("Code: ", SwingConstants.RIGHT);
		JLabel flightLbl = new JLabel("Flight: ", SwingConstants.RIGHT);
		JLabel airlineLbl = new JLabel("AirLine: ", SwingConstants.RIGHT);
		JLabel costLbl = new JLabel("Cost: ", SwingConstants.RIGHT);
		JLabel nameLbl = new JLabel("Name: ", SwingConstants.RIGHT);
		JLabel citizenLbl = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		JLabel statusLbl = new JLabel("Status: ", SwingConstants.RIGHT);

		panel.setLayout(new GridLayout(7, 1));

		panel.add(codeLbl);
		panel.add(flightLbl);
		panel.add(airlineLbl);
		panel.add(costLbl);
		panel.add(nameLbl);
		panel.add(citizenLbl);
		panel.add(statusLbl);

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

		String[] active =
		{ "Active", "Inactive" };

		rCodeTF = new JTextField(12);
		flightTF = new JTextField(12);
		rAirlineTF = new JTextField(12);
		costTF = new JTextField(12);
		rNameTF = new JTextField(12);
		citizenTF = new JTextField(12);
		statusCB = new JComboBox<>(active);

		rCodeTF.setEditable(false);
		flightTF.setEditable(false);
		rAirlineTF.setEditable(false);
		costTF.setEditable(false);

		panel.setLayout(new GridLayout(7, 1));

		panel.add(rCodeTF);
		panel.add(flightTF);
		panel.add(rAirlineTF);
		panel.add(costTF);
		panel.add(rNameTF);
		panel.add(citizenTF);
		panel.add(statusCB);

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

		JButton update = new JButton("Update");

		panel.setLayout(new GridLayout(1, 1));
		update.addActionListener(new updateListener());
		panel.add(update);

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

		JLabel title = new JLabel("Search", SwingConstants.CENTER);
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

		JLabel codeLbl = new JLabel("Code: ", SwingConstants.RIGHT);
		JLabel airLineLbl = new JLabel("AirLine: ", SwingConstants.RIGHT);
		JLabel nameLbl = new JLabel("Name: ", SwingConstants.RIGHT);

		panel.setLayout(new GridLayout(3, 1));

		panel.add(codeLbl);
		panel.add(airLineLbl);
		panel.add(nameLbl);

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

		sCodeTF = new JTextField();
		sAirLineTF = new JTextField();
		sNameTF = new JTextField();

		panel.setLayout(new GridLayout(3, 1));

		panel.add(sCodeTF);
		panel.add(sAirLineTF);
		panel.add(sNameTF);

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

		JButton findReservation = new JButton("Find Reservations");

		panel.setLayout(new GridLayout(1, 1));

		findReservation.addActionListener(new FindReservationListener());
		panel.add(findReservation);

		return panel;
	}

	/**
	 * Inner action listener class that listens for the reservations list to be
	 * clicked.
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
				rCodeTF.setText(reservationList.getSelectedValue().getCode());
				flightTF.setText(reservationList.getSelectedValue().getFlightCode());
				rAirlineTF.setText(reservationList.getSelectedValue().getAirline());
				costTF.setText("$" + reservationList.getSelectedValue().getCost());
				rNameTF.setText(reservationList.getSelectedValue().getName());
				citizenTF.setText(reservationList.getSelectedValue().getCitizenship());
				if (reservationList.getSelectedValue().isActive())
				{
					statusCB.setSelectedIndex(0);
				} else
				{
					statusCB.setSelectedIndex(1);
				}
			}
		}
	}

	/**
	 * Inner action listener class that listens for the Update button to be clicked.
	 * 
	 * @author Jaeyoung Kim
	 * @version March 22, 2020
	 */
	private class updateListener implements ActionListener
	{
		/**
		 * Called when user clicks the Update button.
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{

			boolean status = true;
			if (statusCB.getSelectedIndex() == 1)
			{
				status = false;
			}

			try
			{
				Reservation reservation = reservationManager
						.findReservationByCode(reservationList.getSelectedValue().getCode());
				String name = (String) rNameTF.getText();
				String citizenship = (String) citizenTF.getText();
				reservation.setName(name);
				reservation.setCitizenship(citizenship);
				reservation.setActive(status);
				reservationManager.persist();

				String result = String
						.format("Reservation " + reservationList.getSelectedValue().getCode() + " is updated.");
				JOptionPane.showMessageDialog(null, result);
			} catch (InvalidNameException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (InvalidCitizenshipException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (NullPointerException e1)
			{
				JOptionPane.showMessageDialog(null, "Reservation is missing.");
			}
		}
	}

	/**
	 * Inner action listener class that listens for the Find Reservation button to
	 * be clicked.
	 * 
	 * @author Jaeyoung Kim
	 * @version March 22, 2020
	 */
	private class FindReservationListener implements ActionListener
	{
		/**
		 * Called when user clicks the Find Reservation button.
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			listnerActive = false;
			reservationModel.removeAllElements();

			rCodeTF.setText("");
			flightTF.setText("");
			rAirlineTF.setText("");
			costTF.setText("");
			rNameTF.setText("");
			citizenTF.setText("");
			statusCB.setSelectedIndex(0);

			String reserveCode = (String) sCodeTF.getText();
			String airLine = (String) sAirLineTF.getText();
			String name = (String) sNameTF.getText();

			ArrayList<Reservation> reservations = reservationManager.findReservations(reserveCode, airLine, name);
			for (int i = 0; i < reservations.size(); i++)
			{
				reservationModel.addElement(reservations.get(i));
			}

			listnerActive = true;
		}
	}
}
