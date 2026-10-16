package firebase.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;

public class FirebaseSetup {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseSetup.class);



    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void initialize() {
        try {

            Resource resource = resourceLoader.getResource("classpath:kecattendance-account-info.json");
            InputStream serviceAccount = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kecattendance.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            logger.error("Firebase initialisation failed", e);
        }

    }
}
