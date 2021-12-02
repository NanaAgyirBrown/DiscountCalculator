package com.improve.discountcalculator.Domain.Responses;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ServerErrorResponse{
    public ServerErrorResponse(HttpStatus status, List<String> message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return message;
    }

    public HttpStatus status;
    public List<String> message;

}