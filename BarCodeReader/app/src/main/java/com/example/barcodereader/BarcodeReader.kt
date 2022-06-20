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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.runBlocking
import java.util.*

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

    }

    fun buyBarcodeReader(view: View) {
        val intent = Intent(applicationContext, BuyActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("manager", ManagerId)
        startActivity(intent);
    }

    fun permuteBarcodeReader(view: View) {
        val intent = Intent(applicationContext, PermuteActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("manager", ManagerId)
        startActivity(intent);
    }
}