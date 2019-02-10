import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Board {

	protected GUI graphics = new GUI();
	protected ArrayList<State> open_list = new ArrayList<State>();
	protected ArrayList<State> closed_list = new ArrayList<State>();
	protected Coordinate goal_square;
	protected int rows;
	protected int cols;
	private boolean win;

	// getters
	public GUI get_graphics() { return this.graphics; }
	public ArrayList<State> get_open_list() { return this.open_list; }
	public ArrayList<State> get_closed_list() { return this.closed_list; }
	public Coordinate get_goal_square() { return this.goal_square; }
	public int get_rows() { return this.rows; }
	public int get_cols() { return this.cols; }
	public boolean get_win() { return this.win; }

	// setters
	public void set_goal_square(Scanner sc, int r, int c) {					// iterate through the map to find the goal tile

		while(sc.hasNext()) {
			for (int row = 0; row < r; row++) {
				for (int col = 0; col < c; col++) {
					if (graphics.game[row][col].equals("G")) {
						this.goal_square = new Coordinate(row, col);
						break;												// break out of loop once the goal tile is found
					}
				}
			}
		}

	}

	public int set_rows(Scanner sc) {

		int r = 0;

		while (sc.hasNext()) {							// use the nextLine() method to find the number of rows in the .in file
			String line = sc.nextLine();
			if (line != null) {
				r++;
			}
		}

		return r;

	}

	public int set_cols(Scanner sc) {

		int c = 0;
		String line = sc.nextLine();					// scan the first line into a string

		line = line.replaceAll("\\s+", "");				// delete all the whitespaces using regex
		c = line.length();								// get the length of the new string to get the number of columns

		return c;

	}

	public void set_win(State s) {						// determine if the current tile is the goal tile

		if (s.is_goal == true && s.player_location == this.goal_square) {
			this.win = true;
		}

	}

	public void set_open_list() {}

	public Board() {
		
		try {

			Scanner row_scan = new Scanner(graphics.game_layout);		// scanners for rows, columns, and the final chosen map
			Scanner col_scan = new Scanner(graphics.game_layout);
			Scanner scan_map = new Scanner(graphics.game_layout);

			rows = set_rows(row_scan);
			cols = set_cols(col_scan);

			initialize_game_layout(rows, cols);

			read_into_array(scan_map, rows, cols);	

			System.out.println("rows: " + rows + ", cols: " + cols);

			save_curr_state(graphics, graphics.game);

			// test if state is being initialized properly
			State first = new State(this.rows, this.cols, graphics, 
							this.goal_square, scan_map);
			
		} catch (Exception e) {
			System.out.println("Error in Board(): " + e);
		}

	}

	public void initialize_game_layout(int r, int c) {	// initialize an empty 2d String array with the defined row and column values

		graphics.game = new String[r][c];

	}

	public void read_into_array(Scanner sc, int r, int c) {		// read the map into the array

		while(sc.hasNext()) {
			for (int row = 0; row < r; row++) {
				for (int col = 0; col < c; col++) {
					graphics.game[row][col] = sc.next();
				}
			}
		}

	}

	public void save_curr_state(GUI g, String[][] arr) {		// write the current state into current_state.txt

		try {

			FileWriter write_file = new FileWriter(g.current_state);
			PrintWriter print_to_file = new PrintWriter(write_file);

			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					print_to_file.print(g.game[i][j] + " ");
				}
				print_to_file.print("\n");
			}

			print_to_file.close();

		} catch (Exception e) {
			System.out.println("Error in save_current_state(): " + e);
		}

	}

}