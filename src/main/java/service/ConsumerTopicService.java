package service;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTopicService {
    public static final String topicName = "FooTopic";

    public ConsumerTopicService() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        //Create Connection
        Connection connection = factory.createConnection();

        // Start the connection
        connection.start();

        // Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Create destination (queue or topic)
        Destination topic = session.createTopic(topicName);

        MessageConsumer consumerTopic = session.createConsumer(topic);

        consumerTopic.setMessageListener(new ConsumerTopicService.consumerTopicListener());
    }

    private static class consumerTopicListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Topic Consumer " + Thread.currentThread().getName() + " received message from "+ topicName +": " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
