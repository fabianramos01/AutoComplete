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
		loadRoot();
	}

	public void loadRoot() {
		pFrame.setVisible(false);
		managerWord.predict("hello");
		pFrame.paintTree(managerWord.getRoot());
		pFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionCommand.valueOf(e.getActionCommand())) {
		case COMMAND_LOAD_TREE:
			loadRoot();
			break;
		}

	}

}