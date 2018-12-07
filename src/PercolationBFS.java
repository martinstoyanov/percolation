import java.util.*;

/**
 * 
 */

/**
 * @author martinstoyanov
 *
 */
public class PercolationBFS extends PercolationDFSFast {
	
	public PercolationBFS(int k) {
		super(k);
	}
	
	@Override
	
	protected void dfs(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(String.format("out of bounds..."));
		}
		
		myGrid[row][col] = FULL;
	 
				
		Queue<Integer> queue = new LinkedList<>();
		queue.add(row*myGrid.length + col);
		
		int[] deltaR = {-1,1,0,0};
		int[] deltaC = {0,0,-1,1};
		
		while(queue.size() != 0) {
			Integer idx = queue.remove();
			for (int k=0; k < deltaR.length; k++) {
				row = idx/myGrid.length + deltaR[k];
				col = idx%myGrid.length + deltaC[k];
				if (inBounds(row,col) && myGrid[row][col] == OPEN) {
					myGrid[row][col] = FULL;
					queue.add(row*myGrid.length + col);
				}
			}
		}
	}
	
}
