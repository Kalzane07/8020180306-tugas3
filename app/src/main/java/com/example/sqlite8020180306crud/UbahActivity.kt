package com.example.sqlite8020180306crud

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah)

        val actionBar = supportActionBar
        if(intent.hasExtra("nama")){
            actionBar?.title = intent.getStringExtra("judul")
        }

        val btnUbah=findViewById<Button>(R.id.btn_ubah)

        getIntentData()

        btnUbah.setOnClickListener{
            val dbKita = myDBHelper(this)

            val idPegawai = intent.getStringExtra("id")
            val namaPegawai = findViewById<EditText>(R.id.txt_edit_nama).text.toString()
            val emailPegawai = findViewById<EditText>(R.id.txt_ubah_email).text.toString()
            val alamatPegawai = findViewById<EditText>(R.id.txt_ubah_alamat).text.toString()

            dbKita.ubahPegawai(idPegawai, namaPegawai, emailPegawai, alamatPegawai)
        }
        val btnDelete = findViewById<Button>(R.id.btn_hapus)
        btnDelete.setOnClickListener{
            dialogKonfirmasi()
        }
    }

    fun getIntentData(){
        if(
            intent.hasExtra("id") && intent.hasExtra("nama") &&
            intent.hasExtra("email") && intent.hasExtra("alamat")

        ) {
            val idPegawai       = intent.getStringExtra("id")
            val namaPegawai     = intent.getStringExtra("nama")
            val emailPegawai    = intent.getStringExtra("email")
            val alamatPegawai   = intent.getStringExtra("alamat")

            val txtNama         = findViewById<EditText>(R.id.txt_edit_nama)
            val txtEmail        = findViewById<EditText>(R.id.txt_ubah_email)
            val txtAlamat       = findViewById<EditText>(R.id.txt_ubah_alamat)

            txtNama.setText(namaPegawai)
            txtEmail.setText(emailPegawai)
            txtAlamat.setText(alamatPegawai)

        } else{
            Toast.makeText(this, "Tidak Ada Data!", Toast.LENGTH_SHORT).show()
        }

    }
    fun dialogKonfirmasi(){
        val idPegawai = intent.getStringExtra("id")
        val namaPegawai = intent.getStringExtra("nama")

        val alertDialog =AlertDialog.Builder(this)
        alertDialog.setTitle("Delete" + namaPegawai + " ?")
        alertDialog.setMessage("Apakah anda yakin Menghapus " + namaPegawai + " ?")

        alertDialog.setPositiveButton("iya", DialogInterface.OnClickListener{ dialog, which ->
            val dbKita = myDBHelper(this)
            dbKita.hapusPegawai(idPegawai)
            startActivity(Intent(this, MainActivity::class.java))
        })

        alertDialog.setNegativeButton("tidak", DialogInterface.OnClickListener { dialog, which ->
        })
        alertDialog.create().show()
    }
}