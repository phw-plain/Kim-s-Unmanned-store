package firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firestore.v1.WriteRequest;

import code.Setting;
import lombok.extern.java.Log;
import code.Setting;

public class App extends Setting {
	FileInputStream serviceAccount = null;
	public static FirebaseOptions options = null;
	static Firestore db;
	FirebaseApp firebaseApp = null; 
	public App() {
		
		try {
		
			serviceAccount = new FileInputStream("./src/main/json/parklee-firebase.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.util.List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		if(firebaseApps != null && !firebaseApps.isEmpty()){
			for(FirebaseApp app : firebaseApps){
				if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
					firebaseApp = app;
					}
				}
			}else{
				try {
					options = new FirebaseOptions.Builder()
							.setCredentials(GoogleCredentials.fromStream(serviceAccount))
							.setDatabaseUrl("https://parklee-4b50c-default-rtdb.firebaseio.com")
							.setStorageBucket("parklee-market.appspot.com")
							.build();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				firebaseApp = FirebaseApp.initializeApp(options);
			}
	}
	

}
