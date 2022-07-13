import java.awt.Color;

import javax.swing.*;

public class GalagaFrame extends JFrame {
	protected JPanel pnl;
	
	GalagaFrame(String title){
		super(title);
		this.setSize(600, 600);
		this.setLocation(300, 100);
		GalagaPanel galPanel = new GalagaPanel();
		pnl = galPanel.getPanel();
		this.add(pnl);
	}
}
