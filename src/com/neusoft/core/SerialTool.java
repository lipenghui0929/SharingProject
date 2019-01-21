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
 * 串口服务类，提供打开、关闭串口，读取、发送串口数据等服务（采用单例设计模式）
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
				//打开串口
				SerialPort serialPort=openPort(port,baudrate);
				byte[] s="AT+CMGF=1".getBytes();
				byte[] ss=new byte[s.length+2];
				for(int i=0;i<s.length;i++){
					byte sda=s[i];
					ss[i]=sda;
				}
				ss[s.length]=13;
				ss[s.length+1]=10;
				//往串口发送数据
				System.out.println("发送AT+CMGF=1指令");
				sendToPort(serialPort,ss);
				Thread.sleep(4000);
				//接收返回数据
				byte[] a=readFromPort(serialPort);
//				System.out.println("返回:"+new String(a));
				byte[] s1="AT+CSMP=17,167,2,25".getBytes();
				byte[] ss1=new byte[s1.length+2];
				for(int i=0;i<s1.length;i++){
					byte sda=s1[i];
					ss1[i]=sda;
				}
				ss1[s1.length]=13;
				ss1[s1.length+1]=10;
				System.out.println("发送AT+CSMP=17,167,2,25指令");
				sendToPort(serialPort,ss1);
				Thread.sleep(4000);
				byte[] a1=readFromPort(serialPort);
//				System.out.println("返回:"+new String(a1));
				byte[] s11="AT+CSCS=\"UCS2\"".getBytes();
				byte[] ss11=new byte[s11.length+2];
				for(int i=0;i<s11.length;i++){
					byte sda=s11[i];
					ss11[i]=sda;
				}
				ss11[s11.length]=13;
				ss11[s11.length+1]=10;
				System.out.println("发送AT+CSCS=\"UCS2\"指令");
				sendToPort(serialPort,ss11);
				Thread.sleep(4000);
				byte[] a11=readFromPort(serialPort);
