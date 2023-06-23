package interfazGrafica;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import programa.ADN;
import programa.Histograma;

public class TrabajoVistaPanelV2 extends JPanel{

	private static final long serialVersionUID = 1L;
	private ADN adn;
	private JLabel lEspacioArriba1;
	private JLabel lEspacioArriba2;
	private JLabel lEspacioArriba3;
	private JLabel lCabecera1;
	private JLabel lSubCabecera1;
	private JLabel lCabecera2;
	private JLabel lSubCabecera2;
	private JTextArea taM1, taM2, taM3, taM_1, taM_2, taM_3;
	private JScrollPane spM1, spM2, spM3, spM_1, spM_2, spM_3;
	private JTextArea taLon1, taLon2, taLon3, taLon_1, taLon_2, taLon_3;
	private JScrollPane spLon1, spLon2, spLon3, spLon_1, spLon_2, spLon_3;
	private JButton bGuardar;
	
	public TrabajoVistaPanelV2(ADN adnEntrada) {
		this.adn = adnEntrada;
		adn.traducir();
		
		lEspacioArriba1 = new JLabel("                                                                                                                                       "
				+ "                                                                                                                                                                                    "
				+ "                                                                                                           ");
		
		lCabecera1 = new JLabel("                                             Marco de lectura 1:                                                "
				+ "                                                                Marco de lectura 2:                                                                           "
				+ "                                      Marco de lectura 3:                                                  ");
		
		lSubCabecera1 = new JLabel("Secuencia:                                              Fragmentos:                                       "
				+ "                 Secuencia:                                                  Fragmentos:                                                   "
				+ "   Secuencia:                                                  Fragmentos:    ");
		
		taM1 = new JTextArea(20, 33);
		Font mono = new Font("Monospaced", Font.PLAIN, 9);
		taM1.setFont(mono);
		taM1.setEditable(false);
		taM1.setLineWrap(true);
		spM1 = new JScrollPane(taM1);
		taM1.setText(adn.getTraducciones()[3]);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon1 = new JTextArea(8, 42);
		taLon1.setFont(mono);
		taLon1.setEditable(false);
		spLon1 = new JScrollPane(taLon1);
		taLon1.setText(adn.getLongitudesText()[3]);
		Histograma his1 = adn.getHistogramas()[3];
		
		p1.add(spLon1);
		p1.add(his1);
		
		taM2 = new JTextArea(20, 33);
		taM2.setFont(mono);
		taM2.setEditable(false);
		taM2.setLineWrap(true);
		spM2 = new JScrollPane(taM2);
		taM2.setText(adn.getTraducciones()[4]);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon2 = new JTextArea(8, 42);
		taLon2.setFont(mono);
		taLon2.setEditable(false);
		spLon2 = new JScrollPane(taLon2);
		taLon2.setText(adn.getLongitudesText()[4]);
		Histograma his2 = adn.getHistogramas()[4];
		
		p2.add(spLon2);
		p2.add(his2);
		
		taM3 = new JTextArea(20, 33);
		taM3.setFont(mono);
		taM3.setEditable(false);
		taM3.setLineWrap(true);
		spM3 = new JScrollPane(taM3);
		taM3.setText(adn.getTraducciones()[5]);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon3 = new JTextArea(8, 42);
		taLon3.setFont(mono);
		taLon3.setEditable(false);
		spLon3 = new JScrollPane(taLon3);
		taLon3.setText(adn.getLongitudesText()[5]);
		Histograma his3 = adn.getHistogramas()[5];
		
		p3.add(spLon3);
		p3.add(his3);

		lEspacioArriba2 = new JLabel("                                                                                                                                       "
				+ "                                                                                                                                                                                    "
				+ "                                                                                                           ");
		
		lCabecera2 = new JLabel("                                             Marco de lectura -1:                                               "
				+ "                                                                Marco de lectura -2:                                                                          "
				+ "                                      Marco de lectura -3:                                                 ");
		
		lSubCabecera2 = new JLabel("Secuencia:                                              Fragmentos:                                       "
				+ "                 Secuencia:                                                  Fragmentos:                                                   "
				+ "   Secuencia:                                                  Fragmentos:    ");
		
		taM_1 = new JTextArea(20, 33);
		taM_1.setFont(mono);
		taM_1.setEditable(false);
		taM_1.setLineWrap(true);
		spM_1 = new JScrollPane(taM_1);
		taM_1.setText(adn.getTraducciones()[2]);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon_1 = new JTextArea(8, 42);
		taLon_1.setFont(mono);
		taLon_1.setEditable(false);
		spLon_1 = new JScrollPane(taLon_1);
		taLon_1.setText(adn.getLongitudesText()[2]);
		Histograma his_1 = adn.getHistogramas()[2];
		
		p4.add(spLon_1);
		p4.add(his_1);
		
		taM_2 = new JTextArea(20, 33);
		taM_2.setFont(mono);
		taM_2.setEditable(false);
		taM_2.setLineWrap(true);
		spM_2 = new JScrollPane(taM_2);
		taM_2.setText(adn.getTraducciones()[1]);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon_2 = new JTextArea(8, 42);
		taLon_2.setFont(mono);
		taLon_2.setEditable(false);
		spLon_2 = new JScrollPane(taLon_2);
		taLon_2.setText(adn.getLongitudesText()[1]);
		Histograma his_2 = adn.getHistogramas()[1];
		
		p5.add(spLon_2);
		p5.add(his_2);
		
		taM_3 = new JTextArea(20, 33);
		taM_3.setFont(mono);
		taM_3.setEditable(false);
		taM_3.setLineWrap(true);
		spM_3 = new JScrollPane(taM_3);
		taM_3.setText(adn.getTraducciones()[0]);
		
		JPanel p6 = new JPanel();
		p6.setLayout(new GridLayout( 2 , 1 ) ) ;
		taLon_3 = new JTextArea(8, 42);
		taLon_3.setFont(mono);
		taLon_3.setEditable(false);
		spLon_3 = new JScrollPane(taLon_3);
		taLon_3.setText(adn.getLongitudesText()[0]);
		Histograma his_3 = adn.getHistogramas()[0];
		
		p6.add(spLon_3);
		p6.add(his_3);
		
		lEspacioArriba3 = new JLabel("                                                                                                                                       "
				+ "                                                                                                                                                                                    "
				+ "                                                                                                                             ");
		
		bGuardar = new JButton ("Guardar");
		
		JPanel pRelleno1 = new JPanel();
		pRelleno1.setSize(20, 10);
		
		JPanel pRelleno2 = new JPanel();
		pRelleno2.setSize(20, 10);
		
		JPanel pRelleno3 = new JPanel();
		pRelleno3.setSize(20, 10);
		
		JPanel pRelleno4 = new JPanel();
		pRelleno4.setSize(20, 10);
		
		this.add(lEspacioArriba1);
		this.add(lCabecera1);
		this.add(lSubCabecera1);
		this.add(spM1);
		this.add(p1);
		this.add(pRelleno1);
		this.add(spM2);
		this.add(p2);
		this.add(pRelleno2);
		this.add(spM3);
		this.add(p3);
		this.add(lEspacioArriba2);
		this.add(lCabecera2);
		this.add(lSubCabecera2);
		this.add(spM_1);
		this.add(p4);
		this.add(pRelleno3);
		this.add(spM_2);
		this.add(p5);
		this.add(pRelleno4);
		this.add(spM_3);
		this.add(p6);
		this.add(lEspacioArriba3);
		this.add(bGuardar);
	}

	public JTextArea getTaM1() {
		return taM1;
	}

	public JTextArea getTaM2() {
		return taM2;
	}

	public JTextArea getTaM3() {
		return taM3;
	}

	public JTextArea getTaM_1() {
		return taM_1;
	}

	public JTextArea getTaM_2() {
		return taM_2;
	}

	public JTextArea getTaM_3() {
		return taM_3;
	}

	public JTextArea getTaLon1() {
		return taLon1;
	}

	public JTextArea getTaLon2() {
		return taLon2;
	}

	public JTextArea getTaLon3() {
		return taLon3;
	}

	public JTextArea getTaLon_1() {
		return taLon_1;
	}

	public JTextArea getTaLon_2() {
		return taLon_2;
	}

	public JTextArea getTaLon_3() {
		return taLon_3;
	}

	public JButton getbGuardar() {
		return bGuardar;
	}
	
}
