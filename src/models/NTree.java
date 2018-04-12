package models;

public class NTree<T> {

	private Node<T> root;

	public NTree() {
	}

	public void addNode(Node<T> father, Node<T> node) {
		if (father != null) {
			father.addChild(node);
		} else {
			root = node;
		}
	}

	public Node<T> search(String info) {
		if (info != null) {
			return search(root, info);
		}
		return null;
	}

	private Node<T> search(Node<T> actual, String info) {
		if (actual.toString().equalsIgnoreCase(info)) {
			return actual;
		} else if (!actual.getChilds().isEmpty()) {
			for (Node<T> node : actual.getChilds()) {
				Node<T> seacrh = search(node, info);
				if (seacrh != null) {
					return seacrh;
				}
			}
		}
		return null;
	}

	public void print() {
		if (root != null) {
			print(root, "");
		}
	}

	private void print(Node<T> actual, String line) {
		System.out.println(line + actual.toString());
		if (!actual.getChilds().isEmpty()) {
			for (Node<T> node : actual.getChilds()) {
				print(node, line + "-");
			}
		}
	}

	public Node<T> getRoot() {
		return root;
	}
}