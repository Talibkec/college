package com.college.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application settings under {@code kec.*} (see application.properties). These values were hard-coded in the Java
 * code before the refactoring; the defaults in application.properties are the old values.
 */
@ConfigurationProperties(prefix = "kec")
public record KecProperties(Mail mail, Paths paths, Sms sms, Firebase firebase, GoogleCalendar calendar, Report report) {

    /**
     * @param from        sender address of the mails the application sends
     * @param grievanceTo recipient of the grievance form
     */
    public record Mail(String from, String grievanceTo) {
    }

    /**
     * @param noticeUploadDir    directory of notice files on disk (deleted together with a notice)
     * @param homePageImageDir   web-app directory of the cached home page images
     * @param pdfLogo            logo file (absolute path) printed on the attendance-report and leave PDFs
     */
    public record Paths(String noticeUploadDir, String homePageImageDir, String pdfLogo) {
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

    /**
     * Firebase Realtime Database of the attendance app.
     *
     * @param databaseUrl  database URL
     * @param credentials  service-account JSON (Spring resource location)
     */
    public record Firebase(String databaseUrl, String credentials) {
    }

    /**
     * Google Calendar for leave events.
     *
     * @param serviceAccount service-account e-mail
     * @param p12Key         classpath location of the service account's P12 key
     * @param calendarId     calendar that receives the leave events
     */
    public record GoogleCalendar(String serviceAccount, String p12Key, String calendarId) {
    }

    /** @param attendanceMailTo recipient of every attendance report (/rn) */
    public record Report(String attendanceMailTo) {
    }
}
