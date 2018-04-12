package models;

import java.util.ArrayList;

public class Node<T> {

	private T info;
	private ArrayList<Node<T>> childs;
	
	public Node(T info) {
		this.info = info;
		childs = new ArrayList<>();
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public ArrayList<Node<T>> getChilds() {
		return childs;
	}
	
	protected void addChild(Node<T> node) {
		childs.add(node);
	}
	
	@Override
	public String toString() {
		return info.toString();
	}
}