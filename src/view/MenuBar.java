package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.ActionCommand;
import controller.ConstantList;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuBar(ActionListener listener) {
		JMenu tree = new JMenu(ConstantList.MENU_TREE);
		JMenuItem load = new JMenuItem(ActionCommand.COMMAND_LOAD_TREE.getTitle());
		load.setActionCommand(ActionCommand.COMMAND_LOAD_TREE.getCommand());
		load.addActionListener(listener);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		tree.add(load);
		add(tree);
	}
}
