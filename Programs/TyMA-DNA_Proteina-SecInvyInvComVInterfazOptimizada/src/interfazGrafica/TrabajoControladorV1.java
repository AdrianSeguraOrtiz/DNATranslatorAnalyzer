package interfazGrafica;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import programa.ADN;

public class TrabajoControladorV1 implements ActionListener {

	private TrabajoVistaPanelV1 tvp;
	private ADN adn;
	
	public TrabajoControladorV1(TrabajoVistaPanelV1 tvp) {
		this.tvp = tvp;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(tvp.getbMostrar())) {
			String fichero = tvp.getTaArchivo().getText();
			adn = new ADN (fichero);
			tvp.getTaSec().setText(adn.getSecuencia());
			
			String cabeceraPS = "\n                  Secuencia \n\n     Porcentajes Nucleótidos: \n\n";
			String cuerpoPS = adn.getListaPorcentajesSec();
			tvp.getTaPorcSec().setText(cabeceraPS + cuerpoPS);
			
			String cabeceraPSIC = "                 Secuencia \n    Complementaria Inversa \n\n     Porcentajes Nucleótidos: \n\n";
			String cuerpoPSIC = adn.getListaPorcentajesSecInvCom();
			tvp.getTaPorcSecInvCom().setText(cabeceraPSIC + cuerpoPSIC);
			
			String cabeceraPA = "\n    Porcentajes agrupados por nucleótidos complementarios: \n\n";
			String cuerpoPA = adn.getListaPorcentajesAgrupados();
			tvp.getTaPorcAgrupados().setText(cabeceraPA + cuerpoPA);
			
			tvp.getP4().add(adn.getDiagramaBarras());
					
			tvp.getTaSecInvCom().setText(adn.getSecuenciaInvCom());
			tvp.getbTraducir().setVisible(true);
			tvp.getlCabecera().setVisible(true);
		}
			
		else if (e.getSource().equals(tvp.getbTraducir())) {
			
			JFrame ventana = new JFrame ("Ventana");
			
			TrabajoVistaPanelV2 tvp2 = new TrabajoVistaPanelV2(adn);
			TrabajoControladorV2 tc2 = new TrabajoControladorV2(adn);
			tvp2.getbGuardar().addActionListener(tc2);
			
	    	ventana.getContentPane().add(tvp2);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.pack();
			ventana.setExtendedState(Frame.MAXIMIZED_BOTH);
			ventana.setVisible(true);	
		}
	}
}