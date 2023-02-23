import com.fazecast.jSerialComm.SerialPort;

public class Main {

    static final SerialPort chosenPort = SerialPort.getCommPort("cu.usbmodem14101");
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        int length = inputStream.available();

        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port: ports) {
            System.out.println(port.getSystemPortName());
        }

        System.out.println();

    }

}

