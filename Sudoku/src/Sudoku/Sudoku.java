package Sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Adrian Bollerslev,u1115021
 * @author Melvin Bosnjak,
 *
 *This class imports Sudoku puzzles through text files. The files must be in a specific format
 *This class can solve and modify and do a myriad of operations on the given Sudoku board
 */
public class Sudoku {
	//One dimensional board and a Two dimensional board
	int[] onedboard;
	int[][] twodboard;
	//the amount of guesses when solving recursively
	private int guesscount;
	//the numbers which are possibilities whilst using the constraint solver
	ArrayList<HashSet<Integer>> setPossibilities;
	//A list of sets, each containing one of the 9 (3x3) "boxes" or "blocks" of Sudoku
	private List<Set<Integer>> boxes;
	//A simple way to easily convert from the one dimensional board to the two dimensional
	//the math is fairly simple but this makes the code more readable at times
	static String[] converter;


	/**
	 * The empty constructor which initializes all of the necessary variables
	 */
	public Sudoku() {
		onedboard = new int[81];
		twodboard = new int[9][9];
		converter = new String[81];
		boxes = new ArrayList<Set<Integer>>();
		for (int i = 0; i < 9; i++) {
			boxes.add(new HashSet<Integer>());
		}
	}

	/**
	 * Create a new puzzle by reading a file
	 *
	 * the file should be 9 rows of 9 numbers separated by whitespace the
	 * numbers should be 1-9 or 0 representing an empty square
	 *
	 * Improper format (too many numbers per line, no numbers, too many total
	 * numbers, etc) should result in a runtime exception being thrown.
	 * 
	 * @throws IOException
	 *
	 */
	public Sudoku(BufferedReader reader) throws IOException {
		//initialize variables
		this();
		//System.out.println("Original puzzle: " + "\n");
		int count = 0;
		int row = 0;
		try {
			//while the reader is ready and still has more lines
			while (reader.ready()) {
				//take the current line and create a string from it
				String[] currentline = reader.readLine().trim().split(" ");
				//if the line is not an empty line
				if (currentline.length > 1) {
					//for every string
					for (String string : currentline) {
						if (string.isEmpty()) {
							//System.out.print(" ");
						} else {
							//add the value to the appropriate box
							if (Integer.parseInt(string) != 0) {
								boxes.get(checkBox(row, count - (9 * row))).add(Integer.parseInt(string));
							}
							//add the value to the one dimensional and two dimensional board
							onedboard[count] = Integer.parseInt(string);
							twodboard[row][count - (9 * row)] = Integer.parseInt(string);
							//used for easy conversions
							converter[count] = row + " " + (count - (9 * row));
							//System.out.print(Integer.parseInt(string));
							count++;
						}
					}
					//System.out.println();
					row++;
				} else {
					//System.out.println("                        ");
				}
			}
		} catch (IOException e) {
			throw e; 
		} finally {
			//close reader
			assert reader != null;
			reader.close();
		}
		//initialize possibilities
		setPossibilities = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 81; i++) {
			//if the number is not chosen give all possibilities
			if (twodboard[Integer.parseInt(converter[i].split(" ")[0])][Integer.parseInt(converter[i].split(" ")[1])] == 0) {
				HashSet<Integer> possible = new HashSet<Integer>();
				Collections.addAll(possible, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
				setPossibilities.add(possible);
			//if its already chosen add only the correct value
			} else {
				HashSet<Integer> possible = new HashSet<Integer>();
				possible.add(twodboard[Integer.parseInt(converter[i].split(" ")[0])][Integer.parseInt(converter[i].split(" ")[1])]);
				setPossibilities.add(possible);
			}
		}
	}

	/**
	 * @return a copy of the puzzle as a 1D matrix
	 */
	public int[] get_puzzle_1D() {
		return this.onedboard;
	}

	/**
	 * @return a copy of the puzzle as a 2D matrix
	 */
	public int[][] get_puzzle_2D() {
		return this.twodboard;
	}

	/**
	 * @return how many guesses it took to recursively solve the problem.
	 */
	public int get_guess_count() {
		return this.guesscount;
	}

