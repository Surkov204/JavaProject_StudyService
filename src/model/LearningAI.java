package model;

public class LearningAI {
    private String subject;
    private String topic;
    private String response;

    public LearningAI(String subject, String topic, String response) {
        this.subject = subject;
        this.topic = topic;
        this.response = response;
    }

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Subject: " + subject + "\nTopic: " + topic + "\nResponse: " + response;
    }
}
