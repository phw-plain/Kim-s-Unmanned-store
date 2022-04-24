package firebase;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class App {
//
    private FirebaseOptions option;
    private Firestore db; 
    private final static String PATH = "C:/github/parklee-market-firebase-adminsdk-1upxv-91878fa17c.json";
    private final static String COLLECTION_NAME = "컬렉션";

    public static void main( String[] args ) {
        App app = new App();
        try {
            app.init();
            app.select();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void init() throws Exception{
        FileInputStream refreshToken = new FileInputStream(PATH);
        option = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://parklee-market-default-rtdb.firebaseio.com")  //내 저장소 주소
                .build();
        FirebaseApp.initializeApp(option);
    }
    private void makeDatabaseConn(){  //Firestore 인스턴스 생성
        db = FirestoreClient.getFirestore();
    }
    private void select(){
        db.collection(COLLECTION_NAME).addSnapshotListener( (target, exception)->{
            System.out.println(" - select start - ");
            target.forEach(manager->{
                System.out.println("primary id : "+manager.getId() + "  ||  value : " + manager.getData());
            });
            System.out.println(" - select end - ");
        });
    }
    public void insert(String id, String pw, String name, String brand, String location,int empsal){  //등록
    	try{
        Map<Object, Object> manager = new HashMap<Object, Object>();
        manager.put("cl_id",id );
        manager.put("cl_password",pw );
        manager.put("cl_name", name);
        manager.put("cl_brand", brand);
        manager.put("cl_location", location);
        manager.put("cl_salary", empsal);
        db.collection(COLLECTION_NAME).add(manager);
    	}
    	 catch (Exception e) {
             e.printStackTrace();
         }
    }
}
