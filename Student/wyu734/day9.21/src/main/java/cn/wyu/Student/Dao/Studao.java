package cn.wyu.Student.Dao;

import java.util.List;

import cn.wyu.Student.model.student;

public interface Studao {
	public student findstuById(int id) throws Exception;

	public List<student> findAllStus() throws Exception;

	public void insertUser(student stu) throws Exception;

	public void deleteStuById(int id) throws Exception;

	public void updateStuName(student stu) throws Exception;
}
