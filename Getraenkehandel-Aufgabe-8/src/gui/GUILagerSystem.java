package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import inheritance.Location;
import main.LocationManager;

import gui.GUIHelperMethods;

import javax.swing.JTabbedPane;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.Box;

public class GUILagerSystem {

	private JFrame frmGetrnkeladenAi;
	private JTextField tfInputSellAmount;
	private JTextField tfInputRebookingAmount;

	/**
	 * Launch the application.
	 */

	static List<Location> locations = new ArrayList<>();
	static LocationManager manager = new LocationManager();
	static GUIHelperMethods helper = new GUIHelperMethods();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUILagerSystem window = new GUILagerSystem();
					window.frmGetrnkeladenAi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUILagerSystem() {
		initialize();
	}

	public DefaultTableModel defaultModul(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		return model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frames---------
		frmGetrnkeladenAi = new JFrame();
		frmGetrnkeladenAi.setType(Type.UTILITY);
		frmGetrnkeladenAi.setTitle("Getränkeladen AI2021");
		frmGetrnkeladenAi.setBounds(100, 100, 1012, 681);
		frmGetrnkeladenAi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGetrnkeladenAi.getContentPane().setLayout(null);
		
		// Initialize Location
		locations.add(manager.getCentral());
		locations.addAll(Arrays.asList(manager.getLocations()));
			
		JPanel panelMainPanel = new JPanel();
		panelMainPanel.setLayout(null);	
			
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 635, 457, -635);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));
		frmGetrnkeladenAi.getContentPane().add(tabbedPane);
		
		JPanel panelRebooking = new JPanel();
		tabbedPane.addTab("Umbuchen", null, panelRebooking, null);
		panelRebooking.setLayout(null);
		tabbedPane.add(panelRebooking);
		
		JPanel panelSell = new JPanel();
		tabbedPane.addTab("Verkaufen", null, panelSell, null);
		panelSell.setLayout(null);
		

		JLabel lblSellProduct = new JLabel("Produkt");
		lblSellProduct.setVerticalAlignment(SwingConstants.TOP);
		lblSellProduct.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSellProduct.setBounds(10, 97, 60, 14);
		panelSell.add(lblSellProduct);

		JComboBox cbSellProduct = new JComboBox();
		cbSellProduct.setModel(new DefaultComboBoxModel());
		cbSellProduct.setFont(new Font("Arial", Font.PLAIN, 12));
		cbSellProduct.setBounds(80, 93, 164, 22);
		panelSell.add(cbSellProduct);

		JComboBox cbSellDestination = new JComboBox();
		cbSellDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbSellDestination.getSelectedItem() != null) {
					cbSellProduct.setModel(new DefaultComboBoxModel(
							helper.getProductFromLocation(cbSellDestination.getSelectedIndex(), locations)));
				}

			}
		});
		cbSellDestination.setModel(new DefaultComboBoxModel(helper.getAllLocationNames(locations)));
		cbSellDestination.setFont(new Font("Arial", Font.PLAIN, 12));
		cbSellDestination.setBounds(80, 24, 164, 22);
		panelSell.add(cbSellDestination);

		JLabel lblSellAmount = new JLabel("Menge");
		lblSellAmount.setVerticalAlignment(SwingConstants.TOP);
		lblSellAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSellAmount.setBounds(10, 161, 60, 14);
		panelSell.add(lblSellAmount);

		tfInputSellAmount = new JTextField();
		tfInputSellAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		tfInputSellAmount.setBounds(80, 158, 86, 20);
		panelSell.add(tfInputSellAmount);
		tfInputSellAmount.setColumns(10);

		JLabel lblRebookDestinationFrom = new JLabel("Standort Von");
		lblRebookDestinationFrom.setVerticalAlignment(SwingConstants.TOP);
		lblRebookDestinationFrom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRebookDestinationFrom.setBounds(10, 28, 70, 14);
		panelRebooking.add(lblRebookDestinationFrom);

		JComboBox cbRebookingDestinationFrom = new JComboBox();
		cbRebookingDestinationFrom.setModel(new DefaultComboBoxModel(helper.getAllLocationNames(locations)));
		cbRebookingDestinationFrom.setFont(new Font("Arial", Font.PLAIN, 12));
		cbRebookingDestinationFrom.setBounds(90, 24, 164, 22);
		panelRebooking.add(cbRebookingDestinationFrom);

		JLabel lblRebooking1 = new JLabel("Umbuchen");
		lblRebooking1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRebooking1.setVerticalAlignment(SwingConstants.TOP);
		lblRebooking1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRebooking1.setBounds(127, 136, 70, 14);
		panelRebooking.add(lblRebooking1);

		JLabel lblRebookingProduct = new JLabel("Produkt");
		lblRebookingProduct.setVerticalAlignment(SwingConstants.TOP);
		lblRebookingProduct.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRebookingProduct.setBounds(10, 194, 60, 14);
		panelRebooking.add(lblRebookingProduct);

		JComboBox cbRebookingProduct = new JComboBox();
		cbRebookingProduct.setFont(new Font("Arial", Font.PLAIN, 12));
		cbRebookingProduct.setBounds(90, 190, 164, 22);
		panelRebooking.add(cbRebookingProduct);

		JLabel lblRebookingAmount = new JLabel("Menge");
		lblRebookingAmount.setVerticalAlignment(SwingConstants.TOP);
		lblRebookingAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRebookingAmount.setBounds(10, 249, 60, 14);
		panelRebooking.add(lblRebookingAmount);

		tfInputRebookingAmount = new JTextField();
		tfInputRebookingAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		tfInputRebookingAmount.setColumns(10);
		tfInputRebookingAmount.setBounds(90, 246, 86, 20);
		panelRebooking.add(tfInputRebookingAmount);

		JLabel lblRebookingDestinationTo = new JLabel("Standort Nach");
		lblRebookingDestinationTo.setVerticalAlignment(SwingConstants.TOP);
		lblRebookingDestinationTo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRebookingDestinationTo.setBounds(10, 372, 86, 14);
		panelRebooking.add(lblRebookingDestinationTo);

		JComboBox cbRebookingDestinationTo = new JComboBox();
		cbRebookingDestinationTo.setModel(new DefaultComboBoxModel(helper.getAllLocationNames(locations)));
		cbRebookingDestinationTo.setFont(new Font("Arial", Font.PLAIN, 12));
		cbRebookingDestinationTo.setBounds(90, 368, 164, 22);
		panelRebooking.add(cbRebookingDestinationTo);

		JButton btnNewButton = new JButton("Buchen");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(115, 489, 115, 62);
		panelRebooking.add(btnNewButton);

		// ---------------------------------------------------------------------------------------------

	}
}
