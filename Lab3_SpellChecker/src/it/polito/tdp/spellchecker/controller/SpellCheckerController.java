/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;


import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrongWords"
    private TextArea txtWrongWords; // Value injected by FXMLLoader

    @FXML // fx:id="lblErrors"
    private Label lblErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="lblTime"
    private Label lblTime; // Value injected by FXMLLoader

	Dictionary model;

    @FXML
    void doClearText(ActionEvent event) {
    	txtWrongWords.clear();
    	txtInput.clear();
    	lblErrors.setText("");
    }

    /**
     * Trasforma il testo inserito in una lista di parole e passa la listaa Dictionary che controlla la correttezza
     * @param event 
     */
    @FXML
    void doSpellCheck(ActionEvent event) {
    	double t1 = System.nanoTime();
    	//Carico il dizionario
    	model.loadDictionary(cmbLanguage.getValue());
    	//Trasformare la frase in lista di parole miniscole e senza punteggiatura
    	String temp = txtInput.getText().toLowerCase();
    	String temp2=temp.replaceAll("[\\p{Punct}]", "");
    	String array[]=temp2.split(" ");
    	List<String> listaParole = new ArrayList<String>();
    	for (String s: array){
    		if (s.compareTo("")!=0)
    			listaParole.add(s);
    	}
    	ArrayList<RichWord> output= new ArrayList<RichWord>(model.ricercaDicotomica(listaParole));
    	//contare numero di parole errate
    	int contatore =0;
    	for(RichWord r : output){
    		if (!r.isCorrect()){
    			contatore++;
    			txtWrongWords.appendText(r.getTxt()+"\n");
    		}
    	}
    	lblErrors.setText("The text contains "+ contatore+" errors");
    	model.clear();
    	double t2 = System.nanoTime();
    	lblTime.setText("Time" + (t2-t1)/1e9);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        cmbLanguage.getItems().addAll("English","Italian");
        if (cmbLanguage.getItems().size()>0){
        	cmbLanguage.setValue(cmbLanguage.getItems().get(0));
        }
    }

	public void setModel(Dictionary model) {
		this.model= model;
		
	}
}
