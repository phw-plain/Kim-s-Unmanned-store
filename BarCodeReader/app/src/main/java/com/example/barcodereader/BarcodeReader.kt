package com.example.barcodereader

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.zxing.integration.android.IntentIntegrator

class BarcodeReader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        val id = intent.getStringExtra("id");
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.contents != null) {
                Toast.makeText(this, "scanned: ${result.contents} format: ${result.formatName}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}