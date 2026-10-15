package com.college.core.controller;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

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
        if (ex.getMessage() != null && ex.getMessage().contains("Broken pipe")) {
            logger.error("Broken Pipe exception has occured");
        } else {
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
        if (ex.getMessage() != null && ex.getMessage().contains("Broken pipe")) {
            logger.error("Broken Pipe exception has occured");
        } else {
            logger.error("Something went wrong", ex);
        }
    }

    // Upgrade compatibility (Spring 6): standard MVC exceptions (missing parameter, wrong method, ...) now get an
    // RFC 7807 JSON body. Before the upgrade these responses had the status code only, so drop the body.
    @Override
    protected ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode,
                                                          WebRequest request) {
        return new ResponseEntity<>(headers, statusCode);
    }

    // Upgrade compatibility (Spring 6.1+): unknown URLs raise NoResourceFoundException. Before the upgrade they went
    // to the container's 404 error page (/error -> error-404.jsp), so send the error instead of answering here.
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers,
                                                                    HttpStatusCode status, WebRequest request) {
        try {
            ((ServletWebRequest) request).getResponse().sendError(HttpStatus.NOT_FOUND.value());
        } catch (IOException e) {
            logger.error("Could not send 404", e);
        }
        return null;
    }

}