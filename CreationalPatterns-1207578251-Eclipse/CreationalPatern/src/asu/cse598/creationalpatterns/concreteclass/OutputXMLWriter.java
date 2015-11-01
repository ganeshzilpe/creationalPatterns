package asu.cse598.creationalpatterns.concreteclass;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import asu.cse598.creationalpatterns.abstractclass.OutputWriter;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.AssignedWork;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.dao.GradedWork;
import asu.cse598.creationalpatterns.singleton.BlackBoard;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class OutputXMLWriter extends OutputWriter{

	@Override
	public String generateFile(List<Student> studentList) {

		BlackBoard blackBoard= BlackBoard.getInstance();
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("GradeBook");
			rootElement.setAttribute("class", "CSE 598");
			doc.appendChild(rootElement);

			
			GradeItem gradeItem;
			if(studentList.get(0) instanceof GraduateStudent)
				gradeItem = GradeBook.getInstance().getGraduateGradeItem();
			else
				gradeItem = GradeBook.getInstance().getUndergraduateGradeItem();
			
			// Grades elements
			Element grades = doc.createElement("Grades");
			rootElement.appendChild(grades);
			for(int i=0; i<studentList.size(); i++)
			{

				//student node
				Element studentNode = doc.createElement("Student");
				grades.appendChild(studentNode);


				Element name = doc.createElement("Name");
				name.appendChild(doc.createTextNode(studentList.get(i).getName()));
				studentNode.appendChild(name);

				Element id = doc.createElement("ID");
				id.appendChild(doc.createTextNode(studentList.get(i).getId()));
				studentNode.appendChild(id);

				
				AssignedWork graduateAssignedWork = studentList.get(i).getAssignedWork();
				Map <String, GradedWork> assignedWorkList = graduateAssignedWork.getAssignedWorkList();
				for (Entry<String, String> entry : (gradeItem.getGradeItemList()).entrySet()) {

					String gradeItemType = entry.getKey();
					Element assignedWorkNode = doc.createElement("AssignedWork");
					assignedWorkNode.setAttribute("category", gradeItemType);
					studentNode.appendChild(assignedWorkNode);


					GradedWork gradework = assignedWorkList.get(gradeItemType);
					Map <String, String> gradedWorkList = gradework.getGradedWorkList();

					for (Entry<String, String> innerEntry : gradedWorkList.entrySet())
					{
						Element gradedWorkNode = doc.createElement("GradedWork");
						assignedWorkNode.appendChild(gradedWorkNode);


						Element nameNode = doc.createElement("Name");
						nameNode.appendChild(doc.createTextNode(innerEntry.getKey()));
						gradedWorkNode.appendChild(nameNode);

						Element gradeNode = doc.createElement("Grade");
						gradeNode.appendChild(doc.createTextNode(innerEntry.getValue()));
						gradedWorkNode.appendChild(gradeNode);
					}	
				}
				Element letterGradeNode = doc.createElement("LetterGrade");
				letterGradeNode.appendChild(doc.createTextNode((studentList.get(i)).getFinalGrade().getFinalGrade()));
				studentNode.appendChild(letterGradeNode);
				
			}


			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File("XMLOutputGradeBook.xml");
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
			System.out.println("File saved!");
			return file.getAbsolutePath();
			

		} catch (ParserConfigurationException pce) {
			blackBoard.getUserInterface().setMessage("FIle not found or File is not in proper format");
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			blackBoard.getUserInterface().setMessage("FIle not found or File is not in proper format");
			tfe.printStackTrace();
		}
		return "Error while creating output file";


	}

}