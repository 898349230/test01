package com.ab.copyfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestPrint {

	public static void main(String[] args) {
		//print("C:/Users/Administrator/Desktop/���Ա�����","C:/Users/Administrator/Desktop/ceshi",0);
		//indexOf("abcdefg","cde");
		try {
			System.out.println(System.currentTimeMillis());
			copy("C:/Users/Administrator/Desktop/java��ҵ���Ա���.chm","C:/Users/Administrator/Desktop/��ҵ���Ա���.chm");
			System.out.println("�������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��״��ӡ�ļ�����
	 * @param sourceFolder
	 * @param a
	 */
	public static void print(String sourceFolder,int a){
		File file = new File(sourceFolder);
		//�Ƿ�Ϊ�ļ�
		file.isFile();
		//ɾ���ļ�
		file.delete();
		//�����ļ�
		file.mkdir();
		if(file.isFile()){
			for(int i = 0;i<=a;i++){
				System.out.print("-");
			}
			System.out.println(file.getName());
		}else{
			for(int i = 0;i<=a;i++){
				System.out.print("-");
			}
			System.out.println(file.getName());
			File[] files = file.listFiles();
			for(File f : files){
				print(f.getAbsolutePath(),a+1);
			}
		}
	}
	/**
	 * ��һ���ַ����в���ĳһ�ַ���������λ��
	 * @param source
	 * @param target
	 * @return
	 */
	public static int indexOf(String source,String target){
		String[] strArr = source.split(target);
		if(strArr.length<0){
			System.out.println(source+"��û�и��ַ���");
			return 0;
		}else{
			int idx = strArr[0].length();
			//System.out.println(idx);
			return idx;
		}
	}
	/**
	 * �����ļ�
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void copy(String source,String target) throws Exception{
		
			InputStream is = new FileInputStream(source);
			OutputStream os = new FileOutputStream(target);
			byte[] b = new byte[1024];
				while(is.read(b)!=-1){
					os.write(b);
				}
	}
}
