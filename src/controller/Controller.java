package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.ManagerWord;
import view.PrincipalFrame;

public class Controller implements ActionListener, KeyListener {

	private ManagerWord managerWord;
	private PrincipalFrame pFrame;

	public Controller() {
		managerWord = new ManagerWord();
		pFrame = new PrincipalFrame(this);
	}

	public void loadWord() {
		managerWord.predict(pFrame.getText());
		pFrame.paintTree(managerWord.getRoot());
		pFrame.setList(managerWord.getWords());
		pFrame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionCommand.valueOf(e.getActionCommand())) {
		case COMMAND_ENTER:
			loadWord();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if (key.getKeyChar() == KeyEvent.VK_ENTER) {
			loadWord();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}