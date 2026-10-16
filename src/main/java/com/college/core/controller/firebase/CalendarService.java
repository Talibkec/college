package com.college.core.controller.firebase;

import com.college.core.config.KecProperties;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * Google Calendar client for the leave events (service account with a P12 key, settings under kec.calendar.*).
 *
 * <p>A configuration problem used to call {@code System.exit(1)} and stop the whole server; now
 * {@link #configure()} throws an {@link IllegalStateException}, so only the request that needs the calendar fails.
 */
@Component
public class CalendarService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private static final String APPLICATION_NAME = "Google Calendar API";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final KecProperties.GoogleCalendar settings;

    public CalendarService(KecProperties kecProperties) {
        this.settings = kecProperties.calendar();
    }

    public Calendar configure() {
        if (settings.serviceAccount().startsWith("Enter ")) {
            throw new IllegalStateException("Google Calendar service account is not configured");
        }
        try {
            HttpTransport httpTransport = new NetHttpTransport();
            HttpRequestInitializer setHttpTimeout = httpRequest -> {
                httpRequest.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
                httpRequest.setReadTimeout(3 * 60000);  // 3 minutes read timeout
            };
            URL loc = this.getClass().getResource(settings.p12Key());
            File file = new File(loc.getPath());
            String p12Content = Files.readFirstLine(file, Charset.defaultCharset());
            if (p12Content.startsWith("Please")) {
                throw new IllegalStateException("Google Calendar P12 key file is a placeholder");
            }
            // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(settings.serviceAccount())
                    .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
                    .setRequestInitializer(setHttpTimeout)
                    .setServiceAccountPrivateKeyFromP12File(file)
                    .build();
            Calendar client = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            logger.debug("Google Calendar client created");
            return client;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Could not configure the Google Calendar client", e);
        }
    }
}
