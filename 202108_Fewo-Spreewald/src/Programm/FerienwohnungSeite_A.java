package Programm;

import java.util.InputMismatchException;
import java.util.Scanner;

import Benutzer.B_Anbieter;
import Benutzer.B_Direktkunde;
import Ferienwohnung.Ferienwohnung;
/**
 * Zweck: Men�-�bersicht f�r den Anbieter
 * 
 * @author SpieldennerN
 */
public class FerienwohnungSeite_A {

	/**
	 * Effekt: Men�auswahl f�r die Anbieterseite
	 * 
	 * @param: B_Anbieter
	 * @return: boolean (wenn false, Men� wird nicht verlassen)
	 * @throws: InputMismatchException
	 */
	public static boolean hauptmen�Anbieter(B_Anbieter myAnbieter) {
		System.out.println();
		System.out.println("MEN� - ANBIETER");
		System.out.println(
				"-1- Zeige bisherige Buchungen\n-2- Zeige Pers�nliche Daten\n-3- �nderung Pers�nlicher Daten\n-4- Zeige Ferienwohnungsdaten\n-5- �ndere Ferienwohnungsdaten\n-6- Suche Kontaktdaten Gast\n-7- LogOut");
		Scanner scan = new Scanner(System.in);
		try {
			int eingabeZahlOptionen = scan.nextInt();
			switch (eingabeZahlOptionen) {
			case 1: {
				System.out.println();
				Ferienwohnung wahlFerienwohnung = BuchungSeite.hauptmen�();
				if (wahlFerienwohnung == null) {
					return false;
				}
				if (wahlFerienwohnung.listeAnfragen.size() > 0) {
					System.out.println(
							"Es wurden insgesamt " + wahlFerienwohnung.listeAnfragen.size() + " Buchungen get�tigt");
					for (String string : wahlFerienwohnung.listeAnfragen) {
						System.out.println(string);
					}

				} else {
					System.out.println("Es sind keine bestehenden Buchungen vorhanden.");

				}
				return false;
			}
			case 2: {
				myAnbieter.zeigeDaten();
				return false;
			}
			case 3: {
				myAnbieter.�ndereDaten();
				return false;
			}
			case 4: {
				System.out.println();
				Ferienwohnung wahlFerienwohnung = BuchungSeite.hauptmen�();
				if (wahlFerienwohnung == null) {
					return false;
				}
				wahlFerienwohnung.zeigeDaten();
				return false;
			}
			case 5: {
				System.out.println();
				Ferienwohnung wahlFerienwohnung = BuchungSeite.hauptmen�();
				if (wahlFerienwohnung == null) {
					return false;
				}
				wahlFerienwohnung.�ndereDaten();
				return false;
			}
			case 6: {
				try {
					System.out.println("Bitte geben Sie den gesuchten Benutzernamen ein:");
					String benutzerSuche = scan.next();
					B_Direktkunde gesuchterBenutzer = (B_Direktkunde) LoginSeite.spreewaldDB
							.getBenutzer(LoginSeite.AdminMap.get(benutzerSuche));
					gesuchterBenutzer.zeigeDaten();
				} catch (InputMismatchException e) {
					System.err.println(
							"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
					System.out.println();
					return false;

				} catch (NullPointerException e) {
					System.err.println("Die eingegebenen Benutzerdaten stimmen mit keinem Gast �berein.");
					return false;

				}
				break;
			}
			case 7: {
				System.out.println("Sie haben sich erfolgreich ausgeloggt");
				System.out.println();
				return true;
			}
			default: {
				System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
				return false;
			}
			}
		} catch (InputMismatchException e) {
			System.err.println("Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
			System.out.println();
			return false;

		}
		return false;
	}

}
