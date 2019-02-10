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

			State win_state = DFSearch();
			// win = goal_test(win_state);
			// System.out.println(win);

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

	public void generate_initial_frontier(Stack<State> state_stack) {	// generates the frontier for the DFS function 

		int cost = 1;

		for (int i = 0; i < 5 ; i++) {
			for (int j = 0; j < 5; j++) {
				
				State s = new State();
				s.set_cost(cost);
				game_UI.board_toggle(i,j);
				s.set_current_board(game_UI);
				s.set_is_level_2_state(true);
				s.set_coordinates(i,j);
				game_UI.board_toggle(i,j);

				state_stack.push(s);

			}
		}

	}

	public ArrayList<ClickedButton> actions(State curr_state) { 			// generates possible actions for any given state

		ArrayList<ClickedButton> action_set = new ArrayList<ClickedButton>();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				ClickedButton button = new ClickedButton(i,j);
				action_set.add(button);

			}
		}

		return action_set;		

	}

	public boolean goal_test(State s) {

		boolean winning = true;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				if (s.current_board[i][j].equals("n")) {
					winning = false;
				}

			}
		}

		return winning;

	}

	public State result(State s, ClickedButton c) {

		int new_cost = s.get_cost() + 1;

		game_UI.board_toggle(c.x_coor, c.y_coor);

		State res = new State();
		res.set_cost(new_cost);
		res.set_current_board(game_UI);
		res.set_is_level_2_state(false);
		res.set_parent(s);
		res.set_coordinates(c.x_coor, c.y_coor);

		System.out.println("Result:");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(new_state.current_board[i][j] + " ");
			}
			System.out.println();
		}

		game_UI.board_toggle(c.x_coor, c.y_coor);

		return res;


	}

	public State DFSearch() {

		State win_state = new State();
		State curr = new State();
		int state_count = 0;
		
		generate_initial_frontier(frontier);

		System.out.println("Generating states. Please bear with us.");
		while (!frontier.empty()) {

			state_count++;

			curr = (State)frontier.pop();
			System.out.println("State count: " + state_count);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(curr.current_board[i][j] + " ");
				}
				System.out.println();
			}

			if (state_count == 3) { break; }

			if (goal_test(curr)) {

				win_state = curr;

			} else {

				System.out.println("generating new states...");
				for (ClickedButton action : actions(curr)) {
					State new_state = new State();
					new_state = result(curr, action);

					// System.out.println("[New state] (" + new_state.coordinates.x_coor + "," + new_state.coordinates.y_coor + ") :");
					// for (int i = 0; i < 5; i++) {
					// 	for (int j = 0; j < 5; j++) {
					// 		System.out.print(new_state.current_board[i][j] + " ");
					// 	}
					// 	System.out.println();
					// }

					frontier.push(new_state);
				}

			}

		}

		return win_state;

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