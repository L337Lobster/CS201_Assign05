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

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		
//		System.out.println("Please enter coordinates of region to render:");
//		System.out.print("  x1: ");
//		double x1 = keyboard.nextDouble();
//		System.out.print("  y1: ");
//		double y1 = keyboard.nextDouble();
//		System.out.print("  x2: ");
//		double x2 = keyboard.nextDouble();
//		System.out.print("  y2: ");
//		double y2 = keyboard.nextDouble();
//
		System.out.print("Output filename: ");
		String fileName = keyboard.next();
		double x1, x2, y1, y2;
		// TODO: create the rendering, save it to a file
		int[][] iterCounts = new int[HEIGHT][WIDTH];
		x1 = -1.286667;
		y1 = -0.413333;
		x2 = -1.066667;
		y2 = -0.193333;
		MandelbrotTask task = new MandelbrotTask(x1, y1, x2, y2, 0, WIDTH, 0, HEIGHT, iterCounts);
		task.run();
		
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		for(int i = 0; i < iterCounts.length; i++)
		{
			for(int j = 0; j < iterCounts.length; j++)
			{
				int rgb = iterCounts[i][j];
				bufferedImage.setRGB(i, j, new Color(rgb,rgb,rgb).getRGB());
			}
		}

		System.out.println(Color.BLUE.getRGB());
		g.dispose();
		
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		try {
		    ImageIO.write(bufferedImage, "PNG", os);
		} finally {
		    os.close();
		}
	}
}
