package com.sample.demo.controllers.dto;

import lombok.Data;

@Data
public class HttpUserInfoResponse {
    private String resultCode;
    private String resultMessage;
    private String resultDescription;

    public HttpUserInfoResponse() {
    }

    public HttpUserInfoResponse(String resultCode, String resultMessage, String resultDescription) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultDescription = resultDescription;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }
}
