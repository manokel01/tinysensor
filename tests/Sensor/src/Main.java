import com.fazecast.jSerialComm.SerialPort;
import gnu.io.CommPortIdentifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final File fileOut = new File("/Users/manokel/_code/java/SensorGraph/fileOut.txt");

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    list.add(com.getName());
            }
        }
    }
}