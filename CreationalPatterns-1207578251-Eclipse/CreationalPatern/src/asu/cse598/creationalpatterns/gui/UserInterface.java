package asu.cse598.creationalpatterns.gui;

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

import asu.cse598.creationalpatterns.abstractclass.Student;
import asu.cse598.creationalpatterns.singleton.BlackBoard;
import asu.cse598.creationalpatterns.singleton.GradeBook;

public class UserInterface extends JFrame implements ActionListener{


	private JRadioButton jsonRadioButton = new JRadioButton("JSON");
	private JRadioButton xmlRadioButton = new JRadioButton("XML");

	private JRadioButton csvRadioButton = new JRadioButton("CSV");
	private JRadioButton xmlRadioButton1 = new JRadioButton("XML");
	private JRadioButton htmlRadioButton = new JRadioButton("HTML");
	private ButtonGroup buttonGroup = new ButtonGroup();

	private JTextField inputFilePath = new JTextField(80);
	private JTextField outputFilePath = new JTextField(80);

	private JButton submitButton = new JButton("Submit");
	private JLabel message = new JLabel("");


	public UserInterface() {
		super("Creational Pattern: GradeBook");

		// create a new panel with GridBagLayout manager
		JPanel newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		JLabel inputType = new JLabel("Select the format of input:");
		constraints.gridx = 0;
		constraints.gridy = 0;     
		newPanel.add(inputType, constraints);

		jsonRadioButton.addActionListener(this);
		xmlRadioButton.addActionListener(this);
		buttonGroup.add(jsonRadioButton);
		buttonGroup.add(xmlRadioButton);

		constraints.gridx = 1;
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jsonRadioButton);
		buttonPanel.add(xmlRadioButton);
		newPanel.add(buttonPanel, constraints);


		JLabel inputfilePathLabel = new JLabel("Input file path:");
		constraints.gridx = 0;
		constraints.gridy = 1;     
		newPanel.add(inputfilePathLabel, constraints);

		constraints.gridx = 1;
		newPanel.add(inputFilePath, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		submitButton.addActionListener(this);

		newPanel.add(submitButton, constraints);

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Input Panel"));

		// add the panel to this frame
		getContentPane().add(BorderLayout.NORTH, newPanel);

		// create a message panel with GridBagLayout manager
		JPanel messagePanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.anchor = GridBagConstraints.WEST;
		constraints1.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints1.gridx = 0;
		constraints1.gridy = 0;   
		messagePanel.add(message, constraints1);

		messagePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Message Panel"));

		// add the panel to this frame
		getContentPane().add(BorderLayout.CENTER, messagePanel);

		//output Panel
		JPanel outputPanel = new JPanel(new GridBagLayout());

		JLabel outputFilePathLabel = new JLabel("Output File Path:");
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.anchor = GridBagConstraints.WEST;
		constraints2.insets = new Insets(10, 10, 10, 10);


		JLabel outputType = new JLabel("Select the format of output:");
		constraints2.gridx = 0;
		constraints2.gridy = 0;     
		outputPanel.add(outputType, constraints2);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		csvRadioButton.addActionListener(this);
		xmlRadioButton1.addActionListener(this);
		htmlRadioButton.addActionListener(this);
		radioButtonGroup.add(csvRadioButton);
		radioButtonGroup.add(xmlRadioButton1);
		radioButtonGroup.add(htmlRadioButton);


		constraints2.gridx = 1;

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.add(csvRadioButton);
		radioButtonPanel.add(xmlRadioButton1);
		radioButtonPanel.add(htmlRadioButton);
		outputPanel.add(radioButtonPanel, constraints2);




		// add components to the panel
		constraints2.gridx = 0;
		constraints2.gridy = 1;     
		outputPanel.add(outputFilePathLabel, constraints2);

		constraints2.gridx = 1;
		outputPanel.add(outputFilePath, constraints2);

		outputPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Output Panel"));
		// add the panel to this frame
		getContentPane().add(BorderLayout.SOUTH, outputPanel);
		pack();
		setLocationRelativeTo(null);
	}



