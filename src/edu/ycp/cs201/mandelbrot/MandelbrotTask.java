/**
 * 
 */
package edu.ycp.cs201.mandelbrot;

/**
 * @author Jackson
 *
 */
public class MandelbrotTask implements Runnable {
    private double x1, y1, x2, y2, incrementX, incrementY;
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
        incrementX = (x2-x1)/endRow;
        incrementY = (y2-y1)/endCol;
        System.out.println("x1:" + x1 + " x2:" + x2 + " y1:" + y1 + " y2:" + y2 + " startCol:" +startCol + " endCol:" + endCol + " startRow:" + startRow + " endRow:" + endRow);
    }

    public void run() {
        for (int i = startCol; i < endCol; i++) {
            for (int j = startRow; j < endRow; j++) {
                Complex c = getComplex(i, j);
                int iterCount = computeIterCount(c);
                iterCounts[i][j] = iterCount;
            }
        }
    }

	private int computeIterCount(Complex c) {
		Complex z = new Complex(0,0);
		for(int i = 7000; i >= 0; i--)
		{
			if(z.getMagnitude() > 2.0)
			{
				return i;
			}
			z = z.multiply(z).add(c);
		}
		return 0;
	}

	private Complex getComplex(int i, int j) {
		double x = x1+(i*incrementX);
		double y = y1+(j*incrementY);
		return new Complex(x, y);
	}
}
