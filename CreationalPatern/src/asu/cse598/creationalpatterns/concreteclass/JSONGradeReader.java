package asu.cse598.creationalpatterns.concreteclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;

import asu.cse598.creationalpatterns.abstractclass.GradeReader;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.AssignedWork;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.dao.GradedWork;
import asu.cse598.creationalpatterns.singleton.BlackBoard;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class JSONGradeReader extends GradeReader{

	String fileName = "";
	@Override
	public boolean readData() {
		//for testing
		//fileName = "/asu/cse598/creationalpatterns/input/jsonGradesGoodData.json";
		boolean success = false;
		try {
			//fileName = "C:\\Users\\zilpe\\Desktop\\jsonGradesBadData.json";
			InputStream fileStream = new FileInputStream(fileName);//JSONGradeReader.class.getResourceAsStream(fileName);

			//Gradebook Instance
			GradeBook gradeBookInstance = GradeBook.getInstance();

			System.out.println(fileName);
			
			//print the file
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
			StringBuilder builder = new StringBuilder();
			String line;
			
			
			while((line=bufferedReader.readLine()) != null)
			{
				System.out.println(line);
				builder.append(line);
			}

			System.out.println(builder.toString());

			JSONObject jsonObject = new JSONObject(builder.toString());

			JSONObject gradingBook = jsonObject.getJSONObject("GradeBook");
			String className = gradingBook.getString("-class");

			//set class name in gradeBookInstance
			gradeBookInstance.setClassName(className);

			//get grading schema for undergraduate students
			JSONObject gradingSchema = gradingBook.getJSONObject("GradingSchema");

			//get gradeItems of grading schema
			JSONArray gradeItems = gradingSchema.getJSONArray("GradeItem");

			GradeItem gradeItemForUndergraduate  = new GradeItem();
			Map<String, String> map = gradeItemForUndergraduate.getGradeItemList();

			for (int i = 0; i < gradeItems.length(); i++) {
				JSONObject gradeItem = gradeItems.getJSONObject(i);

				map.put(gradeItem.getString("Category"), gradeItem.getString("Percentage"));

			}
			gradeBookInstance.setUndergraduateGradeItem(gradeItemForUndergraduate);

			//get grades of student
			JSONObject grades = gradingBook.getJSONObject("Grades");
			JSONArray students = grades.getJSONArray("Student");

			List<Student> studentList = gradeBookInstance.getStudentList();



			for (int i = 0; i < students.length(); i++) {
				Student underGraduateStudent  = new UndergraduateStudent();
				JSONObject student = students.getJSONObject(i);

				underGraduateStudent.setName(student.getString("Name"));
				underGraduateStudent.setId(student.getString("ID"));

				JSONArray assignedWorks = student.getJSONArray("AssignedWork");
				AssignedWork undergraduateAssignedWork = new AssignedWork();
				Map<String, GradedWork> assignedWorkList = undergraduateAssignedWork.getAssignedWorkList();

				for (int j = 0; j < assignedWorks.length(); j++) 
				{

					JSONObject assignedWork = assignedWorks.getJSONObject(j);

					String category = assignedWork.getString("-category");
					JSONArray gradedWorks = null;
					Object sample = assignedWork.get("GradedWork");
					GradedWork undergraduategGradedWork = new GradedWork();
					if (sample instanceof JSONArray) {
						// It's an array
						gradedWorks = (JSONArray)sample;


						for (int k = 0; k < gradedWorks.length(); k++) 
						{
							JSONObject gradedWork = gradedWorks.getJSONObject(k);
							undergraduategGradedWork.addGradedWork(gradedWork.getString("Name"), gradedWork.getString("Grade"));
						}

					}
					else if(sample instanceof JSONObject)
					{
						JSONObject gradedWork = (JSONObject)sample	;
						undergraduategGradedWork.addGradedWork(gradedWork.getString("Name"), gradedWork.getString("Grade"));
					}
					assignedWorkList.put(category, undergraduategGradedWork);

				}
				underGraduateStudent.setAssignedWork(undergraduateAssignedWork);
				underGraduateStudent.setStudentType("Undergraduate");
				studentList.add(underGraduateStudent);

			}
			StreamResult result = new StreamResult(new File("MenuXML.xml"));

			gradeBookInstance.setStudentList(studentList);
			success = true;
			return success;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JSON file is not proper.");
			
			success = false;
			return false;
		}
		finally {
			if(success)
				return true;
			else
			{
				return success;
			}
		}
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;

	}

}
