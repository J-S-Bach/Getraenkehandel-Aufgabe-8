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
	private JPanel panelMenue;
	private JPanel panelBuchen;
	private JPanel panelVerkauf;
	private JTable tableBuchenVon;
	private JTable tableBuchenNach;
	private JTable tableVerkaufVon;
	private JTextField txtBuchenMengeEingabe;
	private JTextField txtVerkaufenMengeEingabe;


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
		final JPanel panelMenue = new JPanel();
		frame.getContentPane().add(panelMenue, "name_473979631140500");
		panelMenue.setLayout(null);
		panelMenue.setVisible(true);

		final JPanel panelBuchen = new JPanel();
		frame.getContentPane().add(panelBuchen, "name_473992955029000");
		panelBuchen.setLayout(null);
		panelBuchen.setVisible(false);

		final JPanel panelVerkauf = new JPanel();
		frame.getContentPane().add(panelVerkauf, "name_474000871403000");
		panelVerkauf.setLayout(null);
		panelVerkauf.setVisible(false);

		// ---------------------------------------------------------------------------------------------
		// PanelBuchen---------
		JButton btnBuchen = new JButton("Buchen");
		btnBuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBuchen.setVisible(true);
				panelMenue.setVisible(false);
			}
		});
		btnBuchen.setBounds(74, 108, 120, 50);
		panelMenue.add(btnBuchen);

		JButton btnZurueckVonBuchen = new JButton("Zurück");
		btnZurueckVonBuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBuchen.setVisible(false);
				panelMenue.setVisible(true);
			}
		});
		btnZurueckVonBuchen.setBounds(560, 416, 89, 23);
		panelBuchen.add(btnZurueckVonBuchen);

		JButton btnBuchenBuchen = new JButton("Jetzt Buchen");
		btnBuchenBuchen.setBounds(265, 221, 130, 23);
		panelBuchen.add(btnBuchenBuchen);

		JComboBox cbBuchenVon = new JComboBox();
		cbBuchenVon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbBuchenVon.setModel(new DefaultComboBoxModel(new String[] { "Standort1", "Zentrallager", "Standort2" }));
		cbBuchenVon.setToolTipText("");
		cbBuchenVon.setBounds(49, 35, 135, 22);
		panelBuchen.add(cbBuchenVon);

		JComboBox cbBuchenNach = new JComboBox();
		cbBuchenNach.setModel(new DefaultComboBoxModel(new String[] { "Zentrallager", "Standort1", "Standort2" }));
		cbBuchenNach.setToolTipText("");
		cbBuchenNach.setBounds(487, 35, 135, 22);
		panelBuchen.add(cbBuchenNach);

		JComboBox cbBuchenArtickel = new JComboBox();
		cbBuchenArtickel.setModel(new DefaultComboBoxModel(new String[] { "Mineralwasser, still",
				"Mineralwasser, mit Kohlensäure", "Apfelsaft", "Orangensaft", "Limonade", "Bier" }));
		cbBuchenArtickel.setBounds(265, 100, 127, 22);
		panelBuchen.add(cbBuchenArtickel);

		JLabel lblBuchenVon = new JLabel("von:");
		lblBuchenVon.setBounds(23, 39, 46, 14);
		panelBuchen.add(lblBuchenVon);

		JLabel lblBuchenNach = new JLabel("nach:");
		lblBuchenNach.setBounds(453, 39, 46, 14);
		panelBuchen.add(lblBuchenNach);

		JLabel lblBuchenUmbuchen = new JLabel("Umbuchen:");
		lblBuchenUmbuchen.setBounds(287, 39, 89, 14);
		panelBuchen.add(lblBuchenUmbuchen);

		JLabel lblBuchenMenge = new JLabel("Menge in Flaschen:");
		lblBuchenMenge.setBounds(265, 156, 115, 14);
		panelBuchen.add(lblBuchenMenge);

		tableBuchenVon = new JTable();
		tableBuchenVon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableBuchenVon.setBounds(10, 100, 230, 180);
		panelBuchen.add(tableBuchenVon);

		tableBuchenNach = new JTable();
		tableBuchenNach.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableBuchenNach.setBounds(419, 100, 230, 180);
		panelBuchen.add(tableBuchenNach);

		txtBuchenMengeEingabe = new JTextField();
		txtBuchenMengeEingabe.setToolTipText("Eingabe der Menge");
		txtBuchenMengeEingabe.setBounds(265, 170, 86, 20);
		panelBuchen.add(txtBuchenMengeEingabe);
		txtBuchenMengeEingabe.setColumns(10);

		// ---------------------------------------------------------------------------------------------
		// PanelVerkaufen
		JButton btnVerkaufen = new JButton("Verkaufen");
		btnVerkaufen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelVerkauf.setVisible(true);
				panelMenue.setVisible(false);
			}
		});
		btnVerkaufen.setBounds(404, 108, 120, 50);
		panelMenue.add(btnVerkaufen);

		JButton btnZurueckVonVerkauf = new JButton("Zurück");
		btnZurueckVonVerkauf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelVerkauf.setVisible(false);
				panelMenue.setVisible(true);
			}
		});
		btnZurueckVonVerkauf.setBounds(560, 416, 89, 23);
		panelVerkauf.add(btnZurueckVonVerkauf);

		JButton btnVerkaufenVerkaufen = new JButton("Jetzt Verkaufen");
		btnVerkaufenVerkaufen.setBounds(265, 221, 130, 23);
		panelVerkauf.add(btnVerkaufenVerkaufen);

		JComboBox cbVerkaufenVon = new JComboBox();
		cbVerkaufenVon.setModel(new DefaultComboBoxModel(new String[] { "Zentrallager", "Standort1", "Standort2" }));
		cbVerkaufenVon.setToolTipText("");
		cbVerkaufenVon.setBounds(49, 35, 135, 22);
		panelVerkauf.add(cbVerkaufenVon);

		JComboBox cbVerkaufenArtickel = new JComboBox();
		cbVerkaufenArtickel.setModel(new DefaultComboBoxModel(new String[] { "Mineralwasser, still",
				"Mineralwasser, mit Kohlensäure", "Apfelsaft", "Orangensaft", "Limonade", "Bier" }));
		cbVerkaufenArtickel.setBounds(265, 100, 127, 22);
		panelVerkauf.add(cbVerkaufenArtickel);

		JLabel lblVerkaufenVon = new JLabel("von:");
		lblVerkaufenVon.setBounds(23, 39, 46, 14);
		panelVerkauf.add(lblVerkaufenVon);

		JLabel lblVerkaufenUmVerkaufen = new JLabel("UmVerkaufen:");
		lblVerkaufenUmVerkaufen.setBounds(287, 39, 89, 14);
		panelVerkauf.add(lblVerkaufenUmVerkaufen);

		JLabel lblVerkaufMenge = new JLabel("Menge in Flaschen:");
		lblVerkaufMenge.setBounds(265, 156, 115, 14);
		panelVerkauf.add(lblVerkaufMenge);

		tableVerkaufVon = new JTable();
		tableVerkaufVon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		tableVerkaufVon.setBounds(10, 100, 230, 180);
		panelVerkauf.add(tableVerkaufVon);

		txtVerkaufenMengeEingabe = new JTextField();
		txtVerkaufenMengeEingabe.setToolTipText("Eingabe der Menge");
		txtVerkaufenMengeEingabe.setBounds(265, 170, 86, 20);
		panelVerkauf.add(txtVerkaufenMengeEingabe);
		txtVerkaufenMengeEingabe.setColumns(10);

		// ---------------------------------------------------------------------------------------------

	}
}
