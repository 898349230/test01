package java8.copy3;

import java.io.File;
import java.io.FileFilter;

public class File01 {
	public static void main(String[] args) {
		// java 8 之前
		File[] hiddenFile = new File("D:\\新建文件夹").listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isHidden();
			}
		});
		for (File file : hiddenFile) {
			System.out.println("旧 ： " + file.getName());
		}
		// java 8
		File[] hiddenFile2 = new File("D:\\新建文件夹").listFiles(File :: isHidden);
		for (File file : hiddenFile) {
			System.out.println("新  ： " + file.getName());
		}
	}
}
