package asu.cse598.creationalpatterns.concreteclass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import asu.cse598.creationalpatterns.abstractclass.OutputWriter;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.AssignedWork;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.dao.GradedWork;
import asu.cse598.creationalpatterns.singleton.BlackBoard;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class OutputCSVWriter extends OutputWriter{

	@Override
	public String generateFile(List<Student> studentList) 
	{
		BlackBoard blackBoard= BlackBoard.getInstance();
		FileWriter writer;
		try 
		{
			//File file = new File(getFilename());
			File file = new File("CSVOutputGradeBook.csv");
			writer = new FileWriter(file);


			Student student = studentList.get(0);

			writer.append("Name");
			writer.append(',');
			writer.append("ID");
			writer.append(',');

			GradeItem gradeItem;
			if(student instanceof GraduateStudent)
				gradeItem = GradeBook.getInstance().getGraduateGradeItem();
			else
				gradeItem = GradeBook.getInstance().getUndergraduateGradeItem();
			AssignedWork graduateAssignedWork = student.getAssignedWork();
			Map <String, GradedWork> assignedWorkList = graduateAssignedWork.getAssignedWorkList();
			for (Entry<String, String> entry : (gradeItem.getGradeItemList()).entrySet()) {

				String gradeItemType = entry.getKey();

				GradedWork gradework = assignedWorkList.get(gradeItemType);
				Map <String, String> gradedWorkList = gradework.getGradedWorkList();

				for (Entry<String, String> innerEntry : gradedWorkList.entrySet())
				{
					writer.append(innerEntry.getKey());
					writer.append(',');
				}
			}
			writer.append("Grade");
			writer.append('\n');


			for(int i=0; i<studentList.size(); i++)
			{
				writer.append(studentList.get(i).getName());
				writer.append(',');
				writer.append(studentList.get(i).getId());
				writer.append(',');
				graduateAssignedWork = studentList.get(i).getAssignedWork();
				assignedWorkList = graduateAssignedWork.getAssignedWorkList();
				for (Entry<String, String> entry : (gradeItem.getGradeItemList()).entrySet()) {

					String gradeItemType = entry.getKey();

					GradedWork gradework = assignedWorkList.get(gradeItemType);
					Map <String, String> gradedWorkList = gradework.getGradedWorkList();

					for (Entry<String, String> innerEntry : gradedWorkList.entrySet())
					{
						writer.append(innerEntry.getValue());
						writer.append(',');
					}
				}
				writer.append((studentList.get(i)).getFinalGrade().getFinalGrade());
				writer.append('\n');
			}
			
			writer.flush();
			writer.close();
			return file.getAbsolutePath();
		} catch (IOException e) 
		{
			blackBoard.getUserInterface().setMessage("FIle not found or File is not in proper format");
			
			e.printStackTrace();
			return "Error while creating output file";
		}
	}

}
