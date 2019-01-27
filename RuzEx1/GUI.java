import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
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

							save_curr_state(game);

						} else if (upper < 0 && left < 0) {
							/*
								Area looks like this:
									f f
									f
							*/

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

							save_curr_state(game);

						} else if (upper < 0 && right == 5) {
							/*
								Area looks like this:
									f f
									  f
							*/
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

							if (game[final_row][final_col].equals("f")) {
								lights_grid[final_row][final_col].setBackground(Color.YELLOW);
								game[final_row][final_col] = "n";
							} else if (game[final_row][final_col].equals("n")) {
								lights_grid[final_row][final_col].setBackground(Color.WHITE);
								game[final_row][final_col] = "f";
							}

							save_curr_state(game);

						} else if (lower == 5 && left < 0) {
							/*
								Area looks like this:
									f
									f f
							*/
							if (game[upper][final_col].equals("f")) {
								lights_grid[upper][final_col].setBackground(Color.YELLOW);
								game[upper][final_col] = "n";
							} else if (game[upper][final_col].equals("n")) {
								lights_grid[upper][final_col].setBackground(Color.WHITE);
								game[upper][final_col] = "f";
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

							save_curr_state(game);

						} else if (lower == 5 && right == 5) {
							/*
								Area looks like this:
									  f
									f f
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

							if (game[final_row][final_col].equals("f")) {
								lights_grid[final_row][final_col].setBackground(Color.YELLOW);
								game[final_row][final_col] = "n";
							} else if (game[final_row][final_col].equals("n")) {
								lights_grid[final_row][final_col].setBackground(Color.WHITE);
								game[final_row][final_col] = "f";
							}

							save_curr_state(game);

						} else if (upper < 0) {
							/*
								Area looks like this:
									f f f
									  f
							*/

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

							save_curr_state(game);

						} else if (lower == 5) {
							/*
								Area looks like this:
									  f 
									f f f
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

							save_curr_state(game);

						} else if (right == 5) {
							/*
								Area looks like this:
									  f  
									f f
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

							if (game[final_row][final_col].equals("f")) {
								lights_grid[final_row][final_col].setBackground(Color.YELLOW);
								game[final_row][final_col] = "n";
							} else if (game[final_row][final_col].equals("n")) {
								lights_grid[final_row][final_col].setBackground(Color.WHITE);
								game[final_row][final_col] = "f";
							}

							save_curr_state(game);

						} else if (left < 0) {
							/*
								Area looks like this:
									f  
									f f
									f
							*/

							if (game[upper][final_col].equals("f")) {
								lights_grid[upper][final_col].setBackground(Color.YELLOW);
								game[upper][final_col] = "n";
							} else if (game[upper][final_col].equals("n")) {
								lights_grid[upper][final_col].setBackground(Color.WHITE);
								game[upper][final_col] = "f";
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

							save_curr_state(game);

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

	public void save_curr_state(String[][] arr) {

		try {

			FileWriter write_file = new FileWriter(current_state);
			PrintWriter print_to_file = new PrintWriter(write_file);

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					print_to_file.print(game[i][j] + " ");
				}
				print_to_file.print("\n");
			}

			print_to_file.close();

		} catch (Exception e) {

		} finally {

		}


	}

}