	/**
	 * Function: valid_for_row
	 *
	 * Description: Determine if the given number can be placed in the given row
	 * without violating the rules of sudoku
	 *
	 * Inputs:
	 * 
	 * @input row : which row to see if the number can go into
	 * @input number: the number of interest
	 *
	 *        Outputs:
	 *
	 *        true if it is possible to place that number in the row without
	 *        violating the rule of 1 unique number per row.
	 *
	 *        In other words, if the given number is already present in the row,
	 *        it is not possible to place it again (return false), otherwise it
	 *        is possible to place it (return true);
	 * 
	 *        WARNING: call this function before placing the number in the
	 *        puzzle...
	 *
	 */
	protected boolean valid_for_row(int row, int number) {
		for (int col = 0; col < 9; col++) {
			if (twodboard[row][col] == number) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Function: valid_for_col (see above)
	 */
	protected boolean valid_for_column(int col, int number) {
		for (int row = 0; row < 9; row++) {
			if (twodboard[row][col] == number) {
				return false;
			}
		}
		return true;
	}
	//check the box of the cell based upon the row and column
	protected static int checkBox(int row, int col) {
		int boxc = (row / 3) * 3;
		boxc += col / 3;
		return boxc;
	}

	/**
	 * Function: valid_for_box (see above)
	 *
	 * The sudoku boxes are:
	 *
	 * 0 | 1 | 2 ---+---+--- 3 | 4 | 5 ---+---+--- 6 | 7 | 8
	 *
	 * where each box represents a 3x3 square in the game.
	 *
	 */
	protected boolean valid_for_box(int box, int number) {
		if (boxes.get(box).contains(number)) {
			return false;
		}
		return true;
	}

	/**
     *
     * Function is_valid( position, value )
     *
     * Determine if the given value is valid in the puzzle at that position.
     *
     * Inputs:
     * 
     * @param position - which bucket in the puzzle to check for validity - should be empty
     * @param possible_value - the value to check (1-9)
     * 
     * @return true if valid
     */
    protected boolean is_valid( int position, int possible_value ){
    	int row = Integer.parseInt(converter[position].split(" ")[0]);
    	int col = Integer.parseInt(converter[position].split(" ")[1]);
    	if (this.valid_for_box(checkBox(row,col),possible_value) && this.valid_for_column(col, possible_value) && this.valid_for_row(row, possible_value)){
    		return true;
    	}
    	return false;
    }

	/**
	 * solve the sudoku problem
	 * 
	 * @return true if successful
	 */
	public boolean solve_sudoku() {
		return solve_sudoku(0);
	}

	/**
	 *
	 * Function solve_sudoku( puzzle, position )
	 *
	 * Recursively check to see if the puzzle can be solved following class
	 * algorithm
	 *
	 * Inputs: position - the index of the "current" box in the array (should be
	 * set to 0 by initial call)
	 *
	 */
	public boolean solve_sudoku(int position) {
		if (position == 81) {
			return true;
		}
		if (this.onedboard[position] != 0) {
			return solve_sudoku(position + 1);
		}
		for (int guess = 1; guess <= 9; guess++) {
			if (this.is_valid(position, guess)) {
				this.onedboard[position] = guess;
				this.twodboard[Integer.parseInt(converter[position].split(" ")[0])][Integer.parseInt(converter[position].split(" ")[1])] = guess;
				this.guesscount++;
				if (solve_sudoku(position + 1)){
					return true;
				}
			}
		}
		this.onedboard[position] = 0;
		this.twodboard[Integer.parseInt(converter[position].split(" ")[0])][Integer.parseInt(converter[position].split(" ")[1])] = 0;
		return false;
	}

	/**
	 * Function: toString( )
	 *
	 * @return a string showing the state of the board
	 *
	 */
	@Override
	public String toString() {
		String result = " ";
		int count = 0;
		for (int row = 0; row < 27; row++) {
			for (int col = 0; col < 3; col++) {
				result += onedboard[count] + " ";
				count++;
				
				// adds a new line at the end of a row
				if (count == 9 || count == 18 || count == 27 || count == 36 || count == 45 || count == 54 || count == 63 || count == 72) {
					result += "\n";
				}
				
				//adds another new line at the end of each 3 rows
				if (count == 27 || count == 54) {
					result += "\n";
				}
			}
			result += " ";
		}
		return result;
	}

	/**
	 * Given a puzzle (filled or partial), verify that every element does not
	 * repeat in row, col, or box.
	 * 
	 * @return true if a validly solved puzzle
	 */
	public boolean verify() {
		//check every column for repeats
		for (int row = 0; row < 9; row++) {
			//boolean array to determine if values are found
			//the index of the array is used to see if a number repeats
			//we subtract by 1 so 1 will be foundvals[0], and 9 will be foundvals[8]
			//sudoku is 1-9 but we access array (0-8)
			boolean[] foundvals = new boolean[9];
			for (int col = 0; col < 9; col++) {
				//if the value has been initialized
				if (twodboard[row][col] != 0) {
					//if we have already found the value and set it to true then there is a duplicate!
					if (foundvals[twodboard[row][col] - 1]) {
						return false;
					}
					//we have accessed the current number so
					//we set the current number to true (or found)
					foundvals[twodboard[row][col] - 1] = true;
				}
			}
		}
		//check every row for repeats
		for (int col = 0; col < 9; col++) {
			//boolean array to determine if values are found (see above)
			boolean[] foundvals = new boolean[9];
			for (int row = 0; row < 9; row++) {
				//if the value has been initialized
				if (twodboard[row][col] != 0) {
					//if we have already found the value and set it to true then there is a duplicate!
					if (foundvals[twodboard[row][col] - 1]) {
						return false;
					}
					//we have accessed the current number so
					//we set the current number to true (or found)
					foundvals[twodboard[row][col] - 1] = true;
				}
			}
		}
		
		//check every box for repeats
		for (int box = 0;box<9;box++){
			//boolean array to determine if values are found (see above)
			boolean[] foundvals = new boolean[9];
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					//if the current pos is part of current box repeat same process as above
					if(checkBox(row,col)==box){
						if (twodboard[row][col] != 0) {
							//if we have already found the value and set it to true then there is a duplicate!
							if (foundvals[twodboard[row][col] - 1]) {
								return false;
							}
							//we have accessed the current number so
							//we set the current number to true (or found)
							foundvals[twodboard[row][col] - 1] = true;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * A helper method to update the possibilities when using constraint method
	 * @param possibilities the possible values for every cell
	 */
	public void update_possibilities(ArrayList<HashSet<Integer>> possibilities) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (twodboard[row][col] != 0) {
					this.prune_row(possibilities, row * 9 + col, twodboard[row][col]);
					this.prune_column(possibilities, row * 9 + col, twodboard[row][col]);
					this.prune_box(possibilities, row * 9 + col, twodboard[row][col]);
				}
			}
		}
	}

	/**
	 * Attempt to solve a sudoku by eliminating obviously wrong values Algorithm
	 *
	 * 1) build a 81 (representing 9x9) array of sets 2) put a set of 1-9 in
	 * each of the 81 spots 3) do initial elimination for each known value,
	 * eliminate nubmers from sets in same row, col, box eliminate all values in
	 * the given square 4) while progress is being made (initially true) find a
	 * non-processed square with one possible answer and eliminate this number
	 * from row, col, box
	 */
	public void solve_by_elimination() {
		//create array of possible values for every cell
		ArrayList<HashSet<Integer>> possibilevals = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 81; i++) {
			int temprow = Integer.parseInt(converter[i].split(" ")[0]);
			int tempcol = Integer.parseInt(converter[i].split(" ")[1]);
			//if the value is 0 add all possibilities
			if (twodboard[temprow][tempcol] == 0) {
				HashSet<Integer> possible = new HashSet<Integer>();
				Collections.addAll(possible, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
				possibilevals.add(possible);
				//this cell is already chosen
			} else {
				HashSet<Integer> possible = new HashSet<Integer>();
				possible.add(twodboard[temprow][tempcol]);
				possibilevals.add(possible);
			}
		}
		//count will determine when we have reached all possibilities
		int count = 0;
		while (count != 81) {
			count = 0;
			//update the possibilities
			this.update_possibilities(possibilevals);
			//print them
			this.print_possibilities(possibilevals);
			for (int i = 0; i < 81; i++) {
				//if there is only one possible value choose it
				if (possibilevals.get(i).size() == 1) {
					count++;
					//simple way to access a value from a set
					for (Integer temp : possibilevals.get(i)) {
						onedboard[i] = temp;
						int temprow = Integer.parseInt(converter[i].split(" ")[0]);
						int tempcol = Integer.parseInt(converter[i].split(" ")[1]);
						twodboard[temprow][tempcol] = temp;
					}
				}
			}
		}
	}

	/**
	 * print a grid showing all possible valid answers This should be a 27x27
	 * matrix.
	 *
	 * I would suggest you do this first to get good debugging help
	 * 
	 * @param possibilities
	 *            - array list of all the sets of 1-9s
	 */
	public static void print_possibilities(ArrayList<HashSet<Integer>> possibilities) {
		int count = 0;
		String grid = "";
		//print the possibilities
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				grid += possibilities.get(count).toString() + "--";
				count++;
			}
			grid += "\n";
		}
		System.out.println(grid);
	}

	/**
	 * Given a possibility constraint matrix (81 sets of [1-9]) remove the given
	 * number from every matrix in the given box
	 * 
	 * @param possibilities
	 *            - 81 sets of [1-9]
	 * @param position
	 *            - where the value exists (transform to row,col)
	 * @param value
	 *            - the value topossibilities prune
	 */
	protected void prune_box(ArrayList<HashSet<Integer>> possibilities, int position, Integer value) {
		int thisrow = Integer.parseInt(converter[position].split(" ")[0]);
		int thiscol = Integer.parseInt(converter[position].split(" ")[1]);
		//scan through every element
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				//if it is not the current value
				if (row != thisrow || col != thiscol) {
					//remove the value from possibilities
					if (checkBox(row, col) == checkBox(thisrow, thiscol)) {
						if (possibilities.get((row * 9) + col).contains(value)) {
							possibilities.get((row * 9) + col).remove(value);
						}
					}
				}
			}
		}
	}

	/**
	 * Given a possibility constraint matrix (81 sets of [1-9]) remove the given
	 * number from every matrix in the given column
	 * 
	 * @param possibilities
	 *            - 81 sets of [1-9]
	 * @param position
	 *            - where the value exists (transform to row,col)
	 * @param value
	 *            - the value topossibilities prune
	 */
	protected void prune_column(ArrayList<HashSet<Integer>> possibilities, int position, Integer value) {
		for (int current = position % 9; current < 81; current += 9) {
			if (current != position) {
				//remove the value from possibilities
				if (possibilities.get(current).contains(value)) {
					possibilities.get(current).remove(value);
				}
			}
		}
	}
	/**
	 * Given a possibility constraint matrix (81 sets of [1-9]) remove the given
	 * number from every matrix in the given column
	 * 
	 * @param possibilities
	 *            - 81 sets of [1-9]
	 * @param position
	 *            - where the value exists (transform to row,col)
	 * @param value
	 *            - the value topossibilities prune
	 */
	protected void prune_row(ArrayList<HashSet<Integer>> possibilities, int position, Integer value) {
		int thisrow = Integer.parseInt(converter[position].split(" ")[0]);
		int thiscol = Integer.parseInt(converter[position].split(" ")[1]);
		for (int col = 0; col < 9; col++) {
			if (thiscol != col) {
				//remove the value from possibilities
				if (possibilities.get((thisrow * 9) + col).contains(value)) {
					possibilities.get((thisrow * 9) + col).remove(value);
				}
			}
		}
	}

	/**
	 * Add any private helper functions you need as appropriate
	 */

	/**
	 * Function: toString( )
	 *
	 * @return a string showing the state of the board using the 1D board
	 *
	 */
	public String toString1D() {
		String result = "";
		int count = 0;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				result += onedboard[count] + " ";
				count++;
			}
			result += "\n";
		}
		return result;
	}
	

	
	/**
	 * places a number in the specified index
	 * 
	 * @param what number you want to place
	 * @param where you want to place the number
	 */
	public void addNum(int num, int pos ) {
		this.onedboard[pos] = num;
		this.twodboard[Integer.parseInt(converter[pos].split(" ")[0])][Integer.parseInt(converter[pos].split(" ")[1])] = num;
	}
	
	/**
	 * @return a copy of the puzzle as a 1D matrix
	 */
	public List<Set<Integer>> getList() {
		return this.boxes;
	}

}
