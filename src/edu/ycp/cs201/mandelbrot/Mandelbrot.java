package edu.ycp.cs201.mandelbrot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Mandelbrot {
	public static final int HEIGHT = 600;

	public static final int WIDTH = 600;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter coordinates of region to render:");
		System.out.print("  x1: ");
		double x1 = keyboard.nextDouble();
		System.out.print("  y1: ");
		double y1 = keyboard.nextDouble();
		System.out.print("  x2: ");
		double x2 = keyboard.nextDouble();
		System.out.print("  y2: ");
		double y2 = keyboard.nextDouble();
//
		System.out.print("Output filename: ");
		String fileName = keyboard.next();
//		double x1, x2, y1, y2;
		// TODO: create the rendering, save it to a file
		int[][] iterCounts = new int[HEIGHT][WIDTH];
//		x1 = -1.286667;
//		y1 = -0.413333;
//		x2 = -1.066667;
//		y2 = -0.193333;
		int split = HEIGHT/2;
		double x3 = x2/4;
		double y3 = y2/4;
		MandelbrotTask task1 = new MandelbrotTask(x1, y1, x3, y3, 0, split, 0, split, iterCounts);
		MandelbrotTask task2 = new MandelbrotTask(x3, y3, x3*2, y3*2, split, WIDTH, 0, split, iterCounts);
		MandelbrotTask task3 = new MandelbrotTask(x3*2, y3*2, x3*3, y3*3, 0, split, split, HEIGHT, iterCounts);
		MandelbrotTask task4 = new MandelbrotTask(x3*3, y3*3, x2, y2, split, WIDTH, split, HEIGHT, iterCounts);
		Thread[] tasks = new Thread[4];
		tasks[0] = new Thread(task1);
		tasks[1] = new Thread(task2);
		tasks[2] = new Thread(task3);
		tasks[3] = new Thread(task4);
		for(int i = 0; i < 4; i++)
		{
			tasks[i].start();
		}
		for(int i = 0; i < 4; i++)
		{
			tasks[i].join();
		}
		
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		for(int i = 0; i < iterCounts.length; i++)
		{
			for(int j = 0; j < iterCounts.length; j++)
			{
				int rgb = iterCounts[i][j];
				g.setColor(getColor(rgb));
				g.fillRect(i, j, 1, 1);
				g.fillRect(i, j, 1, 1);
			}
		}
		g.dispose();
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		try {
		    ImageIO.write(bufferedImage, "PNG", os);
		} finally {
		    os.close();
		}
	}
	//Trying to blend colors, not working so it just outputs a color based on how many thousand iterations it took.
	private static Color getColor(int rgb) {
		CustomColor[] colors = CustomColor.values();
		Color c = null;
		double thousands = (int)rgb/1000;
		double hundreds = (int)rgb%1000/100;
		double tens = (int)rgb%100/10;
		double ones = (int)rgb%10;
		if(thousands==0)
		{
			c = colors[0].getColor();
		}
		else
		{
			CustomColor one = colors[(int)thousands];
			CustomColor two = colors[(int) ((thousands < colors.length) ? thousands : thousands-1)];
			double numer = tens*ones;
			double denom = hundreds*ones*ones;
			if(numer > denom)
			{
				double temp = numer;
				numer = denom;
				denom = temp;
			}
			double ratio = (numer > 0 && denom > 0) ? (numer/denom) : ((numer+1)/(denom+1));
			c = one.blendColor(two, ratio);
		}
		c = colors[(int)thousands].getColor();
		return c;
	}
}
