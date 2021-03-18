package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import guiUIComponents.StyledTabbedPanelUI;
import inheritance.Location;
import main.LocationManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ViewportLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ResponseCache;

/**
 * Creates the GUI
 * 
 * @author Jan Bauer
 *
 */
public class GUI {

	static List<Location> locations = new ArrayList<>();
	static LocationManager manager = new LocationManager();

	public JFrame frmGetrnkehandel;
	private JTextField tfSellImputAmount;
	private JTextField tfRebookingImputAmount;
	private Object[] columnHeader = { "Getränk", "Flaschen haben", "Flaschen soll", "Kästen haben", "Kästen soll" };
	private Object[] columnHeaderWithAttributes = { "Getränk", "Flaschen haben", "Flaschen soll", "Kästen soll",
			"Kästen haben", "Eigenschaft" };
	private JTable tableSell;
	private JTable tableRebookingTop;
	private JTable tableRebookingBottum;

	// own color
	private Color VERY_LIGHT_GREEN = new Color(144, 238, 144);
	private JTextField tfSellOutputGlas;
	private JTextField tfSellOutputPlastik;
	private JTable tableRestock;

	/**
	 * Run the class initialize
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

// initialize locations
		locations.add(manager.getCentral());
		locations.addAll(Arrays.asList(manager.getLocations()));

// create object of GUIHelperMethods
		GUIHelperMethods helper = new GUIHelperMethods(locations);

// create frame
		frmGetrnkehandel = new JFrame();
		frmGetrnkehandel.setTitle("Saftladen");
		frmGetrnkehandel.setVisible(true);
		frmGetrnkehandel.setBackground(Color.LIGHT_GRAY);
		frmGetrnkehandel
				.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/gui/images/saftladen-green.png")));
		frmGetrnkehandel.setBounds(100, 100, 952, 615);
		frmGetrnkehandel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// create tabbedPane
		UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setOpaque(true);
		tabbedPane.setUI(new StyledTabbedPanelUI());
		frmGetrnkehandel.getContentPane().add(tabbedPane, BorderLayout.CENTER);

// create tabbedPane tab 'Verkaufen'
		// create panels for tab 'Verkaufen'
		JPanel panelSell = new JPanel();
		tabbedPane.addTab("Verkaufen", new ImageIcon(GUI.class.getResource("/gui/images/SellIcon.png")), panelSell,
				null);
		panelSell.setLayout(new BorderLayout(0, 0));

		JPanel panelSellInput = new JPanel();
		panelSellInput.setBackground(Color.LIGHT_GRAY);
		panelSellInput.setPreferredSize(new Dimension(300, 100));
		panelSell.add(panelSellInput, BorderLayout.NORTH);

// create swing components			
		JPanel panelSellOutput = new JPanel();

		JPanel panelRebookingTop = new JPanel();

		JPanel panelRebookingBottum = new JPanel();

		JPanel panelSellDetails = new JPanel();

		JScrollPane scrollPaneSell = new JScrollPane();

		JLabel lblSellDestination = new JLabel("Standort:");

		JLabel lblSellProduct = new JLabel("Produkt:");

		JLabel lblSellAmount = new JLabel("Menge:");

		JLabel lblSellAtrributeGlas = new JLabel("Glas:");
		lblSellAtrributeGlas.setVisible(false);

		JLabel lblSellAtrributePlastik = new JLabel("Plastik:");
		lblSellAtrributePlastik.setVisible(false);

		JButton btnSellShowDetails = new JButton("Details anzeigen");

		JButton btnSellSell = new JButton("Verkaufen");

		JComboBox cbSellProduct = new JComboBox();

		JComboBox cbSellDestination = new JComboBox();

		tfSellOutputPlastik = new JTextField();
		tfSellOutputPlastik.setVisible(false);

		tfSellOutputGlas = new JTextField();
		tfSellOutputGlas.setVisible(false);

		tfSellImputAmount = new JTextField();
		tfSellImputAmount.setColumns(10);

		tableSell = new JTable();

		GroupLayout gl_panelSellInput = new GroupLayout(panelSellInput);

		GroupLayout gl_panelSellDetails = new GroupLayout(panelSellDetails);

		/*
		 * - fill cbSellProduct with products from selected location an set table with
		 * data - render table and set column color - set border with locationname in it
		 */
		cbSellDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblSellAtrributeGlas.setVisible(false);
				lblSellAtrributePlastik.setVisible(false);
				tfSellOutputGlas.setVisible(false);
				tfSellOutputPlastik.setVisible(false);

