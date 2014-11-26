package org.jumbo.imageCompare;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

public class ImageCompare {

	// buffered images are just better.
	protected static BufferedImage imageToBufferedImage(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, null, null);
		return bi;
	}

	// read a jpeg file into a buffered image
	protected static Image loadJPG(String filename) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(filename));
		} catch (java.io.IOException io) {
			System.out.println("IOException");
		}
		return bi;
	}

	/* create a runable demo thing. */
	public static void main(String[] args) {
		// Create a compare object specifying the 2 images for comparison.
		ImageCompare ic = new ImageCompare("c:\\test1.jpg", "c:\\test2.jpg");
		// Set the comparison parameters.
		// (num vertical regions, num horizontal regions, sensitivity,
		// stabilizer)
		ic.setParameters(8, 6, 5, 10);
		// Display some indication of the differences in the image.
		ic.setDebugMode(2);
		// Compare.
		ic.compare();
		// Display if these images are considered a match according to our
		// parameters.
		System.out.println("Match: " + ic.match());
		// If its not a match then write a file to show changed regions.
		if (!ic.match()) {
			saveJPG(ic.getChangeIndicator(), "c:\\changes.jpg");
		}
	}

	// write a buffered image to a jpeg file.
	public static void saveJPG(Image img, String filename) {
		BufferedImage newBufferedImage = imageToBufferedImage(img);
		newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE,
				null);

		// write to jpeg file
		try {
			ImageIO.write(newBufferedImage, "jpg", new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * BufferedImage bi = imageToBufferedImage(img); FileOutputStream out =
		 * null; try { out = new FileOutputStream(filename); } catch
		 * (java.io.FileNotFoundException io) {
		 * System.out.println("File Not Found"); } JPEGImageEncoder encoder =
		 * JPEGCodec.createJPEGEncoder(out); JPEGEncodeParam param =
		 * encoder.getDefaultJPEGEncodeParam(bi); param.setQuality(0.8f,false);
		 * encoder.setJPEGEncodeParam(param); try { encoder.encode(bi);
		 * out.close(); } catch (java.io.IOException io) {
		 * System.out.println("IOException"); }
		 */
	}

	protected BufferedImage img1 = null;
	protected BufferedImage img2 = null;
	protected BufferedImage imgc = null;
	protected int comparex = 0;
	protected int comparey = 0;

	protected int factorA = 0;

	protected int factorD = 10;

	protected boolean match = false;

	protected int debugMode = 0; // 1: textual indication of change, 2:
									// difference of factors

	// constructor 3. use buffered images. all roads lead to the same place.
	// this place.
	public ImageCompare(BufferedImage img1, BufferedImage img2) {
		this.img1 = img1;
		this.img2 = img2;
		autoSetParameters();
	}

	// constructor 2. use awt images.
	public ImageCompare(Image img1, Image img2) {
		this(imageToBufferedImage(img1), imageToBufferedImage(img2));
	}

	// constructor 1. use filenames
	public ImageCompare(String file1, String file2) {
		this(loadJPG(file1), loadJPG(file2));
	}

	// like this to perhaps be upgraded to something more heuristic in the
	// future.
	public void autoSetParameters() {
		comparex = 20;
		comparey = 20;
		factorA = 0;
		factorD = 10;
	}

	// compare the two images in this object.
	public void compare() {
		// setup change display image
		imgc = imageToBufferedImage(img2);
		Graphics2D gc = imgc.createGraphics();
		gc.setColor(Color.RED);
		// convert to gray images.
		img1 = imageToBufferedImage(GrayFilter.createDisabledImage(img1));
		img2 = imageToBufferedImage(GrayFilter.createDisabledImage(img2));
		// how big are each section
		int blocksx = img1.getWidth() / comparex;
		int blocksy = img1.getHeight() / comparey;
		// set to a match by default, if a change is found then flag non-match
		this.match = true;
		// loop through whole image and compare individual blocks of images
		for (int y = 0; y < comparey; y++) {
			if (debugMode > 0)
				System.out.print("|");
			for (int x = 0; x < comparex; x++) {
				int b1 = getAverageBrightness(img1.getSubimage(x * blocksx, y
						* blocksy, blocksx - 1, blocksy - 1));
				int b2 = getAverageBrightness(img2.getSubimage(x * blocksx, y
						* blocksy, blocksx - 1, blocksy - 1));
				int diff = Math.abs(b1 - b2);
				if (diff >= factorA) { // the difference in a certain region has
										// passed the threshold value of factorA
					// draw an indicator on the change image to show where
					// change was detected.
					gc.drawRect(x * blocksx, y * blocksy, blocksx - 1,
							blocksy - 1);
					this.match = false;
				}
				if (debugMode == 1)
					System.out.print((diff > factorA ? "X" : " "));
				if (debugMode == 2)
					System.out.print(diff + (x < comparex - 1 ? "," : ""));
			}
			if (debugMode > 0)
				System.out.println("|");
		}
	}

	// returns a value specifying some kind of average brightness in the image.
	protected int getAverageBrightness(BufferedImage img) {
		Raster r = img.getData();
		int total = 0;
		for (int y = 0; y < r.getHeight(); y++) {
			for (int x = 0; x < r.getWidth(); x++) {
				total += r.getSample(r.getMinX() + x, r.getMinY() + y, 0);
			}
		}
		return total / ((r.getWidth() / factorD) * (r.getHeight() / factorD));
	}

	// return the image that indicates the regions where changes where detected.
	public BufferedImage getChangeIndicator() {
		return imgc;
	}

	// returns true if image pair is considered a match
	public boolean match() {
		return this.match;
	}

	// want to see some stuff in the console as the comparison is happening?
	public void setDebugMode(int m) {
		this.debugMode = m;
	}

	// set the parameters for use during change detection.
	public void setParameters(int x, int y, int factorA, int factorD) {
		this.comparex = x;
		this.comparey = y;
		this.factorA = factorA;
		this.factorD = factorD;
	}

}
