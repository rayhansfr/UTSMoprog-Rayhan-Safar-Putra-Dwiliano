package pnj.uts.ti.rayhan_safar_putra_dwiliano

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pnj.uts.ti.rayhan_safar_putra_dwiliano.database.DatabaseHelper
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.AlumniModel
import java.util.Locale

class DetailAlumniActivity : AppCompatActivity() {
    private val myCalendar = Calendar.getInstance()

    lateinit var etTanggalLahir: EditText
    lateinit var btnSimpan : Button
    lateinit var btnHapus : Button
    lateinit var etNIM : EditText
    lateinit var etNama : EditText
    lateinit var etTempat : EditText
    lateinit var etAlamat : EditText
    lateinit var etAgama : EditText
    lateinit var etNohp : EditText
    lateinit var etMasuk : EditText
    lateinit var etLulus : EditText
    lateinit var etPekerjaan : EditText
    lateinit var etJabatan : EditText

    var dbHelper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_alumni)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etTanggalLahir = findViewById(R.id.etTanggalLahir)

        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        etTanggalLahir.setOnClickListener {
            DatePickerDialog(
                this,
                dateListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        val selectedId = intent.getIntExtra("selected_id", -1)

        if (selectedId != -1) {

            val dbHelper = DatabaseHelper(this)
            val alumni: AlumniModel = dbHelper.getDataAlumni(selectedId)

            alumni?.let {
                findViewById<EditText>(R.id.et_nim).setText(it.nim)
                findViewById<EditText>(R.id.et_nama).setText(it.nama)
                findViewById<EditText>(R.id.etTempatLahir).setText(it.tempatLahir)
                findViewById<EditText>(R.id.etTanggalLahir).setText(it.tanggalLahir)
                findViewById<EditText>(R.id.etAlamat).setText(it.alamat)
                findViewById<EditText>(R.id.etAgama).setText(it.agama)
                findViewById<EditText>(R.id.etTelepon).setText(it.telepon)
                findViewById<EditText>(R.id.etTahunMasuk).setText(it.tahunMasuk)
                findViewById<EditText>(R.id.etTahunLulus).setText(it.tahunLulus)
                findViewById<EditText>(R.id.etPekerjaan).setText(it.pekerjaan)
                findViewById<EditText>(R.id.etJabatan).setText(it.jabatan)
            }
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnSimpan = findViewById(R.id.btnSimpan)
        btnHapus = findViewById(R.id.btnHapus)

        etNIM = findViewById(R.id.et_nim)
        etNama = findViewById(R.id.et_nama)
        etTempat = findViewById(R.id.etTempatLahir)
        etAlamat = findViewById(R.id.etAlamat)
        etAgama = findViewById(R.id.etAgama)
        etNohp = findViewById(R.id.etTelepon)
        etMasuk = findViewById(R.id.etTahunMasuk)
        etLulus = findViewById(R.id.etTahunLulus)
        etPekerjaan = findViewById(R.id.etPekerjaan)
        etJabatan = findViewById(R.id.etJabatan)

        dbHelper = DatabaseHelper(this)

        btnSimpan.setOnClickListener {
            val data = AlumniModel()

            data.id = selectedId
            data.nim = etNIM.text.toString()
            data.nama = etNama.text.toString()
            data.tempatLahir = etTempat.text.toString()
            data.tanggalLahir = etTanggalLahir.text.toString()
            data.alamat = etAlamat.text.toString()
            data.agama = etAgama.text.toString()
            data.telepon = etNohp.text.toString()
            data.tahunMasuk = etMasuk.text.toString()
            data.tahunLulus = etLulus.text.toString()
            data.pekerjaan = etPekerjaan.text.toString()
            data.jabatan = etJabatan.text.toString()

            val isSuccess = dbHelper.updateDataAlumni(data)

            if (isSuccess) {
                Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ReadDataActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Gagal memperbarui data. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
            }
        }

        btnHapus.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi")
            builder.setMessage("Apakah Anda yakin ingin menghapus data ini?")
            builder.setPositiveButton("Ya") { dialog, _ ->
                val isSuccess = dbHelper.deleteDataAlumni(selectedId)

                if (isSuccess) {
                    Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ReadDataActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Gagal menghapus data. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        etTanggalLahir.setText(dateFormat.format(myCalendar.time))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, HomePageActivity::class.java))
                finish()
                true
            }R.id.tambahData -> {
                startActivity(Intent(this, InsertDataActivity::class.java))
                finish()
                true
            }
            R.id.dataAlumni -> {
                startActivity(Intent(this, ReadDataActivity::class.java))
                finish()
                true
            }
            R.id.logout -> {
                val sharedPreferences = getSharedPreferences("profile", MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
