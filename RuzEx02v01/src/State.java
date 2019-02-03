import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class State {

	protected String[][] current_board = new String[5][5];
	protected State parent;
	protected ClickedButton coordinates;
	protected int cost;
	protected boolean is_level_2_state;


	// getters
	public int get_cost() { return this.cost; }
	public boolean get_is_level_2_state() { return this.is_level_2_state; }
	public ClickedButton get_coordinates() {return this.coordinates; }
	public String[][] get_current_board() { return this.current_board; }
	public State get_parent() { return this.parent; }

	// setters
	public void set_cost(int c) { this.cost = c; }
	public void set_is_level_2_state(boolean b) { this.is_level_2_state = b; }
	public void set_coordinates(int x, int y) { this.coordinates = new ClickedButton(x,y); }
	public void set_parent(State p) { this.parent = p; }
	public void set_current_board(GUI g) {

		try {

			Scanner s = new Scanner(g.current_state);

			while(s.hasNext()) {							
				for (int row = 0; row < 5; row++) {
					for (int col = 0; col < 5; col++) {
						this.current_board[row][col] = s.next();
					}
				}
			}

		} catch (Exception e) {}

	}

}