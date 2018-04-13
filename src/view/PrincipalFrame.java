package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.ConstantList;
import models.Letter;
import models.Node;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree jTree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private PanelText panelText;

	public PrincipalFrame(ActionListener listener) {
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_APP)).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		panelText = new PanelText(listener);
		add(panelText, BorderLayout.NORTH);
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

	public String getText() {
		return panelText.getText();
	}
}