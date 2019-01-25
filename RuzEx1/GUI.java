import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI {

	protected JButton[][] lights_grid = new JButton[5][5];
	protected String[][] game = new String[5][5];
	protected String[][] current_state = new String[5][5];

	public GUI() {

		JFrame frame = new JFrame("Lights Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();
		JPanel game_grid = new JPanel();
		game_grid.setLayout(new GridLayout(5,5));

		// System.out.println("di nagpapakita ui mumsh");

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
						if (upper >= 0 && lower >= 0 && right >= 0 && left >= 0) {
							/*
								Area looks like this:
									f
								  f f f
								    f

							*/
							lights_grid[upper][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);

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
							// System.out.println("here");
							lights_grid[final_row][left].setBackground(Color.WHITE);
							lights_grid[lower][final_col].setBackground(Color.WHITE);
							lights_grid[final_row][right].setBackground(Color.WHITE);
							lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (lower >= 4) {
							// bugs in these next three conditions

							// System.out.println("now here");
							// lights_grid[upper][final_col].setBackground(Color.WHITE);
							// lights_grid[final_row][left].setBackground(Color.WHITE);
							// lights_grid[final_row][right].setBackground(Color.WHITE);
							// lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (right == 5) {
							// System.out.println("then here");
							// lights_grid[upper][final_col].setBackground(Color.WHITE);
							// lights_grid[final_row][left].setBackground(Color.WHITE);
							// lights_grid[lower][final_col].setBackground(Color.WHITE);
							// lights_grid[final_row][final_col].setBackground(Color.WHITE);
						} else if (left > 0) {
							// System.out.println("and here");
							// lights_grid[upper][final_col].setBackground(Color.WHITE);
							// lights_grid[lower][final_col].setBackground(Color.WHITE);
							// lights_grid[final_row][right].setBackground(Color.WHITE);
							// lights_grid[final_row][final_col].setBackground(Color.WHITE);
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