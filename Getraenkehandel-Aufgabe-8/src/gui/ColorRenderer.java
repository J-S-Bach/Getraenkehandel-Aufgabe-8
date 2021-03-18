package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.table.*;

/**
 * The class {@link ColorRenderer} render a table and change the color of rows
 * 
 * @author Jan Bauer
 *
 */
class ColorRenderer extends JLabel implements TableCellRenderer {

	private Object[] columnName;
	private double twentyPercent = 0.2;

	/**
	 * Own color VERY_LIGHT_RED
	 */
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(144, 238, 144);

	/**
	 * Constructor
	 * 
	 * @param namesOfColumn Takes the column to check
	 */
	public ColorRenderer(Object[] namesOfColumn) {
		this.columnName = namesOfColumn;
		setOpaque(true);
	}

	/**
	 * This component checks if a columnValue equals a certain condition
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		ArrayList<Object> listOfColumnNames = new ArrayList<Object>();

		for (int i = 0; i < columnName.length; i++) {
			for (Object element : columnName) {
				listOfColumnNames.add(table.getValueAt(row, table.getColumnModel().getColumnIndex(element)));
			}
		}

		// gets 20 percent of capacity
		Double twentyPercentOfCapacity = Math
				.ceil(Double.parseDouble((String) listOfColumnNames.get(4)) * twentyPercent);

		int lowerOrHigherTwentyPercent = twentyPercentOfCapacity
				.compareTo(Double.parseDouble((String) listOfColumnNames.get(3)));

		if (value != null)
			setText(value.toString());
		if (isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
			if (listOfColumnNames.get(3).equals("0"))
				setBackground(VERY_LIGHT_RED);
			if (!listOfColumnNames.get(3).equals("0"))
				setBackground(VERY_LIGHT_GREEN);
			if (lowerOrHigherTwentyPercent == 1 && !listOfColumnNames.get(3).equals("0")) {
				setBackground(Color.YELLOW);
			}
		}
		return this;
	}

}
