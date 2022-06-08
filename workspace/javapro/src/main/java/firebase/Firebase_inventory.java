package firebase;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import code.Inventory;
import code.Setting;

public class Firebase_inventory extends App{
	Setting set = new Setting();
	public HashMap<String, String> getQuoteFormHTTP(String code, String name, String category, String standard, String cnt, String price, String cost, String amount, String explain, String picture) throws IOException{
    	db = FirestoreClient.getFirestore();
    	HashMap<String,String> map = new HashMap();
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
    	HashMap<String, String> quote = getQuote.getQuoteFormHTTP(code, name, category, standard, cnt, price, cost, amount, explain, picture);
    	try {
        	ApiFuture<WriteResult> hello = db.collection(set.getId()).document(code).set(quote);
    	System.out.println("we Did" + hello.get().getUpdateTime());
    	}catch(Exception e){
    		   e.printStackTrace(); //오류 출력(방법은 여러가지)
    		   throw e; //최상위 클래스가 아니라면 무조건 던져주자
    	}
	}
	
	public void show_inventory() {
		Inventory inventory = new Inventory();
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
			inventory.code.add(document.getString("code"));
			inventory.name.add(document.getString("name"));
			inventory.standard.add(document.getString("standard"));
			inventory.category.add(document.getString("category"));
			inventory.amount.add(Integer.parseInt(document.getString("amount")));
			inventory.cnt.add(Integer.parseInt(document.getString("cnt")));
			inventory.price.add(Integer.parseInt(document.getString("price")));
			inventory.cost.add(Integer.parseInt(document.getString("cost")));
			inventory.explain.add(document.getString("explain"));
			inventory.picture.add(document.getString("code"));
		}
	}
}
