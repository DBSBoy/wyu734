package day03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
/*
 * ʹ���ļ�������myfile.txt�ļ�Ϊmyfile_cp.txt
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
		FileInputStream fis = new FileInputStream(f1); //���ȹ����������Ķ���,ָ����Ҫ��ȡ���ļ�·�� 
		FileOutputStream fos = new FileOutputStream(f2,false); //�����ļ�������Ķ��󣬼����ļ�����������ȥ,�����true����ÿ��д��ʱ����յ�ǰ�ļ����� 

		//����һ�����ֽڸ���
		 int value = fis.read();  //һ���ֽ�һ���ֽڵĶ�ȡ�ļ������� 
		 while(value!=-1){
		 fos.write(value);
		fos.flush();
		 value = fis.read();
		 	
		 }
		}
}
