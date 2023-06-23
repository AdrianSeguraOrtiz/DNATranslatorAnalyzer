package interfazGrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import programa.ADN;

public class TrabajoControladorV2 implements ActionListener {

	public ADN adn;
	
	public TrabajoControladorV2(ADN adn) {
		this.adn = adn;
	}
	
	public void actionPerformed(ActionEvent e) {
		adn.redactaPDF();
		JDialog dialog = new JDialog ();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout( 2 , 1 ) ) ;
		JLabel l1 = new JLabel ("         Las secuencias y traducción se han guardado en el fichero:");
		JLabel l2 = new JLabel ("         Secuencias_y_Traducción" + adn.getFichero() +".pdf");
		panel.add(l1);
		panel.add(l2);
		dialog.add(panel);
		dialog.setSize(475, 100);
		dialog.setVisible(true);
	}
}