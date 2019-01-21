package com.neusoft.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import serialException.*;

/**
 * ���ڷ����࣬�ṩ�򿪡��رմ��ڣ���ȡ�����ʹ������ݵȷ��񣨲��õ������ģʽ��
 * @author zhong
 *
 */
public class SerialTool {
	private static String serival="COM4";
	private static int baudrate=9600;
	
	public static void main(String[] args) {
		try{
			ArrayList<String> portArr=findPort();
			for(String port:portArr){
				port="COM12";
				System.out.println(port);
				//�򿪴���
				SerialPort serialPort=openPort(port,baudrate);
				byte[] s="AT+CMGF=1".getBytes();
				byte[] ss=new byte[s.length+2];
				for(int i=0;i<s.length;i++){
					byte sda=s[i];
					ss[i]=sda;
				}
				ss[s.length]=13;
				ss[s.length+1]=10;
				//�����ڷ�������
				System.out.println("����AT+CMGF=1ָ��");
				sendToPort(serialPort,ss);
				Thread.sleep(4000);
				//���շ�������
				byte[] a=readFromPort(serialPort);
//				System.out.println("����:"+new String(a));
				byte[] s1="AT+CSMP=17,167,2,25".getBytes();
				byte[] ss1=new byte[s1.length+2];
				for(int i=0;i<s1.length;i++){
					byte sda=s1[i];
					ss1[i]=sda;
				}
				ss1[s1.length]=13;
				ss1[s1.length+1]=10;
				System.out.println("����AT+CSMP=17,167,2,25ָ��");
				sendToPort(serialPort,ss1);
				Thread.sleep(4000);
				byte[] a1=readFromPort(serialPort);
//				System.out.println("����:"+new String(a1));
				byte[] s11="AT+CSCS=\"UCS2\"".getBytes();
				byte[] ss11=new byte[s11.length+2];
				for(int i=0;i<s11.length;i++){
					byte sda=s11[i];
					ss11[i]=sda;
				}
				ss11[s11.length]=13;
				ss11[s11.length+1]=10;
				System.out.println("����AT+CSCS=\"UCS2\"ָ��");
				sendToPort(serialPort,ss11);
				Thread.sleep(4000);
				byte[] a11=readFromPort(serialPort);
//				System.out.println("����:"+new String(a11));
				String hm=Snippet.cnToUnicodeHm("17338145202");
				byte[] s2=("AT+CMGS=\""+hm+"\"").getBytes();
				//��������ָ���ֻ���
				byte[] ss2=new byte[s2.length+2];
				for(int i=0;i<s2.length;i++){
					byte sda=s2[i];
					ss2[i]=sda;
				}
				ss2[s2.length]=13;
				ss2[s2.length+1]=10;
				System.out.println("����AT+CMGS=\"17338145202\"ָ��");
				sendToPort(serialPort,ss2);
				Thread.sleep(4000);
				byte[] a2=readFromPort(serialPort);
				System.out.println(new String(a2));
				 byte[] s3=Snippet.cnToUnicodeHm("��Ӣ�Ķ��ŷ��Ͳ���,���Գɹ��ģ�����������").getBytes();
				sendToPort(serialPort,s3);
				Thread.sleep(4000);
				//����1Aָ��
				 int s4=0X1A;
				 sendToPortHex16(serialPort,s4);
				Thread.sleep(14000);
				byte[] a3=readFromPort(serialPort);
				System.out.println(new String(a3));
				closePort(serialPort);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean sendSms(String port,String jshm,String conut) throws Exception{
		boolean bool=false;
		SerialPort serialPort=openPort(port,baudrate);
		byte[] s="AT+CMGF=1".getBytes();
		byte[] ss=new byte[s.length+2];
		for(int i=0;i<s.length;i++){
			byte sda=s[i];
			ss[i]=sda;
		}
		ss[s.length]=13;
		ss[s.length+1]=10;
		//�����ڷ�������
		System.out.println("����AT+CMGF=1ָ��");
		sendToPort(serialPort,ss);
		Thread.sleep(1000);
		//���շ�������
		byte[] a=readFromPort(serialPort);
		System.out.println("����:"+new String(a));
		byte[] s1="AT+CSMP=17,167,2,25".getBytes();
		byte[] ss1=new byte[s1.length+2];
		for(int i=0;i<s1.length;i++){
			byte sda=s1[i];
			ss1[i]=sda;
		}
		ss1[s1.length]=13;
		ss1[s1.length+1]=10;
		System.out.println("����AT+CSMP=17,167,2,25ָ��");
		sendToPort(serialPort,ss1);
		Thread.sleep(1000);
		byte[] a1=readFromPort(serialPort);
		System.out.println("����:"+new String(a1));
		byte[] s11="AT+CSCS=\"UCS2\"".getBytes();
		byte[] ss11=new byte[s11.length+2];
		for(int i=0;i<s11.length;i++){
			byte sda=s11[i];
			ss11[i]=sda;
		}
		ss11[s11.length]=13;
		ss11[s11.length+1]=10;
		System.out.println("����AT+CSCS=\"UCS2\"ָ��");
		sendToPort(serialPort,ss11);
		Thread.sleep(1000);
		byte[] a11=readFromPort(serialPort);
		System.out.println("����:"+new String(a11));
		String hm=Snippet.cnToUnicodeHm(jshm);
		byte[] s2=("AT+CMGS=\""+hm+"\"").getBytes();
		//��������ָ���ֻ���
		byte[] ss2=new byte[s2.length+2];
		for(int i=0;i<s2.length;i++){
			byte sda=s2[i];
			ss2[i]=sda;
		}
		ss2[s2.length]=13;
		ss2[s2.length+1]=10;
		System.out.println("����AT+CMGS=\"17338145202\"ָ��");
		sendToPort(serialPort,ss2);
		Thread.sleep(4000);
		byte[] a2=readFromPort(serialPort);
		System.out.println(new String(a2));
		 byte[] s3=Snippet.cnToUnicodeHm(conut).getBytes();
		sendToPort(serialPort,s3);
		Thread.sleep(4000);
		//����1Aָ��
		 int s4=0X1A;
		 sendToPortHex16(serialPort,s4);
		Thread.sleep(14000);
		byte[] a3=readFromPort(serialPort);
		System.out.println(new String(a3));
		closePort(serialPort);
		return true;
	}
	
	private static SerialTool serialTool = null;
	
	static {
		//�ڸ��౻ClassLoader����ʱ�ͳ�ʼ��һ��SerialTool����
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
	}
	
	//˽�л�SerialTool��Ĺ��췽��������������������SerialTool����
	private SerialTool() {}	
	
	/**
	 * ��ȡ�ṩ�����SerialTool����
	 * @return serialTool
	 */
	public static SerialTool getSerialTool() {
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}



	/**
	 * �������п��ö˿�
	 * @return ���ö˿������б�
	 */
	public static final ArrayList<String> findPort() {

		//��õ�ǰ���п��ô���
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        
        ArrayList<String> portNameList = new ArrayList<String>();

        //�����ô�������ӵ�List�����ظ�List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            //AT+CNUM����ȡ�ֻ���ָ��
            
            portNameList.add(portName);
        }

        return portNameList;

    }
	/**
	 * �������п��ö˿�
	 * @return ���ö˿������б�
	 * @throws PortInUse 
	 * @throws NoSuchPort 
	 * @throws NotASerialPort 
	 * @throws SerialPortParameterFailure 
	 * @throws SerialPortOutputStreamCloseFailure 
	 * @throws SendDataToSerialPortFailure 
	 * @throws InterruptedException 
	 */
	public static final ArrayList<String[]> findPortHm() throws Exception {

		//��õ�ǰ���п��ô���
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        ArrayList<String[]> portNameList = new ArrayList<String[]>();
        //�����ô�������ӵ�List�����ظ�List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            //AT+CNUM����ȡ�ֻ���ָ��
            SerialPort serialPort=openPort(portName,baudrate);
			byte[] s="AT+CNUM".getBytes();
			byte[] ss=new byte[s.length+2];
			for(int i=0;i<s.length;i++){
				byte sda=s[i];
				ss[i]=sda;
			}
			ss[s.length]=13;
			ss[s.length+1]=10;
			//�����ڷ�������
			System.out.println("����AT+CNUMָ��");
			sendToPort(serialPort,ss);
			Thread.sleep(4000);
			//���շ�������
			byte[] a=readFromPort(serialPort);
			System.out.println("����:"+new String(a));
            portNameList.add(new String[]{portName,new String(a)});
            closePort(serialPort);
        }

        return portNameList;

    }
	
    
    /**
     * �򿪴���
     * @param portName �˿�����
     * @param baudrate ������
     * @return ���ڶ���
     * @throws SerialPortParameterFailure ���ô��ڲ���ʧ��
     * @throws NotASerialPort �˿�ָ���豸���Ǵ�������
     * @throws NoSuchPort û�иö˿ڶ�Ӧ�Ĵ����豸
     * @throws PortInUse �˿��ѱ�ռ��
     */
    public static final SerialPort openPort(String portName, int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse {

        try {

            //ͨ���˿���ʶ��˿�
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //�򿪶˿ڣ������˿����ֺ�һ��timeout���򿪲����ĳ�ʱʱ�䣩
            CommPort commPort = portIdentifier.open(portName, 2000);

            //�ж��ǲ��Ǵ���
            if (commPort instanceof SerialPort) {
            	
                SerialPort serialPort = (SerialPort) commPort;
                
                try {                    	
                    //����һ�´��ڵĲ����ʵȲ���
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);                              
                } catch (UnsupportedCommOperationException e) {  
                	throw new SerialPortParameterFailure();
                }
                
                System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            
            }        
            else {
            	//���Ǵ���
            	throw new NotASerialPort();
            }
        } catch (NoSuchPortException e1) {
          throw new NoSuchPort();
        } catch (PortInUseException e2) {
        	throw new PortInUse();
        }
    }
    
    /**
     * �رմ���
     * @param serialport ���رյĴ��ڶ���
     */
    public static void closePort(SerialPort serialPort) {
    	if (serialPort != null) {
    		serialPort.close();
    		serialPort = null;
    	}
    }
    
    /**
     * �����ڷ�������
     * @param serialPort ���ڶ���
     * @param order	����������
     * @throws SendDataToSerialPortFailure �򴮿ڷ�������ʧ��
     * @throws SerialPortOutputStreamCloseFailure �رմ��ڶ�������������
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
    	OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
            
        } catch (IOException e) {
        	throw new SendDataToSerialPortFailure();
        } finally {
        	try {
        		if (out != null) {
        			out.close();
        			out = null;
        		}				
			} catch (IOException e) {
				throw new SerialPortOutputStreamCloseFailure();
			}
        }
    }
    
    /**
     * д16����
     * @param serialPort
     * @param order
     * @throws SendDataToSerialPortFailure
     * @throws SerialPortOutputStreamCloseFailure
     */
    public static void sendToPortHex16(SerialPort serialPort, int order)throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure{
    	OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
            
        } catch (IOException e) {
        	throw new SendDataToSerialPortFailure();
        } finally {
        	try {
        		if (out != null) {
        			out.close();
        			out = null;
        		}				
			} catch (IOException e) {
				throw new SerialPortOutputStreamCloseFailure();
			}
        }
    }
    
