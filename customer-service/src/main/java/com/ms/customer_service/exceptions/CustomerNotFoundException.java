package com.ms.customer_service.exceptions;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
