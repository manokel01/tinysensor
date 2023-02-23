package frame;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;
 
public class MainFrame extends JFrame {
	
    private static final long serialVersionUID = 1L;
	static final SerialPort[] portlist = SerialPort.getCommPorts();
	static final String[] baudList = {"100", "200", "300"};
    public static final XYSeries lightData = new XYSeries("Light Sensor Readings");
    public static final XYSeries tempData = new XYSeries("Temperature Sensor Readings");
    public static final XYSeries humidData = new XYSeries("Humidity Sensor Readings");
    private static final JComboBox comboBox_comPort = new JComboBox(portlist);
    private static final JComboBox comboBox_bauRate = new JComboBox(baudList);
    JButton btnConenct = new JButton("Connect");
    //GridLayout experimentLayout = new GridLayout(0,2);

    public MainFrame(String name) {
        super(name);
        setResizable(true);
    }
     
    public void addComponentsToPane(final Container pane) {
        // top graph panel
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0,2));
         
        //Add buttons to experiment with Grid Layout
        XYSeriesCollection dataset1 = new XYSeriesCollection(lightData);
        JFreeChart chart1 = ChartFactory.createXYLineChart(
                "Light Sensor Readings", "Time (seconds)", "ADC Reading", dataset1);
        topPanel.add(new ChartPanel(chart1), -1);
        XYSeriesCollection dataset2 = new XYSeriesCollection(tempData);
        JFreeChart chart2 = ChartFactory.createXYLineChart(
                "Temperature Sensor Readings", "Time (seconds)", "degrees Celsius", dataset2);
        topPanel.add(new ChartPanel(chart2), -1);
        XYSeriesCollection dataset3 = new XYSeriesCollection(humidData);
        JFreeChart chart3 = ChartFactory.createXYLineChart(
                "Humidity Sensor Readings", "Time (seconds)", "% humidity", dataset3);
        topPanel.add(new ChartPanel(chart3), -1);
        topPanel.add(new ChartPanel(chart1), -1);
        topPanel.add(new ChartPanel(chart1), -1);
        topPanel.add(new ChartPanel(chart1), -1);
        
        // bottom controls panel
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,3));
        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
        
        controls.add(comboBox_bauRate);
        comboBox_bauRate.setModel(new DefaultComboBoxModel(new String[] {"4800", "9600", "38400", "57600", "115200"}));
		comboBox_bauRate.setSelectedItem("9600");
		
        controls.add(comboBox_comPort);
        comboBox_comPort.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBox_comPort.removeAllItems();
				SerialPort[] portList = SerialPort.getCommPorts();
				for (SerialPort port : portList) {
					comboBox_comPort.addItem(port.getSystemPortName());
				}
			}
		});
        
        controls.add(btnConenct);
         
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        MainFrame frame = new MainFrame("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
