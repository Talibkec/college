package firebase.bean;

import com.college.core.config.KecProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;

public class FirebaseSetup {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseSetup.class);



    private final ResourceLoader resourceLoader;
    private final KecProperties.Firebase settings;

    public FirebaseSetup(ResourceLoader resourceLoader, KecProperties kecProperties) {
        this.resourceLoader = resourceLoader;
        this.settings = kecProperties.firebase();
    }

    @PostConstruct
    public void initialize() {
        try {

            Resource resource = resourceLoader.getResource(settings.credentials());
            InputStream serviceAccount = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(settings.databaseUrl())
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            logger.error("Firebase initialisation failed", e);
        }

    }
}
