package programa;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histograma extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	
	public Histograma(double vector[], String titulo) {
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Logitudes Fragmentos", vector, 8);
		chart = ChartFactory.createHistogram(titulo, null, "Frecuencia", dataset, PlotOrientation.VERTICAL,
				true, true, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		JPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(200, 100));
		this.add(chartPanel);
	}

	public JFreeChart getChart() {
		return chart;
	}
	
}
