/**
 * 
 */
package edu.ycp.cs201.mandelbrot;

import java.awt.Color;

/**
 * @author Jackson
 *
 */
public enum CustomColor {
	
	BLACK(0, 0, 0, "BLACK"),
	RED(255, 0, 0, "RED"),
	ORANGE(255, 165, 0, "ORANGE"),
	YELLOW(255, 255, 0, "YELLOW"),
	GREEN(0, 255, 0, "GREEN"),
	BLUE(0, 0, 255, "BLUE"),
	PURPLE(128, 0, 128, "PURPLE");
	
	int alpha;
	private double red, green, blue;
	private String name;
	private CustomColor(int r, int g, int b, String s)
	{
		alpha = 255;
		red = r;
		green = g;
		blue = b;
		name = s;
	}
	
	public Color getColor()
	{
		return new Color((int)red, (int)green, (int)blue, alpha);
	}
	
	public Color blendColor(CustomColor c, double ratio)
	{
		double r = ratio;
		double ir =  1.0 - r;
		double red = this.red*r + c.getRed()*ir;
		double blue = this.blue*r + c.getBlue()*ir;
		double green = this.green*r + c.getGreen()*ir;
		
		if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }
		return new Color((int)red, (int)green, (int)blue, alpha);
	}

	/**
	 * @return the alpha
	 */
	public int getAlpha() {
		return alpha;
	}

	/**
	 * @return the red
	 */
	public double getRed() {
		return red;
	}

	/**
	 * @return the green
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public double getBlue() {
		return blue;
	}
	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
