package Programm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import Datenbank.DatenbankBenutzer;
import Datenbank.DatenbankFerienwohnung;
import Ferienwohnung.Ferienwohnung;
/**
 * Zweck: Menü-Übersicht für den Buchungsbereich
 * 
 * @author SpieldennerN
 */
public class BuchungSeite {

	public static HashMap<Integer, Ferienwohnung> spreewaldFerienwohnungMap = new HashMap<>();
	public static DatenbankFerienwohnung spreewaldDB = new DatenbankFerienwohnung(spreewaldFerienwohnungMap);

	// Anlegen Ferienwohnungen
	static Ferienwohnung ersteFerienwohnung = new Ferienwohnung("Naturpalast", 82, 75, 4, 4, "Lübben",
			"Kirchstrasse 5");
	static Ferienwohnung zweiteFerienwohnung = new Ferienwohnung("Gurkenfass", 45, 40, 2, 4, "Burg", "Marktplatz 9");
	static Ferienwohnung dritteFerienwohnung = new Ferienwohnung("Kanalsiedlung", 93, 85, 5, 4, "Schlepzig",
			"Bachweg 1");
	
	
	// Übersicht Ferienwohnung
	/**
	 * Effekt: Ferienwohnungen 1-3 werden in die Datenbank geladen, Bilderdatenbank wird befüllt, Ferienauswahl findet statt
	 * 
	 * @param: -
	 * @return: Ferienwohnung
	 * @throws: -
	 */
	public static Ferienwohnung hauptmenü() {
		spreewaldDB.insertDatenbank(1, ersteFerienwohnung);
		spreewaldDB.insertDatenbank(2, zweiteFerienwohnung);
		spreewaldDB.insertDatenbank(3, dritteFerienwohnung);
		
		if (ersteFerienwohnung.Bilderdatenbank.isEmpty()) {
			ersteFerienwohnung.setBild("Fewo11");
			ersteFerienwohnung.setBild("Fewo12");
			ersteFerienwohnung.setBild("Fewo13");
			zweiteFerienwohnung.setBild("Fewo21");
			zweiteFerienwohnung.setBild("Fewo22");
			zweiteFerienwohnung.setBild("Fewo23");
			dritteFerienwohnung.setBild("Fewo31");
			dritteFerienwohnung.setBild("Fewo32");
			dritteFerienwohnung.setBild("Fewo33");
		}
		


		Ferienwohnung myFerienwohnung = wähleFerienwohnung();
		return myFerienwohnung;

	}

// Auswahl Ferienwohnungen
	/**
	 * Effekt: Auswahl der jeweiligen Ferienwohnung
	 * 
	 * @param: -
	 * @return: Ferienwohnung
	 * @throws: InputMismatchException
	 */
	public static Ferienwohnung wähleFerienwohnung() {
		
		System.out.println("Auswahl Ferienwohnung:");

		for (int i = 1; i <= spreewaldDB.myDatenbank.size(); i++) {
			System.out.println("-" + i + "-" + " " + spreewaldDB.myDatenbank.get(i).getName() + " (Personen: "
					+ spreewaldDB.myDatenbank.get(i).getAnzahl_pax() + " )");
		}
		try {
		Scanner scan = new Scanner(System.in);
		int eingabeZahl = scan.nextInt();
		if (eingabeZahl>spreewaldFerienwohnungMap.size()) {
			System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
			return null;
		}
		
		
		System.out.println();
		Ferienwohnung myFewo = spreewaldFerienwohnungMap.get(eingabeZahl);
		
		System.out.println("FERIENWOHNUNG " + myFewo.getName().toUpperCase());
		myFewo.zeigeDaten();
		System.out.println();

		return myFewo;
		}
		catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
			return null;
		}
		}

	}

