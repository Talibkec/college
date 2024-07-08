package com.college.core.controller.firebase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.lang.Override;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.common.io.Files;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.util.DateTime;


public class CalendarService {
    private static final String APPLICATION_NAME = "Google Calendar API";

    /** E-mail address of the service account. */
    private static final String SERVICE_ACCOUNT_EMAIL = "online-attendance-7e3c3@appspot.gserviceaccount.com";

    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;
     private HttpRequestInitializer setHttpTimeout;
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    /*private static final HttpRequestInitializer setHttpTimeout(final HttpRequestInitializer requestInitializer) {
        return new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
                requestInitializer.initialize(httpRequest);
                httpRequest.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
                httpRequest.setReadTimeout(3 * 60000);  // 3 minutes read timeout
            }
        };*/
    public  Calendar configure() {
        
        try {
            try {
                httpTransport = new NetHttpTransport();
                setHttpTimeout = new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest httpRequest) throws IOException {
                        
                        httpRequest.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
                        httpRequest.setReadTimeout(3 * 60000);  // 3 minutes read timeout
                    }
                };
                // check for valid setup
                if (SERVICE_ACCOUNT_EMAIL.startsWith("Enter ")) {
                    System.err.println(SERVICE_ACCOUNT_EMAIL);
                    System.exit(1);
                }
                
                    
                URL loc = this.getClass().getResource("/online-attendance-7e3c3-8252f7614e5e.p12");
                String path = loc.getPath();
                File file = new File(path);
                String p12Content = Files.readFirstLine(file, Charset.defaultCharset());
                if (p12Content.startsWith("Please")) {
                    System.err.println(p12Content);
                    System.exit(1);
                }
                
               
                // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
                GoogleCredential credential = new GoogleCredential.Builder()
                        .setTransport(httpTransport)
                        .setJsonFactory(JSON_FACTORY)
                        .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                        .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
                        .setRequestInitializer(setHttpTimeout)
                        .setServiceAccountPrivateKeyFromP12File(file)
                        .build();
                Calendar   client = new com.google.api.services.calendar.Calendar.Builder(
                        httpTransport, JSON_FACTORY, credential)
                        .setApplicationName(APPLICATION_NAME).build();
                System.out.println("Client : "+client);
                return client;

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
        return null;
    }
}
