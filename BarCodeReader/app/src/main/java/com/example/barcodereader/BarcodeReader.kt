package com.example.barcodereader

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.runBlocking

class BarcodeReader : AppCompatActivity() {
    var id = "";
    var ManagerId = "";
    var db = FirebaseFirestore.getInstance();//db연결
    var result = "";
    var productCode = "";
    var productPrice = "";
    var productName = "";
    var productPicture = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        id = intent.getStringExtra("id").toString();
        ManagerId = intent.getStringExtra("manager").toString();
        setContentView(R.layout.activity_reader);
        val idText = findViewById<TextView>(R.id.idText);
        idText.setText(id);
        supportActionBar?.hide();
        val eventText: TextView = findViewById(R.id.eventText)
        FadeInEvent(eventText);
    }

    fun FadeInEvent(view: View) {
        YoYo.with(Techniques.FadeIn)
            .duration(1000)
            .repeat(-1)
            .playOn(findViewById(R.id.eventText))
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
    fun firebase(){
        val docRef = db.collection("Manager").document(ManagerId)
            .collection("inventory").document(result)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    productCode = document.getString("code").toString();
                    productPrice = document.getString("cost").toString();
                    productName = document.getString("name").toString();
                    productPicture = document.getString("picture").toString();
                    Toast.makeText(this, productName, Toast.LENGTH_LONG).show()
                } else {
                    ManagerId = "";
                    Toast.makeText(this, "안대..", Toast.LENGTH_LONG).show()
                    id = "";
                }
            }

        var product: HashMap<String, String>? = null;
        if(productCode!="") {
            product = hashMapOf(
                "productCode" to productCode,
                "productPrice" to productPrice,
                "productName" to productName,
                "productPicture" to productPicture
            )
        }
        if (product != null) {
            db.collection("Manager").document(ManagerId).collection("barcode").document(id!!)
                .collection("cart").document(result.toString())
                .set(product)
                .addOnSuccessListener {
                    Toast.makeText(this, "됐당!", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "안됐당!", Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data).contents;
        if(result != null) {
            Toast.makeText(this, "scanned: ${result} format: ${result}", Toast.LENGTH_LONG).show()
            runBlocking {
                firebase();
            }
        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}