				cbSellProduct.setModel(new DefaultComboBoxModel(
						helper.getAllProductsFromLocation(cbSellDestination.getSelectedIndex())));
				tableSell.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbSellDestination.getSelectedIndex()), columnHeader));

				panelSellOutput.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
						(String) cbSellDestination.getSelectedItem(), TitledBorder.LEFT, TitledBorder.TOP, null,
						Color.WHITE));
				helper.renderColor(columnHeader, tableSell);
			}
		});
		cbSellDestination.setModel(new DefaultComboBoxModel(helper.getAllLocationNames()));

		/*
		 * - remove selected amount from location - catch exceptions if thrown - set
		 * table with updated data - render table and set column color
		 */
		btnSellSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					locations.get(cbSellDestination.getSelectedIndex())
							.removeDrink(
									helper.stingToDrinkType(cbSellDestination.getSelectedIndex(),
											cbSellProduct.getSelectedItem()),
									Integer.parseInt(tfSellImputAmount.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1, "Dialog", JOptionPane.ERROR_MESSAGE);
				}
				tableSell.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbSellDestination.getSelectedIndex()), columnHeader));

				helper.renderColor(columnHeader, tableSell);
			}
		});
		btnSellSell.setBackground(VERY_LIGHT_GREEN);

// create groupLayout and align swing components	
		gl_panelSellInput.setHorizontalGroup(gl_panelSellInput.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSellInput.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelSellInput.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSellDestination).addComponent(lblSellProduct))
						.addGap(27)
						.addGroup(gl_panelSellInput.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbSellProduct, 0, 130, Short.MAX_VALUE).addComponent(cbSellDestination,
										GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGap(91)
						.addGroup(gl_panelSellInput.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelSellInput.createSequentialGroup().addComponent(lblSellAmount)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(tfSellImputAmount,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSellSell, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(446, Short.MAX_VALUE)));
		gl_panelSellInput.setVerticalGroup(gl_panelSellInput.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSellInput.createSequentialGroup().addGap(19)
						.addGroup(gl_panelSellInput.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSellDestination)
								.addComponent(cbSellDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSellAmount).addComponent(tfSellImputAmount, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								gl_panelSellInput.createParallelGroup(Alignment.BASELINE).addComponent(lblSellProduct)
										.addComponent(cbSellProduct, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSellSell))
						.addContainerGap()));
		panelSellInput.setLayout(gl_panelSellInput);

// create panel for tab 'Verkaufen'				
		panelSellOutput.setBackground(Color.GRAY);
		panelSellOutput.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				(String) cbSellDestination.getSelectedItem(), TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		panelSell.add(panelSellOutput, BorderLayout.CENTER);
		panelSellOutput.setLayout(new BorderLayout(0, 0));

// create panel to switch to detailmode		
		panelSellDetails.setBackground(Color.LIGHT_GRAY);
		panelSellDetails.setPreferredSize(new Dimension(300, 35));
		panelSellOutput.add(panelSellDetails, BorderLayout.SOUTH);

		/*
		 * - set labels and textFields for details true - fill table with products and
		 * its attributes - render table and set column color - fill textFields with
		 * data
		 */
		btnSellShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblSellAtrributeGlas.setVisible(true);
				lblSellAtrributePlastik.setVisible(true);
				tfSellOutputGlas.setVisible(true);
				tfSellOutputPlastik.setVisible(true);

				tableSell.setModel(new DefaultTableModel(
						helper.getSortimentAndAttributesOfLocation(cbSellDestination.getSelectedIndex()),
						columnHeaderWithAttributes));
				helper.renderColor(columnHeaderWithAttributes, tableSell);

				int[] bottleTypeBoxes = locations.get(cbSellDestination.getSelectedIndex()).getBottleTypeBoxes();

				tfSellOutputGlas.setText(Integer.toString(bottleTypeBoxes[0]));
				tfSellOutputPlastik.setText(Integer.toString(bottleTypeBoxes[1]));
			}
		});
		btnSellShowDetails.setForeground(Color.WHITE);
		btnSellShowDetails.setBackground(Color.DARK_GRAY);

		tfSellOutputGlas.setEditable(false);
		tfSellOutputGlas.setColumns(10);

		tfSellOutputPlastik.setEditable(false);
		tfSellOutputPlastik.setColumns(10);

