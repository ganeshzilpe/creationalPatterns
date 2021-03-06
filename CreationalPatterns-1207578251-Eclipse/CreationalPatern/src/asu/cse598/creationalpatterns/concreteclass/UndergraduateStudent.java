package asu.cse598.creationalpatterns.concreteclass;

import java.util.Map;
import java.util.Map.Entry;

import asu.cse598.creationalpatterns.abstractclass.Grade;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.AssignedWork;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.dao.GradedWork;
import asu.cse598.creationalpatterns.factory.GradeFactory;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class UndergraduateStudent extends Student{

	@Override
	public void calculateFinalGrade() {


		double totalMarks = 0.0;

		GradeItem undergradauteGradeItem = GradeBook.getInstance().getUndergraduateGradeItem();
		AssignedWork undergraduateAssignedWork = getAssignedWork();
		Map <String, GradedWork> assignedWorkList = undergraduateAssignedWork.getAssignedWorkList();
		
		for (Entry<String, String> entry : (undergradauteGradeItem.getGradeItemList()).entrySet()) {

			String gradeItemType = entry.getKey();
			Double percentage = Double.parseDouble(entry.getValue());
			
			GradedWork gradework = assignedWorkList.get(gradeItemType);
			Map <String, String> gradedWorkList = gradework.getGradedWorkList();
			double totalMarksForCategory = 0.0;
			int counter = 0;
			for (Entry<String, String> innerEntry : gradedWorkList.entrySet())
			{
				counter++;
				if(innerEntry.getValue().matches("[0-9]{1,3}"))
				{
					totalMarksForCategory += Double.parseDouble(innerEntry.getValue());
				}
				else
				{
					totalMarksForCategory += getMarksForLetterGrade(innerEntry.getValue());
				}
			}
			
			totalMarks += ((totalMarksForCategory /(counter*100)) * (percentage/100));
		}
		setFinalGrade(calculateLetterGrade(totalMarks));
	
		
	}

	@Override
	public void setFinalGrade(String finalGradeTemp) {

		Grade grade = GradeFactory.getGrade("Undergraduate");
		grade.setFinalGrade(finalGradeTemp);
		finalGrade = grade;
		
	}

	@Override
	public Grade getFinalGrade() {
		// TODO Auto-generated method stub
		return finalGrade;
	}

}
