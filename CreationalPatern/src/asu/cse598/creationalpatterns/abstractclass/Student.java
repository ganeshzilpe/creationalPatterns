package asu.cse598.creationalpatterns.abstractclass;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import asu.cse598.creationalpatterns.dao.AssignedWork;

public abstract class Student {

	private String name = "";
	private String id = "";
	private AssignedWork assignedWork=null;
	private String StudentType = "";
	protected Grade finalGrade = null;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public AssignedWork getAssignedWork() {
		return assignedWork;
	}
	public void setAssignedWork(AssignedWork assignedWork) {
		this.assignedWork = assignedWork;
	}
	public String getStudentType() {
		return StudentType;
	}
	public void setStudentType(String studentType) {
		StudentType = studentType;
	}
	

	abstract public void calculateFinalGrade();
	
	abstract public void setFinalGrade(String finalGrade);
	
	abstract public Grade getFinalGrade();

	public double getMarksForLetterGrade(String letterGrade)
	{
		Map<String, Double> list = new LinkedHashMap<String ,Double>();

		list.put("A+", (double) 99);
		list.put("A", (double) 95);
		list.put("A-", (double) 90);
		list.put("B+", (double) 87);
		list.put("B", (double) 84);
		list.put("B-", (double) 80);
		list.put("C+", (double) 75);
		list.put("A", (double) 70);
		list.put("D", (double) 60);
		list.put("E", (double) 59);
		if(list.containsKey(letterGrade))
			return list.get(letterGrade);
		else
			return 0.0;
	}

	public String calculateLetterGrade (double marks)
	{

		if(marks >= 99)
			return "A+";
		else if(marks <99 && marks >= 95)
			return "A";
		else if(marks <95 && marks >= 90)
			return "A-";
		else if(marks <90 && marks >= 87)
			return "B+";
		else if(marks <87 && marks >= 84)
			return "B";
		else if(marks <84 && marks >= 80)
			return "B-";
		else if(marks <80 && marks >= 75)
			return "C+";
		else if(marks <75 && marks >= 70)
			return "C";
		else if(marks <70 && marks >= 60)
			return "D";
		else
			return "E";
	}
}
