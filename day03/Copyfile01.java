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
		FileInputStream fis = new FileInputStream(f1); //���ȹ����������Ķ���,ָ����Ҫ��ȡ���ļ�·�� 
		BufferedInputStream bfi=new BufferedInputStream(fis);
		FileOutputStream fos = new FileOutputStream(f2,false); //�����ļ�������Ķ��󣬼����ļ�����������ȥ,�����true����ÿ��д��ʱ����յ�ǰ�ļ����� 
		BufferedOutputStream bfo=new BufferedOutputStream(fos);
		//����һ�����ֽڸ���
		byte[] b=new byte[1024];   //����һ������ȡ1KB������

		int length = 0 ; //����ʵ�ʶ�ȡ���ֽ���
		while( (length = bfi.read( b ) )!= -1 ){
			//length ����ʵ�ʶ�ȡ���ֽ���
			bfo.write(b, 0, length );
		}
        //������������д�뵽�ļ�
		bfo.flush();
		bfo.close();
		}

}
