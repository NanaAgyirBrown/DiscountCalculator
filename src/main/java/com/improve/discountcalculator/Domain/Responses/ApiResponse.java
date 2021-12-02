package com.improve.discountcalculator.Domain.Responses;

public class ApiResponse {
    public ApiResponse() {
        ErrorPayload = new ServerErrorResponse(null, null);
    }
    public boolean IsSuccessful;
    public boolean HasError;
    public boolean HasException;
    public Object Data;
    public ServerErrorResponse ErrorPayload;

    public ApiResponse(boolean isSuccessful, boolean hasException, boolean hasError, Object data, ServerErrorResponse errorPayload) {
        this.IsSuccessful = isSuccessful;
        this.HasError = hasError;
        this.HasException = hasException;
        this.Data = data;
        this.ErrorPayload = errorPayload;
    }
}
