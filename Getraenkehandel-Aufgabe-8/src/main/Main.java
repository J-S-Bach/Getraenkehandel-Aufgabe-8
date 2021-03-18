package main;

import inheritance.Location;
import inheritance.OrangeJuice;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.GUI;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmGetrnkehandel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
