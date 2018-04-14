package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.ConstantList;
import controller.Controller;
import models.Letter;
import models.Node;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree jTree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private PanelText panelText;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JPanel panelCenter;

	public PrincipalFrame(Controller listener) {
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_APP)).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		init(listener);
		setVisible(true);
	}
	
	private void init(Controller listener) {
		panelText = new PanelText(listener);
		add(panelText, BorderLayout.NORTH);
		panelCenter = new JPanel(new GridLayout(1, 2));
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		panelCenter.add(list);
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		jTree = new JTree(model);
		panelCenter.add(new JScrollPane(jTree));
		add(panelCenter, BorderLayout.CENTER);
	}

	public void paintTree(Node<Letter> rootNode) {
		root = new DefaultMutableTreeNode(rootNode);
		model.setRoot(root);
		for (Node<Letter> node : rootNode.getChilds()) {
			createNode(root, node);
		}
	}

	private void createNode(DefaultMutableTreeNode actual, Node<Letter> node) {
		if (node != null) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					node.getInfo().getLetter() + " - " + node.getInfo().getVisit());
			actual.add(newNode);
			for (Node<Letter> child : node.getChilds()) {
				createNode(newNode, child);
			}
		}
	}
	
	public void setList(ArrayList<String> words) {
		listModel.removeAllElements();
		for (String string : words) {
			listModel.addElement(string);
		}
	}
	
	public String getText() {
		return panelText.getText();
	}
}