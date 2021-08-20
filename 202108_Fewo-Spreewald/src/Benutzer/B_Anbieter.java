package Benutzer;

import java.util.Scanner;

/**
 * Zweck: Informationen von Anbieter werden gespeichert und können verwaltet
 * werden
 * 
 * @author SpieldennerN
 */
public class B_Anbieter extends Benutzer {
	// Attribute

	// Konstruktor

	public B_Anbieter() {
		super();
		setGast(false);
	}

	public B_Anbieter(String benutzer, String passwort, String vorname, String nachname) {
		super(benutzer, passwort, vorname, nachname);
		setGast(false);
	}

	// Methoden
	/**
	 * Effekt: Die persönlichen Daten des Anbieters werden angezeigt (Passwort
	 * nicht)
	 */
	public void zeigeDaten() {

		System.out.println();
		System.out.println("***Benutzerdaten***");
		System.out.println("(1) Benutzername: " + getBenutzer());
		System.out.println("(2) Vorname: " + getVorname());
		System.out.println("(3) Nachname: " + getNachname());
		System.out.println();

	}

	/**
	 * Effekt: bestehende persönliche Daten der Klasse B_Anbieter können geändert
	 * werden
	 * 
	 * @throws: InputMismatchException
	 */
	public void ändereDaten() {
		zeigeDaten();
		System.out.println("Bitte eine der oben aufgeführten Zahlen ein um die entsprechende Information anzupassen");
		Scanner scan = new Scanner(System.in);
		int eingabe = scan.nextInt();
		switch (eingabe) {
		case 1: {
			System.out.println("Benutzername aktuell: " + getBenutzer());
			System.out.println("Eingabe neuer Benutzername:");
			String benutzerString = scan.next();
			setBenutzer(benutzerString);
			break;
		}
		case 2: {
			System.out.println("Vorname aktuell: " + getVorname());
			System.out.println("Eingabe neuer Vorname:");
			String vornameString = scan.next();
			setVorname(vornameString);
			break;
		}

		case 3: {
			System.out.println("Nachname aktuell: " + getNachname());
			System.out.println("Eingabe neuer Nachname");
			String nachnameString = scan.next();
			setNachname(nachnameString);
			break;

		}

		default:
			System.err.println(
					"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");		}

	}

}
