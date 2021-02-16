package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class GUILagerSystem {

	private JFrame frame;
	private JPanel panelMenu;
	private JPanel panelBooking;
	private JPanel panelSell;
	private JTable tableBookingOf;
	private JTable tableBookingTo;
	private JTable tableSellOf;
	private JTextField txtBookingQuantityInput;
	private JTextField txtSellQuantityInput;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUILagerSystem window = new GUILagerSystem();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		// ---------------------------------------------------------------------------------------------
		// Panels----------
		final JPanel panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_473979631140500");
		panelMenu.setLayout(null);
		panelMenu.setVisible(true);

		final JPanel panelBooking = new JPanel();
		frame.getContentPane().add(panelBooking, "name_473992955029000");
		panelBooking.setLayout(null);
		panelBooking.setVisible(false);

		final JPanel panelSell = new JPanel();
		frame.getContentPane().add(panelSell, "name_474000871403000");
		panelSell.setLayout(null);
		panelSell.setVisible(false);

		// ---------------------------------------------------------------------------------------------
		// PanelBuchen---------
		JButton btnBooking = new JButton("Buchen");
		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBooking.setVisible(true);
				panelMenu.setVisible(false);
			}
		});
		btnBooking.setBounds(74, 108, 120, 50);
		panelMenu.add(btnBooking);

		JButton btnBackofBooking = new JButton("Zurück");
		btnBackofBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBooking.setVisible(false);
				panelMenu.setVisible(true);
			}
		});
		btnBackofBooking.setBounds(560, 416, 89, 23);
		panelBooking.add(btnBackofBooking);

		JButton btnBookingBooking = new JButton("Jetzt Buchen");
		btnBookingBooking.setBounds(265, 221, 130, 23);
		panelBooking.add(btnBookingBooking);

		JComboBox cbBookingOf = new JComboBox();
		cbBookingOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbBookingOf.setModel(new DefaultComboBoxModel(new String[] { "Standort1", "Zentrallager", "Standort2" }));
		cbBookingOf.setToolTipText("");
		cbBookingOf.setBounds(49, 35, 135, 22);
		panelBooking.add(cbBookingOf);

		JComboBox cbBookingTo = new JComboBox();
		cbBookingTo.setModel(new DefaultComboBoxModel(new String[] { "Zentrallager", "Standort1", "Standort2" }));
		cbBookingTo.setToolTipText("");
		cbBookingTo.setBounds(487, 35, 135, 22);
		panelBooking.add(cbBookingTo);

		JComboBox cbBookingArticle = new JComboBox();
		cbBookingArticle.setModel(new DefaultComboBoxModel(new String[] { "Mineralwasser, still",
				"Mineralwasser, mit Kohlensäure", "Apfelsaft", "Orangensaft", "Limonade", "Bier" }));
		cbBookingArticle.setBounds(265, 100, 127, 22);
		panelBooking.add(cbBookingArticle);

		JLabel lblBookingOf = new JLabel("von:");
		lblBookingOf.setBounds(23, 39, 46, 14);
		panelBooking.add(lblBookingOf);

		JLabel lblBookingTo = new JLabel("nach:");
		lblBookingTo.setBounds(453, 39, 46, 14);
		panelBooking.add(lblBookingTo);

		JLabel lblBookingRebooking = new JLabel("Umbuchen:");
		lblBookingRebooking.setBounds(287, 39, 89, 14);
		panelBooking.add(lblBookingRebooking);

		JLabel lblBookingQuantity = new JLabel("Menge in Flaschen:");
		lblBookingQuantity.setBounds(265, 156, 115, 14);
		panelBooking.add(lblBookingQuantity);

		tableBookingOf = new JTable();
		tableBookingOf.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableBookingOf.setBounds(10, 100, 230, 180);
		panelBooking.add(tableBookingOf);

		tableBookingTo = new JTable();
		tableBookingTo.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableBookingTo.setBounds(419, 100, 230, 180);
		panelBooking.add(tableBookingTo);

		txtBookingQuantityInput = new JTextField();
		txtBookingQuantityInput.setToolTipText("Eingabe der Menge");
		txtBookingQuantityInput.setBounds(265, 170, 86, 20);
		panelBooking.add(txtBookingQuantityInput);
		txtBookingQuantityInput.setColumns(10);

		// ---------------------------------------------------------------------------------------------
		// PanelVerkaufen
		JButton btnSell = new JButton("Verkaufen");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSell.setVisible(true);
				panelMenu.setVisible(false);
			}
		});
		btnSell.setBounds(404, 108, 120, 50);
		panelMenu.add(btnSell);
		
		JButton btnExportCSV = new JButton("Lagerbest\u00E4nde als CSV exportieren");
		btnExportCSV.setBounds(450, 420, 203, 23);
		panelMenu.add(btnExportCSV);
		
		JButton btnStock = new JButton("Lagerbestand");
		btnStock.setBounds(74, 248, 120, 82);
		panelMenu.add(btnStock);

		JButton btnBackOfSell = new JButton("Zurück");
		btnBackOfSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSell.setVisible(false);
				panelMenu.setVisible(true);
			}
		});
		btnBackOfSell.setBounds(560, 416, 89, 23);
		panelSell.add(btnBackOfSell);

		JButton btnSell_SellNow = new JButton("Jetzt Verkaufen");
		btnSell_SellNow.setBounds(265, 221, 130, 23);
		panelSell.add(btnSell_SellNow);

		JComboBox cbSellOf = new JComboBox();
		cbSellOf.setModel(new DefaultComboBoxModel(new String[] { "Zentrallager", "Standort1", "Standort2" }));
		cbSellOf.setToolTipText("");
		cbSellOf.setBounds(49, 35, 135, 22);
		panelSell.add(cbSellOf);

		JComboBox cbSellArticle = new JComboBox();
		cbSellArticle.setModel(new DefaultComboBoxModel(new String[] { "Mineralwasser, still",
				"Mineralwasser, mit Kohlensäure", "Apfelsaft", "Orangensaft", "Limonade", "Bier" }));
		cbSellArticle.setBounds(265, 100, 127, 22);
		panelSell.add(cbSellArticle);

		JLabel lblSellOf = new JLabel("von:");
		lblSellOf.setBounds(23, 39, 46, 14);
		panelSell.add(lblSellOf);

		JLabel lblSellSelling = new JLabel("UmVerkaufen:");
		lblSellSelling.setBounds(287, 39, 89, 14);
		panelSell.add(lblSellSelling);

		JLabel lblSellQuantity = new JLabel("Menge in Flaschen:");
		lblSellQuantity.setBounds(265, 156, 115, 14);
		panelSell.add(lblSellQuantity);

		tableSellOf = new JTable();
		tableSellOf.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableSellOf.setBounds(10, 100, 230, 180);
		panelSell.add(tableSellOf);

		txtSellQuantityInput = new JTextField();
		txtSellQuantityInput.setToolTipText("Eingabe der Menge");
		txtSellQuantityInput.setBounds(265, 170, 86, 20);
		panelSell.add(txtSellQuantityInput);
		txtSellQuantityInput.setColumns(10);

		// ---------------------------------------------------------------------------------------------

	}
}
