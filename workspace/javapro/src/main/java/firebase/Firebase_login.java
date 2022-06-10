package firebase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage.BucketAccessControls.List;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
	Setting setting= new Setting();
	public boolean login() {
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
				setting.setName(document.getString("name"));
				setting.setBrand(document.getString("brand"));
				setting.setLocation(document.getString("location"));
				setting.setEmpsal(Integer.parseInt(document.getString("empsal")));
				return true;
			}
		}
		return false;
	}
	public Map<String, Object> getQuoteFormHTTP() throws IOException{
    	db = FirestoreClient.getFirestore();
    	Map<String, Object> map = new HashMap();
    	Setting set = new Setting();
    	map.put("id", set.getId());
    	map.put("pw", set.getPw());
    	map.put("name", set.getName());
    	map.put("brand", set.getBrand());
    	map.put("location", set.getLocation());
    	map.put("empsal", Integer.toString(set.getEmpsal()));
    	//id,pw,name,brand,location,empsal
		return map;
    }
	public void Update_MyPage() throws Exception {
		db = FirestoreClient.getFirestore();
    	Map<String, Object> quote = getQuoteFormHTTP();
    	try {
        	ApiFuture<WriteResult> hello = db.collection("Manager").document(getId())
    				.update(quote);
    	System.out.println("we Did" + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
		
	}
	
}
