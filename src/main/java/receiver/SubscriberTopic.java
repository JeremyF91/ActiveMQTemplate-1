package receiver;

import javax.jms.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SubscriberTopic {

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

            /** Open a session **/
            TopicSession session = connection.createTopicSession( false, Session.AUTO_ACKNOWLEDGE) ;

            /** start the connection **/
            connection.start();

            /** Create a subscriber **/
            TopicSubscriber subscriber = session.createSubscriber(topic) ;
            TopicSubscriber subscriber2 = session.createSubscriber(topic) ;

            /** Receive the message **/
            TextMessage message = (TextMessage)subscriber.receive();
            System.out.println("\nLE MESSAGE EST :" + message.getText() + "\n");

            TextMessage message2 = (TextMessage)subscriber2.receive();
            System.out.println("\nLE MESSAGE EST :" + message2.getText() + "\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
