package firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

import code.Inventory;
import code.Setting;

public class Firebase_inventory extends App{
	Setting set = new Setting();
	public HashMap<String, Object> getQuoteFormHTTP(String code, String name, String category, String standard, int cnt, String cost, String price, int amountDay, int amountMonth, String explain, String picture) throws IOException{
    	db = FirestoreClient.getFirestore();
    	HashMap<String,Object> map = new HashMap();
    	map.put("code", code);
    	map.put("name", name);
    	map.put("category", category );
    	map.put("standard",standard );
    	map.put("cnt", cnt);
    	map.put("cost", cost);
    	map.put("price", price);
    	map.put("amountDay", amountDay);
    	map.put("amountMonth", amountMonth);
    	map.put("explain", explain);
    	map.put("picture", picture);
    	
    	//id,pw,name,brand,location,empsal
		return map;
    }
	public void Add_inventory(String code, String name, String category, String standard, int cnt, String cost, String price,int amountDay, int amountMonth,  String explain, String picture) throws Exception {
		db = FirestoreClient.getFirestore();
    	Firebase_inventory getQuote = new Firebase_inventory();
    	HashMap<String, Object> quote = getQuote.getQuoteFormHTTP(code, name, category, standard, cnt, cost, price, amountDay, amountMonth, explain, picture);
    	try {
        	ApiFuture<WriteResult> hello = db.collection("Manager").document(getId()).collection("inventory").document(code).set(quote);
    	System.out.println("we Did" + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
	
	public void show_inventory() {
		db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection("Manager").document(getId()).collection("inventory").get();
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
			set.code.add(document.getString("code"));
			set.product_name.add(document.getString("name"));
			set.category.add(document.getString("category"));
			set.standard.add(document.getString("standard"));
			set.amountDay.add((document.getLong("amountDay")).intValue());
			set.amountMonth.add((document.getLong("amountMonth")).intValue());
			set.cost.add(Integer.parseInt(document.getString("cost")));
			set.price.add(Integer.parseInt(document.getString("price")));
			set.cnt.add((document.getLong("cnt")).intValue());
			set.explain.add(document.getString("explain"));
			set.picture.add(document.getString("picture"));
		}
	}
	public void remove_inventory(String code) {
		db.collection("Manager").document(getId()).collection("inventory").document(code).delete();
	}
	
	public void update_Inventory(String code, String name, String category, String standard, int cnt, String cost, String price, int amountDay, int amountMonth, String explain, String picture) throws Exception {
		db = FirestoreClient.getFirestore();
		Firebase_inventory getQuote = new Firebase_inventory();
		HashMap<String, Object> quote = null;
		try { 
			quote = getQuote.getQuoteFormHTTP(code, name, category, standard, cnt, cost, price, amountDay,amountMonth,explain, picture);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
        	ApiFuture<WriteResult> hello = db.collection("Manager").document(getId()).collection("inventory").document(code).update(quote);
    	System.out.println("update_Inventory at " + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
	
}
