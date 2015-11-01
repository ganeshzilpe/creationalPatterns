package asu.cse598.creationalpatterns.abstractclass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class OutputWriter {

	String filename = "";

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		try {
			File yourFile = new File(filename);
			if(!yourFile.exists()) {

				yourFile.createNewFile();

			} 
			FileOutputStream oFile = new FileOutputStream(yourFile, false); 
			this.filename = filename;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File does not exist");
			e.printStackTrace();
			
		}
	}

	abstract public String generateFile(List<Student> studentList);
}
