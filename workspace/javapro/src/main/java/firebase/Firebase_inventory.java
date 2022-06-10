package firebase;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import code.Inventory;
import code.Setting;

public class Firebase_inventory extends App{
	Setting set = new Setting();
	public HashMap<String, Object> getQuoteFormHTTP(String code, String name, String category, String standard, String cnt, String price, String cost, String amount, String explain, String picture) throws IOException{
    	db = FirestoreClient.getFirestore();
    	HashMap<String,Object> map = new HashMap();
    	map.put("code", code);
    	map.put("name", name);
    	map.put("category", category );
    	map.put("standard",standard );
    	map.put("cnt", cnt);
    	map.put("price", price);
    	map.put("cost", cost);
    	map.put("amount", amount);
    	map.put("explain", explain);
    	map.put("picture", picture);
    	
    	//id,pw,name,brand,location,empsal
		return map;
    }
	public void Add_inventory(String code, String name, String category, String standard, String cnt, String price, String cost, String amount, String explain, String picture) throws Exception {
		db = FirestoreClient.getFirestore();
    	Firebase_inventory getQuote = new Firebase_inventory();
    	HashMap<String, Object> quote = getQuote.getQuoteFormHTTP(code, name, category, standard, cnt, price, cost, amount, explain, picture);
    	try {
        	ApiFuture<WriteResult> hello = db.collection(set.getId()).document(code).set(quote);
    	System.out.println("we Did" + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
	
	public void show_inventory() {
		Setting inventory = new Setting();
		db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection(getId()).get();
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
			set.amount.add(Integer.parseInt(document.getString("amount")));
			set.category.add(document.getString("category"));
			set.cnt.add(Integer.parseInt(document.getString("cnt")));
			set.code.add(document.getString("code"));
			set.cost.add(Integer.parseInt(document.getString("cost")));
			set.explain.add(document.getString("explain"));
			set.product_name.add(document.getString("name"));
			set.picture.add(document.getString("picture"));
			set.price.add(Integer.parseInt(document.getString("price")));
			set.standard.add(document.getString("standard"));
		}
	}
	public void remove_inventory(String code) {
		db.collection(getId()).document(code).delete();
	}
	
	public void update_Inventory(String code, String name, String category, String standard, String cnt, String price, String cost, String amount, String explain, String picture) throws Exception {
		db = FirestoreClient.getFirestore();
		Firebase_inventory getQuote = new Firebase_inventory();
		HashMap<String, Object> quote = null;
		try {
			quote = getQuote.getQuoteFormHTTP(code, name, standard, category, amount, cnt, price, cost, explain, picture);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
        	ApiFuture<WriteResult> hello = db.collection(set.getId()).document(code).update(quote);
    	System.out.println("update_Inventory at " + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
}
