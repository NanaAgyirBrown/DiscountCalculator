package com.improve.discountcalculator.WebApi.Helpers;

import com.improve.discountcalculator.Domain.Responses.ApiResponse;
import com.improve.discountcalculator.Domain.Responses.ServerErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Responses {
    public ApiResponse GetResponse(OperationStatus Status, HttpStatus StatusCode, List<String> Messages, Object ResponseData){

        switch(Status) {
            case Exception:
                return new ApiResponse(false, true, false, null, new ServerErrorResponse(StatusCode, Messages));
            case Failed:
                return new ApiResponse(false, false, true, null, new ServerErrorResponse(StatusCode, Messages));
            case Success:
                return new ApiResponse(true, false, false, ResponseData,null);
            default:
                return new ApiResponse(false, false, false, ResponseData, null);
        }
    }
}
