package models;

import java.util.ArrayList;

import controller.ConstantList;

public class ManagerWord {

	private NTree<Letter> nTree;
	private ArrayList<String> words;

	public ManagerWord() {
		nTree = new NTree<Letter>();
		nTree.addNode(null, createNodeLetter(".", 0));
	}

	public void predict(String predict) {
		words = new ArrayList<>();
		if (!predict.equals("")) {
			Node<Letter> actual = searchChild(nTree.getRoot(), String.valueOf(predict.charAt(0)));
			if (actual != null) {
				actual.getInfo().addVisit();
				addLetter(actual, predict, 1);
			} else {
				addLetter(nTree.getRoot(), predict, 0);
			}
		}
		setString();
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
			predict(father, info, 0);
		}
	}

	private void predict(Node<Letter> actual, String info, int i) {
		for (Node<Letter> node : actual.getChilds()) {
			predict(node, info + node.toString(), i + node.getInfo().getVisit());
		}
		if (actual.getChilds().isEmpty()) {
			addToArray(info + ConstantList.SEPARATOR + i);
		}
	}

	private void addToArray(String info) {
		boolean option = false;
		for (int i = 0; i < words.size(); i++) {
			int wordI = info.lastIndexOf(ConstantList.SEPARATOR) + 1;
			int wordNI = words.get(i).lastIndexOf(ConstantList.SEPARATOR) + 1;
			if (Integer.parseInt(words.get(i).substring(wordNI, words.get(i).length())) < Integer
					.parseInt(info.substring(wordI, info.length()))) {
				words.add(i, info);
				option = true;
				break;
			}
		}
		if (!option) {
			words.add(info);
		}
	}

	private void setString() {
		for (int i = 0; i < words.size(); i++) {
			words.set(i, words.get(i).substring(0, words.get(i).lastIndexOf(ConstantList.SEPARATOR)));
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