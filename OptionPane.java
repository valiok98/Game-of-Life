import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OptionPane {

	private JFrame frame;
	private String ansx, ansy;
	
	private int X;
	private int Y;
	
	
	public OptionPane(String name) {
		frame = new JFrame(name);
		frame.setSize(300, 300);
		frame.setLayout(null);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ansx = JOptionPane.showInputDialog(frame, "input for x");	// Getting the x input. If false we continue to seek a correct positive number.
		ansy = JOptionPane.showInputDialog(frame, "input for y");	// Same as x, but for y.
		
		int ans_x = 0, ans_y = 0;
		
		while (ansx == null || ansy == null || ans_x <= 0 || ans_y <= 0) { // Checking if the input is correct, null strings and negative input.
			try {
				ans_x = Integer.parseInt(ansx);
				ans_y = Integer.parseInt(ansy);
			} catch (Exception e) {
				
				ansx = JOptionPane.showInputDialog(frame, "please enter correct positive digit for x");
				ansy = JOptionPane.showInputDialog(frame, "please enter correct positive digit for y");
				
			}
			
		}
		
		setX(ans_x);
		setY(ans_y);
		
	}
	
	private void setX(int X) {
		this.X = X;
	}
	
	private void setY(int Y) {
		this.Y = Y;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}

}

