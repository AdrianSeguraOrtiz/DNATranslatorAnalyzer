package programa;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DiagramaBarras extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	
	public DiagramaBarras(double pAT, double pGC) {
		DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(pAT, "Frecuencia","A-T");
        datos.setValue(pGC, "Frecuencia","G-C");
		chart = ChartFactory.createBarChart3D(null, null, null, datos, PlotOrientation.HORIZONTAL,
				true, true, false);
		JPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(250, 75));
		this.add(chartPanel);
	}
	public JFreeChart getChart() {
		return chart;
	}
}
