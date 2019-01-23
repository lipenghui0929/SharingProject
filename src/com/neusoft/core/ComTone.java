package com.neusoft.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SerialPortParameterFailure;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 * �˿ڼ�⹤��
 * 
 * @author Administrator
 * 
 */
public class ComTone {

	/** �����˿ڲ����� */
	public static int bauds[] = { 9600, 19200, 57600, 115200 };

	@SuppressWarnings("unchecked")
	public static void mains(String[] args) {

		Enumeration<CommPortIdentifier> portList = CommPortIdentifier
				.getPortIdentifiers();

		long st = System.currentTimeMillis();

		System.out.println("�����豸�˿����Ӳ���...");

		while (portList.hasMoreElements()) {

			CommPortIdentifier portId = (CommPortIdentifier) portList
					.nextElement();

			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("�ҵ����ڣ�" + portId.getName() + "\t���ͣ�"
						+ getPortTypeName(portId.getPortType()));

				for (int i = 0; i < bauds.length; i++) {
					System.out.print("  Trying at " + bauds[i] + "...");
					try {
						SerialPort serialPort;
						InputStream inStream;
						OutputStream outStream;
						int c;
						String response;
						serialPort = (SerialPort) portId.open(
								"SMSLibCommTester", 1000);
						serialPort
								.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
						serialPort.setSerialPortParams(bauds[i],
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						inStream = serialPort.getInputStream();
						outStream = serialPort.getOutputStream();
						serialPort.enableReceiveTimeout(1000);
						c = inStream.read();
						while (c != -1)
							c = inStream.read();
						outStream.write('A');
						outStream.write('T');
						outStream.write('\r');
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
						}
						response = "";
						c = inStream.read();
						while (c != -1) {
							response += (char) c;
							c = inStream.read();
						}
						if (response.indexOf("OK") >= 0) {
							try {
								System.out.print("  ��ȡ�豸��Ϣ...");
								outStream.write('A');
								outStream.write('T');
								outStream.write('+');
								outStream.write('C');
								outStream.write('G');
								outStream.write('M');
								outStream.write('M');
								outStream.write('\r');
								response = "";
								c = inStream.read();
								while (c != -1) {
									response += (char) c;
									c = inStream.read();
								}
								System.out.println("  �����豸: "
										+ response.replaceAll("\\s+OK\\s+", "")
												.replaceAll("\n", "")
												.replaceAll("\r", ""));
							} catch (Exception e) {
								System.out.println("  û�з����豸!");
							}
						} else
							System.out.println("  û�з����豸!");
						serialPort.close();
					} catch (Exception e) {
						System.out.println("  û�з����豸!");
					}
				}
				System.out.println("�ҵ�����: " + portId.getName());
			}
		}
		long et = System.currentTimeMillis();
		long time = et - st;
		System.out.println("ϵͳ��������ʱ��:" + time + "����");
	}

	private static String getPortTypeName(int portType) {
		switch (portType) {
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL:
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485:
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL:
			return "Serial";
		default:
			return "unknown type";
		}
	}
	
	public static void ss(){
		ArrayList<String> portArr=SerialTool.findPort();
		for(String port:portArr){
			System.out.println("���Զ˿�:"+port);
			//�򿪴���
			for(Integer baudrate:bauds){
				try {
					SerialPort serialPort=SerialTool.openPort(port,baudrate);
					byte[] s="AT+CMGF=1".getBytes();
					byte[] ss=new byte[s.length+2];
					for(int i=0;i<s.length;i++){
						byte sda=s[i];
						ss[i]=sda;
					}
					ss[s.length]=13;
					ss[s.length+1]=10;
					//�����ڷ�������
					SerialTool.sendToPort(serialPort,ss);
					Thread.sleep(4000);
					//���շ�������
					byte[] a=SerialTool.readFromPort(serialPort);
					if(null!=a){
						System.out.println("����:"+new String(a));
					}else{
						System.out.println("   �˿�:"+port+"  ����:"+baudrate+"������;");
					}
				} catch (Exception e) {
					System.out.println("   �˿�:"+port+"  ����:"+baudrate+"������;");
				} 
			}
		}
	}
	
	public static void main(String[] args) {
		ss();
	}

}
