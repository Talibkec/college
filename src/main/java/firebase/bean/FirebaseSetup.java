package firebase.bean;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;

public class FirebaseSetup {

    @PostConstruct
    public void initialize() {
        try {

            String accountInfoPath = "." + File.separator +
                    "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "online-attendance-accountInfo.json";

            FileInputStream serviceAccount =
                    new FileInputStream(accountInfoPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://online-attendance-7e3c3.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
