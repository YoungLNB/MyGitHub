package com.google.javase.nio;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class MappedIO {

	private static int numOfInts = 4000000;

	
	private abstract static class Tester{
		private String name;
		public Tester(String name){
			this.name = name;
		}
		public void runTest() {
			System.out.print(name+":");
			long start = System.nanoTime();
			test();
			double duration = System.nanoTime()-start;
			
			System.out.format("%.2f\n", duration/1.0e9);
		}
		public abstract void test();
	}
	private static Tester[] testers= {
			new Tester("Stream Write"){

				@Override
				public void test() {
					try {
						OutputStream out =new FileOutputStream("E:\\javaseio\\d.txt");
						DataOutputStream dos = new DataOutputStream(out);
	                    for(int i=0;i<numOfInts;i++) {
	                        dos.writeByte(i);
	                        dos.flush();
	                    }                   
	                    dos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			},
			new Tester("Mapped Write") {

				@Override
				public void test() {
						try(
							FileChannel fc = FileChannel.open(Paths.get("E:\\javaseio\\d.txt"),
						StandardOpenOption.READ,StandardOpenOption.WRITE);){
							MappedByteBuffer mapBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts);
		                    for(int i=0;i<numOfInts;i++) {
		                        mapBuffer.put((byte)0);
		                    }
		                    mapBuffer.flip();
		                    while(mapBuffer.hasRemaining()) {
		                        mapBuffer.get();
		                    }
						
					} catch (IOException e) {
						e.printStackTrace();
					}																		
				}
				
			},
			 new Tester("Mapped PRIVATE") {
	            public void test() {
	                try (FileChannel channel = FileChannel.open(Paths.get("E:\\javaseio\\d.txt"),
	                        StandardOpenOption.READ, StandardOpenOption.WRITE);) {
	                    MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.PRIVATE, 0, numOfInts);
	                    for(int i=0;i<numOfInts;i++) {
	                        mapBuffer.put((byte)0);
	                    }
	                    mapBuffer.flip();
	                    while(mapBuffer.hasRemaining()) {
	                        mapBuffer.get();
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }, 
	};
	public static void main(String[] args) {
		for(Tester tester:testers) {
            tester.runTest();
        }

	}
	
}
