package main;

import frame.*;

import com.fazecast.jSerialComm.SerialPort;

import frame.MainFrame;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    static final String portName = "/dev/cu.usbmodem14101";
    static long timeNow = System.currentTimeMillis();
    public static void main(String[] args) throws IOException {

        SerialPort sp = SerialPort.getCommPort(portName);
        sp.setComPortParameters(9600, 8, 1, 0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        sp.openPort();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame loginFrame = new MainFrame();
                loginFrame.setVisible(true);
            };
        });
        Thread dataTasks = new Thread() {
            @Override
            public void run() {
                double x = 0;
                Scanner scanner = new Scanner(sp.getInputStream());
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
                        MainFrame.lightData.add(x++, Double.valueOf(lightValue));
                        MainFrame.tempData.add(x++, Double.valueOf(tempValue));
                        MainFrame.humidData.add(x++, Double.valueOf(humidValue));
                        //br.lines().forEach(line -> System.out.println(line));
                    } catch (Exception e) {
                        System.err.println("Corrupt incoming data. " + e);
                        scanner.nextLine(); 
                        //throw e;
                        //sp.closePort();
                    }
                }
            }
        };

        dataTasks.start();
    }
}