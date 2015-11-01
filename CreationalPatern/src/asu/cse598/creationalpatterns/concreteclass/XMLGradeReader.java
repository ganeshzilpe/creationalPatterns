package asu.cse598.creationalpatterns.concreteclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import asu.cse598.creationalpatterns.abstractclass.GradeReader;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.dao.AssignedWork;
import asu.cse598.creationalpatterns.dao.GradeItem;
import asu.cse598.creationalpatterns.dao.GradedWork;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class XMLGradeReader extends GradeReader{

	String fileName = "";

	@Override
	public boolean readData() {

		//for testing
		//fileName = "src/asu/cse598/creationalpatterns/input/xmlGradesGoodData.xml";
		//remember to replace the file separator with "/"
		//fileName = "G:/ASU/MS III/SAD/Projects/Project1/xmlGradesGoodData.xml";
		boolean success = false;
		try{
			File XmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();

			Element rootElement = doc.getDocumentElement();

			//Gradebook Instance
			GradeBook gradeBookInstance = GradeBook.getInstance();

			gradeBookInstance.setClassName(""+( rootElement).getAttribute("class"));

			System.out.println(gradeBookInstance.getClassName());

			//get the grading schema
			GradeItem gradeItemForGraduate  = new GradeItem();
			Map<String, String> map = gradeItemForGraduate.getGradeItemList();

			Element gradingSchema = null;
			Element gradesNode = null;
			NodeList nodeList = rootElement.getChildNodes();
			//Node parent = rootElement.getFirstChild();

			int i=0, j=0;
			int count = 0;
			for (j = 0; j < nodeList.getLength(); j++) {
				Node childNode = nodeList.item(j);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {

					if(count == 0)
					{
						System.out.println(childNode.getNodeType());
						gradingSchema = (Element)childNode;
					}
					else if(count == 1)
					{
						System.out.println(childNode.getNodeType());
						gradesNode = (Element)childNode;
					}
					else 
						break;
					count++;
				}
			}
			nodeList = null; 
			if(gradingSchema != null)
				nodeList = gradingSchema.getChildNodes();

			for (j = 0; j < nodeList.getLength(); j++) {

				Node childNode = nodeList.item(j);
				if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE)
				{

					Element eElement = (Element) childNode;
					String category = eElement.getElementsByTagName("Category").item(0).getTextContent();
					String percentage = eElement.getElementsByTagName("Percentage").item(0).getTextContent();
					map.put(category, percentage);

				}
			}

			gradeBookInstance.setGraduateGradeItem(gradeItemForGraduate);
			nodeList = null;
			if(gradesNode != null)
				nodeList = gradesNode.getChildNodes();

			//get the students' grades

			List<Student> studentList = gradeBookInstance.getStudentList();
			int k =0, l=0;
			for (j = 0; j < nodeList.getLength(); j++) {

				Node childNode = nodeList.item(j);
				if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Student graduateStudent = new GraduateStudent();

					Element eElement = (Element) childNode;
					String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
					String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
					//set name of student
					graduateStudent.setName(name);
					//set id of student
					graduateStudent.setId(id);
					NodeList innerChildList = eElement.getElementsByTagName("AssignedWork");

					AssignedWork graduateAssignedWork = new AssignedWork();
					Map<String, GradedWork> assignedWorkList = graduateAssignedWork.getAssignedWorkList();

					for (k = 0; k < innerChildList.getLength(); k++) 
					{

						Node innerChildNodeLevel1 = innerChildList.item(k);
						//innerChildNode will be AssignedWork
						if (innerChildNodeLevel1 != null && innerChildNodeLevel1.getNodeType() == Node.ELEMENT_NODE)
						{
							//AssignedWork categoty
							String category1 = ((Element)innerChildNodeLevel1).getAttribute("category");

							GradedWork graduategGradedWork = new GradedWork();

							NodeList mostInnerChildList =  innerChildNodeLevel1.getChildNodes();

							for(l=0; l< mostInnerChildList.getLength(); l++)
							{
								Node mostInnerChildNode = mostInnerChildList.item(l);
								if (mostInnerChildNode != null && mostInnerChildNode.getNodeType() == Node.ELEMENT_NODE)
								{
									String name1 = ((Element)mostInnerChildNode).getElementsByTagName("Name").item(0).getTextContent();
									String grade = ((Element)mostInnerChildNode).getElementsByTagName("Grade").item(0).getTextContent();
									graduategGradedWork.addGradedWork(name1, grade);
								}
							}
							assignedWorkList.put(category1, graduategGradedWork);

						}
					}

					graduateStudent.setAssignedWork(graduateAssignedWork);
					graduateStudent.setStudentType("Graduate");
					studentList.add(graduateStudent);

				}
			}
			success = true;
			return success;

		}
		catch(Exception e)
		{
			System.out.println("XML File is not in proper format");
			e.printStackTrace();
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