//				System.out.println("返回:"+new String(a11));
				String hm=Snippet.cnToUnicodeHm("17338145202");
				byte[] s2=("AT+CMGS=\""+hm+"\"").getBytes();
				//往串口里指定手机号
				byte[] ss2=new byte[s2.length+2];
				for(int i=0;i<s2.length;i++){
					byte sda=s2[i];
					ss2[i]=sda;
				}
				ss2[s2.length]=13;
				ss2[s2.length+1]=10;
				System.out.println("发送AT+CMGS=\"17338145202\"指令");
				sendToPort(serialPort,ss2);
				Thread.sleep(4000);
				byte[] a2=readFromPort(serialPort);
				System.out.println(new String(a2));
				 byte[] s3=Snippet.cnToUnicodeHm("中英文短信发送测试,测试成功的！哈哈哈哈哒").getBytes();
				sendToPort(serialPort,s3);
				Thread.sleep(4000);
				//发送1A指令
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
		//往串口发送数据
		System.out.println("发送AT+CMGF=1指令");
		sendToPort(serialPort,ss);
		Thread.sleep(1000);
		//接收返回数据
		byte[] a=readFromPort(serialPort);
		System.out.println("返回:"+new String(a));
		byte[] s1="AT+CSMP=17,167,2,25".getBytes();
		byte[] ss1=new byte[s1.length+2];
		for(int i=0;i<s1.length;i++){
			byte sda=s1[i];
			ss1[i]=sda;
		}
		ss1[s1.length]=13;
		ss1[s1.length+1]=10;
		System.out.println("发送AT+CSMP=17,167,2,25指令");
		sendToPort(serialPort,ss1);
		Thread.sleep(1000);
		byte[] a1=readFromPort(serialPort);
		System.out.println("返回:"+new String(a1));
		byte[] s11="AT+CSCS=\"UCS2\"".getBytes();
		byte[] ss11=new byte[s11.length+2];
		for(int i=0;i<s11.length;i++){
			byte sda=s11[i];
			ss11[i]=sda;
		}
		ss11[s11.length]=13;
		ss11[s11.length+1]=10;
		System.out.println("发送AT+CSCS=\"UCS2\"指令");
		sendToPort(serialPort,ss11);
		Thread.sleep(1000);
		byte[] a11=readFromPort(serialPort);
		System.out.println("返回:"+new String(a11));
		String hm=Snippet.cnToUnicodeHm(jshm);
		byte[] s2=("AT+CMGS=\""+hm+"\"").getBytes();
		//往串口里指定手机号
		byte[] ss2=new byte[s2.length+2];
		for(int i=0;i<s2.length;i++){
			byte sda=s2[i];
			ss2[i]=sda;
		}
		ss2[s2.length]=13;
		ss2[s2.length+1]=10;
		System.out.println("发送AT+CMGS=\"17338145202\"指令");
		sendToPort(serialPort,ss2);
		Thread.sleep(4000);
		byte[] a2=readFromPort(serialPort);
		System.out.println(new String(a2));
		 byte[] s3=Snippet.cnToUnicodeHm(conut).getBytes();
		sendToPort(serialPort,s3);
		Thread.sleep(4000);
		//发送1A指令
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
		//在该类被ClassLoader加载时就初始化一个SerialTool对象
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
	}
	
	//私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
	private SerialTool() {}	
	
	/**
	 * 获取提供服务的SerialTool对象
	 * @return serialTool
	 */
	public static SerialTool getSerialTool() {
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}



	/**
	 * 查找所有可用端口
	 * @return 可用端口名称列表
	 */
	public static final ArrayList<String> findPort() {

		//获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        
        ArrayList<String> portNameList = new ArrayList<String>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            //AT+CNUM，获取手机号指令
            
            portNameList.add(portName);
        }

        return portNameList;

    }
	/**
	 * 查找所有可用端口
	 * @return 可用端口名称列表
	 * @throws PortInUse 
	 * @throws NoSuchPort 
	 * @throws NotASerialPort 
	 * @throws SerialPortParameterFailure 
	 * @throws SerialPortOutputStreamCloseFailure 
	 * @throws SendDataToSerialPortFailure 
	 * @throws InterruptedException 
	 */
	public static final ArrayList<String[]> findPortHm() throws Exception {

		//获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        ArrayList<String[]> portNameList = new ArrayList<String[]>();
        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            //AT+CNUM，获取手机号指令
            SerialPort serialPort=openPort(portName,baudrate);
			byte[] s="AT+CNUM".getBytes();
			byte[] ss=new byte[s.length+2];
			for(int i=0;i<s.length;i++){
				byte sda=s[i];
				ss[i]=sda;
			}
			ss[s.length]=13;
			ss[s.length+1]=10;
			//往串口发送数据
			System.out.println("发送AT+CNUM指令");
			sendToPort(serialPort,ss);
			Thread.sleep(4000);
			//接收返回数据
			byte[] a=readFromPort(serialPort);
			System.out.println("返回:"+new String(a));
            portNameList.add(new String[]{portName,new String(a)});
            closePort(serialPort);
        }

        return portNameList;

    }
	
    
    /**
     * 打开串口
     * @param portName 端口名称
     * @param baudrate 波特率
     * @return 串口对象
     * @throws SerialPortParameterFailure 设置串口参数失败
     * @throws NotASerialPort 端口指向设备不是串口类型
     * @throws NoSuchPort 没有该端口对应的串口设备
     * @throws PortInUse 端口已被占用
     */
    public static final SerialPort openPort(String portName, int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse {

        try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {
            	
                SerialPort serialPort = (SerialPort) commPort;
                
                try {                    	
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);                              
                } catch (UnsupportedCommOperationException e) {  
                	throw new SerialPortParameterFailure();
                }
                
                System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            
            }        
            else {
            	//不是串口
            	throw new NotASerialPort();
            }
        } catch (NoSuchPortException e1) {
          throw new NoSuchPort();
        } catch (PortInUseException e2) {
        	throw new PortInUse();
        }
    }
    
    /**
     * 关闭串口
     * @param serialport 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
    	if (serialPort != null) {
    		serialPort.close();
    		serialPort = null;
    	}
    }
    
    /**
     * 往串口发送数据
     * @param serialPort 串口对象
     * @param order	待发送数据
     * @throws SendDataToSerialPortFailure 向串口发送数据失败
     * @throws SerialPortOutputStreamCloseFailure 关闭串口对象的输出流出错
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
     * 写16进制
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
     * 写16进制
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
     * 从串口读取数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     * @throws ReadDataFromSerialPortFailure 从串口读取数据时出错
     * @throws SerialPortInputStreamCloseFailure 关闭串口对象输入流出错
     */
    public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

    	InputStream in = null;
        byte[] bytes = null;

        try {
        	
        	in = serialPort.getInputStream();
        	int bufflenth = in.available();		//获取buffer里的数据长度
            
        	while (bufflenth != 0) {                             
                bytes = new byte[bufflenth];	//初始化byte数组为buffer中数据的长度
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
     * 添加监听器
     * @param port     串口对象
     * @param listener 串口监听器
     * @throws TooManyListeners 监听类对象过多
     */
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {

        try {
        	
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
          //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
        	throw new TooManyListeners();
        }
    }
    
    
}
