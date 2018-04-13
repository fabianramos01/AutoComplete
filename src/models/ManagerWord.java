package models;

//import java.util.ArrayList;

public class ManagerWord {

	private NTree<Letter> nTree;
	// private ArrayList<String> words;

	public ManagerWord() {
		nTree = new NTree<Letter>();
		nTree.addNode(null, createNodeLetter(".", 0));
	}

	public void predict(String predict) {
		Node<Letter> actual = searchChild(nTree.getRoot(), String.valueOf(predict.charAt(0)));
		if (actual != null) {
			addLetter(actual, predict, 1);
		} else {
			addLetter(nTree.getRoot(), predict, 0);
		}
	}

	private void addLetter(Node<Letter> father, String info, int i) {
		if (i < info.length()) {
			System.out.println(info.charAt(i));
			Node<Letter> actual = searchChild(father, String.valueOf(info.charAt(i)));
			if (actual != null) {
				System.out.println("....");
				addLetter(actual, info, i + 1);
			} else {
				actual = createNodeLetter(info, i);
				father.addChild(actual);
				addLetter(actual, info, i + 1);
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

	public void name() {

	}

	private Node<Letter> createNodeLetter(String info, int index) {
		return new Node<Letter>(new Letter(info.charAt(index)));
	}

	public Node<Letter> getRoot() {
		return nTree.getRoot();
	}

}