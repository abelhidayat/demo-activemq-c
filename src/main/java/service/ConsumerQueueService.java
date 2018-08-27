package service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerQueueService {
    public static final String queueName = "FooQueue";

    public ConsumerQueueService() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        //Create Connection
        Connection connection = factory.createConnection();

        // Start the connection
        connection.start();

        // Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createQueue(queueName);

        MessageConsumer consumerQueue = session.createConsumer(queue);

        consumerQueue.setMessageListener(new consumerQueueListener());
    }

    private static class consumerQueueListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                Thread.sleep(3000);
                System.out.println("Queue Consumer " + Thread.currentThread().getName() + " received message from ("+ queueName +") : " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
