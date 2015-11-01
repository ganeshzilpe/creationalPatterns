package asu.cse598.creationalpatterns.factory;

import asu.cse598.creationalpatterns.abstractclass.GradeReader;
import asu.cse598.creationalpatterns.concreteclass.JSONGradeReader;
import asu.cse598.creationalpatterns.concreteclass.XMLGradeReader;

public class GradeReaderFactory {
	
	public GradeReader getGradeReader(String objectType)
	{
		GradeReader gradeReader = null;
		if("JSON".equalsIgnoreCase(objectType))
		{
			gradeReader = new JSONGradeReader();
		}
		else if("XML".equalsIgnoreCase(objectType))
		{
			gradeReader = new XMLGradeReader();
		}
		return gradeReader;
	}

}
