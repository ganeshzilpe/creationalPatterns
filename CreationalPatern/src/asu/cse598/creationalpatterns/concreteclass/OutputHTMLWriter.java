package asu.cse598.creationalpatterns.concreteclass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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

public class OutputHTMLWriter extends OutputWriter{

	@Override
	public String generateFile(List<Student> studentList) {

		BlackBoard blackBoard= BlackBoard.getInstance();
		System.out.println("HTML file created");
		Writer output = null;
		File file = new File("HTMLOutputGradeBook.html");
		try
		{
			output = new BufferedWriter(new FileWriter(file));
			output.write("<HTML><HEAD>	<TITLE>Gradebook</TITLE></HEAD><BODY>	<CENTER>");
			String category="";
			Student student = studentList.get(0);
			output.write("<TABLE border='1'><TH bgcolor='#4141FF'>Name</TH><TH bgcolor='#4141FF'>ID</TH>");
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
					output.write("<TH bgcolor='#4141FF'>"+innerEntry.getKey()+"</TH>");
				}
			}

			output.write("<TH bgcolor='#4141FF'>Grade</TH>");
			for(int i=0; i<studentList.size(); i++)
			{
				output.write("<TR>");
				output.write("<TD>"+studentList.get(i).getName()+"</TD>");
				output.write("<TD>"+studentList.get(i).getId()+"</TD>");

				
				graduateAssignedWork = studentList.get(i).getAssignedWork();
				assignedWorkList = graduateAssignedWork.getAssignedWorkList();
				for (Entry<String, String> entry : (gradeItem.getGradeItemList()).entrySet()) {

					String gradeItemType = entry.getKey();

					GradedWork gradework = assignedWorkList.get(gradeItemType);
					Map <String, String> gradedWorkList = gradework.getGradedWorkList();

					for (Entry<String, String> innerEntry : gradedWorkList.entrySet())
					{
						output.write("<TD>"+innerEntry.getValue()+"</TD>");

					}
				}
				output.write("<TD>"+(studentList.get(i)).getFinalGrade().getFinalGrade()+"</TD>");
				output.write("</TR>");
			}
			output.write("</CENTER></BODY></HTML>");
			output.close();
			System.out.println("Find your file at: "); 
			return file.getAbsolutePath();
			
		}
		catch(Exception e)
		{
			blackBoard.getUserInterface().setMessage("FIle not found or File is not in proper format");
			System.out.println("File is corrupted");
			e.printStackTrace();
			return "Error while creating output file";
		}
		
	}

}