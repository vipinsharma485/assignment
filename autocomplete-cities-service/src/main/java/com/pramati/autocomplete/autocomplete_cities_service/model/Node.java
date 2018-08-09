package com.pramati.autocomplete.autocomplete_cities_service.model;

import java.util.HashMap;

class Node {
	public Node(char ch) {
		value = ch;
		children = new HashMap<>();
		isEnd = false;
	}

	public HashMap<Character, Node> getChildren() {
		return children;
	}

	public char getValue() {
		return value;
	}

	public void setIsEnd(boolean val) {
		isEnd = val;
	}

	public boolean isEnd() {
		return isEnd;
	}

	private char value;
	private HashMap<Character, Node> children;
	private boolean isEnd;
}