package com.example.sqlite8020180306crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)
        val txtNama     =findViewById<EditText>(R.id.txt_nama)
        val txtEmail    =findViewById<EditText>(R.id.txt_email)
        val txtAlamat   =findViewById<EditText>(R.id.txt_alamat)
        val btnSimpan   =findViewById<Button>(R.id.btn_simpan)

        btnSimpan.setOnClickListener{
            val dbSaya = myDBHelper (this)

            dbSaya.tambahPegawai(
                txtNama.text.toString().trim(),
                txtEmail.text.toString().trim(),
                txtAlamat.text.toString().trim()
            )
        }
    }
}