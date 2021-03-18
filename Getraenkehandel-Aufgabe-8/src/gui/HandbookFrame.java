package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * Creates a handbook for the GUI
 * 
 * @author Jan Bauer
 * @author Jonas Kroker
 *
 */
public class HandbookFrame extends JFrame {

	private JPanel contentPane;
	public JFrame frmIntroduction;

	public HandbookFrame() {

// create frame 

		frmIntroduction = new JFrame("Handbuch/Help");
		frmIntroduction.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(HandbookFrame.class.getResource("/gui/images/saftladen-green.png")));
		frmIntroduction.setResizable(true);
		frmIntroduction.setBounds(100, 100, 700, 775);
		frmIntroduction.setVisible(true);
		frmIntroduction.setAlwaysOnTop(true);

// create swing components		

		JEditorPane jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(jEditorPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

// create HTMLEditorKit

		HTMLEditorKit kit = new HTMLEditorKit();
		jEditorPane.setEditorKit(kit);

// CSS		

		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule(
				"body {color:#000; font-family:arial; margin-left: 10px; margin-right: 10px; margin-bottum: 10px; background-color : #C0C0C0");
		styleSheet.addRule("h1 {color: #404040; font-family: Segoe UI}");
		styleSheet.addRule("h2 {color: red; font-family: Corbel; font-size: 15px}");
		styleSheet.addRule("p {text-align: justify;}");

// create string with HTML inside		

		String htmlString = "<html>\n" + "<body>\n" + "<h1>Handbuch des Saftladen-Managers</h1>\n"
				+ "<h2>Bitte keine Negativzahlen oder Buchstaben in die Eingabefelder eingeben. Es wird eine Fehlermeldung angezeigt.</h2>"
				+ "<p>Zum Programmstart sind alle Lagerbstände im Zentrallager vorhanden.</p>\n"
				+ "<p><font color=red>Rot</font> makierte Zeilen zeigen einen leeren Lagerbestand.</p>\n"
				+ "<p><font color=#cce907>Gelb</font> markierte Zeilen zeigen einen Lagerbestand kleiner 20%.</p>\n"
				+ "<p><font color=green>Grün</font> markierte Zeilen zeigen einen Lagerbestand größer 20%.</p>\n"
				+ "<p></p>" + "<h3>Reiter 'Verkaufen':</h3>\n"
				+ "<p>Über das Drop-Down Menü wählen Sie den Standort aus. Danach wählen Sie das Produkt aus. Über das Eingabefeld geben Sie die zu verkaufenden Flaschen ein. Zum Schluss betätigen Sie mit dem Button 'Verkaufen'. Die Lagerbestände werden automatisch aktualisiert. Mit dem Button 'Details anzeigen' können die Produkteigenschaften angezeigt werden.</p>\n"
				+ "<p></p>" + "<h3>Reiter 'Umbuchen':</h3>\n"
				+ "<p>Mit dieser Funktion können Sie Lagerbestände zwischen den Standorten umbuchen. Zuerst wählen Sie bei 'Standort von' über das Drop Down Menü den Standort aus, von welchem die Lagerbestände umgebucht werden sollen. Danach bei 'Standort nach' den Zielstandort. Nun noch das Produkt auswählen. Die Bestände werden jeweils in Kästen, nicht in Flaschen umgebucht. Zum Schluss noch den 'Buchen' Button betätigen.</p>\n"
				+ "<p></p>" + "<h3>Reiter 'Auffüllen':</h3>\n"
				+ "<p>Diese Funktionen füllt den jeweiligen Bestand auf entweder nach Wunsch oder gesamt auf. Hierzu wählen Sie durch das Drop-Down den aufzufüllenden Standort und das Produkt auf oder wählen den Standort aus und setzen den Haken bei 'Alles auffüllen'. Zum Schluss den Button 'Auffüllen' betätigen. </p>"
				+ "</body>\n";

// create doc filled with a HTMLEditorKit document 	

		Document doc = kit.createDefaultDocument();
		jEditorPane.setDocument(doc);
		jEditorPane.setText(htmlString);

// add to frame

		frmIntroduction.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
