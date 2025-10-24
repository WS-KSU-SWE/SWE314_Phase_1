package swe314_project_phase1;

public class Cell {

	private int row;
	private int col;
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	public int getRow() {
		return row;
	}
	
	
	public int getCol() {
		return col;
	}
	
	
	public boolean equals(Cell cell) {
		return cell.row == row && cell.col == col;
	}
	
}
