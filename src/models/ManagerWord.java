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
		orderArray();
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
			words.add(predict(father, info, 0));
		}
	}

	private String predict(Node<Letter> actual, String info, int i) {
		if (!actual.getChilds().isEmpty()) {
			for (Node<Letter> node : actual.getChilds()) {
				words.add(predict(node, info + node.toString(), i + node.getInfo().getVisit()));
			}
		}
		return info + i;
	}

	private void orderArray() {
		for (int i = 1; i < words.size(); i++) {
			String word = words.get(i-1);
			if (Integer.parseInt(word.charAt(words.get(i - 1).length() - 1) + "") < Integer
					.parseInt(words.get(i).charAt(words.get(i).length() - 1) + "")) {
				words.add(i, word);
				words.add(i - 1, words.get(i));
			}
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