// create groupLayout and align swing components	
		gl_panelSellDetails.setHorizontalGroup(gl_panelSellDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSellDetails.createSequentialGroup().addGap(12).addComponent(btnSellShowDetails)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblSellAtrributeGlas)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tfSellOutputGlas, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblSellAtrributePlastik)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(tfSellOutputPlastik,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(451, Short.MAX_VALUE)));
		gl_panelSellDetails.setVerticalGroup(gl_panelSellDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSellDetails.createSequentialGroup().addGap(4)
						.addGroup(gl_panelSellDetails.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSellShowDetails).addComponent(lblSellAtrributeGlas)
								.addComponent(tfSellOutputGlas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSellAtrributePlastik).addComponent(tfSellOutputPlastik,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))));
		panelSellDetails.setLayout(gl_panelSellDetails);

		scrollPaneSell.getViewport().setBackground(Color.LIGHT_GRAY);
		panelSellOutput.add(scrollPaneSell, BorderLayout.CENTER);

		tableSell.setBackground(Color.LIGHT_GRAY);
		scrollPaneSell.setViewportView(tableSell);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);

// create tabbedPane tab 'Umbuchen'
		// create panel 'Umbuchen'
		JPanel panelRebooking = new JPanel();
		tabbedPane.addTab("Umbuchen", new ImageIcon(GUI.class.getResource("/gui/images/RebookingIcon.png")),
				panelRebooking, null);
		panelRebooking.setLayout(new BorderLayout(0, 0));
		tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);

		JPanel panelRebookingInput = new JPanel();
		panelRebookingInput.setBackground(Color.LIGHT_GRAY);
		panelRebookingInput.setPreferredSize(new Dimension(300, 100));
		panelRebooking.add(panelRebookingInput, BorderLayout.NORTH);

// create swing components				
		JSplitPane splitPaneOutput = new JSplitPane();

		JScrollPane scrollPanelRebookingBottum = new JScrollPane();

		JScrollPane scrollPanelRebookingTop = new JScrollPane();

		JLabel lblRebookingFrom = new JLabel("Standort von:");

		JLabel lblRebookingTo = new JLabel("Standort nach:");

		JLabel lblRebookingProduct = new JLabel("Produkt:");

		JLabel lblRebookingAmount = new JLabel("Menge:");

		JLabel lblRebookingUnit = new JLabel("/Kästen");

		tableRebookingTop = new JTable();

		tableRebookingBottum = new JTable();

		tfRebookingImputAmount = new JTextField();
		tfRebookingImputAmount.setColumns(10);

		JComboBox cbRebookingProduct = new JComboBox();

		JComboBox cbRebookingDestinationFrom = new JComboBox();

		JComboBox cbRebookingDestinationTo = new JComboBox();

		JButton btnRebookingBooking = new JButton("Buchen");

		GroupLayout gl_panelRebookingInput = new GroupLayout(panelRebookingInput);

		/*
		 * - fill cbRebookingProduct with products from selected location - set table
		 * with data - set border with locationname in it - render table and set column
		 * color
		 */
		cbRebookingDestinationFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbRebookingProduct.setModel(new DefaultComboBoxModel(
						helper.getAllProductsFromLocation(cbRebookingDestinationFrom.getSelectedIndex())));
				tableRebookingTop.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRebookingDestinationFrom.getSelectedIndex()), columnHeader));

				panelRebookingTop
						.setBorder(new TitledBorder(null, (String) cbRebookingDestinationFrom.getSelectedItem(),
								TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));

				helper.renderColor(columnHeader, tableRebookingTop);
			}
		});
		cbRebookingDestinationFrom.setModel(new DefaultComboBoxModel(helper.getAllLocationNames()));

		/*
		 * - set table with data - set border with locationname in it - render table and
		 * set column color
		 */
		cbRebookingDestinationTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRebookingBottum.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRebookingDestinationTo.getSelectedIndex()), columnHeader));

				panelRebookingBottum
						.setBorder(new TitledBorder(null, (String) cbRebookingDestinationTo.getSelectedItem(),
								TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));

				helper.renderColor(columnHeader, tableRebookingBottum);
			}
		});
		cbRebookingDestinationTo.setModel(new DefaultComboBoxModel(helper.getAllLocationNames()));

		/*
		 * - moves the selected amount from one to a another location - show error
		 * message if exception was thrown - set tableTop and tableBottum with updated
		 * data - render table and set column color
		 */
		btnRebookingBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					locations.get(cbRebookingDestinationFrom.getSelectedIndex()).moveDrinks(
							locations.get(cbRebookingDestinationTo.getSelectedIndex()),
							helper.stingToDrinkType(cbRebookingDestinationFrom.getSelectedIndex(),
									cbRebookingProduct.getSelectedItem()),
							Integer.parseInt(tfRebookingImputAmount.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1, "Dialog", JOptionPane.ERROR_MESSAGE);
				}
				tableRebookingTop.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRebookingDestinationFrom.getSelectedIndex()), columnHeader));

				helper.renderColor(columnHeader, tableRebookingTop);

				tableRebookingBottum.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRebookingDestinationTo.getSelectedIndex()), columnHeader));

				helper.renderColor(columnHeader, tableRebookingBottum);
			}
		});
		btnRebookingBooking.setBackground(VERY_LIGHT_GREEN);

