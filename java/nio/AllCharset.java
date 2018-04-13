package com.google.javase.nio;
/**
 * 打印所有的编码格式
 * @author LENOVO
 *
 */
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AllCharset {
	
	public static void main(String[] args) {
		SortedMap<String,Charset> charset = Charset.availableCharsets();
		Iterator<String> it = charset.keySet().iterator();
		while(it.hasNext()) {
			String csName = it.next();
			System.out.print(csName);
			Iterator<String> alias = charset.get(csName).aliases().iterator();
			if(alias.hasNext()) {
				System.out.print(":");
			}
			while(alias.hasNext()) {
				System.out.print(alias.next());
			}
			System.out.println();
		}
		
	}

}
