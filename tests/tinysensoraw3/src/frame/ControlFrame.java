package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuListener;

import org.jfree.data.xy.XYSeries;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.event.PopupMenuEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class ControlFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static SerialPort chosenPort;
	private JPanel contentPane;
	private final JPanel panelSQL = new JPanel();
	private static OutputStream out;
	
	private static JButton btnOpen;
	private static JButton btnClose;
	private static JButton btnGraphs;
	private static JButton btnSendData;
	private static JButton btnSQLStart;
	private static JButton btnSQLLogin;
	private static JComboBox comboBoxComPorts;
	private static JComboBox comboBoxBaudRate;
	private static JComboBox comboBoxDataBits;
	private static JComboBox comboBoxStopBits;
	private static JComboBox comboBoxParity;
	private static JProgressBar progressSerial;
	private static JProgressBar progressBarSQL;
	private JTextField textFieldSendData;
	private String dataBufffer;
	
    public static XYSeries lightData = new XYSeries("Light Sensor Readings");
    public static XYSeries tempData = new XYSeries("Temperature Sensor Readings");
    public static XYSeries humidData = new XYSeries("Humidity Sensor Readings");


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ControlFrame frame = new ControlFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public ControlFrame() {		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelSQL.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "MySQL Connection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSQL.setBounds(6, 470, 788, 96);
		contentPane.add(panelSQL);
		panelSQL.setLayout(null);
		
		JButton btnSQLStart = new JButton("Start");
		btnSQLStart.setBounds(180, 39, 117, 29);
		panelSQL.add(btnSQLStart);
		btnSQLStart.setEnabled(false);
		
		JButton btnSQLLogin = new JButton("Login");
		btnSQLLogin.setBounds(45, 39, 117, 29);
		panelSQL.add(btnSQLLogin);
		
		JProgressBar progressBarSQL = new JProgressBar();
		progressBarSQL.setValue(100);
		progressBarSQL.setBounds(551, 39, 212, 20);
		panelSQL.add(progressBarSQL);
		progressBarSQL.setValue(0);
		
		JLabel lblSQLStatus = new JLabel("MySQL connection status");
		lblSQLStatus.setBounds(368, 39, 169, 16);
		panelSQL.add(lblSQLStatus);
		
		JPanel panelTop = new JPanel();
		panelTop.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Serial Connection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTop.setBounds(6, 6, 788, 452);
		contentPane.add(panelTop);
		panelTop.setLayout(null);
		
		JPanel panelSettings = new JPanel();
		panelSettings.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettings.setBounds(22, 34, 295, 392);
		panelTop.add(panelSettings);
		panelSettings.setLayout(null);
		
		JComboBox comboBoxComPorts = new JComboBox();
		comboBoxComPorts.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBoxComPorts.removeAllItems();
				SerialPort[] portList = SerialPort.getCommPorts();
				for (SerialPort port : portList) {
					comboBoxComPorts.addItem(port.getSystemPortName());
				}
			}
		});
		comboBoxComPorts.setBounds(122, 20, 149, 27);
		panelSettings.add(comboBoxComPorts);
		comboBoxComPorts.setEnabled(true);
		
		JComboBox comboBoxBaudRate = new JComboBox();
		comboBoxBaudRate.setModel(new DefaultComboBoxModel(new String[] {"4800", "9600", "38400", "57600", "115200"}));
		comboBoxBaudRate.setBounds(122, 66, 149, 27);
		panelSettings.add(comboBoxBaudRate);
		comboBoxBaudRate.setSelectedItem("9600");
		
		JComboBox comboBoxDataBits = new JComboBox();
		comboBoxDataBits.setModel(new DefaultComboBoxModel(new String[] {"6", "7", "8"}));
		comboBoxDataBits.setBounds(122, 113, 149, 27);
		panelSettings.add(comboBoxDataBits);
		comboBoxDataBits.setSelectedItem("8");
		
		JComboBox comboBoxStopBits = new JComboBox();
		comboBoxStopBits.setModel(new DefaultComboBoxModel(new String[] {"1", "1.5", "2"}));
		comboBoxStopBits.setBounds(121, 164, 149, 27);
		panelSettings.add(comboBoxStopBits);
		comboBoxStopBits.setSelectedItem("1");
		
		JLabel lblComPorts = new JLabel("COM port");
		lblComPorts.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblComPorts.setBounds(28, 24, 95, 16);
		panelSettings.add(lblComPorts);
		
		JLabel lblBaudRate = new JLabel("Baud rate");
		lblBaudRate.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblBaudRate.setBounds(28, 70, 95, 16);
		panelSettings.add(lblBaudRate);
		
		JLabel lblDataBits = new JLabel("Data bits");
		lblDataBits.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblDataBits.setBounds(28, 117, 61, 16);
		panelSettings.add(lblDataBits);
		
		JLabel lblStopBits = new JLabel("Stop Bits");
		lblStopBits.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblStopBits.setBounds(27, 168, 95, 16);
		panelSettings.add(lblStopBits);
		
		JProgressBar progressBarSerial = new JProgressBar();
		progressBarSerial.setValue(100);
		progressBarSerial.setBounds(121, 262, 137, 20);
		panelSettings.add(progressBarSerial);
		progressBarSerial.setValue(0);
		
		JLabel lblComStatus = new JLabel("COM Status");
		lblComStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblComStatus.setBounds(27, 266, 82, 16);
		panelSettings.add(lblComStatus);
		
		JButton btnGraphs = new JButton("Graphs");
		btnGraphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphFrame graphFrame = new GraphFrame("Test");
			}
		});
		btnGraphs.setBounds(16, 303, 117, 29);
		panelSettings.add(btnGraphs);
		btnGraphs.setEnabled(false);

		
		JLabel lblParity = new JLabel("Parity Bits");
		lblParity.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblParity.setBounds(28, 217, 95, 16);
		panelSettings.add(lblParity);
		
		JComboBox comboBoxParity = new JComboBox();
		comboBoxParity.setModel(new DefaultComboBoxModel(new String[] {"NO_PARITY", "EVEN_PARITY", "ODD-PARITY", "MARK_PARITY", "SPACE_PARITY"}));
		comboBoxParity.setBounds(122, 213, 149, 27);
		panelSettings.add(comboBoxParity);
		comboBoxParity.setSelectedItem("NO_PARITY");
		
		JPanel panelSend = new JPanel();
		panelSend.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSend.setBounds(352, 34, 400, 191);
		panelTop.add(panelSend);
		panelSend.setLayout(null);
		
		JTextArea textAreaSendData = new JTextArea();
		textAreaSendData.setBounds(6, 48, 388, 137);
		panelSend.add(textAreaSendData);
		
		textFieldSendData = new JTextField();
		textFieldSendData.setBounds(6, 13, 259, 26);
		panelSend.add(textFieldSendData);
		textFieldSendData.setColumns(10);
		
		JButton btnSendData = new JButton("Send");
		btnSendData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataToSend = "";
				dataToSend = textFieldSendData.getText();
				try (OutputStream out = chosenPort.getOutputStream()) {
					out.write(dataToSend.getBytes());
				} catch (IOException e5) {
					JOptionPane.showMessageDialog(btnSendData, e);
				}
			}
		});
		btnSendData.setBounds(277, 10, 117, 29);
		panelSend.add(btnSendData);
		btnSendData.setEnabled(false);	
		
		JPanel panelReceive = new JPanel();
		panelReceive.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Received data", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReceive.setBounds(352, 237, 400, 191);
		panelTop.add(panelReceive);
		panelReceive.setLayout(null);
		
		JTextArea textAreaReceiveData = new JTextArea();
		textAreaReceiveData.setEditable(false);
		textAreaReceiveData.setBounds(6, 21, 388, 164);
		panelReceive.add(textAreaReceiveData);
		
		JButton btnClose = new JButton("Close");
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SerialPort[] portList = SerialPort.getCommPorts();
					chosenPort = portList[comboBoxComPorts.getSelectedIndex()];
					chosenPort.setBaudRate(Integer.parseInt(comboBoxBaudRate.getSelectedItem().toString()));
					chosenPort.setNumDataBits(Integer.parseInt(comboBoxDataBits.getSelectedItem().toString()));
					chosenPort.setNumStopBits(Integer.parseInt(comboBoxStopBits.getSelectedItem().toString()));
					chosenPort.setNumStopBits(comboBoxParity.getSelectedIndex());
					chosenPort.openPort();

					if (chosenPort.isOpen()) {	
						//JOptionPane.showMessageDialog(btnOpen, chosenPort.getDescriptivePortName() + " --- Port is OPEN.");
						comboBoxComPorts.setEnabled(false);
						btnOpen.setEnabled(false);
						btnClose.setEnabled(true);
						btnGraphs.setEnabled(true);
						btnSendData.setEnabled(true);
						progressBarSerial.setValue(100);
						
						Thread dataTasks = new Thread(new Runnable() {
				            @Override
				            public void run() {
				            	
				                double x = 0;
				                Scanner scanner = new Scanner(chosenPort.getInputStream());
				                scanner.nextLine(); // consumes first unreliable reading
				                while (scanner.hasNextLine()) {
				                    try  {
				                        String line = scanner.nextLine();
				                        String[] values = line.split(",");
				                        String lightValue = values[0];
				                        String tempValue = values[1];
				                        String humidValue = values[2];
				                        System.out.println(lightValue);
				                        System.out.println(tempValue);
				                        System.out.println(humidValue);
				                        lightData.add(x++, Double.valueOf(lightValue));
				                        tempData.add(x++, Double.valueOf(tempValue));
				                        humidData.add(x++, Double.valueOf(humidValue));
				                        //br.lines().forEach(line -> System.out.println(line));
				                    } catch (Exception e) {
				                        System.err.println("Corrupt incoming data. " + e);
				                        scanner.nextLine(); 
				                        //throw e;
				                        //sp.closePort();
				                    }
				                }
				            }
				        });
						dataTasks.start();
					} else {
						JOptionPane.showMessageDialog(btnOpen, chosenPort.getDescriptivePortName() + " -- FAILED to open.");
					}
					
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(btnOpen, "Please chose COM port.", "EROOR", getDefaultCloseOperation());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnOpen, e2, "ERROR", getDefaultCloseOperation());
				}
			}
		});
		btnOpen.setBounds(151, 303, 117, 29);
		panelSettings.add(btnOpen);
		btnOpen.setEnabled(true);

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (chosenPort.isOpen()) {
						chosenPort.closePort();
						comboBoxComPorts.setEnabled(true);	
						btnOpen.setEnabled(true);
						btnClose.setEnabled(false);
						btnGraphs.setEnabled(false);
						progressBarSerial.setValue(0);
					}		
				} catch (Exception e3) {
					
				}
			}
		});
		btnClose.setBounds(154, 348, 117, 29);
		panelSettings.add(btnClose);
		btnClose.setEnabled(false);
	}

}