// create groupLayout and align swing components		
		gl_panelRebookingInput.setHorizontalGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRebookingInput.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRebookingFrom).addComponent(lblRebookingTo))
						.addGap(27)
						.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbRebookingDestinationTo, 0, 130, Short.MAX_VALUE)
								.addComponent(cbRebookingDestinationFrom, GroupLayout.PREFERRED_SIZE, 130,
										GroupLayout.PREFERRED_SIZE))
						.addGap(91)
						.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRebookingProduct).addComponent(lblRebookingAmount,
										GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfRebookingImputAmount)
								.addComponent(cbRebookingProduct, 0, 130, Short.MAX_VALUE))
						.addGap(2).addComponent(lblRebookingUnit).addGap(38)
						.addComponent(btnRebookingBooking, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(166, Short.MAX_VALUE)));
		gl_panelRebookingInput
				.setVerticalGroup(gl_panelRebookingInput.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRebookingInput.createSequentialGroup().addGap(19)
								.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRebookingFrom)
										.addComponent(cbRebookingDestinationFrom, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRebookingProduct)
										.addComponent(cbRebookingProduct, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelRebookingInput.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRebookingTo)
										.addComponent(cbRebookingDestinationTo, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRebookingAmount)
										.addComponent(tfRebookingImputAmount, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRebookingBooking).addComponent(lblRebookingUnit))
								.addContainerGap()));
		panelRebookingInput.setLayout(gl_panelRebookingInput);
		panelRebookingInput.setLayout(gl_panelRebookingInput);

		splitPaneOutput.setResizeWeight(0.5);
		splitPaneOutput.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panelRebooking.add(splitPaneOutput, BorderLayout.CENTER);

		panelRebookingBottum.setBackground(Color.GRAY);
		panelRebookingBottum.setBorder(new TitledBorder(null, (String) cbRebookingDestinationTo.getSelectedItem(),
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		splitPaneOutput.setRightComponent(panelRebookingBottum);
		panelRebookingBottum.setLayout(new BorderLayout(0, 0));

		scrollPanelRebookingBottum.getViewport().setBackground(Color.LIGHT_GRAY);
		panelRebookingBottum.add(scrollPanelRebookingBottum, BorderLayout.CENTER);

		tableRebookingBottum.setBackground(Color.LIGHT_GRAY);
		scrollPanelRebookingBottum.setViewportView(tableRebookingBottum);

		panelRebookingTop.setBackground(Color.GRAY);
		panelRebookingTop.setBorder(new TitledBorder(null, (String) cbRebookingDestinationFrom.getSelectedItem(),
				TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		splitPaneOutput.setLeftComponent(panelRebookingTop);
		panelRebookingTop.setLayout(new BorderLayout(0, 0));

		scrollPanelRebookingTop.getViewport().setBackground(Color.LIGHT_GRAY);
		panelRebookingTop.add(scrollPanelRebookingTop, BorderLayout.CENTER);

		tableRebookingTop.setBackground(Color.LIGHT_GRAY);
		scrollPanelRebookingTop.setViewportView(tableRebookingTop);

// create tabbedPane tab 'Auffüllen'
		// create panel 'Auffüllen'
		JPanel panelRestockOutput = new JPanel();

		JPanel panelRestock = new JPanel();
		tabbedPane.addTab("Auffüllen", new ImageIcon(GUI.class.getResource("/gui/images/FillIcon.png")), panelRestock,
				null);
		tabbedPane.setBackgroundAt(2, Color.LIGHT_GRAY);
		panelRestock.setLayout(new BorderLayout(0, 0));

		JPanel panelRestockInput = new JPanel();
		panelRestockInput.setBackground(Color.LIGHT_GRAY);
		panelRestockInput.setPreferredSize(new Dimension(300, 100));
		panelRestock.add(panelRestockInput, BorderLayout.NORTH);

// create swing components	
		JScrollPane scrollPaneRestock = new JScrollPane();

		JLabel lblRestockDestination = new JLabel("Standort:");

		JLabel lblRestockProduct = new JLabel("Produkt:");

		JLabel lblRestockOR = new JLabel("-- oder --");

		tableRestock = new JTable();

		JComboBox cbRestockProduct = new JComboBox();

		JComboBox cbRestockDestination = new JComboBox();

		JButton btnRestock = new JButton("Auffüllen");

		JButton btnRestockFillEverything = new JButton("Alle Standorte auffüllen");

		JMenuBar menuBar = new JMenuBar();

		JMenu menuHandbook = new JMenu("");

		GroupLayout gl_panelRestockInput = new GroupLayout(panelRestockInput);

		/*
		 * - fill cbRestockProduct with all product from location - set table with data
		 * - render table and set column color
		 */
		cbRestockDestination.setModel(new DefaultComboBoxModel(helper.getAllLocationNames()));
		cbRestockDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbRestockProduct.setModel(new DefaultComboBoxModel(
						helper.getAllProductsFromLocation(cbRestockDestination.getSelectedIndex())));
				tableRestock.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRestockDestination.getSelectedIndex()), columnHeader));
				helper.renderColor(columnHeader, tableRestock);

			}
		});

		/*
		 * - restock a location with the selected product - shows am error message if
		 * exception was thrown - set table with updated data - render table and set
		 * column color
		 */
		btnRestock.setBackground(VERY_LIGHT_GREEN);
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					locations.get(cbRestockDestination.getSelectedIndex())
							.fillFromLocation(helper.stingToDrinkType(cbRestockDestination.getSelectedIndex(),
									cbRestockProduct.getSelectedItem()), locations.get(0));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1, "Dialog", JOptionPane.ERROR_MESSAGE);
				}
				tableRestock.setModel(new DefaultTableModel(
						helper.getSortimentOfLocation(cbRestockDestination.getSelectedIndex()), columnHeader));

				helper.renderColor(columnHeader, tableRestock);

			}
		});

		/*
		 * - fills all location automatically
		 */
		btnRestockFillEverything.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.autoFill();
			}
		});
		btnRestockFillEverything.setBackground(VERY_LIGHT_GREEN);

