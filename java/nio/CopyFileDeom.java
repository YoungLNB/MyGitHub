package com.google.javase.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class CopyFileDeom {

	private static void RandomAccessFileCopy() {
		try {
			RandomAccessFile in = new RandomAccessFile("E:\\javaseio\\my\\向往的.jpg", "r");
			RandomAccessFile out = new RandomAccessFile("E:\\javaseio\\my\\me\\Like.jpg", "rw");
			FileChannel fcin = in.getChannel();
			FileChannel fcout = out.getChannel();

			long size = fcin.size(); // 输入流的字节大小
			// 输入流的缓冲区
			MappedByteBuffer inbuf = fcin.map(MapMode.READ_ONLY, 0, size);
			// 输出流的缓冲区
			MappedByteBuffer outbuf = fcout.map(MapMode.READ_WRITE, 0, size);
			for (int i = 0; i < size; i++) {
				outbuf.put(inbuf.get());
			}
			System.out.println("Copy Success.");
			// 关闭通道时会写入数据块
			in.close();
			out.close();
			fcin.close();
			fcout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过文件通道实现文件复制
	 * 
	 * @throws Exception
	 */
	private static void CopyFile() throws Exception {
		// 创建一个文件输入通道
		FileChannel fcin = new FileInputStream("E:\\javaseio\\my\\向往的.jpg").getChannel();
		// 创建一个文件输出通道
		FileChannel fcout = new FileOutputStream("E:\\javaseio\\my\\me\\向往的.jpg").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		while (fcin.read(buf) != -1) {
			buf.flip();
			fcout.write(buf);
			buf.clear();
		}
		System.out.println("Copy Success.");
		fcout.close();
		fcin.close();
	}

	public static void main(String[] args) {
		try {
			// CopyFile();
			RandomAccessFileCopy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
