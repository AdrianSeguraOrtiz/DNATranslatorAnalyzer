package interfazGrafica;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EjecutaParaInterfaz {
	public static void main(String[] args){
		Runnable rr = new Runnable(){
				public void run ( ) {
					
					JFrame ventana = new JFrame ("Ventana");
					
					TrabajoVistaPanelV1 tvp = new TrabajoVistaPanelV1();
					TrabajoControladorV1 tc = new TrabajoControladorV1(tvp);
					
					tvp.getbMostrar().addActionListener(tc);
					tvp.getbTraducir().addActionListener(tc);
					
			    	ventana.getContentPane().add(tvp);
					ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ventana.pack();
					ventana.setExtendedState(Frame.MAXIMIZED_BOTH);
					ventana.setVisible(true);			
				}
		};
		SwingUtilities.invokeLater(rr);
	}
}

