package edu.german.tools;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapOrganizer {
	private List<Map<String, String>> mapList;
	private String[] order;

	public MapOrganizer(List<Map<String, String>> mapList, String type) {
		this.mapList = mapList;
		if (type.equals("WORDS"))
			this.order = new MyProperties("word.cfg").getValuesArray("WORD_ORDER");
		else
			this.order = new MyProperties("sentence.cfg").getValuesArray("SENTECE_ORDER");
	}

	/**
	 * @return List of string prepared to export
	 */
	public List<String> sortedList() {
		List<String> list = new LinkedList<>();
		mapList.forEach(map -> {
			StringBuilder sbSort = new StringBuilder();
			StringBuilder sbUnsort = new StringBuilder();

			for (int i = 0; i < order.length; i++) {
				String o = order[i].toUpperCase();
				for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
					String key = iterator.next();
					if (key.equals(o.toUpperCase())) {
						sbSort.append(o + ":" + map.get(key) + ";");
						iterator.remove();
					}
				}
			}

			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (!entry.getKey().contains("OID"))
					sbUnsort.append(entry.getKey() + ":" + entry.getValue() + ";");
			}

			StringBuilder sb = new StringBuilder();
			sb.append(sbSort);
			sb.append(sbUnsort);

			list.add(sb.toString());
		});

		return list;
	}

}
