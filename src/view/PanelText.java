package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ActionCommand;
import controller.ConstantList;
import controller.Controller;

public class PanelText extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public PanelText(Controller listener) {
		setLayout(new GridLayout(1,2));
		textField = new JTextField();
		textField.setFont(ConstantList.AGENCY_FB);
		textField.addKeyListener(listener);
		add(textField);
		add(UtilityList.createJButtonText(ActionCommand.COMMAND_ENTER.getCommand(),
				ActionCommand.COMMAND_ENTER.getTitle(), Color.BLUE, ConstantList.AGENCY_FB, listener));
	}
	
	public String getText() {
		return textField.getText();
	}
}