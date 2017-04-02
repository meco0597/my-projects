package Sudoku;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

public class SudokuTest {
	
	private Sudoku testEasy;
	
	@Test
	public void testValidForRowTrue() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_row(0, 4) == true);
	}
	
	@Test
	public void testValidForRowFalse() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_row(0, 3) == false);
	}
	
	@Test
	public void testValidForColumnTrue() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_column(0, 3) == true);
	}
	
	@Test
	public void testValidForColumnFalse() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_column(0, 5) == false);
	}
	
	@Test
	public void testValidForBoxTrue() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_box(0, 6) == true);
	}
	
	@Test
	public void testValidForBoxFalse() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.valid_for_box(0, 3) == false);
	}
	
	@Test
	public void testIsValidTrue() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.is_valid(0, 1) == true);
	}
	
	@Test
	public void testIsValidFalse() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.is_valid(0, 3) == false);
	}
	
	@Test
	public void testVerifyTrue() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.verify() == true);
	}
	
	@Test
	public void testVerifyFalse() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		testEasy.addNum(3, 0);
		assertTrue(testEasy.verify() == false);
	}

	@Test
	public void testVerifyFalseEdgeCase() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		testEasy.addNum(3, 10);
		assertTrue(testEasy.verify() == false);
	}
	
	@Test
	public void testVerifyFalseEdgeCase2() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		testEasy.addNum(3, 9);
		assertTrue(testEasy.verify() == false);
	}
	
	@Test
	public void testSolveSudoku() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(testEasy.solve_sudoku(0) == true);
	}
	
	@Test
	public void testPruneBox() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<HashSet<Integer>> setPossibilities = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 81; i++) {
			if (testEasy.twodboard[Integer.parseInt(Sudoku.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])] == 0) {
				HashSet<Integer> possible = new HashSet<Integer>();
				Collections.addAll(possible, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
				setPossibilities.add(possible);
			} else {
				HashSet<Integer> possible = new HashSet<Integer>();
				possible.add(testEasy.twodboard[Integer.parseInt(testEasy.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])]);
				setPossibilities.add(possible);
			}
		}
		testEasy.prune_box(setPossibilities, 1, 3);
		assertTrue(setPossibilities.get(1).contains(3) == true);
	}
	
	@Test
	public void testPruneRow() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<HashSet<Integer>> setPossibilities = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 81; i++) {
			if (testEasy.twodboard[Integer.parseInt(Sudoku.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])] == 0) {
				HashSet<Integer> possible = new HashSet<Integer>();
				Collections.addAll(possible, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
				setPossibilities.add(possible);
			} else {
				HashSet<Integer> possible = new HashSet<Integer>();
				possible.add(testEasy.twodboard[Integer.parseInt(testEasy.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])]);
				setPossibilities.add(possible);
			}
		}
		testEasy.prune_row(setPossibilities, 1, 3);
		assertTrue(setPossibilities.get(1).contains(3) == true);
	}
	
	@Test
	public void testPruneCol() {
		try {
			testEasy = new Sudoku(new BufferedReader(new FileReader("puzzles/easy.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<HashSet<Integer>> setPossibilities = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 81; i++) {
			if (testEasy.twodboard[Integer.parseInt(Sudoku.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])] == 0) {
				HashSet<Integer> possible = new HashSet<Integer>();
				Collections.addAll(possible, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
				setPossibilities.add(possible);
			} else {
				HashSet<Integer> possible = new HashSet<Integer>();
				possible.add(testEasy.twodboard[Integer.parseInt(testEasy.converter[i].split(" ")[0])][Integer.parseInt(testEasy.converter[i].split(" ")[1])]);
				setPossibilities.add(possible);
			}
		}
		testEasy.prune_column(setPossibilities, 1, 3);
		assertTrue(setPossibilities.get(1).contains(3) == true);
	}
	
}
