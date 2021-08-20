package Datenbank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import Ferienwohnung.Ferienwohnung;
/**
 * Zweck: Daten für Ferienwohnungen werden in einer Map (Datenbank) gespeichert
 * 
 * @author SpieldennerN
 */
public class DatenbankFerienwohnung {

	// Attribute

	public Map<Integer, Ferienwohnung> myDatenbank;

	// Konstruktor

	public DatenbankFerienwohnung(HashMap<Integer, Ferienwohnung> db) {
		this.myDatenbank = db;

	}

	// Methoden
	/**
	 * Effekt: Benutzerdaten können in der Datenbank gespeichert werden
	 * 
	 * @param: Passwort, Benutzer
	 */
	public void insertDatenbank(int nummer, Ferienwohnung newF) {
		myDatenbank.put(nummer, newF);

	}

}
