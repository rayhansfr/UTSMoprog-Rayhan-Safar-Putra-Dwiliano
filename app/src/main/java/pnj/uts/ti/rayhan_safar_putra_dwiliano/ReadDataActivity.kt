package pnj.uts.ti.rayhan_safar_putra_dwiliano
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.rayhan_safar_putra_dwiliano.adapter.AdapterBerita
import pnj.uts.ti.rayhan_safar_putra_dwiliano.adapter.AlumniAdapter

import pnj.uts.ti.rayhan_safar_putra_dwiliano.database.DatabaseHelper
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.AlumniModel

class ReadDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_read_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the RecyclerView
        val recyclerViewAlumni = findViewById<RecyclerView>(R.id.recyclerViewAlumni)

        // Initialize DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        // Fetch all data
        val alumniList: List<AlumniModel> = dbHelper.getAllDataAlumni()

        // Create and set the custom adapter
        val adapter = AlumniAdapter(this, alumniList)
        recyclerViewAlumni.layoutManager = LinearLayoutManager(this)
        recyclerViewAlumni.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu,menu)
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