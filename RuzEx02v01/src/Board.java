import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;

public class Board {
	
	protected File puzzle;
	protected GUI game_UI = new GUI();
	protected ArrayList<State> move_set = new ArrayList<State>();
	private boolean win;
	
	public Board() {

		puzzle = new File("puzzle2.in");

		try {

			Scanner initial_state = new Scanner(puzzle);

			read_into_array(initial_state);
			set_grid(game_UI);

		} catch (Exception e) {
			System.out.println("reeeeeeeeeeee");
		}

	}

	public void read_into_array(Scanner sc) { // read contents of text file with initial state into layout array

		while(sc.hasNext()) {
			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					game_UI.game[row][col] = sc.next();
				}
			}
		}

	}

	public void set_grid(GUI ui) {

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (game_UI.game[row][col].equals("f")) {
					game_UI.lights_grid[row][col].setBackground(Color.WHITE);
				} else if (game_UI.game[row][col].equals("n")) {
					game_UI.lights_grid[row][col].setBackground(Color.YELLOW);
				}
			}
		}

	}

}

/* 
	REFERENCES:

		https://stackoverflow.com/questions/36779354/java-creating-a-grid-of-buttons-using-a-2d-array
		https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
		https://www.guru99.com/java-swing-gui.html
		https://coderanch.com/t/663479/java/Setting-size-JButton
		https://www.javatpoint.com/java-actionlistener

*/