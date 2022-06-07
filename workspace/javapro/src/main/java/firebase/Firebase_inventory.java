package firebase;

import java.io.IOException;
import java.util.HashMap;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

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
	public void inventory(String code, String name, String category, String standard, String cnt, String price, String cost, String amount, String explain, String picture) throws Exception {
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
}
