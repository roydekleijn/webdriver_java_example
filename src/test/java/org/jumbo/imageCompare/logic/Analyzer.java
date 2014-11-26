package org.jumbo.imageCompare.logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jumbo.imageCompare.model.ImageArray;
import org.jumbo.imageCompare.model.Point;

public class Analyzer {
	private final int colorMaxValue = Color.BLACK.getRGB();
	private final int delta = 100;
	private int[][] firstArray;
	private int height, width;
	private final List<Point> points = new ArrayList<Point>();
	private List<ArrayList<Point>> polygons;
	private final int rate = 10;
	private BufferedImage resultImage;
	private int[][] secondArray;

	private void analyze() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				resultImage.setRGB(j, i, secondArray[i][j]);
				final double firstPercentage = percentage(firstArray[i][j]);
				final double secondPercentage = percentage(secondArray[i][j]);
				if (Math.abs(firstPercentage - secondPercentage) > rate) {
					points.add(new Point(j, i));
				}
			}
		}
	}

	public void compare(ImageArray firstImageArray, ImageArray secondImageArray) throws IOException {
		makePixelArrays(firstImageArray, secondImageArray);
		resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		analyze();
		findAreas();
		drawFrames();
		ImageIO.write(resultImage, "jpg", new File("target/screenshots/layout/result.jpg"));
	}

	private boolean contains(Point point) {
		for (int i = 0; i < polygons.size(); i++) {
			for (int j = 0; j < polygons.get(i).size(); j++) {
				final int x = polygons.get(i).get(j).getX();
				final int y = polygons.get(i).get(j).getY();
				if ((point.getX() < (x + delta)) && (point.getX() > (x - delta)) && (point.getY() < (y + delta))
						&& (point.getY() > (y - delta))) {
					polygons.get(i).add(point);
					return true;
				}
			}
		}
		return false;
	}

	private void drawFrames() {
		for (int i = 0; i < polygons.size(); i++) {
			int left = Integer.MAX_VALUE, right = 0, top = Integer.MAX_VALUE, bottom = 0;
			for (int j = 0; j < polygons.get(i).size(); j++) {
				final Point point = polygons.get(i).get(j);
				if (point.getX() < left) {
					left = point.getX();
				}
				if (point.getX() > right) {
					right = point.getX();
				}
				if (point.getY() > bottom) {
					bottom = point.getY();
				}
				if (point.getY() < top) {
					top = point.getY();
				}
			}
			final Graphics2D g2d = resultImage.createGraphics();
			g2d.setColor(Color.RED);
			g2d.drawRect(left, top, right - left, bottom - top);
			g2d.dispose();
		}
	}

	private void findAreas() {
		for (final Point point : points) {
			System.out.println(point.getX() + " | " + point.getY());
			if (polygons == null) {
				polygons = new ArrayList<ArrayList<Point>>();
				final ArrayList<Point> firstArea = new ArrayList<Point>();
				firstArea.add(points.get(0));
				polygons.add(firstArea);
			} else {
				if (!contains(point)) {
					final ArrayList<Point> newArea = new ArrayList<Point>();
					newArea.add(point);
					polygons.add(newArea);
				}
			}
		}
	}

	private void makePixelArrays(ImageArray firstImageArray, ImageArray secondImageArray) {
		firstArray = firstImageArray.getArray();
		secondArray = secondImageArray.getArray();
		height = firstImageArray.getHeight();
		width = firstImageArray.getWidth();
	}

	private double percentage(int value) {
		return Math.abs(((double) value / colorMaxValue) * 100);
	}

}