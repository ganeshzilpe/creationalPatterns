package asu.cse598.creationalpatterns.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import asu.cse598.creationalpatterns.abstractclass.GradeReader;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.factory.GradeReaderFactory;

/**
 * 
 * @author ganesh zilpe
 * GradeBook is a singleton class
 *
 */
public class GradeBook {
	
	List<Student> studentList = new ArrayList<Student>();
	Map <String, GradeItem> gradeItemList= new LinkedHashMap<String, GradeItem>();
	private String className = "";

	// Create the object for the first and last time
	private static  GradeBook object = new GradeBook(); 
	 
    private GradeBook() {
       gradeItemList.put("Undergraduate", new GradeItem());
       gradeItemList.put("Graduate", new GradeItem());
    }

    public static GradeBook getInstance() {
        return object;
    }
    
    public void clear()
    {
    	studentList = new ArrayList<Student>();
    	gradeItemList= new LinkedHashMap<String, GradeItem>();
    	gradeItemList.put("Undergraduate", new GradeItem());
        gradeItemList.put("Graduate", new GradeItem());
    	className = "";
    }
    
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void addStudent(Student student)
	{
		this.studentList.add(student);
	}
	
	
	public GradeItem getGraduateGradeItem()
	{
		return gradeItemList.get("Graduate");
	}
	
	public GradeItem getUndergraduateGradeItem()
	{
		return gradeItemList.get("Undergraduate");
	}
	
	public void setGraduateGradeItem(GradeItem gradeItem)
	{
		gradeItemList.put("Graduate", gradeItem);
	}
	
	public void setUndergraduateGradeItem(GradeItem gradeItem)
	{
		gradeItemList.put("Undergraduate", gradeItem);
	}
	
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean setStudentData(String studentType, String fileName)
	{
		GradeReaderFactory readerFactory = new GradeReaderFactory();
		GradeReader reader = readerFactory.getGradeReader(studentType);
		reader.setFileName(fileName);
		return reader.readData();
	}
	
	
}
