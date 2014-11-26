package org.jumbo.imageCompare.logic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jumbo.imageCompare.model.ImageArray;

public class Loader {
	private int height, width;
	private BufferedImage inputImage;

	public ImageArray getPixelsArray(String path) {
		final File file = new File(path);
		inputImage = null;
		try {
			inputImage = ImageIO.read(file);
		} catch (final IOException e) {
			throw new RuntimeException("Exception during reading", e);
		}
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		return new ImageArray(parseImage(), height, width);
	}

	private int[][] parseImage() {
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics graphics = image.getGraphics();
		graphics.drawImage(inputImage, 0, 0, null);
		graphics.dispose();
		final int[] line = new int[width * height];
		image.getRGB(0, 0, width, height, line, 0, width);
		final int[][] result = new int[height][width];
		int index = 0;
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				result[x][y] = line[index];
				index++;
			}
		}
		return result;
	}
}