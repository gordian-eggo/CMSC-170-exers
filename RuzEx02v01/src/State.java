import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class State {

	protected String[][] current_board = new String[5][5];
	protected String[][] parent_board  = new String[5][5];
	protected int cost;
	protected boolean is_level_2_state;


	// getters
	public int get_cost() { return this.cost; }
	public boolean get_is_level_2_state() { return this.is_level_2_state; }
	public String[][] get_current_board() { return this.current_board; }
	public String[][] get_parent_board() { return this.parent_board; }

	// setters
	public void set_cost(int c) { this.cost = c; }
	public void set_is_level_2_state(boolean b) { this.is_level_2_state = b; }
	public void set_parent_board(File f) { 
		
		try {

			Scanner s = new Scanner(f);

			while(s.hasNext()) {								// populate the temporary 
				for (int row = 0; row < 5; row++) {
					for (int col = 0; col < 5; col++) {
						this.current_board[row][col] = s.next();
					}
				}
			}

		} catch (Exception e) {}

	}
	public void set_parent_board(State s) { this.parent_board = s.current_board; }
	public void set_current_board(GUI g) {

		try {

			Scanner s = new Scanner(g.current_state);

			while(s.hasNext()) {								// populate the temporary 
				for (int row = 0; row < 5; row++) {
					for (int col = 0; col < 5; col++) {
						this.current_board[row][col] = s.next();
					}
				}
			}

		} catch (Exception e) {}

		System.out.println("Current state: ");
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				System.out.print(this.current_board[r][c] + " ");
			}
			System.out.println();
		}

	}

}