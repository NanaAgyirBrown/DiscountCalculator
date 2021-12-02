package com.improve.discountcalculator.WebApi.Helpers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DiscountStatus {
    public final HttpStatus Created = HttpStatus.CREATED;
    public final HttpStatus Fetched = HttpStatus.OK;
    public final HttpStatus Updated = HttpStatus.ACCEPTED;
    public final HttpStatus Deleted = HttpStatus.IM_USED;
    public final HttpStatus Crashed = HttpStatus.INTERNAL_SERVER_ERROR;
    public final HttpStatus Failed = HttpStatus.PRECONDITION_FAILED;
    public final HttpStatus InValidObject = HttpStatus.BAD_REQUEST;
    public final HttpStatus NotFound = HttpStatus.NOT_FOUND;
}
