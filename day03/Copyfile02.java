package day03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
/*
 * 使用文件流复制myfile.txt文件为myfile_cp.txt
 */
public class Copyfile02 {
	public static void main(String[] args) {

		Copyfile02 f= new Copyfile02();
		try {
		f.copy("e:\\IBMFile\\myfile.txt","e:\\IBMFile\\myfile_cp_.txt");

		} catch (Exception e) {
		e.printStackTrace();
		}

		}
		public void copy(String f1,String f2) throws Exception{
		FileInputStream fis = new FileInputStream(f1); //首先构建输入流的对象,指定需要读取的文件路径 
		FileOutputStream fos = new FileOutputStream(f2,false); //构建文件输出流的对象，即将文件复制在哪里去,后面的true代表每次写入时不清空当前文件内容 

		//方法一，单字节复制
		 int value = fis.read();  //一个字节一个字节的读取文件的内容 
		 while(value!=-1){
		 fos.write(value);
		fos.flush();
		 value = fis.read();
		 	
		 }
		}
}
