package com.moko.support.mk107dpro35d.event;

public class MQTTUnSubscribeFailureEvent {
    private String topic;

    public MQTTUnSubscribeFailureEvent(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
