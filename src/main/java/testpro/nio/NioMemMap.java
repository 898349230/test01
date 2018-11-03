package testpro.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioMemMap {
    public static void main(String[] args) {
    	
    }
    
    /**
     * 测试读取时间
     * 
     */
	public static void readFileTime() {
		try {
			RandomAccessFile file = new RandomAccessFile(
					"C:\\Users\\lzx-t050\\Desktop\\新建文件夹\\pdf\\MySQL高性能书籍_第3版（中文）.pdf", "rw");
			FileChannel channel = file.getChannel();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			ByteBuffer buffer1 = ByteBuffer.allocate(1024);
			byte[] b = new byte[1024];

			long len = file.length();
			long startTime = System.currentTimeMillis();
			// 读取内存映射文件
			for (int i = 0; i < file.length(); i += 1024 * 10) {
				if (len - i > 1024) {
					buffer.get(b);
				} else {
					buffer.get(new byte[(int) (len - i)]);
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("使用内存映射方式读取文件总耗时： " + (endTime - startTime));

			// 普通IO流方式
			long startTime1 = System.currentTimeMillis();
			while (channel.read(buffer1) > 0) {
				buffer1.flip();
				buffer1.clear();
			}

			long endTime1 = System.currentTimeMillis();
			System.out.println("使用普通IO流方式读取文件总耗时： " + (endTime1 - startTime1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    private static int len;
    
    /**
     * 读文件
     *
     * @param fileName
     * @return
     */
    public static ByteBuffer readFile(String fileName) {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            len = (int) file.length();
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, len);

            return buffer.get(new byte[(int) file.length()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写文件
     *
     * @param readFileName
     * @param writeFileName
     */
    public static void writeFile(String readFileName, String writeFileName) {
        try {
            RandomAccessFile file = new RandomAccessFile(writeFileName, "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = readFile(readFileName);

            MappedByteBuffer bytebuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, len);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                bytebuffer.put(i, buffer.get(i));
            }
            bytebuffer.flip();
            long endTime = System.currentTimeMillis();
            System.out.println("写文件耗时： " + (endTime - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}