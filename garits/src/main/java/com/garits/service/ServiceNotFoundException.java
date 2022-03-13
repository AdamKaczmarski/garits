package com.garits.service;

public class ServiceNotFoundException extends RuntimeException{
    ServiceNotFoundException(Integer id){
        super("Could not find service " + id);
    }
}
