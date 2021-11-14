import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import utils.Message;
import utils.Suggestion;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        /* enter */
        System.out.println("*** ENTER PROGRAMME **************************************");

        System.out.print("\n");

        /* object samples */
        System.out.println(new Message("Hello World!", "my.email@skylire.langvest.by", "Skylire CEO", "12", "4"));
        System.out.println(new Suggestion("my.email@skylire.langvest.by", "65", "g", "87437321"));

        System.out.print("\n");

        /* firebase admin initialization */
        FileInputStream serviceAccount = null;
        FirebaseOptions options = null;
        try {
            serviceAccount = new FileInputStream("skylire-langvest-by-firebase-adminsdk-n72a1-1d05d10fbe.json");
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://skylire-langvest-by-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(Objects.requireNonNull(options));

        /* FBD usage example 1 */
        DatabaseReference FBD_mode = FirebaseDatabase.getInstance().getReference().child("mode");
        System.out.println("Fetching app mode from FBD...");
        FBD_mode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer value = snapshot.getValue(Integer.class);
                if(value != null) System.out.println("App mode is " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
        // make delay as FBD request is async
        try {
            Thread.sleep(5000); // 5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n");

        /* FBD usage example 2 */
        DatabaseReference FBD_messages = FirebaseDatabase.getInstance().getReference().child("messages");
        System.out.println("Fetching 5 last messages from FBD...");
        FBD_messages.limitToLast(5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Iterable<DataSnapshot> messagesDataSnapshotList = snapshot.getChildren();
                for(DataSnapshot snapshotItem : messagesDataSnapshotList) {
                    Message message = snapshotItem.getValue(Message.class);
                    if(message != null) {
                        //message._setKey(snapshotItem.getKey());
                        System.out.println(message);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
        // make delay as FBD request is async
        try {
            Thread.sleep(5000); // 5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n");

        /* exit */
        System.out.println("*** EXIT PROGRAMME ***************************************");
    }
}