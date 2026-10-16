package com.college.core.controller;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Global exception handler for all controllers.
 *
 * <p>Behaviour kept from the original application: an exception thrown by a controller ends the request with
 * whatever was already written, i.e. usually an empty HTTP 200 page (the handler returns {@code void} and writes
 * nothing). Only the logging is done here: aborted client connections are logged briefly, everything else with the
 * request and the stack trace.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, NativeWebRequest request) {
        if (isClientAbort(ex)) {
            log.warn("Client aborted the connection: {} {}", describe(request), ex.getClass().getSimpleName());
        } else if (ex instanceof SocketTimeoutException) {
            log.error("Socket timeout while handling {}", describe(request), ex);
        } else {
            log.error("Unhandled exception while handling {}", describe(request), ex);
        }
    }

    private static boolean isClientAbort(Throwable ex) {
        if (ex instanceof ClientAbortException) {
            return true;
        }
        return ex instanceof IOException && ex.getMessage() != null && ex.getMessage().contains("Broken pipe");
    }

    private static String describe(NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        return servletRequest == null ? request.getDescription(false)
                : servletRequest.getMethod() + " " + servletRequest.getRequestURI();
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
            log.error("Could not send 404", e);
        }
        return null;
    }

}
