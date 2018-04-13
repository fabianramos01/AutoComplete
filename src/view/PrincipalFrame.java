package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JLabel label;

	public PrincipalFrame(Controller listener) {
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_APP)).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		addKeyListener(listener);
		panelText = new PanelText(listener);
		add(panelText, BorderLayout.NORTH);
		label = UtilityList.createJLabel("", ConstantList.AGENCY_FB, Color.BLACK);
		add(label, BorderLayout.SOUTH);
		root = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(root);
		jTree = new JTree(model);
		add(new JScrollPane(jTree), BorderLayout.CENTER);
		setVisible(true);
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
	
	public void setLabel(String word) {
		label.setText(word);
	}
	
	public String getText() {
		return panelText.getText();
	}
}