package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ManagerWord;
import view.PrincipalFrame;

public class Controller implements ActionListener {

	private ManagerWord managerWord;
	private PrincipalFrame pFrame;

	public Controller() {
		managerWord = new ManagerWord();
		pFrame = new PrincipalFrame(this);
	}

	public void loadWord() {
		managerWord.predict(pFrame.getText());
		pFrame.paintTree(managerWord.getRoot());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionCommand.valueOf(e.getActionCommand())) {
		case COMMAND_ENTER:
			loadWord();
			break;
		}

	}

}