    /**
     * д16����
     * @param serialPort
     * @param order
     * @throws SendDataToSerialPortFailure
     * @throws SerialPortOutputStreamCloseFailure
     */
    public static void sendToPortUNICODE(SerialPort serialPort, char[] order)throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure{
    	OutputStream out = null;
    	 OutputStreamWriter outcode=null;
        try {
            out = serialPort.getOutputStream();
            outcode=new OutputStreamWriter(out,"UNICODE");
            outcode.write(order);
            outcode.flush();
            
        } catch (IOException e) {
        	throw new SendDataToSerialPortFailure();
        } finally {
        	try {
        		if (out != null) {
        			out.close();
        			out = null;
        		}
        		if (outcode != null) {
        			outcode.close();
        			outcode = null;
        		}
			} catch (IOException e) {
				throw new SerialPortOutputStreamCloseFailure();
			}
        }
    }
    
    /**
     * �Ӵ��ڶ�ȡ����
     * @param serialPort ��ǰ�ѽ������ӵ�SerialPort����
     * @return ��ȡ��������
     * @throws ReadDataFromSerialPortFailure �Ӵ��ڶ�ȡ����ʱ����
     * @throws SerialPortInputStreamCloseFailure �رմ��ڶ�������������
     */
    public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

    	InputStream in = null;
        byte[] bytes = null;

        try {
        	
        	in = serialPort.getInputStream();
        	int bufflenth = in.available();		//��ȡbuffer������ݳ���
            
        	while (bufflenth != 0) {                             
                bytes = new byte[bufflenth];	//��ʼ��byte����Ϊbuffer�����ݵĳ���
                in.read(bytes);
                bufflenth = in.available();
        	} 
        } catch (IOException e) {
        	throw new ReadDataFromSerialPortFailure();
        } finally {
        	try {
            	if (in != null) {
            		in.close();
            		in = null;
            	}
        	} catch(IOException e) {
        		throw new SerialPortInputStreamCloseFailure();
        	}

        }

        return bytes;

    }
    
    /**
     * ��Ӽ�����
     * @param port     ���ڶ���
     * @param listener ���ڼ�����
     * @throws TooManyListeners ������������
     */
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {

        try {
        	
            //��������Ӽ�����
            port.addEventListener(listener);
            //���õ������ݵ���ʱ���Ѽ��������߳�
            port.notifyOnDataAvailable(true);
          //���õ�ͨ���ж�ʱ�����ж��߳�
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
        	throw new TooManyListeners();
        }
    }
    
    
}