	public JLabel getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message.setText(message);;
	}



	public JRadioButton getJsonRadioButton() {
		return jsonRadioButton;
	}



	public JRadioButton getXmlRadioButton() {
		return xmlRadioButton;
	}



	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}



	public JTextField getInputFilePath() {
		return inputFilePath;
	}



	public JTextField getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String path) {
		this.outputFilePath.setText(path);;
	}



	public void createUI() {
		// set look and feel to the system look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UserInterface().setVisible(true);
			}
		});
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton)
		{
			if(!jsonRadioButton.isSelected() && !xmlRadioButton.isSelected())
			{
				message.setForeground(Color.RED);
				message.setText("Select the type of input file type");
			}
			else if(!csvRadioButton.isSelected() && !xmlRadioButton1.isSelected() && !htmlRadioButton.isSelected())
			{
				message.setForeground(Color.RED);
				message.setText("Select the type of output file type");
			}
			else if(inputFilePath.getText().equalsIgnoreCase(""))
			{
				message.setForeground(Color.RED);
				message.setText("Input the file path in textfield 'Input file path'");
			}
			else if(!inputFilePath.getText().equalsIgnoreCase("") && !(inputFilePath.getText().toLowerCase().contains(".xml") || inputFilePath.getText().toLowerCase().contains(".json")))
			{
				message.setForeground(Color.RED);
				message.setText("Input the proper filepath (file path should contain xml or json path)");
			}
			else if(!inputFilePath.getText().equalsIgnoreCase(""))
			{
				if((inputFilePath.getText().toLowerCase().contains(".xml") && jsonRadioButton.isSelected()) || (inputFilePath.getText().toLowerCase().contains(".json") && xmlRadioButton.isSelected()))
				{
					message.setForeground(Color.RED);
					message.setText("ERROR: Inputed file path mismatches with selected Input type format");
				}
				else
				{
					message.setForeground(Color.RED);
					message.setText("");
					submitInputFiles();	
				}
			}
		}
		else if(e.getSource() instanceof JRadioButton)
		{
			JRadioButton radioButton = (JRadioButton)e.getSource();
			String radiobuttonText = radioButton.getText();
			switch(radiobuttonText){
			case "XML":
				message.setForeground(Color.RED);
				message.setText("You have selected XML file type.");
				break;
			case "JSON":
				message.setForeground(Color.RED);
				message.setText("You have selected JSON file type.");
				break;
			case "CSV":
				message.setForeground(Color.RED);
				message.setText("You have selected CSV file type.");
				break;
			case "HTML":
				message.setForeground(Color.RED);
				message.setText("You have selected HTML file type.");
				break;
			}
		}
	}

	public void submitInputFiles()
	{
		GradeBook book = GradeBook.getInstance();
		boolean result = false;
		String fileName = inputFilePath.getText();
		if(xmlRadioButton.isSelected())
			result = book.setStudentData("XML", fileName);
		else
			result = book.setStudentData("JSON", fileName);

		if(!result)
		{
			message.setText("ERROR: Input file is not in proper format.");
			book.clear();
			outputFilePath.setText("");
			return;
		}
		for(Student student : book.getStudentList())
		{
			student.calculateFinalGrade();
		}

		BlackBoard blackBoard = BlackBoard.getInstance();
		if(csvRadioButton.isSelected())
		{
			fileName = "src/asu/cse598/creationalpatterns/output/CSVOutputGradeBook.csv";
			outputFilePath.setText(blackBoard.generateOutputFile(fileName,"csv"));;

		}
		else if(htmlRadioButton.isSelected())
		{
			fileName = "src/asu/cse598/creationalpatterns/output/HTMLOutputGradeBook.html";
			outputFilePath.setText(blackBoard.generateOutputFile(fileName,"html"));
		}
		else if(xmlRadioButton1.isSelected())
		{
			fileName = "src/asu/cse598/creationalpatterns/output/XMLOutputGradeBook.xml";
			outputFilePath.setText(blackBoard.generateOutputFile(fileName,"xml"));
		}
		message.setForeground(Color.RED);
		message.setText("Successful! Output file is generated.");
		book.clear();
	}
}
