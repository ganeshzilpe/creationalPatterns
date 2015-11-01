package asu.cse598.creationalpatterns.factory;

import asu.cse598.creationalpatterns.abstractclass.Grade;
import asu.cse598.creationalpatterns.concreteclass.GraduateGrade;
import asu.cse598.creationalpatterns.concreteclass.UndergraduateGrade;

public class GradeFactory {
	
	public static Grade getGrade(String objectType)
	{
		Grade grade = null;
		if("Undergraduate".equalsIgnoreCase(objectType))
		{
			grade = new UndergraduateGrade();
		}
		else if("Graduate".equalsIgnoreCase(objectType))
		{
			grade = new GraduateGrade();
		}
		return grade;
	}

}