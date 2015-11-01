package asu.cse598.creationalpatterns.factory;

import asu.cse598.creationalpatterns.abstractclass.Grade;
import asu.cse598.creationalpatterns.abstractclass.OutputWriter;
import asu.cse598.creationalpatterns.concreteclass.GraduateGrade;
import asu.cse598.creationalpatterns.concreteclass.OutputCSVWriter;
import asu.cse598.creationalpatterns.concreteclass.OutputHTMLWriter;
import asu.cse598.creationalpatterns.concreteclass.OutputXMLWriter;
import asu.cse598.creationalpatterns.concreteclass.UndergraduateGrade;

public class GradeOutputFactory {
	public static OutputWriter getGrade(String outputType)
	{
		OutputWriter writer = null;
		if("csv".equalsIgnoreCase(outputType))
		{
			writer = new OutputCSVWriter();
		}
		else if("html".equalsIgnoreCase(outputType))
		{
			writer = new OutputHTMLWriter();
		}
		else if("xml".equalsIgnoreCase(outputType))
		{
			writer = new OutputXMLWriter();
		}
		return writer;
	}

}
