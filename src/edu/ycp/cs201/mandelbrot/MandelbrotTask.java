/**
 * 
 */
package edu.ycp.cs201.mandelbrot;

/**
 * @author Jackson
 *
 */
public class MandelbrotTask implements Runnable {
    private double x1, y1, x2, y2;
    private int startCol, endCol, startRow, endRow;
    private int[][] iterCounts;

    public MandelbrotTask(double x1, double y1, double x2, double y2,
                          int startCol, int endCol, int startRow, int endRow,
                          int[][] iterCounts) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.startCol = startCol;
        this.endCol = endCol;
        this.startRow = startRow;
        this.endRow = endRow;
        this.iterCounts = iterCounts;
    }

    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                Complex c = getComplex(i, j);
                int iterCount = computeIterCount(c);
                iterCounts[i][j] = iterCount;
            }
        }
    }

	private int computeIterCount(Complex c) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Complex getComplex(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

    // TODO: implement getComplex and computeIterCount methods
}
