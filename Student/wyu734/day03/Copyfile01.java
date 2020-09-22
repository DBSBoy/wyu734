package day03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copyfile01 {
	public static void main(String[] args) {

		Copyfile01 f= new Copyfile01();
		try {
		f.copy("e:\\IBMFile\\myfile.txt","e:\\IBMFile\\myfile_cp_3.txt");

		} catch (Exception e) {
		e.printStackTrace();
		}

		}
		public void copy(String f1,String f2) throws Exception{
		FileInputStream fis = new FileInputStream(f1); //首先构建输入流的对象,指定需要读取的文件路径 
		BufferedInputStream bfi=new BufferedInputStream(fis);
		FileOutputStream fos = new FileOutputStream(f2,false); //构建文件输出流的对象，即将文件复制在哪里去,后面的true代表每次写入时不清空当前文件内容 
		BufferedOutputStream bfo=new BufferedOutputStream(fos);
		//方法一，单字节复制
		byte[] b=new byte[1024];   //代表一次最多读取1KB的内容

		int length = 0 ; //代表实际读取的字节数
		while( (length = bfi.read( b ) )!= -1 ){
			//length 代表实际读取的字节数
			bfo.write(b, 0, length );
		}
        //缓冲区的内容写入到文件
		bfo.flush();
		bfo.close();
		}

}
