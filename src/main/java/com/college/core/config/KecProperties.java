package com.college.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application settings under {@code kec.*} (see application.properties). These values were hard-coded in the Java
 * code before the refactoring; the defaults in application.properties are the old values.
 */
@ConfigurationProperties(prefix = "kec")
public record KecProperties(Mail mail, Paths paths, Sms sms) {

    /**
     * @param from        sender address of the mails the application sends
     * @param grievanceTo recipient of the grievance form
     */
    public record Mail(String from, String grievanceTo) {
    }

    /**
     * @param noticeUploadDir    directory of notice files on disk (deleted together with a notice)
     * @param homePageImageDir   web-app directory of the cached home page images
     */
    public record Paths(String noticeUploadDir, String homePageImageDir) {
    }

    /**
     * Textlocal bulk SMS.
     *
     * @param url    API endpoint
     * @param sender sender id
     * @param apiKey API key (environment variable {@code SMS_API_KEY}; never commit it)
     */
    public record Sms(String url, String sender, String apiKey) {
    }
}
