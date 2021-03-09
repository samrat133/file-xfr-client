package com.poc.filexfrclient;

import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.poc.filexfrclient.filesender.Sender;
import com.poc.filexfrclient.helper.SocketChannelHelper;

public class FileXfrClient {
	private static Logger log = Logger.getLogger(FileXfrClient.class);

	public static void main(String[] args) {
		log.info("Establishing connection...");
		SocketChannelHelper socketChannelHelper = new SocketChannelHelper();
		Sender sender = new Sender();
		SocketChannel socketChannel = socketChannelHelper.createChannel();
		sender.sendFile(socketChannel, args[0]);
	}
}