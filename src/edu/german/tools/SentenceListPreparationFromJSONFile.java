package edu.german.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.*;

public class SentenceListPreparationFromJSONFile {
	private BufferedReader br;
	private List<String[]> list;
	private JSONParser parser = new JSONParser();

	public SentenceListPreparationFromJSONFile(BufferedReader br) {
		this.br = br;
		list = new LinkedList<String[]>();
	}
	
	
	public List<String[]> getList(String jsonText) {
		ContainerFactory containerFactory = new ContainerFactory(){
		    public List creatArrayContainer() {
		        return new LinkedList();
		    }

		    public Map createObjectContainer() {
		        return new LinkedHashMap();
		    }                     
		};
		
		try {
			Map json = (Map)parser.parse(jsonText, containerFactory);
			Iterator iter = json.entrySet().iterator();
		    System.out.println("==iterate result==");

		    while(iter.hasNext()) {
		        Map.Entry entry = (Map.Entry)iter.next();
//		        System.out.println(entry.getKey() + "=>" );  //+ entry.getValue().toString());
		        String var = (entry.getValue()).toString();
//		        Object[] objects = (Object[]) var;
		        System.out.println(var);
		    }

//		    System.out.println("==toJSONString()==");
//		    System.out.println(JSONValue.toJSONString(json));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public void readFromBR() {
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
//				System.out.println(line);
				getList(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
