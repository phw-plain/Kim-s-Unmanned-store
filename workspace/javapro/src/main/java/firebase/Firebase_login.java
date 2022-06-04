package firebase;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import code.Setting;

public class Firebase_login extends App{
	public boolean login() {
		Setting setting= new Setting();
		db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection("Manager").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = null;
		try {
			querySnapshot = query.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.util.List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			if(setting.getId().equals(document.getString("id"))&&setting.getPw().equals(document.getString("pw"))) {
				return true;
			}
		}
		return false;
	}
	
}
