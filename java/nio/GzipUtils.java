package com.google.javase.nio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
/**
 * 图片压缩工具类
 * @author liningbo
 * @date:2018年3月25日下午4:20:02
 * @version 1.0
 */

public class GzipUtils {
	public static void gzip(byte[] data) {
		try {
			//压缩流要和ByteArrayOutputStream一起使用
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			gos.write(data);
			gos.flush();
			//压缩之后文件大小
			byte[] bytes = baos.toByteArray();
			baos.close();
			gos.close();
			System.out.println("压缩后文件大小:"+bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) {
		File file = new File("E:\\javaseio\\my\\向往的.jpg");
		try {
			InputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);    //读入内存
			fis.close();
			System.out.println("压缩前的文件大小:"+bytes.length);
			gzip(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
