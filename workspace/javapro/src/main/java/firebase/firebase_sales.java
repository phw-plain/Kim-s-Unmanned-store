package firebase;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import code.Setting;

public class firebase_sales extends App {
	Setting setting = new Setting();
	
	public void show_sales(int day) {
		ApiFuture<QuerySnapshot> query1 = (ApiFuture<QuerySnapshot>) db.collection(getId()+"_sales");
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = null;
		try {
			querySnapshot = query1.where("date", "==", "dis")
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			}
		}
		if(day==7) {
			
		}
		
	}
}
