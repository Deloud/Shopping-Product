package com.shoppingmall.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//HTTP Status code
// 2XX -> OK
// 4XX -> Client 잘못
// 5XX -> Server 문제

@ResponseStatus(HttpStatus.NOT_FOUND) //NOT_FOUND 에러가 나오도록 만듬
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}