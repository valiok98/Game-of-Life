import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener{
	
		private int i;
		private int j;
		
		
		public ButtonActionListener(Game game, int i, int j) {
			this.i = i;
			this.j = j;
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {		// Marking the cells, from dead to alive and vice versa.
			if(Game.button[i][j].getBackground() == Game.black)
				Game.button[i][j].setBackground(Game.white);
			else
				Game.button[i][j].setBackground(Game.black);
		}
	
	
}
