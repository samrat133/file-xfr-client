package com.poc.filexfrclient.helper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.poc.filexfrclient.constant.AppConstants;

public class SocketChannelHelper implements AppConstants{

	private static Logger log = Logger.getLogger(SocketChannelHelper.class);

	public SocketChannel createChannel() {

		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress(IP_ADDRESS, PORT);
			socketChannel.connect(socketAddress);
			log.info("Connected... Initiating file transfer");
		} catch (IOException iOException) {
			log.error(iOException.getMessage(), iOException);
		}
		return socketChannel;
	}

}
