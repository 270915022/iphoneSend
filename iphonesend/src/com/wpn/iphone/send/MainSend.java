/**
 * MainSend.java
 * ��Ȩ����(C) 2012 
 * ����:cuiran 2012-07-24 11:31:35
 */
package com.wpn.iphone.send;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javapns.back.SSLConnectionHelper;
import javapns.communication.exceptions.InvalidKeystoreReferenceException;
import javapns.data.PayLoad;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO
 * @author cuiran
 * @version TODO
 */
public class MainSend {
	private static Log log = LogFactory.getLog(MainSend.class.getName());
	
    /************************************************ 
    测试推送服务器地址：gateway.sandbox.push.apple.com /2195  
    产品推送服务器地址：gateway.push.apple.com / 2195  

   需要javaPNS_2.2.jar包 

    ***************************************************/  
   /** 

    *这是一个比较简单的推送方法， 

    * apple的推送方法 

    * @param tokens   iphone手机获取的token 

    * @param path 这里是一个.p12格式的文件路径，需要去apple官网申请一个  

    * @param password  p12的密码 此处注意导出的证书密码不能为空因为空密码会报错 

    * @param message 推送消息的内容 

    * @param count 应用图标上小红圈上的数值 

    * @param sendCount 单发还是群发  true：单发 false：群发 

    */ 

	public void sendpush(List<String> tokens,String path, String password, String message,Integer count,boolean sendCount) {

	try {
		  //message是一个json的字符串{“aps”:{“alert”:”iphone推送测试”}}  

			PushNotificationPayload payLoad =  PushNotificationPayload.fromJSON(message);
			
			payLoad.addAlert(""); // 消息内容  
			
			payLoad.addBadge(count); // iphone应用图标上小红圈上的数值  ֵ
			
			payLoad.addSound("default");  // 铃音 默认  
		
			

			PushNotificationManager pushManager = new PushNotificationManager();
			
			//true：表示的是产品发布推送服务 false：表示的是产品测试推送服务  
			
			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(path, password, false));
			
			List<PushedNotification> notifications = new ArrayList<PushedNotification>(); 
			
			 // 发送push消息  
			
			if (sendCount) {
			
			log.debug("--------------------------apple 推送 单-------");
			
			Device device = new BasicDevice();
			
			device.setToken(tokens.get(0));
			
			PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
			
			notifications.add(notification);
			
			} else {
			
				log.debug("--------------------------apple 推送 群-------");
			
			List<Device> device = new ArrayList<Device>();
			
			for (String token : tokens) {
			
			device.add(new BasicDevice(token));
			
			}
			
			notifications = pushManager.sendNotifications(payLoad, device);
			
			}
			
			List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
			
			List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
			
			int failed = failedNotifications.size();
			
			int successful = successfulNotifications.size();
			
			 
			
			if (successful > 0 && failed == 0) {
			
			log.debug("-----All notifications pushed 成功 (" + successfulNotifications.size() + "):");
			
			} else if (successful == 0 && failed > 0) {
			
			log.debug("-----All notifications 失败 (" + failedNotifications.size() + "):");
			
			} else if (successful == 0 && failed == 0) {
			
			System.out.println("No notifications could be sent, probably because of a critical error");
			
			} else {
			
			log.debug("------Some notifications失败 (" + failedNotifications.size() + "):");
			
			log.debug("------Others  成功 (" + successfulNotifications.size() + "):");
			
			}
	
	// pushManager.stopConnection();

	} catch (Exception e) {
	
	e.printStackTrace();
	
	}

}
	
	/**
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		MainSend send=new MainSend();
		List<String> tokens=new ArrayList<String>();
		tokens.add("6d22d09ecf46d717b0ea8608331cd8776ab19ab9b4f05e7781e71294047ee81a");
		tokens.add("dc2cf037bd4465c851b1d96a86b0a028307bc7e443435b6fafe93c2957bb415c");
		String path="F:\\workspace1\\iphonesend\\WebRoot\\dbClientPushDev.p12";
		String password="Minto2016";
		 String message="{'aps':{'alert':'iphone推送测试 www.baidu.com'}}";  
		Integer count=1;
		boolean sendCount=false;
		send.sendpush(tokens, path, password, message, count, sendCount);
	}
}
