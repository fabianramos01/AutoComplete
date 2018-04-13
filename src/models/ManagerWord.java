package models;

import java.util.ArrayList;

public class ManagerWord {

	private NTree<Letter> nTree;
	private ArrayList<String> words;

	public ManagerWord() {
		nTree = new NTree<Letter>();
		nTree.addNode(null, createNodeLetter(".", 0));
	}

	public void predict(String predict) {
		if (!predict.equals("")) {
			Node<Letter> actual = searchChild(nTree.getRoot(), String.valueOf(predict.charAt(0)));
			if (actual != null) {
				actual.getInfo().addVisit();
				addLetter(actual, predict, 1);
			} else {
				addLetter(nTree.getRoot(), predict, 0);
			}
		}
	}

	private void addLetter(Node<Letter> father, String info, int i) {
		if (i < info.length()) {
			Node<Letter> actual = searchChild(father, String.valueOf(info.charAt(i)));
			if (actual != null) {
				actual.getInfo().addVisit();
				addLetter(actual, info, i + 1);
			} else {
				actual = createNodeLetter(info, i);
				father.addChild(actual);
				addLetter(actual, info, i + 1);
			}
		} else {
			predict(father, info);
		}
	}
	
	private void predict(Node<Letter> actual, String info) {
		if (!actual.getChilds().isEmpty()) {
			Node<Letter> high = createNodeLetter(".", 0);
			for (Node<Letter> node : actual.getChilds()) {
				if (high.getInfo().getVisit() <= node.getInfo().getVisit()) {
					high = node;
				}
			}
			info += high.toString();
			predict(high, info);
		}
	}

	private Node<Letter> searchChild(Node<Letter> father, String letter) {
		for (Node<Letter> node : father.getChilds()) {
			if (node.toString().equalsIgnoreCase(letter)) {
				return node;
			}
		}
		return null;
	}

	private Node<Letter> createNodeLetter(String info, int index) {
		return new Node<Letter>(new Letter(info.charAt(index)));
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public Node<Letter> getRoot() {
		return nTree.getRoot();
	}

}