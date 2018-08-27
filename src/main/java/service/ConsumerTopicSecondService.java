package service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTopicSecondService {
    public ConsumerTopicSecondService() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        //Create Connection
        Connection connection = factory.createConnection();

        // Start the connection
        connection.start();

        // Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Create destination (queue or topic)
        Destination topic = session.createTopic(ConsumerTopicService.topicName);

        MessageConsumer consumerTopic = session.createConsumer(topic);

        consumerTopic.setMessageListener(new consumerTopicListener());
    }

    private static class consumerTopicListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Topic Consumer 2 " + Thread.currentThread().getName() + " received message from "+ ConsumerTopicService.topicName +": " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
