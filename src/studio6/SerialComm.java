package studio6;

import jssc.*;

public class SerialComm {

	
	
	
	SerialPort port;
	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	void writeByte(byte b) {
   	 if(debug) {
   	 try {
			port.writeByte(b);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 //System.out.println("<0x" + b + ">");
   	 }
    }
	// TODO: Add available() method
	boolean available() {
		try {
			if(port.getInputBufferBytesCount() > 0) {
				return true;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// TODO: Add readByte() method	
	byte readByte() throws SerialPortException {
		byte[] a = port.readBytes(1);
		
		if(debug) {
			System.out.print("Hex Value: ");
			System.out.println(String.format("%02x",  a[0]));
		}
		return a[0];
	}
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm k = new SerialComm("COM6");
		k.setDebug(true);
		byte reading;
		while(true) {
		if(k.available()) {
			reading = k.readByte();
			System.out.print("ASCII Value: ");
			System.out.println((char)reading);
		}}
	}
}
