package firebase;
import java.io.IOException;

import java.sql.DriverManager; 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;

import code.Setting;  

public class Firebase_join extends App{
	public static void getQuoteFormFirestore(String a) throws ExecutionException, InterruptedException{
    	db = FirestoreClient.getFirestore();
    	DocumentReference docRef = db.collection("Manager").document(a);
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
    
    
    public HashMap<String, String> getQuoteFormHTTP() throws IOException{
    	db = FirestoreClient.getFirestore();
    	HashMap<String,String> map = new HashMap();
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
	public void join(String id, String pw, String name, String brand, String location, int empsal) throws Exception {
		db = FirestoreClient.getFirestore();
    	Firebase_join getQuote = new Firebase_join();
    	HashMap<String, String> quote = getQuote.getQuoteFormHTTP();
    	try {
        	ApiFuture<WriteResult> hello = db.collection("Manager").document(id)
    				.set(quote);
    	System.out.println("we Did" + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
		
	}
	


}
