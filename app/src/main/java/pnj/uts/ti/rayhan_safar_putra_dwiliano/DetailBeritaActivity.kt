package pnj.uts.ti.rayhan_safar_putra_dwiliano

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get data berita from intent
        val beritaTitle = intent.getStringExtra("berita_title")
        val beritaDate = intent.getStringExtra("berita_date")
        val beritaDescription = intent.getStringExtra("berita_description")
        val beritaImageResId = intent.getIntExtra("berita_image_res_id", R.drawable.neverlusen2)

        // Set data to views
        val detailBeritaImage = findViewById<ImageView>(R.id.detailBeritaImg)
        val detailBeritaTitle = findViewById<TextView>(R.id.detailBeritaTitle)
        val detailBeritaDate = findViewById<TextView>(R.id.detailBeritaDate)
        val detailBeritaDescription = findViewById<TextView>(R.id.detailBeritaDescription)

        detailBeritaImage.setImageResource(beritaImageResId)
        detailBeritaTitle.text = beritaTitle
        detailBeritaDate.text = beritaDate
        detailBeritaDescription.text = beritaDescription


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
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