package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The frame that holds the graphical view of the universe
 * @author Adel Hassan
 *
 */
public class MainFrame extends JFrame {
	public static final String version = "v0.1.1";
	private UniverseView universeView;
	private ObjectSelector objectSelector;
	/**
	 * Creates the main frame of the GUI for the program.
	 */
	public MainFrame() {
		// initialize and configure frame
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		this.setTitle("Fizix Engine " + version);
		setLocationRelativeTo(null);
		setLayout(null);
		Container c = getContentPane();
		
		// begin formatting contents
		universeView = new UniverseView();
		universeView.setBounds(10, 20 + (getHeight()-70)/5, getWidth()-30, 4*(getHeight()-70)/5-10);
		c.add(universeView);
		
		objectSelector = new ObjectSelector();
		objectSelector.setBounds(10, 10, getWidth()-30, (getHeight()-70)/5);
		c.add(objectSelector);
		
		// add resize listener to update size of components
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				universeView.setBounds(10, 20 + (getHeight()-70)/5, getWidth()-30, 4*(getHeight()-70)/5-10);
				objectSelector.setBounds(10, 10, getWidth()-30, (getHeight()-70)/5);
			}
		});
	}
}
