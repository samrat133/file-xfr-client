package com.poc.filexfrclient.filesender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.poc.filexfrclient.constant.AppConstants;

public class Sender implements AppConstants{
	int counter;

	private static Logger log = Logger.getLogger(Sender.class);

	public void sendFile(SocketChannel socketChannel, String fileName) {
		RandomAccessFile aFile = null;

		try {
			File file = new File(fileName);
			aFile = new RandomAccessFile(file, FILE_MODE);
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(8192);
			while (inChannel.read(buffer) > 0) {
				buffer.flip();
				socketChannel.write(buffer);
				buffer.clear();
				counter++;
				if (counter % 8192 == 0)
					System.out.print(".");
			}
			log.info("File sent successfully!");
			socketChannel.close();
			aFile.close();
		} catch (FileNotFoundException fNEException) {
			log.error("Exception occurred while sending file : " + fNEException.getMessage(), fNEException);
		} catch (IOException iOException) {
			log.error(iOException.getMessage(), iOException);
		}
	}
}
