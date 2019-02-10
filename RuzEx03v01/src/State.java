import java.util.Scanner;
import java.lang.Math;
import java.lang.Double;

public class State {

	protected String[][] current_board;
	protected State parent;
	protected Coordinate player_location;
	protected int move_count;					// value of g
	protected double h_value;
	protected double f_value;					// value of f
	protected boolean is_goal;

	// getters
	public String[][] get_current_board() { return this.current_board; }
	public State get_parent() { return this.parent; }
	public Coordinate get_player_location() { return this.player_location; }
	public int get_move_count() { return this.move_count; }
	public double get_h_value() { return this.h_value; }
	public boolean get_is_goal() { return this.is_goal; }


	// setters
	public void set_current_board(int r, int c, GUI g) {		// buggy

		try {

			Scanner s = new Scanner(g.current_state);

			while(s.hasNext()) {							
				for (int row = 0; row < r; row++) {
					for (int col = 0; col < c; col++) {
						System.out.println("(" + row + "," + col +")");
						current_board[row][col] = s.next();
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error in set_current_board(): " + e);
		}

	}
	public void set_parent(State s) { this.parent = s; }
	public void set_player_location(int r, int c, GUI g) {		// buggy

		try {

			Scanner s = new Scanner(g.current_state);

			while(s.hasNext()) {							
				for (int row = 0; row < r; row++) {
					for (int col = 0; col < c; col++) {
						System.out.println("(" + row + "," + col +")");
						if (this.current_board[row][col].equals("P")) {
							this.player_location = new Coordinate(row, col);
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error in set_player_location(): " + e);
		}

		System.out.println("player location: " + this.player_location);

	}
	public void set_move_count() {
		if (this.parent == null) {
			this.move_count = 0;
		} else {
			this.move_count = this.parent.move_count + 1;
		}

		System.out.println("g = " + this.move_count);
	}
	public void set_h_value(Coordinate crdnt) {

		try {
			double x_val = crdnt.x_coor - this.player_location.x_coor;
			double y_val = crdnt.y_coor - this.player_location.y_coor;
	
			this.h_value = Math.sqrt((x_val + y_val));
	
			System.out.println("h = " + this.h_value);
		} catch (Exception e) {
			System.out.println("Error in set_h_value(): " + e);
		}

	}
	public void set_f_value() {

		try {

			double g_value = new Double(this.move_count);
	
			this.f_value = g_value + this.h_value;
			System.out.println("f = " + this.f_value);
			
		} catch (Exception e) {
			System.out.println("Error in set_f_value(): " + e);
		}

	}
	public void set_is_goal(int r, int c, GUI g, Scanner sc) {

		try {

			while(sc.hasNext()) {
				for (int row = 0; row < r; row++) {
					for (int col = 0; col < c; col++) {
	
						if (g.game[row][col].equals("G") && 
							this.player_location.x_coor == row && 
							this.player_location.y_coor == col) {
							this.is_goal = true;
							break;
						}
	
					}
				}
			}

			System.out.println("goal state?: " + this.is_goal);

		} catch (Exception e) {
			System.out.println("Error in set_is_goal(): " + e);
		}

	}

	public State(int r, int c, GUI g, Coordinate cr, Scanner sc) {
		this.set_current_board(r, c, g);
		this.set_player_location(r, c, g);
		this.set_move_count();
		this.set_h_value(cr);
		this.set_f_value();
		this.set_is_goal(r, c, g, sc);
	}

}