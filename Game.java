import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Color white = Color.white;
	public static final Color black = Color.black;

	public static JButton button[][];
	public static JPanel panel[][];

	private int X;
	private int Y;

	private JButton start = new JButton("Start!");
	private static volatile boolean allowed = false;

	public Game(String title, int mode, int X, int Y) {

		init(title, mode, X, Y);
	}

	private void init(String title, int mode, int X, int Y) {

		this.X = X;
		this.Y = Y;

		Game This = this;

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.allowed = true;
				This.setVisible(false);
			}

		});

		if (mode == 1) {
			genInput(title);
		} else if (mode == 2) {
			genSimulation(title);
		}

	}

	private void genInput(String title) {

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				button[i][j] = new JButton();
				button[i][j].setBackground(white);
				button[i][j].setBorder(BorderFactory.createLineBorder(black));
				this.add(button[i][j]);

			}
		}
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				button[i][j].addActionListener(new ButtonActionListener(this, i, j));
			}
		}

		this.setTitle(title); // Setting the title of board
		this.add(start); // Adding the start button
		this.setLayout(new GridLayout(X + 1, Y)); // Setting the layout
		this.setSize(X * 80, Y * 80); // Size of the chess board
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	private void genSimulation(String title) {

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				// Just to be consistent with the other for cycles
				panel[i][j] = new JPanel();
				panel[i][j].setBackground(button[i][j].getBackground()); // Getting the color - Black = true, White =
																			// false
				panel[i][j].setBorder(BorderFactory.createLineBorder(black)); // Creating the black border
				this.add(panel[i][j]);
			}
		}

		this.setTitle(title); // Setting the title of board
		this.setLayout(new GridLayout(X, Y)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(X * 80, Y * 80); // Size of the chess board
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		while (true)	// Infinite loop for the simulation.
			getAlgorithm();
	}

	private void getAlgorithm() {

		boolean temp_matrix[][] = new boolean[X][Y];

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				temp_matrix[i][j] = (panel[i][j].getBackground() == black) ? true : false;
			}
		}

		boolean temp_copy[][] = new boolean[X][Y]; // Making a copy of the matrix, because if we change the first one we make mistakes .
												   // Changing the matrix while checking it is a false approach, we can kill/revive cells falsely.
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				temp_copy[i][j] = temp_matrix[i][j];
			}
		}

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				int num_neigbors = neighbors(temp_matrix, i, j); // Getting the number of neighbors(min: 0, max:8)
				if (temp_matrix[i][j]) {

					if (num_neigbors < 2 || num_neigbors > 3) {
						temp_copy[i][j] = !temp_copy[i][j];
					}
				} else {
					if (num_neigbors == 3)
						temp_copy[i][j] = !temp_copy[i][j];
				}
			}
		}

		try {
			Thread.sleep(100); // Putting the thread to sleep.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				panel[i][j].setBackground(temp_copy[i][j] ? black : white); // Updating the panel, alive cells are black, dead are white.
			}
		}
	}

	private int neighbors(boolean[][] temp, int i, int j) {
		int count = 0;

		if (temp[(i + 1) % X][(j + 1) % Y])
			count++;
		if (temp[(i + 1) % X][(j) % Y])
			count++;
		if (temp[(i) % X][(j + 1) % Y])
			count++;
		if (temp[(i - 1) < 0 ? X - 1 : i - 1][(j - 1) < 0 ? Y - 1 : j - 1])
			count++;
		if (temp[(i - 1) < 0 ? X - 1 : i - 1][j])
			count++;
		if (temp[i][(j - 1) < 0 ? Y - 1 : j - 1])
			count++;
		if (temp[(i + 1) % X][(j - 1) < 0 ? Y - 1 : j - 1])
			count++;
		if (temp[(i - 1) < 0 ? X - 1 : i - 1][(j + 1) % Y])
			count++;

		return count;
	}

	public static void main(String[] args) {

		String title = "My Chess Board";

		OptionPane pane = new OptionPane(title);

		Game.button = new JButton[pane.getX()][pane.getY()];
		Game.panel = new JPanel[pane.getX()][pane.getY()];

		Game input = new Game("Select the alive/dead cells", 1, pane.getX(), pane.getY());
		Game game;

		while (true) {
			if (Game.allowed) {
				game = new Game("Let's see the simulation!", 2, pane.getX(), pane.getY()); // Creating the
				break;
			}
		}
	}

}
