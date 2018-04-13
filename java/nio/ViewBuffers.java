package com.google.javase.nio;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class ViewBuffers {
	private static int BESIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(BESIZE);
		IntBuffer ib = buf.asIntBuffer();
		ib.put(new int[] {11,24,56,77,88,35});
		System.out.println(ib.get(3));
		ib.put(3, 111);
		ib.flip();
		while(ib.hasRemaining()) {
			System.out.print(ib.get()+" ");
		}
		ByteBuffer buf1 = ByteBuffer.wrap(new byte[] {1,2,3,4,5,6,7,'a','b'});
		buf1.rewind();
		System.out.println("Byte buffer:");
		while(buf1.hasRemaining()) {
			System.out.print(buf1.position()+"->"+buf1.get()+" ");
		}
		FloatBuffer fb = ((ByteBuffer)buf1.rewind()).asFloatBuffer();
		System.out.println("Float Buffer:");
		while(fb.hasRemaining()) {
			System.out.print(fb.position()+"->"+fb.get()+" ");
		}
		
		
	}

}
