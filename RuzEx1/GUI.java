import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI {

	protected JButton[][] lights_grid = new JButton[5][5];
	protected String[][] game = new String[5][5];
	protected File current_state = new File("current_state.txt");

	public GUI() {

		JFrame frame = new JFrame("Lights Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();
		JPanel game_grid = new JPanel();
		game_grid.setLayout(new GridLayout(5,5));

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {

				int upper = row - 1;
				int lower = row + 1;
				int left = col - 1;
				int right = col + 1;
				int final_col = col;
				int final_row = row;

				lights_grid[row][col] = new JButton();
				lights_grid[row][col].setPreferredSize(new Dimension(100,100));
				lights_grid[row][col].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						// System.out.println("button click works");
						if ((upper >= 0 && upper < 5) && (lower >= 0 && lower < 5) && (right >= 0 && right < 5) && (left >= 0 && left < 5)) {
							/*
								Area looks like this:
									f
								  f f f
								    f

							*/

							if (game[upper][final_col].equals("f")) {
								lights_grid[upper][final_col].setBackground(Color.YELLOW);
								game[upper][final_col] = "n";
							} else if (game[upper][final_col].equals("n")) {
								lights_grid[upper][final_col].setBackground(Color.WHITE);
								game[upper][final_col] = "f";
							}

							if (game[final_row][left].equals("f")) {
								lights_grid[final_row][left].setBackground(Color.YELLOW);
								game[final_row][left] = "n";
							} else if (game[final_row][left].equals("n")) {
								lights_grid[final_row][left].setBackground(Color.WHITE);
								game[final_row][left] = "f";
							}

							if (game[lower][final_col].equals("f")) {
								lights_grid[lower][final_col].setBackground(Color.YELLOW);
								game[lower][final_col] = "n";
							} else if (game[lower][final_col].equals("n")) {
								lights_grid[lower][final_col].setBackground(Color.WHITE);
								game[lower][final_col] = "f";
							}

							if (game[final_row][right].equals("f")) {
								lights_grid[final_row][right].setBackground(Color.YELLOW);
								game[final_row][right] = "n";
							} else if (game[final_row][right].equals("n")) {
								lights_grid[final_row][right].setBackground(Color.WHITE);
								game[final_row][right] = "f";
							}

							if (game[final_row][final_col].equals("f")) {
								lights_grid[final_row][final_col].setBackground(Color.YELLOW);
								game[final_row][final_col] = "n";
							} else if (game[final_row][final_col].equals("n")) {
								lights_grid[final_row][final_col].setBackground(Color.WHITE);
								game[final_row][final_col] = "f";
							}


						} else if (upper < 0 && left < 0) {
							/*
								Area looks like this:
									f f
									f
							*/
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (upper < 0 && right == 5) {
							/*
								Area looks like this:
									f f
									  f
							*/
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (lower == 5 && left < 0) {
							/*
								Area looks like this:
									f
									f f
							*/
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (lower == 5 && right == 5) {
							/*
								Area looks like this:
									  f
									f f
							*/
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (upper < 0) {
							/*
								Area looks like this:
									f f f
									  f
							*/
							// System.out.println("here");
							// System.out.println("upper = " + upper + "\nlower = " + lower + "\nleft = " + left + "\nright = " + right + "\n");
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (lower == 5) {
							/*
								Area looks like this:
									  f 
									f f f
							*/
							// System.out.println("now here");
							// System.out.println("upper = " + upper + "\nlower = " + lower + "\nleft = " + left + "\nright = " + right + "\n");
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (right == 5) {
							/*
								Area looks like this:
									  f  
									f f
									  f
							*/
							// System.out.println("then here");
							// System.out.println("upper = " + upper + "\nlower = " + lower + "\nleft = " + left + "\nright = " + right + "\n");
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (left < 0) {
							/*
								Area looks like this:
									f  
									f f
									f
							*/
							// System.out.println("and here");
							// System.out.println("upper = " + upper + "\nlower = " + lower + "\nleft = " + left + "\nright = " + right + "\n");
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						}
					}
				});

				game_grid.add(lights_grid[row][col]);
			}
		}

		container.add(game_grid);

		frame.setSize(300,300);
		frame.pack();
		frame.setVisible(true);
	}

}