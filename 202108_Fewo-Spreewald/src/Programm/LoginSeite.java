package Programm;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

import Benutzer.B_Anbieter;
import Benutzer.B_Direktkunde;
import Benutzer.Benutzer;
import Datenbank.DatenbankBenutzer;

/**
 * Zweck: Menü-Übersicht für den LogIn-Bereich
 * 
 * @author SpieldennerN
 */
public class LoginSeite {

	//
	static HashMap<String, Benutzer> spreewaldBenutzerMap = new HashMap<>();
	static HashMap<String, String> AdminMap = new HashMap<>();
	static DatenbankBenutzer spreewaldDB = new DatenbankBenutzer(spreewaldBenutzerMap);

	//
	static B_Direktkunde firstUser = new B_Direktkunde("Gast", "gast123", "Mark", "Müller", "0172-552553", 13583,
			"Schlustr. 1", "Berlin", "21.07.1995");
	static B_Direktkunde secondUser = new B_Direktkunde("GastKlein", "gastKlein123", "Marion", "Müller", "0172-9874789",
			13583, "Schlustr. 1", "Berlin", "21.07.2008");
	static B_Direktkunde thirdUser = new B_Direktkunde("SpreewaldFan", "spree123", "Marko", "Müller", "0171-3252525",
			13583, "Schlustr. 1", "Berlin", "21.07.2008");
	static B_Anbieter firstAnbieter = new B_Anbieter("Chef", "pass123", "Meike", "Müller");

	/**
	 * Effekt: User 1-3 werden in die Datenbank geladen, Loginverfahren findet statt
	 * 
	 * @param: -
	 * @return: Integer (0-Hauptmenü, 1-LogIn, 2-Programm beenden)
	 * @throws: InputMismatchException
	 */
	public static int LoginPage() {

		spreewaldDB.insertDatenbank(firstUser.getPasswort(), firstUser);
		spreewaldDB.insertDatenbank(firstAnbieter.getPasswort(), firstAnbieter);
		spreewaldDB.insertDatenbank(secondUser.getPasswort(), secondUser);
		spreewaldDB.insertDatenbank(thirdUser.getPasswort(), thirdUser);
		AdminMap.put(firstUser.getBenutzer(), firstUser.getPasswort());
		AdminMap.put(firstAnbieter.getBenutzer(), firstAnbieter.getPasswort());
		AdminMap.put(secondUser.getBenutzer(), secondUser.getPasswort());
		AdminMap.put(thirdUser.getBenutzer(), thirdUser.getPasswort());

		System.out
				.println("HAUPTMENÜ:\n-1- LogIn\n-2- Passwort vergessen\n-3- Benutzer erstellen\n-4- Programm beenden");
		Scanner scan = new Scanner(System.in);
		try {

			int eingabeZahl = scan.nextInt();
			switch (eingabeZahl) {
			case 1: {
				return 1;
			}
			case 2: {
				try {

					System.out.println("Wie lautet dein Benutzername?");
					String benutzerString = scan.next();
					System.out.println("Wie lautet ihr Vorname? ");
					String vorname = scan.next();
					if (spreewaldDB.myDatenbank.get(AdminMap.get(benutzerString)).getVorname().equals(vorname)) {
						System.out.println("Ihr Passwort lautet " + AdminMap.get(benutzerString));
						System.out.println();
						return 0;
					} else {
						System.out.println(
								"Leider können wir Ihnen keine Auskunft erteilen, weil die Eingaben nicht übereingestimmt haben haben.");
						System.out.println();
						return 0;
					}
				} catch (NullPointerException e) {
					System.out.println(
							"Leider können wir Ihnen keine Auskunft erteilen, weil die Eingaben nicht übereingestimmt haben haben.");
					System.out.println();
					return 0;
				}

			}
			case 3: {
				B_Direktkunde myB_Direktkunde = new B_Direktkunde();
				myB_Direktkunde = myB_Direktkunde.erstelleBenutzer();
				if (myB_Direktkunde == null) {
					return 0;
				}
				spreewaldDB.insertDatenbank(myB_Direktkunde.getPasswort(), myB_Direktkunde);
				AdminMap.put(myB_Direktkunde.getBenutzer(), myB_Direktkunde.getPasswort());
				System.out.println();
				return 0;

			}
			case 4: {
				return 2;
			}
			default:
				System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
				System.out.println();
				return 0;
			}
		} catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
			System.out.println();

			return 0;

		}
	}

	/**
	 * Effekt: Login-Verfahren von Nutzer und Anbieter
	 * 
	 * @param: -
	 * @return: Benutzer
	 * @throws: NullPointerException, InputMismatchException
	 */
	public static Benutzer benutzerLogin() {

		System.out.println("Auswahl Nutzer:\n-1- Gast\n-2- Anbieter");
		Scanner scan = new Scanner(System.in);
		try {
			int eingabeZahl = scan.nextInt();
			switch (eingabeZahl) {
			case 1:
				System.out.println();
				System.out.println("Eingabe Benutzer:");
				String benutzer = scan.next();
				System.out.println("Eingabe Passwort:");
				String passwort = scan.next();
				try {
					if (spreewaldDB.myDatenbank.get(passwort).getBenutzer().equals(benutzer)) {
						return spreewaldDB.myDatenbank.get(passwort);

					} else {
						System.out.println("Passwort oder Benutzername ist falsch. Bitte versuchen Sie es erneut.");
						return null;
					}

				} catch (NullPointerException e) {
					System.out.println("Passwort oder Benutzername ist falsch. Bitte versuchen Sie es erneut.");
					System.out.println();

					return null;
				}
			case 2:
				System.out.println();
				System.out.println("Eingabe Benutzer:");
				benutzer = scan.next();
				System.out.println("Eingabe Passwort:");
				passwort = scan.next();
				try {
					if (spreewaldDB.myDatenbank.get(passwort).getBenutzer().equals(benutzer)) {
						return spreewaldDB.myDatenbank.get(passwort);
					} else {
						System.out.println("Passwort oder Benutzername ist falsch. Bitte versuchen Sie es erneut.");
						return null;
					}

				} catch (NullPointerException e) {
					System.out.println("Passwort oder Benutzername ist falsch. Bitte versuchen Sie es erneut.");
					System.out.println();
					return null;
				}
			default:
				System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
				System.out.println();
			}

		} catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
			System.out.println();
			return null;

		}
		return null;

	}
}
