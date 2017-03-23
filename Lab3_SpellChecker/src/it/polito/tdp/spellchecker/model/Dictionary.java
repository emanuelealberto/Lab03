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
				String temp = new String (word.toLowerCase());
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
	public List<RichWord> ricercaDicotomica(List<String>inputText){
	List<RichWord> risultato = new LinkedList<RichWord>();
	for (String s : inputText){
		int low = 0;
		int high = dictionary.size()-1;
		int mid;
		boolean trovato=false;
		
		while (low<=high-1) {
			mid = (low+high)/2;
				
			if(dictionary.get(mid).compareTo(s)==0) {
				risultato.add(new RichWord(s,true)); //valore trovato nella posizione mid
				trovato=true;
				break;
			    }
			else if (dictionary.get(mid).compareTo(s)<0) {
				low = mid+1;
			}
			else if (dictionary.get(mid).compareTo(s)>0) {
				high = mid-1;
			}
		}
		if(!trovato){
			if(dictionary.indexOf(s)==-1){
			risultato.add(new RichWord(s, false));}
		}
			
		}
		return risultato;
}
	
	public void clear() {
		dictionary.clear();
	}
	}
