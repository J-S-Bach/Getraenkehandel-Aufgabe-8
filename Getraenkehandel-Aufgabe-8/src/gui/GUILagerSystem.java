package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GUILagerSystem {

	private JFrame frame;
	private JButton btnRebooking;
	private JPanel panelStartpage;
	private JButton btnSale;
	private JPanel panelRebooking;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		// Startpage--------------------------------------
		JPanel panelStartpage = new JPanel();
		frame.getContentPane().add(panelStartpage, "name_615469137820100");
		panelStartpage.setLayout(null);
		panelStartpage.setVisible(true);

		JPanel panelRebooking = new JPanel();
		frame.getContentPane().add(panelRebooking, "name_615480645851900");
		panelRebooking.setLayout(null);
		panelRebooking.setVisible(false);

		JPanel panelSale = new JPanel();
		frame.getContentPane().add(panelSale, "name_615482751312600");
		panelSale.setLayout(null);
		panelSale.setVisible(false);

		// Rebooking--------------------------

		JButton btnRebooking = new JButton("Rebooking");
		btnRebooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartpage.setVisible(false);
				panelRebooking.setVisible(true);
			}
		});
		btnRebooking.setBounds(171, 219, 117, 81);
		panelStartpage.add(btnRebooking);

		JButton btnRebookingBackToStart = new JButton("Back to startpage");
		btnRebookingBackToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRebooking.setVisible(false);
				panelStartpage.setVisible(true);
			}
		});
		btnRebookingBackToStart.setBounds(10, 568, 136, 32);
		panelRebooking.add(btnRebookingBackToStart);

		// Sale--------------------------------

		JButton btnSaleBackToStart = new JButton("Back to startpage");
		btnSaleBackToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRebooking.setVisible(false);
				panelStartpage.setVisible(true);
			}
		});
		btnSaleBackToStart.setBounds(10, 568, 136, 32);
		panelSale.add(btnSaleBackToStart);

		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartpage.setVisible(false);
				panelSale.setVisible(true);
			}
		});
		btnSale.setBounds(546, 219, 117, 81);
		panelStartpage.add(btnSale);

	}
}
