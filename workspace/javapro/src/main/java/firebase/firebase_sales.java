package firebase;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import code.Setting;

public class firebase_sales extends App {
	Setting setting = new Setting();
	
	public int[] show_Daysales(String now, String last) throws InterruptedException, ExecutionException {
		int data[] = new int[2];
		DocumentReference Nowquery = db.collection("Manager").document(getId()).collection("TodayRecord").document(now);
		DocumentReference Lastquery = db.collection("Manager").document(getId()).collection("TodayRecord").document(last);
		ApiFuture<DocumentSnapshot> Nowfuture = Nowquery.get();
		ApiFuture<DocumentSnapshot> Lastfuture = Lastquery.get();
		DocumentSnapshot document = Nowfuture.get();
		if (document.exists()) {
		  data[0] = (document.getLong("sales")).intValue();
		} else {
			data[0] = 0;
		}
		DocumentSnapshot document1 = Lastfuture.get();
		if (document1.exists()) {
			data[1] = (document1.getLong("sales")).intValue();
		} else {
			data[1] = 0;
		}
		return data;
	}
	public int[] show_Monthsales(String now, String last) throws InterruptedException, ExecutionException {
		int data[] = new int[2];
		DocumentReference Nowquery = db.collection("Manager").document(getId()).collection("MonthRecord").document(now);
		DocumentReference Lastquery = db.collection("Manager").document(getId()).collection("MonthRecord").document(last);
		ApiFuture<DocumentSnapshot> Nowfuture = Nowquery.get();
		ApiFuture<DocumentSnapshot> Lastfuture = Lastquery.get();
		DocumentSnapshot document = Nowfuture.get();
		if (document.exists()) {
		  
		  data[0] = (document.getLong("sales")).intValue();
		} else {
			data[0] = 0;
		}
		DocumentSnapshot document1 = Lastfuture.get();
		if (document1.exists()) {
			data[1] = (document1.getLong("sales")).intValue();
		} else {
			data[1] = 0;
		}
		
		return data;
	}
}
