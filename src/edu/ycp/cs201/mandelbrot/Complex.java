/**
 * 
 */
package edu.ycp.cs201.mandelbrot;

/**
 * @author Jackson
 *
 */
public class Complex {
    private double real, imag;

    // Constructor
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    // add given complex number to this one, returning the Complex result
    public Complex add(Complex other) {
		return new Complex(real+other.getReal(), imag+other.getImag());
    }

    // multiply given complex number by this one, returning the Complex result
    public Complex multiply(Complex other) {
    	double x = real;
    	double y = imag;
    	double u = other.getReal();
    	double v = other.getImag();
        return new Complex((x*u)-(y*v), (x*v) + (y*u));
    }

    // get the magnitude of this complex number
    public double getMagnitude() {
        return Math.sqrt(Math.pow(real, 2)+Math.pow(imag, 2));
    }

	/**
	 * @return the real portion of the number
	 */
	public double getReal() {
		return real;
	}

	/**
	 * @return the imaginary portion of the number
	 */
	public double getImag() {
		return imag;
	}
	public String toString()
	{
		return real + " + " + imag + "i";
	}
}
