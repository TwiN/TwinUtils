package org.twinnation.twinutilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

// IN PROGRESS
public final class CompressionUtils {
	
	// TODO: allow specification of compression factor by user
	private static final float LOW_COMPRESSION_FACTOR = 0.8f;
	private static final float MED_COMPRESSION_FACTOR = 0.5f;
	private static final float HIGH_COMPRESSION_FACTOR = 0.2f;
	
	/** Prevents instantiation of this utility class */
	private CompressionUtils() {}
	
	
	/**
	 * Compresses a JPG image
	 * @param imageFileName 
	 * @return Whether the file was successfully compressed
	 * @throws Exception
	 */
	public static boolean compressJPG(String imageFileName) throws Exception {
		String extension = FileUtils.getExtension(imageFileName);
		String baseName = FileUtils.stripExtension(imageFileName);
		String outputImageFileName = baseName+ "_min." + extension;
			
		File inputImg = new File(imageFileName); // source
		File outputImg = new File(outputImageFileName); // destination

		// I/O streams & writer
		InputStream input = new FileInputStream(inputImg);
		OutputStream output = new FileOutputStream(outputImg);
		BufferedImage bufferedImage = ImageIO.read(input);
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(extension);
		ImageWriter imageWriter = writers.next();
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(output);
		imageWriter.setOutput(imageOutputStream);
		ImageWriteParam params = imageWriter.getDefaultWriteParam();

		// Compress the image
		params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		params.setCompressionQuality(LOW_COMPRESSION_FACTOR);

		// Create new image file
		imageWriter.write(null,(new IIOImage(bufferedImage, null, null)),params);

		// Close streams
		input.close();
		output.close();
		imageOutputStream.close();
		imageWriter.dispose();

		// calculate size difference between old file vs new file
		double difference = (double)(inputImg.length()-outputImg.length());
		return (difference<=0);
	}
	
	
	// TODO: make method to compress files (rar or gz)
	// XXX: use that ^ new method to compress the old logs @ LogsUtils
}
