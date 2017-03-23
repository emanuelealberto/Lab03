package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	private List<String> dictionary = new ArrayList<String>();
	
	public void loadDictionary(String language){
		try {
			FileReader fr = new 
			FileReader("rsc/"+language+".txt"); 
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word=br.readLine()) !=null) {
				String temp = new String (word);
				dictionary.add(temp);
		}
			br.close();
		} 
		catch (IOException e){
		System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String>inputTextList){
		List<RichWord> result = new LinkedList<RichWord>();
		for (String s : inputTextList){
			boolean controllo=false;
			
			for (String r : dictionary){	
			if(r.compareTo(s)==0){
				result.add(new RichWord(s,true));
				controllo=true;
			}
			
		}
			if(controllo==false){
			result.add(new RichWord(s,false));
		}
		}return result;
		
	}

	public void clear() {
		dictionary.clear();
		
	}
	}
