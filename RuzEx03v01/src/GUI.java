import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class GUI {

	// protected Board game_board;
	protected String[][] game;
	protected File current_state = new File("current_state.txt");
	protected File game_layout;

	public GUI() {

		JFrame frame = new JFrame("Maze Solver (AStar)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JFileChooser layout_picker = new JFileChooser(FileSystemView.getFileSystemView());
		layout_picker.setCurrentDirectory(current_state);
		int return_val = layout_picker.showOpenDialog(null);

		if (return_val == JFileChooser.APPROVE_OPTION) {
			game_layout = layout_picker.getSelectedFile();
		}

		frame.setSize(300,300);
		frame.pack();
		frame.setVisible(true);

	}

}