package com.google.javase.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {
	private static void sysmmeticScramble(CharBuffer buffer) {
		while(buffer.hasRemaining()) {
			buffer.mark();
			char c1 = buffer.get();
			char c2 = buffer.get();
			buffer.reset();
			buffer.put(c2).put(c1);
		}
	}
	public static void main(String[] args) {
		char[] data = "usingbuffer".toCharArray();
		ByteBuffer buf = ByteBuffer.allocate(data.length*2);
		CharBuffer cb = buf.asCharBuffer();
		cb.put(data);
		System.out.println(cb.rewind());
		sysmmeticScramble(cb);
		System.out.println(cb.rewind());
		sysmmeticScramble(cb);
		System.out.println(cb.rewind());
	}

}
