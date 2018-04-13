package models;

//import java.util.ArrayList;

public class ManagerWord {

	private NTree<Letter> nTree;
//	private ArrayList<String> words;

	public ManagerWord() {
		nTree = new NTree<Letter>();
		nTree.addNode(null, createNodeLetter(".", 0));
	}

	public void predict(String predict) {
		Node<Letter> node = nTree.search(predict.substring(1));
		if (node != null) {
			addLetter(predict, 1);
		} else {
			nTree.addNode(nTree.getRoot(), createNodeLetter(predict, 0));
			addLetter(predict, 0);
		}
	}
	
	private void addLetter(String info, int i) {
		Node<Letter> father = nTree.search(String.valueOf(info.charAt(i)));
		if (i+1 < info.length()) {
			if (searchChild(father, String.valueOf(info.charAt(i + 1)))) {
				addLetter(info, i + 2);
			} else {
				father.addChild(createNodeLetter(info, i+1));
				addLetter(info, i+1);
			}
		}	
	}

	private boolean searchChild(Node<Letter> father, String letter) {
		for (Node<Letter> node : father.getChilds()) {
			if (node.toString().equalsIgnoreCase(letter)) {
				return true;
			}
		}
		return false;
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