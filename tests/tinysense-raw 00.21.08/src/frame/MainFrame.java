package frame;

import frame.MainFrame;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainFrame extends JFrame {
    public static final XYSeries lightData = new XYSeries("Light Sensor Readings");
    public static final XYSeries tempData = new XYSeries("Temperature Sensor Readings");
    public static final XYSeries humidData = new XYSeries("Humidity Sensor Readings");
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        this.setSize(800, 1200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sensor Data Readings");

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        XYSeriesCollection dataset1 = new XYSeriesCollection(lightData);
        JFreeChart chart1 = ChartFactory.createXYLineChart(
                "Light Sensor Readings", "Time (seconds)", "ADC Reading", dataset1);
        contentPane.add(new ChartPanel(chart1), -1);

        XYSeriesCollection dataset2 = new XYSeriesCollection(tempData);
        JFreeChart chart2 = ChartFactory.createXYLineChart(
                "Temperature Sensor Readings", "Time (seconds)", "degrees Celsius", dataset2);
        contentPane.add(new ChartPanel(chart2), -1);

        XYSeriesCollection dataset3 = new XYSeriesCollection(humidData);
        JFreeChart chart3 = ChartFactory.createXYLineChart(
                "Humidity Sensor Readings", "Time (seconds)", "% humidity", dataset3);
        contentPane.add(new ChartPanel(chart3), -1);
    }
}
