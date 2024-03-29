package sender;

import javax.jms.*;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {

		try{

			/***************************************/
			/** TP2 - Amel BENAZA - Jérémy FOURES **/
			/***************************************/

			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			Queue queue = (Queue) applicationContext.getBean("queue");

			/** Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html **/
			QueueConnection connection = factory.createQueueConnection() ;

			/** Open a session without transaction and acknowledge automatic **/
			QueueSession session = connection.createQueueSession( false, Session.AUTO_ACKNOWLEDGE) ;

			/** Start the connection **/
			connection.start();

			/** Create a sender **/
			QueueSender sender = session.createSender( queue) ;
			QueueSender sender2 = session.createSender( queue) ;

			/** Create a message **/
			TextMessage message = session.createTextMessage("MESSAGE SENDER 1 ");
			TextMessage message2 = session.createTextMessage("MESSAGE SENDER 2 ");

			/** Send the message **/
			//- Persistent mode
			//- Time to live
			//- Priority
			sender.send(message, DeliveryMode.PERSISTENT,4,10000) ;
			sender2.send(message2, DeliveryMode.PERSISTENT,4,10000) ;

			/** Close the session **/
			session.close();

			/** Close the connection **/
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

