package com.college.core.controller;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.jasper.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(SocketTimeoutException.class)
    public final void SocketTimeException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error("SocketTimeOutException has Occured");
    }


    @ExceptionHandler(IOException.class)
    public final void handleIOException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(ex.getMessage() != null && ex.getMessage().contains("Broken pipe")){
            logger.error("Broken Pipe exception has occured");
        }
        else {
            logger.error("Looks like pipe broken exception");
        }
    }


    @ExceptionHandler(ClientAbortException.class)
    public final void ClientAbortionException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error("ClientAbortException exception");
    }


    final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public final void handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        if(ex.getMessage() != null && ex.getMessage().contains("Broken pipe")){
            logger.error("Broken Pipe exception has occured");
        }
        else {
            logger.error("Something went wrong", ex);
        }
    }


}