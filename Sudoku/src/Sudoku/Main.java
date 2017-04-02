package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {

	private static Sudoku sudoku;

	public static void main(String args[]) {
		// the following will open up a dialog to choose a difficulty
		Object[] possibleDifficultyValues = { "Easy", "Medium", "Hard" };
		Object selectedDifficultyValue = JOptionPane.showInputDialog(null, "Select a difficulty", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, possibleDifficultyValues, possibleDifficultyValues[0]);
		
		// the following will open up a dialog to choose a difficulty
		Object[] possibleValues = { "Recursive", "Constraint" };
		Object selectedValue = JOptionPane.showInputDialog(null, "Select a solver", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		String difficulty;

		// determines which option was selected
		if (selectedDifficultyValue == "Easy") {
			difficulty = "puzzles/easy.txt";
		} else if (selectedDifficultyValue == "Medium") {
			difficulty = "puzzles/medium.txt";
		} else {
			difficulty = "puzzles/hard.txt";
		}

		// tries to open the given file and throws an exception if files not found
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(difficulty));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			sudoku = new Sudoku(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (selectedValue == "Recursive") {
			// tries to solve the puzzle recursively
			if (sudoku.solve_sudoku(0) == true) {
				System.out.println("\n" + "The recursive sover solved it in " + sudoku.get_guess_count() + " tries.");
				System.out.println("Solved puzzle: " + "\n" + sudoku.toString());
			} else {
				System.out.println("The puzzle could not be solved using the recursive solver.");
			}
		}
		
		if (selectedValue == "Constraint") {
			sudoku.solve_by_elimination();
		}
	}

}
