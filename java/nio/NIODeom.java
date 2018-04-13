package com.google.javase.nio;

import java.nio.ByteBuffer;

/**
 * NIO以块的方式处理数据，所有的数据都是在缓冲区处理的
 * @author liningbo
 * @date:2018年3月26日下午3:52:32
 * @version 1.0
 */
public class NIODeom {

	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(8);
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
		buf.put((byte) 10);
		buf.put((byte) 20);
		buf.put((byte) 30);
		buf.put((byte) 40);
		System.out.println("----------------");
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
		//缓冲区反转截取当前字节
		buf.flip();
		System.out.println("----------------");
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
//		while(buf.hasRemaining()) {
//			System.out.println(buf.get());
//		}
		if(buf.hasRemaining()) {
			for(int i=buf.position();i<buf.remaining();i++) {
				byte b = buf.get(i);
				System.out.println(b);
			}
		}
		System.out.println("----------------");
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
	}

}
