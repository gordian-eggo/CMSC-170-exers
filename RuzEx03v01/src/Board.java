import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Board {

	protected File maze_layout;
	protected File current_state = new File("current_state.txt");
	protected GUI graphics = new GUI();
	protected String[][] floor_plan;
	protected ArrayList<State> open_list = new ArrayList<State>();
	protected ArrayList<State> closed_list = new ArrayList<State>();
	protected int rows;
	protected int cols;
	private boolean win;

	public Board() {
		
		try {

			Scanner row_scan = new Scanner(graphics.game_layout);
			Scanner col_scan = new Scanner(graphics.game_layout)/*.useDelimiter("[|\n]")*/;
			Scanner scan_map = new Scanner(graphics.game_layout);

			rows = get_rows(row_scan);
			cols = get_cols(col_scan);

			initialize_game_layout(rows, cols);

			read_into_array(scan_map, rows, cols);	

			System.out.println("rows: " + rows + ", cols: " + cols);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	public int get_rows(Scanner sc) {

		int r = 0;

		while (sc.hasNext()) {
			String line = sc.nextLine();
			if (line != null) {
				r++;
			}
		}

		return r;

	}

	public int get_cols(Scanner sc) {

		int c = 0;
		String line = sc.nextLine();

		line = line.replaceAll("\\s+", "");
		c = line.length();

		return c;

	}

	public void initialize_game_layout(int r, int c) {

		graphics.game = new String[r][c];

	}

	public void read_into_array(Scanner sc, int r, int c) {

		while(sc.hasNext()) {
			for (int row = 0; row < r; row++) {
				for (int col = 0; col < c; col++) {
					graphics.game[row][col] = sc.next();
				}
			}
		}

		for (int row = 0; row < r; row++) {
			for (int col = 0; col < c; col++) {
				System.out.print(graphics.game[row][col]);
			}
			System.out.println();
		}

	}

	public void initiate_open_list() {}



}