package firebase;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

public class Firebase_login extends App{
	public static void getID(String id, String pw) throws ExecutionException, InterruptedException{
    	db = FirestoreClient.getFirestore();
    	DocumentReference docRef = db.collection("Manager").document(id);
    	ApiFuture<DocumentSnapshot> future = docRef.get();
    	DocumentSnapshot document = future.get();
    	if(document.exists()) {
    		System.out.println("true");
    		existence = true;
    	}else {
    		System.out.println("false");
    		existence = false;
    	}
    }
	
}
