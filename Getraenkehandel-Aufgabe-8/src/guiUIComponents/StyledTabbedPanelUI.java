package guiUIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class StyledTabbedPanelUI extends BasicTabbedPaneUI {

	public static final Color VERY_LIGHT_GRAY = new Color(169, 169, 169);

	public void installUI(JComponent c) {
		super.installUI(c);

		JTabbedPane tabbed = (JTabbedPane) c;
		tabbed.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		tabbed.setOpaque(false);

	}

	@Override
	public void paint(Graphics g, JComponent c) {
		JTabbedPane b = (JTabbedPane) c;
		paintBackground(g, b, 2);
		super.paint(g, c);
	}

	private void paintBackground(Graphics g, JComponent c, int yOffset) {
		Dimension size = c.getSize();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(c.getBackground().darker());
		g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
		g.setColor(UIManager.getColor("Panel.background"));
		g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
		c.setBackground(VERY_LIGHT_GRAY);
	}
}
