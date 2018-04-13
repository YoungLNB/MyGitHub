package com.google.javase.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;



public class PathFilesDeom {

	public static void main(String[] args) {
		//Path接口
		File file = new File("E:\\javaseio\\my\\向往的.jpg");
		Path p1 = Paths.get("E:\\javaseio\\my", "向往的.jpg");
		System.out.println(p1);
		Path p2 = file.toPath();
		System.out.println(p2);
		Path p3 = FileSystems.getDefault().getPath("E:\\javaseio\\my", "向往的.jpg");
		System.out.println(p3);
		
		//Files工具类
		Path p4 = Paths.get("E:\\javaseio\\my\\mine\\your.txt");
		//写入文件
//		String info = "努力学习，奋斗不止";
//		try {
//			Files.write(p4, info.getBytes("gb2312"), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//读取文件
		try {
			byte[] bytes = Files.readAllBytes(p4);
			System.out.println(new String(bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path p5 = Paths.get("E:\\图片", "Like.jpg");
		//复制文件
//		try {
//			Files.copy(Paths.get("E:\\javaseio\\my\\me\\HEAT.jpg"), Paths.get("E:\\图片\\Like.jpg"), StandardCopyOption.COPY_ATTRIBUTES);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//移动文件
//		try {
//			Files.move(p5, Paths.get("E:\\javaseio\\my\\me\\HEAT.jpg"), StandardCopyOption.ATOMIC_MOVE);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//删除文件
//		try {
//			Files.delete(Paths.get("E:\\javaseio\\my\\me\\HEAT.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//创建新目录
//		try {
//			Files.createDirectory(Paths.get("E:\\javaseio\\your"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//创建一层一层的文件
		try {
			Files.createDirectories(Paths.get("E:\\javaseio\\Her\\she\\hers"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
