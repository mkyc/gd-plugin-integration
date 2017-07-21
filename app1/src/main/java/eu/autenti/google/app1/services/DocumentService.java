package eu.autenti.google.app1.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class DocumentService {
    public void getAndStoreDocument(String temporaryAccessToken, String fileId, String path) throws GeneralSecurityException, IOException {
        GoogleCredential cd = new GoogleCredential().setAccessToken(temporaryAccessToken);
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory factory = new GsonFactory();

        Drive drive = new Drive.Builder(transport, factory, cd).build();

        try {
            File f = new File(path);

            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(f);

            drive.files().export(fileId, "application/pdf")
                    .executeMediaAndDownloadTo(outputStream);

            outputStream.flush();
            outputStream.close();

            System.out.println("file location: " + f.getAbsolutePath() + " size: " + f.length());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
