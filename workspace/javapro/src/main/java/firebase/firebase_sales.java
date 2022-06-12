package firebase;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import code.Setting;

public class firebase_sales extends App {
	Setting setting = new Setting();
	
	public int[] show_Daysales(int now, int last) throws InterruptedException, ExecutionException {
		CollectionReference query = db.collection("Manager").document(getId()).collection("salesDay");
		Query query1 = query.whereEqualTo("date", Integer.toString(now));
		Query query2= query.whereEqualTo("date", Integer.toString(last));
		int data1 = 0;
		int data2 = 0;
		ApiFuture<QuerySnapshot> querySnapshot = query1.get();
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			  data1 = Integer.parseInt(document.getString("sales"));
		}
		querySnapshot = query2.get();
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			  data2 = Integer.parseInt(document.getString("sales"));
			  break;
		}
		int data[] = {data1,data2};
		return data;
	}
	public int[] show_Monthsales(int now, int last) throws InterruptedException, ExecutionException {
		CollectionReference query = db.collection("Manager").document(getId()).collection("salesYear");
		Query query1 = query.whereEqualTo("date", Integer.toString(now));
		Query query2= query.whereEqualTo("date", Integer.toString(last));
		int data1 = 0;
		int data2 = 0;
		ApiFuture<QuerySnapshot> querySnapshot = query1.get();
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			  data1 = Integer.parseInt(document.getString("sales"));
		}
		querySnapshot = query2.get();
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			  data2 = Integer.parseInt(document.getString("sales"));
			  break;
		}
		int data[] = {data1,data2};
		return data;
	}
}
