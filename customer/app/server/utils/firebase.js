import admin from "firebase-admin";
import firestore from "firebase-admin/firestore";
import { serviceAccount} from "./../../firebasekey.json" assert {type: 'json'};

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

export const DB = firestore.getFirestore();
