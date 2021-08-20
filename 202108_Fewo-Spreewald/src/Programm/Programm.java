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
		int pr�fzahl;

		System.out.println("Herzlich Wilkommen zu unserem Ferienwohnungstool f�r Ferienwohnungen im Spreewald");
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n"
				+ "* In den unterschiedlichen Programm-�bersichten haben Sie die M�glichkeit           *\n"
				+ "* sich durch die Eingabe des entsprechenden Men�punktes im Programm fortzubewegen.  *\n"
				+ "* Wir w�nschen Ihnen dabei viel Spa� und freuen uns darauf,                         *\n"
				+ "* Sie vielleicht bald in einem unserer Ferienwohnungen begr��en zu d�rfen!          *\n"
				+ "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
		// A) Programm
		while (beendeProgramm == false) {
			pr�fzahl = 0;

			// B) Hauptmen�
			// Pr�fzahl 0 - bleibe im LoginBereich
			// Pr�fzahl 1 - Login
			// Pr�fzahl 2 - beende Programm
			while (pr�fzahl == 0) {
				pr�fzahl = LoginSeite.LoginPage();
				if (pr�fzahl == 2) {
					beendeProgramm = true;
				}
			}
			// C) LogIn-Bereich
			if (pr�fzahl == 1) {

				System.out.println();
				Benutzer myBenutzer = LoginSeite.benutzerLogin();

				// D) 1. Hauptmen� - Gast
				if (myBenutzer != null) {
					if (myBenutzer.getIsGast() == true) {
						B_Direktkunde myDirektkunde = (B_Direktkunde) myBenutzer;
						hauptprogrammGast = false;

						while (hauptprogrammGast == false) {
							System.out.println();
							System.out.println("MEN� - GAST");
							System.out.println(
									"-1- �bersicht Ferienwohnungen\n-2- �bersicht Buchungen\n-3- Pers�nliche Daten\n-4- LogOut\n-5- Programm beenden");

							Scanner scan = new Scanner(System.in);
							try {
								int eingabeZahl = scan.nextInt();

								switch (eingabeZahl) {

								// E) 1. �bersicht Ferienwohnungen
								case 1: {
									auswahlFewo = false;
									while (auswahlFewo == false) {
										System.out.println("");
										boolean optionen = false;
										Ferienwohnung wahlFerienwohnung = BuchungSeite.hauptmen�();
										if (wahlFerienwohnung == null) {
											optionen = true;
										}else {
											optionen = false;
										}

										while (optionen == false) {

											System.out.println("Bitte w�hlen Sie eine der folgenden Optionen:");

											System.out.println(
													"-1- Fotos Ferienwohnung\n-2- Buchungsanfrage versenden\n-3- zur�ck zum Hauptmen�");
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
																"Um eine Buchungsanfrage stellen zu k�nnen, m�ssen Sie vollj�hrig sein.");
														System.out.println();
														break;
													}

												}

												// -3- zur�ck zum Hauptmen�
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
														"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
												auswahlFewo = true;
												break;

											}

										} // Ende while-Schleife Buchungsprogramm
									}
									break;

								}

								// C) 2. �bersicht Buchungen
								case 2: {
									System.out.println("");
									if (myDirektkunde.listeBuchungen.size() > 0) {
										System.out.println("Sie haben insgesamt " + myDirektkunde.listeBuchungen.size()
												+ " Buchungen get�tigt");
										for (String string : myDirektkunde.listeBuchungen) {
											System.out.println(string);
										}

									} else {
										System.out.println("Es sind keine bestehenden Buchungen vorhanden.");

									}
									break;

								}

								// C) 3. Pers�nliche Daten
								case 3: {
									System.out.println("");
									System.out.println("Bitte w�hlen Sie eine der folgenden Optionen:");
									System.out.println("-1- Zeige Pers�nliche Daten\n-2- �nderung Pers�nlicher Daten");
									try {

										int eingabeZahlOptionen = scan.nextInt();
										System.out.println();
										switch (eingabeZahlOptionen) {
										case 1: {
											myDirektkunde.zeigeDaten();
											break;
										}
										case 2: {
											myDirektkunde.�ndereDaten();
											break;
										}
										default: {
											System.err.println("Die eingegebene Zahl existiert nicht in der Auswahl.");
											break;
										}
										}
									} catch (InputMismatchException e) {
										System.err.println(
												"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
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
										"Ihre Eingabe, kann nicht verwendet werden. Bitte geben Sie eine g�ltige Zahl ein.");
							}
						}

					} else if (myBenutzer.getIsGast() == false) {
						B_Anbieter myAnbieter = (B_Anbieter) myBenutzer;

						hauptprogrammAnbieter = false;
						while (hauptprogrammAnbieter == false) {
							hauptprogrammAnbieter = FerienwohnungSeite_A.hauptmen�Anbieter(myAnbieter);
						}
					} else {

					}
				}
			}
		}
		// Programm Ende
		System.out.println();
		System.out.println("Vielen Dank f�r die Nutzung des Ferienwohnungstools f�r Ferienwohnungen im Spreewald\n"
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
