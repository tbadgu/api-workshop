package com.badgu.apiworkshop.model;


public class ErrorInfo {
    private String requestUrl;
    private String description;

    public ErrorInfo() {
    }

    public ErrorInfo(String requestUrl, String description) {
        this.requestUrl = requestUrl;
        this.description = description;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "requestUrl='" + requestUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
