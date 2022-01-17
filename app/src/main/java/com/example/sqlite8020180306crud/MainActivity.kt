package com.example.sqlite8020180306crud

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val pegawai_id      : ArrayList<String> = arrayListOf();
    val pegawai_nama    : ArrayList<String> = arrayListOf();
    val pegawai_email   : ArrayList<String> = arrayListOf();
    val pegawai_alamat  : ArrayList<String> = arrayListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.fab_tambah)
        btnTambah.setOnClickListener{
            val intenKita= Intent(this, TambahActivity::class.java)
            startActivity(intenKita)
        }

        simpanDataDiArray()
        val rv_pegawai = findViewById<RecyclerView>(R.id.rv_pegawai)
        val bukuAdapter = BukuAdapter(this, pegawai_id, pegawai_nama, pegawai_email, pegawai_alamat)

        rv_pegawai.adapter = bukuAdapter
        rv_pegawai.layoutManager = LinearLayoutManager(this)
    }

    fun simpanDataDiArray(){
        val dbKita              = myDBHelper(this)
        val dataKita: Cursor    = dbKita.bacaSemuaData()

        if(dataKita.count == 0 ){
            Toast.makeText(this, "Data Tidak Ada !", Toast.LENGTH_SHORT).show()
        }
        else{
            while (dataKita.moveToNext()){
                pegawai_id.add(dataKita.getString(0))
                pegawai_nama.add(dataKita.getString(1))
                pegawai_email.add(dataKita.getString(2))
                pegawai_alamat.add(dataKita.getString(3))

            }
        }
    }
}