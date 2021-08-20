package Datenbank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import Benutzer.Benutzer;
/**
 * Zweck: Daten für Benutzer werden in einer Map (Datenbank) gespeichert
 * 
 * @author SpieldennerN
 */
public class DatenbankBenutzer extends HashMap<String, Benutzer> {

	// Attribute
	public static HashMap<String, Benutzer> myDatenbank;

	// Konstruktor
	public DatenbankBenutzer(HashMap<String, Benutzer> db) {
		this.myDatenbank = db;

	}

	// Methoden
	/**
	 * Effekt: Benutzerdaten können in der Datenbank gespeichert werden
	 * 
	 * @param: Passwort, Benutzer
	 */
	public void insertDatenbank(String passwort, Benutzer newB) {
		myDatenbank.put(passwort, newB);

	}
	public Benutzer getBenutzer(String key) {
		return myDatenbank.get(key);
	}

}
