package receiver;

import javax.jms.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	public static void main(String[] args) {
		try{

			/***************************************/
			/** TP2 - Amel BENAZA - Jérémy FOURES **/
			/***************************************/

			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			Queue queue = (Queue) applicationContext.getBean("queue");

			/** Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html**/
			QueueConnection connection = factory.createQueueConnection() ;

			/** Open a session **/
			QueueSession session = connection.createQueueSession( false, Session.AUTO_ACKNOWLEDGE) ;

			/** start the connection **/
			connection.start();

			/** Create a receive **/
			QueueReceiver receiver = session.createReceiver( queue ) ;
			//QueueReceiver receiver2 = session.createReceiver( queue ) ;

			/** Receive the message **/
			//Is a queue able to receive message from many senders? YES !
			TextMessage m = (TextMessage)receiver.receive();
			System.out.println("\n\n MESSAGE RECU RECEIVER 1: " + m.getText()+"\n\n");
			TextMessage m2 = (TextMessage)receiver.receive();
			System.out.println("\n\n MESSAGE RECU RECEIVER 1 : " + m2.getText()+"\n\n");

			//Is a queue able to send message to many receivers? NO , need to use TOPIC!
			// TEST :
			//TextMessage m1 = (TextMessage)receiver2.receive();
			//System.out.println("\n\n MESSAGE RECU RECEIVER 2 : " + m1.getText()+"\n\n");
			//TextMessage m3 = (TextMessage)receiver2.receive();
			//System.out.println("\n\n MESSAGE RECU RECEIVER 2 : " + m3.getText()+"\n\n");

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}


