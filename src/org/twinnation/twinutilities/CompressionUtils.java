package org.twinnation.twinutilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;

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
	 * @param imageFileName Name of the image to compress
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
		return ((inputImg.length()-outputImg.length())<=0);
	}
	

	/**
	 * Compresses a file
	 * @param fileName Name of the file to compress
	 * @return Whether the compression has been successful or not
	 */
	public static boolean gzip(String fileName) {
		return gzip(fileName, false);
	}
	
	
	/**
	 * Compresses a file and delete the non-compressed file
	 * @param fileName Name of the file to compress
	 * @return Whether the compression has been successful or not
	 */
	public static boolean gzipAndDelete(String fileName) {
		return gzip(fileName, true);
	}
	
	
	/**
	 * Compresses a file using GZIP
	 * @param fileName Name of the file to compress
	 * @param deleteAfterCompress Whether to delete the file after compression
	 * @return Whether the compression has been successful or not
	 */
	private static boolean gzip(String fileName, boolean deleteAfterCompress) {
		try {
            FileInputStream is = new FileInputStream(fileName);
            FileOutputStream os = new FileOutputStream(fileName+".gz");
            GZIPOutputStream gzipOs = new GZIPOutputStream(os);
            byte[] buffer = new byte[1024];
            int len;
            while((len=is.read(buffer)) != -1){
            	gzipOs.write(buffer, 0, len);
            }
            gzipOs.close();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
		return (new File(fileName)).delete();
	}
	
}
