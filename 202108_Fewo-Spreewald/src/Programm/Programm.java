package Programm;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import Benutzer.B_Anbieter;
import Benutzer.B_Direktkunde;
import Benutzer.Benutzer;
import Datenbank.DatenbankBenutzer;
import Ferienwohnung.Ferienwohnung;
import Ferienwohnung.Ferienwohnung_Slicer;

public class Programm {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		boolean beendeProgramm = false;
		boolean hauptprogrammGast;
		boolean hauptprogrammAnbieter;
		boolean auswahlFewo;
		int prüfzahl;

		System.out.println("Herzlich Wilkommen zu unserem Ferienwohnungstool für Ferienwohnungen im Spreewald");
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n"
				+ "* In den unterschiedlichen Programm-Übersichten haben Sie die Möglichkeit           *\n"
				+ "* sich durch die Eingabe des entsprechenden Menüpunktes im Programm fortzubewegen.  *\n"
				+ "* Wir wünschen Ihnen dabei viel Spaß und freuen uns darauf,                         *\n"
				+ "* Sie vielleicht bald in einem unserer Ferienwohnungen begrüßen zu dürfen!          *\n"
				+ "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
		// A) Programm
		while (beendeProgramm == false) {
			prüfzahl = 0;

			// B) Hauptmenü
			// Prüfzahl 0 - bleibe im LoginBereich
			// Prüfzahl 1 - Login
			// Prüfzahl 2 - beende Programm
			while (prüfzahl == 0) {
				prüfzahl = LoginSeite.LoginPage();
				if (prüfzahl == 2) {
					beendeProgramm = true;
				}
			}
			// C) LogIn-Bereich
			if (prüfzahl == 1) {

				System.out.println();
				Benutzer myBenutzer = LoginSeite.benutzerLogin();

				// D) 1. Hauptmenü - Gast
				if (myBenutzer != null) {
					if (myBenutzer.getIsGast() == true) {
						B_Direktkunde myDirektkunde = (B_Direktkunde) myBenutzer;
						hauptprogrammGast = false;

						while (hauptprogrammGast == false) {
							System.out.println();
							System.out.println("MENÜ - GAST");
							System.out.println(
									"-1- Übersicht Ferienwohnungen\n-2- Übersicht Buchungen\n-3- Persönliche Daten\n-4- LogOut\n-5- Programm beenden");

							Scanner scan = new Scanner(System.in);
							try {
								int eingabeZahl = scan.nextInt();

								switch (eingabeZahl) {

								// E) 1. Übersicht Ferienwohnungen
								case 1: {
									auswahlFewo = false;
									while (auswahlFewo == false) {
										System.out.println("");
										boolean optionen = false;
										Ferienwohnung wahlFerienwohnung = BuchungSeite.hauptmenü();
										if (wahlFerienwohnung == null) {
											optionen = true;
										}else {
											optionen = false;
										}

										while (optionen == false) {

											System.out.println("Bitte wählen Sie eine der folgenden Optionen:");

											System.out.println(
													"-1- Fotos Ferienwohnung\n-2- Buchungsanfrage versenden\n-3- zurück zum Hauptmenü");
											try {
												int eingabeZahlOptionen = scan.nextInt();
																
												switch (eingabeZahlOptionen) {

												// -1- Fotos Ferienwohnung
												case 1: {
													Ferienwohnung_Slicer fSlicer = new Ferienwohnung_Slicer(
															wahlFerienwohnung.Bilderdatenbank);
													System.out.println();
													break;

												}

												// -2- Buchungsanfrage versenden
												case 2: {
													if (myDirektkunde.isVolljaehrig() == true) {
														String buchungsanfrageString = wahlFerienwohnung
																.anfrageFerienwohnung(myDirektkunde);
														myDirektkunde.listeBuchungen.add(buchungsanfrageString);
														optionen = true;
														auswahlFewo = true;
														break;
													} else {
														System.out.println(
																"Um eine Buchungsanfrage stellen zu können, müssen Sie volljährig sein.");
														System.out.println();
														break;
													}

												}

												// -3- zurück zum Hauptmenü
												case 3: {
													optionen = true;
													auswahlFewo = true;
													break;

												}
												default:
													System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
													System.out.println();
													
												}} catch (InputMismatchException e) {
												System.err.println(
														"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
												auswahlFewo = true;
												break;

											}

										} // Ende while-Schleife Buchungsprogramm
									}
									break;

								}

								// C) 2. Übersicht Buchungen
								case 2: {
									System.out.println("");
									if (myDirektkunde.listeBuchungen.size() > 0) {
										System.out.println("Sie haben insgesamt " + myDirektkunde.listeBuchungen.size()
												+ " Buchungen getätigt");
										for (String string : myDirektkunde.listeBuchungen) {
											System.out.println(string);
										}

									} else {
										System.out.println("Es sind keine bestehenden Buchungen vorhanden.");

									}
									break;

								}

								// C) 3. Persönliche Daten
								case 3: {
									System.out.println("");
									System.out.println("Bitte wählen Sie eine der folgenden Optionen:");
									System.out.println("-1- Zeige Persönliche Daten\n-2- Änderung Persönlicher Daten");
									try {

										int eingabeZahlOptionen = scan.nextInt();
										System.out.println();
										switch (eingabeZahlOptionen) {
										case 1: {
											myDirektkunde.zeigeDaten();
											break;
										}
										case 2: {
											myDirektkunde.ändereDaten();
											break;
										}
										default: {
											System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
											break;
										}
										}
									} catch (InputMismatchException e) {
										System.err.println(
												"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
										break;

									}
									break;

								}

								// C) 4. LogOut
								case 4: {
									System.out.println("Sie haben sich erfolgreich ausgeloggt");
									System.out.println();
									myBenutzer = myDirektkunde;
									hauptprogrammGast = true;
									break;

								}

								// C) 5. Programm ENDE
								case 5: {
									hauptprogrammGast = true;
									beendeProgramm = true;
									break;

								}
								default:
									System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
								}
							} catch (InputMismatchException e) {
								System.err.println(
										"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine gültige Zahl ein.");
							}
						}

					} else if (myBenutzer.getIsGast() == false) {
						B_Anbieter myAnbieter = (B_Anbieter) myBenutzer;

						hauptprogrammAnbieter = false;
						while (hauptprogrammAnbieter == false) {
							hauptprogrammAnbieter = FerienwohnungSeite_A.hauptmenüAnbieter(myAnbieter);
						}
					} else {

					}
				}
			}
		}
		// Programm Ende
		System.out.println();
		System.out.println("Vielen Dank für die Nutzung des Ferienwohnungstools für Ferienwohnungen im Spreewald\n"
				+ "------------------------------------------------------------------------------------\n"
				+ "Autoren: - Laurenz Kellermann\n" + "         - Philip Dressel\n" + "         - Nicolai Spieldenner\n"
				+ "------------------------------------------------------------------------------------\n"
				+ "Stand:   - 20. August 2021\n"
				+ "------------------------------------------------------------------------------------\n"
				+ "EEEEEE  NN    NN  DDDD     EEEEEE\n" + "EE      NNN   NN  DD  DD   EE\n"
				+ "EEEEEE  NN NN NN  DD   DD  EEEEEE\n" + "EE      NN  NNNN  DD  DD   EE\n"
				+ "EEEEEE  NN   NNN  DDDD     EEEEEE\n"
				+ "------------------------------------------------------------------------------------\n");

	}
}
