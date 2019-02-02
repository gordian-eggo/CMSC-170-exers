import java.util.ArrayList;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;

public class Board {
	
	protected File puzzle;
	protected GUI game_UI = new GUI();
	protected ArrayList<State> move_set = new ArrayList<State>();		// zero-indexed
	protected Stack<State> frontier = new Stack<State>();
	private boolean win;
	
	public Board() {

		try {

			Scanner initial_state = new Scanner(game_UI.puzzle);

			read_into_array(initial_state);
			set_grid(game_UI);

			generate_initial_frontier(frontier);

		} catch (Exception e) {
			System.out.println(e);
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

	public void set_grid(GUI ui) {	// set the initial game grid based off of the chosen puzzle pattern

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

	public void generate_initial_frontier(Stack<State> state_stack) {	// generates the frontier for the BFS and DFS functions 

		int cost = 1;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				State s = new State();
				s.set_cost(cost);
				game_UI.toggle_lights(i,j);
				s.set_current_board(game_UI);
				s.set_is_level_2_state(true);
				game_UI.toggle_lights(i,j);

				state_stack.push(s);

			}
		}

		State temp = state_stack.pop();

		System.out.println(temp.cost);
		System.out.println(temp.is_level_2_state);
		System.out.println(temp.current_board[4][4]);


	}

}

/* 

	REFERENCES:

		https://stackoverflow.com/questions/36779354/java-creating-a-grid-of-buttons-using-a-2d-array
		https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
		https://www.guru99.com/java-swing-gui.html
		https://coderanch.com/t/663479/java/Setting-size-JButton
		https://www.javatpoint.com/java-actionlistener
		https://www.mkyong.com/swing/java-swing-jfilechooser-example/
		https://stackoverflow.com/questions/13516829/jfilechooser-change-default-directory-in-windows
		https://www.geeksforgeeks.org/stack-class-in-java/

*/