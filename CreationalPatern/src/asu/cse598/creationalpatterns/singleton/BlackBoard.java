package asu.cse598.creationalpatterns.singleton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import asu.cse598.creationalpatterns.abstractclass.OutputWriter;
import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.factory.GradeOutputFactory;
import asu.cse598.creationalpatterns.gui.UserInterface;

public class BlackBoard {

	private static BlackBoard object = new BlackBoard();
	private static UserInterface  userInterface; 


	private BlackBoard() {
		userInterface = new UserInterface();
	}

	public static BlackBoard getInstance()
	{
		return object;
	}



	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public String generateOutputFile(String filename, String type)
	{
		OutputWriter writer = GradeOutputFactory.getGrade(type);
		writer.setFilename(filename);
		return writer.generateFile(GradeBook.getInstance().getStudentList());
	}

	public static void main(String[] args) {

		userInterface.createUI();
	}

}



