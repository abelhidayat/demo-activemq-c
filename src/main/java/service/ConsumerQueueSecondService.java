package service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerQueueSecondService {
    public ConsumerQueueSecondService() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        //Create Connection
        Connection connection = factory.createConnection();

        // Start the connection
        connection.start();

        // Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createQueue(ConsumerQueueService.queueName);

        MessageConsumer consumerQueue = session.createConsumer(queue);

        consumerQueue.setMessageListener(new consumerQueueListener());
    }

    private static class consumerQueueListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Queue Consumer 2 " + Thread.currentThread().getName() + " received message from ("+ ConsumerQueueService.queueName +") : " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
