package Ferienwohnung;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * Zweck: Erstelung der GUI zum zeigen der Bilder für Ferienwohnungen
 * 
 * @author SpieldennerN
 */
public class Ferienwohnung_Slicer extends JFrame implements ActionListener {
	ArrayList<ImageIcon> BilderDatenbank;
	JLabel l;
	JButton b1, b2;
	JTextField jTextField;
	int i;
	JPanel p;
	JFrame frame;

	public Ferienwohnung_Slicer(ArrayList<ImageIcon> bilderdatenbank) {
		setLayout(new BorderLayout());
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		JPanel p = new JPanel(new FlowLayout());
		b1 = new JButton("<<");
		b2 = new JButton(">>");
		b1.setVisible(false);
		p.add(b1);
		p.add(b2);
		add(p, BorderLayout.SOUTH);	
		b1.addActionListener(this);
		b2.addActionListener(this);
		BilderDatenbank = bilderdatenbank;
		
		l = new JLabel("", JLabel.CENTER);
		add(l, BorderLayout.CENTER);
		l.setIcon(bilderdatenbank.get(0));

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (i == 1) {
				b1.setVisible(false);
				i = i - 1;
				l.setIcon(BilderDatenbank.get(i));
			}else if (i == BilderDatenbank.size()-1) {
				b2.setVisible(true);
				i = i - 1;
				l.setIcon(BilderDatenbank.get(i));
			}else {
				i = i - 1;
				l.setIcon(BilderDatenbank.get(i));
			}
		}
		if (e.getSource() == b2) {
			if (i == BilderDatenbank.size()-2) {
				b2.setVisible(false);
				i = i + 1;
				l.setIcon(BilderDatenbank.get(i));
			}else if (i == 0) {
				b1.setVisible(true);
				i = i + 1;
				l.setIcon(BilderDatenbank.get(i));
			}
			else {
				i = i + 1;
				l.setIcon(BilderDatenbank.get(i));
			}
			
		}
	}

}