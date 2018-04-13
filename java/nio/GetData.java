/**
 * 获取基本类型数据
 * @author LENOVO
 *
 */
package com.google.javase.nio;

import java.nio.ByteBuffer;

public class GetData {
	private static int BESIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(BESIZE);
		int i=0;
		while(i++<buf.limit())
			if(buf.get()!=0)
				System.out.println("nozero");
		System.out.println("i="+i);
		buf.rewind();
		buf.asCharBuffer().put("Howday!");
		char c;
		while((c=buf.getChar())!=0) {
			System.out.print(c);
		}
		System.out.println();
		buf.rewind();
		buf.asIntBuffer().put(12345);
		System.out.println(buf.getInt());
		
	}

}
