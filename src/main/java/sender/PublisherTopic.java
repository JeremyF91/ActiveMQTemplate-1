package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.jms.*;

public class PublisherTopic {

    public static void main(String[] args) {
        try{

            /***************************************/
            /** TP2 - Amel BENAZA - Jérémy FOURES **/
            /***************************************/

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
            Topic topic = (Topic) applicationContext.getBean("topic");

            /** Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html **/
            TopicConnection connection = factory.createTopicConnection() ;

            /** Open a session without transaction and acknowledge automatic **/
            TopicSession session = connection.createTopicSession( false, Session.AUTO_ACKNOWLEDGE) ;

            /** Start the connection **/
            connection.start();

            /** Create a publisher **/
            TopicPublisher publisher = session.createPublisher(topic);

            /** Create a message **/
            TextMessage message = session.createTextMessage();
            message.setText("MESSAGE PUBLISHER 1 ");
            System.out.println("MESSAGE RECEIVED BY PUBLISHER 1: " + message.getText());

            /** Send the message **/
            publisher.publish(message,DeliveryMode.PERSISTENT,9,10000);

            /** Close the session **/
            session.close();

            /** Close the connection **/
            connection.close();

        }catch(Exception e){

            e.printStackTrace();
        }

    }

}

