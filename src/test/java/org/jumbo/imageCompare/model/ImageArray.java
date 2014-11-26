package org.jumbo.imageCompare.model;

public class ImageArray {
	private int[][] array;
	private int height;
	private int width;

	public ImageArray(int[][] array, int height, int width) {
		this.array = array;
		this.height = height;
		this.width = width;
	}

	public int[][] getArray() {
		return array;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}