// fill groupLayout with swing components 		
		gl_panelRestockInput.setHorizontalGroup(gl_panelRestockInput.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRestockInput.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelRestockInput.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRestockDestination).addComponent(lblRestockProduct))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelRestockInput.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbRestockProduct, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbRestockDestination, 0, 130, Short.MAX_VALUE))
						.addGap(18)
						.addComponent(btnRestock, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblRestockOR, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRestockFillEverything)
						.addContainerGap(372, Short.MAX_VALUE)));
		gl_panelRestockInput
				.setVerticalGroup(
						gl_panelRestockInput.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRestockInput.createSequentialGroup().addGap(16)
										.addGroup(gl_panelRestockInput.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblRestockDestination)
												.addComponent(cbRestockDestination, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_panelRestockInput.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblRestockProduct)
												.addComponent(cbRestockProduct, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRestock).addComponent(lblRestockOR)
												.addComponent(btnRestockFillEverything))
										.addContainerGap(15, Short.MAX_VALUE)));
		panelRestockInput.setLayout(gl_panelRestockInput);

		panelRestockOutput.setBackground(Color.GRAY);
		panelRestockOutput.setBorder(new TitledBorder(null, "Ausgew\u00E4hlter Standort", TitledBorder.LEFT,
				TitledBorder.TOP, null, Color.WHITE));
		panelRestock.add(panelRestockOutput, BorderLayout.CENTER);
		panelRestockOutput.setLayout(new BorderLayout(0, 0));

		scrollPaneRestock.setBackground(Color.LIGHT_GRAY);
		scrollPaneRestock.getViewport().setBackground(Color.LIGHT_GRAY);
		panelRestockOutput.add(scrollPaneRestock, BorderLayout.CENTER);

		tableRestock.setBackground(Color.LIGHT_GRAY);
		scrollPaneRestock.setViewportView(tableRestock);

		menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frmGetrnkehandel.setJMenuBar(menuBar);

// runs HandbookFrame.java if menu is pressed		
		menuHandbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HandbookFrame frame = new HandbookFrame();
							frame.frmIntroduction.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		menuHandbook.setIcon(new ImageIcon(GUI.class.getResource("/gui/images/question-mark-symbol.png")));
		menuHandbook.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(menuHandbook);

	}
}
