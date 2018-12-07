/**
 * 
 */

/**
 * @author martinstoyanov
 *
 */
public class PercolationUF implements IPercolate {
		private boolean[][] myGrid;
		private Integer myOpenCount;
		private IUnionFind myFinder;
		private final int VTOP;
		private final int VBOTTOM;
		
		public PercolationUF(int size, IUnionFind finder) {
			myGrid = new boolean[size][size];
			myOpenCount = 0;
			finder.initialize(size*size + 2);
			myFinder = finder;
			
			VTOP = size * size;
			VBOTTOM = size * size + 1;
			
			for (int i = 0; i< size; i++) {
				for (int y = 0; y<size; y++) {
					myGrid[i][y] = false; 
				}
			}
		}
		
		@Override
		public void open(int row, int col) {
			if (!inBounds(row,col)) {
				throw new IndexOutOfBoundsException("out of bounds!");
			}
			
			if (myGrid[row][col] != true) {
				myGrid[row][col] = true;
				myOpenCount++;
			}
			
			if (row == myGrid.length - 1 ) {
				myFinder.union(VBOTTOM, row*myGrid.length + col);
			}
			
			if (row == 0) {
				myFinder.union(VTOP, row*myGrid.length + col);
			}
			
			
			int[] deltaR = {-1,1,0,0};
			int[] deltaC = {0,0,-1,1};
			
			for (int i = 0; i< deltaR.length;i++) {
				if (inBounds(row+deltaR[i],col+deltaC[i]) && myGrid[row+deltaR[i]][col+deltaC[i]]) 
				{
					myFinder.union((row+deltaR[i]) * myGrid.length + col+deltaC[i], row * myGrid.length + col);
				}
			}
		}
		

		/**
		 * Determine if (row,col) is valid for given grid
		 * @param row specifies row
		 * @param col specifies column
		 * @return true if (row,col) on grid, false otherwise
		 */
		protected boolean inBounds(int row, int col) {
			if (row < 0 || row >= myGrid.length) return false;
			if (col < 0 || col >= myGrid[0].length) return false;
			return true;
		}

		/* (non-Javadoc)
		 * @see IPercolate#isOpen(int, int)
		 */
		@Override
		public boolean isOpen(int row, int col) {
			if (!inBounds(row,col)) {
				throw new IndexOutOfBoundsException("out of bounds!");
			}
			// TODO Auto-generated method stub
			return myGrid[row][col];
		}

		/* (non-Javadoc)
		 * @see IPercolate#isFull(int, int)
		 */
		@Override
		public boolean isFull(int row, int col) {
			// TODO Auto-generated method stub
			if (!inBounds(row,col)) {
				throw new IndexOutOfBoundsException("out of bounds!");
			}
			return myFinder.connected(VTOP, row*myGrid.length+col);
		}

		/* (non-Javadoc)
		 * @see IPercolate#percolates()
		 */
		@Override
		public boolean percolates() {
			// TODO Auto-generated method stub
			return myFinder.connected(VBOTTOM, VTOP);
		}

		/* (non-Javadoc)
		 * @see IPercolate#numberOfOpenSites()
		 */
		@Override
		public int numberOfOpenSites() {
			// TODO Auto-generated method stub
			
			return myOpenCount;
		}
}
