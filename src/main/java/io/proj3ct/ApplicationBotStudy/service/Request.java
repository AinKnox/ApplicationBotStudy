package io.proj3ct.ApplicationBotStudy.service;
public class Request {
    private long chatId;
    private String requestType;

    public Request(long chatId, String requestType) {
        this.chatId = chatId;
        this.requestType = requestType;
    }

    public long getChatId() {
        return chatId;
    }

    public String getRequestType() {
        return requestType;
    }
}
