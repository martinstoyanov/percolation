/**
 * 
 */

/**
 * @author martinstoyanov
 *
 */


public class PercolationDFSFast extends PercolationDFS  {
	
	/**
	 *
	 * Constructor PercolationDFSFast
	 * @param k size of the grid
	 */

	public PercolationDFSFast(int k) {
		super(k);
	}
	
	@Override 
	public void updateOnOpen(int row, int col) {
		
		if (row == 0 || (inBounds(row-1,col) && isFull(row-1,col)) ||
				(inBounds(row+1,col) && isFull(row+1,col)) || 
				(inBounds(row,col-1) && isFull(row,col-1)) || 
				(inBounds(row,col+1) && isFull(row, col+1))) {
			dfs(row, col);
		}
	}	
	
}
