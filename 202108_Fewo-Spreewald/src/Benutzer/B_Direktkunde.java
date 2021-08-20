package Benutzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Zweck: Informationen von Direktkunden werden gespeichert, verarbeitet und
 * können verwaltet werden
 * 
 * @author SpieldennerN
 */
public class B_Direktkunde extends Benutzer {

	// Attribute
	private String telefon;
	private int plz;
	private String strasse;
	private String stadt;
	private Date geburtsdatum;
	public ArrayList<String> listeBuchungen = new ArrayList<>();

	// Konstruktoren
	public B_Direktkunde() {
		setGast(true);

	}

	public B_Direktkunde(String benutzer, String passwort, String vorname, String nachname, String telefon, int plz, String strasse,
			String stadt, String geburtsdatum) {
		super(benutzer, passwort, vorname, nachname);
		this.plz = plz;
		this.strasse = strasse;
		this.stadt = stadt;
		this.telefon = telefon;
		try {
			this.geburtsdatum = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setGast(true);

	}

	// Methoden
	@Override
	public void setGast(boolean isGast) {
		super.setGast(isGast);
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getGeburtsdatum() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
		String date = DATE_FORMAT.format(this.geburtsdatum);
		return date;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		try {
			this.geburtsdatum = new SimpleDateFormat("dd.MM.yyyy").parse(geburtsdatum);
		} catch (ParseException e) {
			this.geburtsdatum = null;
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie ein gültiges Datumsformat ein.");
		}
	}

	/**
	 * Effekt: prüft ob B_Direktkunde buchungsbefugt ist
	 * 
	 * @return: Benutzer
	 * @throws: InputMismatchException
	 */
	public boolean isVolljaehrig() {
		Date date1 = this.geburtsdatum;
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.YEAR, 18);
		date1 = c.getTime();
		Date date2 = Calendar.getInstance().getTime();
		if (date1.before(date2)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Effekt: ein neuer Benutzer der Unterklasse Direktkunde wird erstellt
	 * 
	 * @return: Benutzer
	 * @throws: InputMismatchException
	 */
	public B_Direktkunde erstelleBenutzer() {
		try {

			Scanner scan = new Scanner(System.in);
			System.out.println("Eingabe Benutzername:");
			String benutzerString = scan.next();
			System.out.println("Eingabe Passwort:");
			String passwortString = scan.next();
			System.out.println("Eingabe Vorname:");
			String vornameString = scan.next();
			System.out.println("Eingabe Nachname");
			String nachnameString = scan.next();
			System.out.println("Eingabe Telefon:");
			String telefon = scan.next();
			System.out.println("Eingabe PLZ:");
			int plz = scan.nextInt();
			System.out.println("Eingabe Strasse:");
			String strasseString = scan.next();
			System.out.println("Eingabe Stadt");
			String stadtString = scan.next();
			System.out.println("Eingabe Geburtsdatum:\n(Format dd.mm.jjjj)");
			String geburtsdatum = scan.next();

			B_Direktkunde myBenutzer = new B_Direktkunde(benutzerString, passwortString, vornameString, nachnameString, telefon,
					plz, strasseString, stadtString, geburtsdatum);
			if (this.geburtsdatum == null) {
				return null;
			}
			System.out.println(
					"Ein neuer Gastzugang mit dem Benutzernamen - " + myBenutzer.getBenutzer() + " - wurde erstellt.");
			return myBenutzer;

		} catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
			System.out.println();
			return null;

		}
	}

	/**
	 * Effekt: Die persönlichen Daten des Direktkunden werden angezeigt (Passwort
	 * nicht)
	 */
	public void zeigeDaten() {

		System.out.println("***Benutzerdaten***");
		System.out.println("(1) Benutzername: " + getBenutzer());
		System.out.println("(2) Vorname: " + getVorname());
		System.out.println("(3) Nachname: " + getNachname());
		System.out.println("(4) Telefon-Nummer: " + getTelefon());
		System.out.println("(5) PLZ: " + getPlz());
		System.out.println("(6) Stadt: " + getStadt());
		System.out.println("(7) Strasse: " + getStrasse());
		System.out.println("(8) Geburtsdatum: " + getGeburtsdatum());

	}

	/**
	 * Effekt: bestehende persönliche Daten der Klasse B_Direktkunde können geändert
	 * werden
	 * 
	 * @throws: InputMismatchException
	 */
	public void ändereDaten() {
		try {
			zeigeDaten();
			System.out.println();
			System.out
					.println("Bitte eine der oben aufgeführten Zahlen ein um die entsprechende Information anzupassen");
			Scanner scan = new Scanner(System.in);
			int eingabe = scan.nextInt();
			System.out.println();
			switch (eingabe) {
			case 1: {
				System.out.println("Benutzername aktuell: " + getBenutzer());
				System.out.println("Eingabe neuer Benutzername:");
				String benutzerString = scan.next();
				setBenutzer(benutzerString);
				System.out.println("Ihr Benutzername wurde auf - " + benutzerString + " - geändert");
				break;
			}
			case 2: {
				System.out.println("Vorname aktuell: " + getVorname());
				System.out.println("Eingabe neuer Vorname:");
				String vornameString = scan.next();
				setVorname(vornameString);
				System.out.println("Ihr Vorname wurde auf - " + vornameString + " - geändert");
				break;
			}

			case 3: {
				System.out.println("Nachname aktuell: " + getNachname());
				System.out.println("Eingabe neuer Nachname");
				String nachnameString = scan.next();
				setNachname(nachnameString);
				System.out.println("Ihr Nachname wurde auf - " + nachnameString + " - geändert");
				break;

			}
			case 4: {
				System.out.println("Telefon aktuell: " + getTelefon());
				System.out.println("Eingabe neue Telefon-Nummer");
				String telefonString = scan.next();
				setNachname(telefonString);
				System.out.println("Ihre Telefon-Nummer wurde auf die - " + telefonString + " - geändert");
				break;

			}

			case 5: {
				System.out.println("PLZ aktuell: " + getPlz());
				System.out.println("Eingabe neue PLZ:");
				int plz = scan.nextInt();
				setPlz(plz);
				System.out.println("Ihre PLZ wurde auf - " + plz + " - geändert");
				break;

			}

			case 6: {
				System.out.println("Strasse aktuell: " + getStrasse());
				System.out.println("Eingabe neue Strasse:");
				String strasseString = scan.next();
				setStrasse(strasseString);
				System.out.println("Ihr Strasse wurde auf - " + strasseString + " - geändert");
				break;

			}
			case 7: {
				System.out.println("Stadt aktuell: " + getStadt());
				System.out.println("Eingabe neue Stadt");
				String stadtString = scan.next();
				setStadt(stadtString);
				System.out.println("Ihr Stadt wurde auf - " + stadtString + " - geändert");
				break;

			}
			case 8: {
				System.out.println("Geburtsdatum aktuell: " + getGeburtsdatum());
				System.out.println("Eingabe neues Geburtsdatum:\n(Format dd.mm.jjjj)");
				String geburtsdatum = scan.next();
				setGeburtsdatum(geburtsdatum);
				System.out.println("Ihr Geburtsdatum wurde auf den - " + geburtsdatum + " - geändert");
				break;

			}
			default:
				System.out.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
			}
		} catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
			System.out.println();

		}

	}
}
