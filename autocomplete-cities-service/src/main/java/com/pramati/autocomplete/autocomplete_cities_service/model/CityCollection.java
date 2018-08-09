package com.pramati.autocomplete.autocomplete_cities_service.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.pramati.autocomplete.autocomplete_cities_service.exception.StartDoednotExist;

/**
 * This class is the component to load city list from a file and provides
 * functions to work on populated city list.
 */
@Component
public class CityCollection {
	
	public CityCollection() {
		root = new Node((char) 0);
	}

	@Value("${file.path}")
	private String filePath;
	
	@Value("${city.list.index}")
	private int index;

	/** 
	 * Load city list from filePath
	 * 
	 */
	@PostConstruct
	public void loadCities() {
		FileReader fr;
		try {
			fr = new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				this.insert(tokens[index]);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Insert city in CityCollection
	 * @param city
	 */
	public void insert(String city) {
		city = city.toLowerCase();
		// Find length of the given word
		int length = city.length();
		Node crawl = root;

		// Traverse through all characters of given word
		for (int level = 0; level < length; level++) {
			HashMap<Character, Node> child = crawl.getChildren();
			Character ch = city.charAt(level);

			// If there is already a child for current character of given word
			if (child.containsKey(ch))
				crawl = child.get(ch);
			else // Else create a child
			{
				Node temp = new Node(ch);
				child.put(ch, temp);
				crawl = temp;
			}
		}
		crawl.setIsEnd(true);
	}

	/**
	 * Validated if given city exists in CityCollection and returns last Node if it exists.
	 * @param city
	 * @return
	 */
	public Node cityExists(String city) {
		Node crawl = root;
		int level;
		int n = city.length();
		for (level = 0; level < n; level++) {
			char ch = city.charAt(level);
			HashMap<Character, Node> child = crawl.getChildren();
			if (child.containsKey(ch)) {
				crawl = child.get(ch);
			} else {
				return null;
			}
		}
		return crawl;
	}

	/**
	 * Get suggested cities from CityCollection
	 * @param prefix
	 * @param limit
	 * @return
	 */
	public List<String> getSuggestedCities(String prefix, int limit) {
		prefix = prefix.toLowerCase();
		List<String> list = new ArrayList<String>();
		Node node = cityExists(prefix);
		if (null == node) {
			throw new StartDoednotExist("City not available for given start.");
		}
		appendSuggestedCities(prefix, node, list, limit);
		return list;

	}

	/**
	 * Append suggested cities in list.
	 * @param prefix
	 * @param node
	 * @param list
	 * @param limit
	 */
	private void appendSuggestedCities(String prefix, Node node, List<String> list, int limit) {
		if (null != list && null != node && list.size() < limit) {
			if (node.isEnd()) {
				list.add(prefix);
			}
			Map<Character, Node> children = node.getChildren();
			if (children.isEmpty()) {
				return;
			}
			Iterator<Node> iterator = children.values().iterator();
			while (iterator.hasNext()) {
				Node child = iterator.next();
				appendSuggestedCities(prefix + child.getValue(), child, list, limit);
			}
		}

	}

	private Node root;
}
