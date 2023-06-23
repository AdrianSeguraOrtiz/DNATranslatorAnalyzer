package interfazGrafica;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.*;

public class TrabajoVistaPanelV1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lEspacioArriba1;
	private JLabel lEspacioArriba2;
	private JLabel lCabecera;
	private JLabel lSelectArchivo;
	private JTextArea taArchivo;
	private JButton bMostrar;
	private JTextArea taSec;
	private JScrollPane spSec;
	private JTextArea taPorcSec;
	private JTextArea taPorcSecInvCom;
	private JTextArea taPorcAgrupados;
	private JTextArea taSecInvCom;
	private JScrollPane spSecInvCom;
	private JButton bTraducir;
	private JPanel p4;
	
	public TrabajoVistaPanelV1() {
		
		lEspacioArriba1 = new JLabel("                                                                                                                                       "
				+ "                                                                                                                                                                                    "
				+ "                                                                                                           ");
		lEspacioArriba2 = new JLabel("                                                                                                                                       "
				+ "                                                                                                                                                                                    "
				+ "                                                                                                           ");
		lCabecera = new JLabel("                                                                                               Secuencia:                    "
				+ "                                                                          Porcentajes:                                                               "
				+ "Secuencia inversa complementaria:                                                                  ");
		lCabecera.setVisible(false);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout( 4 , 1 ) ) ;
		
		lSelectArchivo = new JLabel ("Seleccione archivo:    ");
		taArchivo = new JTextArea();
		bMostrar = new JButton("Mostrar");
		
		p1.add(lSelectArchivo);
		p1.add(taArchivo);
		p1.add(new JPanel());
		p1.add(bMostrar);
		
		taSec = new JTextArea(30, 55);
		Font mono = new Font("Monospaced", Font.PLAIN, 9);
		taSec.setFont(mono);
		taSec.setEditable(false);
		spSec = new JScrollPane(taSec);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout( 2 , 1 ) ) ;
		taPorcSec = new JTextArea(10, 15);
		taPorcSec.setEditable(false);
		taPorcSecInvCom = new JTextArea(10, 15);
		taPorcSecInvCom.setEditable(false);
		taPorcAgrupados = new JTextArea(5, 30);
		taPorcAgrupados.setEditable(false);
		
		JPanel p3 = new JPanel();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 2; 
		p3.add (taPorcSec, constraints);
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 2; 
		p3.add (taPorcSecInvCom, constraints);
		
		p4 = new JPanel();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		p4.add (taPorcAgrupados, constraints);
		p4.setLayout(new GridLayout( 2 , 1 ) ) ;
		
		p2.add(p3);
		p2.add(p4);

		taSecInvCom = new JTextArea(30, 55);
		taSecInvCom.setFont(mono);
		taSecInvCom.setEditable(false);
		spSecInvCom = new JScrollPane(taSecInvCom);
		
		bTraducir = new JButton("Traducir");
		bTraducir.setVisible(false);
		
		JPanel pRelleno1 = new JPanel();
		pRelleno1.setSize(20, 10);
		
		JPanel pRelleno2 = new JPanel();
		pRelleno2.setSize(20, 10);
		
		JPanel pRelleno3 = new JPanel();
		pRelleno3.setSize(20, 10);
		
		JPanel pRelleno4 = new JPanel();
		pRelleno4.setSize(20, 10);
		
		JPanel pRelleno5 = new JPanel();
		pRelleno5.setSize(20, 10);
		
		JPanel pRelleno6 = new JPanel();
		pRelleno6.setSize(20, 10);
		
		this.add(lEspacioArriba1);
		this.add(lEspacioArriba2);
		this.add(lCabecera);
		this.add(pRelleno1);
		this.add(p1);
		this.add(pRelleno2);
		this.add(spSec);
		this.add(pRelleno3);
		this.add(p2);
		this.add(pRelleno4);
		this.add(spSecInvCom);
		this.add(pRelleno5);
		this.add(bTraducir);
		this.add(pRelleno6);
	}

	public JLabel getlCabecera() {
		return lCabecera;
	}

	public JPanel getP4() {
		return p4;
	}

	public JTextArea getTaArchivo() {
		return taArchivo;
	}

	public JButton getbMostrar() {
		return bMostrar;
	}

	public JTextArea getTaSec() {
		return taSec;
	}

	public JTextArea getTaPorcSec() {
		return taPorcSec;
	}

	public JTextArea getTaPorcSecInvCom() {
		return taPorcSecInvCom;
	}

	public JTextArea getTaPorcAgrupados() {
		return taPorcAgrupados;
	}

	public JTextArea getTaSecInvCom() {
		return taSecInvCom;
	}

	public JButton getbTraducir() {
		return bTraducir;
	}
	
}
