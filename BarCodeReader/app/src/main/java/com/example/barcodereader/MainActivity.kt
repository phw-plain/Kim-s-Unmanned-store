package com.example.barcodereader

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var id = "";
    val db = FirebaseFirestore.getInstance();//db연결
    var ManagerId = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.setIcon(R.drawable.icon);
        //getSupportActionBar()?.setDisplayUseLogoEnabled(true);
        //getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        supportActionBar?.hide();

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)
        return true
    }

    fun startBarcodeReader(view: View) {
        IntentIntegrator(this).initiateScan()
    }

    fun startBarcodeReaderCustom(view: View) {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.EAN_13)   // 규격 설정(QR_CODE : QR만 찍음 => 속도 형식 ↑)
        integrator.setPrompt("바코드를 사각형 안에 비춰주세요.") // 스캔 및 문구
        integrator.setCameraId(0)   // 0=후면, 1=전면
        integrator.setBeepEnabled(true) // 스캔 후 소리
        integrator.setBarcodeImageEnabled(true) // 결과 뿐만 아닌 이미지도 전달
        integrator.initiateScan()
    }

    fun startKiosk(view: View){
        // 화면 전환
        if(ManagerId!=""&&id!=""){
            val intent = Intent(applicationContext, BarcodeReader::class.java)
            intent.putExtra("id", id)
            intent.putExtra("manager", ManagerId)
            startActivity(intent)
        } else {
            Toast.makeText(this, "키오스크 연동 후 시작할 수 있습니다!", Toast.LENGTH_LONG).show()
        }
    }

    fun hi(){
        val docRef = db.collection("code").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    ManagerId = document.getString("id").toString();
                    Toast.makeText(this, ManagerId, Toast.LENGTH_LONG).show()
                    if (ManagerId != "") {
                        val mFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        val mDate = Date(System.currentTimeMillis());
                        val phone = hashMapOf(
                            "id" to id,
                            "display" to Build.MODEL,
                            "time" to mFormat.format(mDate)
                        );
                        db.collection("Manager").document(ManagerId).collection("barcode")
                            .document(id)
                            .set(phone)
                            .addOnSuccessListener {
                                Toast.makeText(this, "됐당!", Toast.LENGTH_LONG).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "안됐당!", Toast.LENGTH_LONG).show()
                            }
                    }
                } else {
                    Log.d(TAG, "No such document")
                    id = "";
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            };

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.contents != null) {
                id = result.contents;
                if(id!="") {
                    hi();
                }
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}