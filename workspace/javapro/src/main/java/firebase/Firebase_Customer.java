package firebase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import code.Setting;

public class Firebase_Customer extends App{
	Setting set = new Setting();
	public void show_customer() {
		db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection("Manager").document(getId()).collection("customer").get();
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
			set.customer_id.add(document.getString("id"));
			set.customer_pw.add(document.getString("pw"));
			set.customer_name.add(document.getString("name"));
			set.customer_telephone.add(document.getString("tel"));
			set.customer_email.add(document.getString("email"));
			set.customer_point.add((document.getLong("point")).intValue());
			set.customer_exchange.add(1);
			set.customer_refund.add(2);
		}
	}
	public void update_cutomer(String id, String pw, String name, String telephone, String email, int point) throws Exception {
		db = FirestoreClient.getFirestore();
		Firebase_Customer getQuote = new Firebase_Customer();
		HashMap<String, Object> quote = null;
		try {
			quote = getQuote.getQuoteFormHTTP(id,  pw,  name,  telephone,  email, point);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
        	ApiFuture<WriteResult> hello = db.collection("Manager").document(getId()).collection("customer").document(id).update(quote);
    	System.out.println("update_customer at " + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
	public HashMap<String, Object> getQuoteFormHTTP(String id, String pw, String name, String telephone, String email, int point) throws IOException{
    	db = FirestoreClient.getFirestore();
    	HashMap<String,Object> map = new HashMap();
    	map.put("id", id);
    	map.put("pw", pw);
    	map.put("name", name );
    	map.put("tel",telephone );
    	map.put("email", email);
    	map.put("point", point);
    	
    	//id,pw,name,brand,location,empsal
		return map;
    }

}
