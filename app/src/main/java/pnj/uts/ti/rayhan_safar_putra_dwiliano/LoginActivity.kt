package pnj.uts.ti.rayhan_safar_putra_dwiliano

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    companion object {
        const val SHARED_PREFS = "profile"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }

    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val emailText = findViewById<EditText>(R.id.emailLogin)
        val passText = findViewById<EditText>(R.id.passwordLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {

            if (emailText.text.toString().equals("rayhan@gmail.com") && passText.text.toString().equals("rayhan123")){
                var edit = sharedpreferences.edit()

                edit.putString("email", "rayhan@gmail.com")
                edit.putString("nim", "2207411027")
                edit.putString("nama", "Rayhan safar Putra Dwiliano")
                edit.putString("kelas", "TI 4A")
                edit.putBoolean("isLogin", true)

                edit.commit()

                val email = sharedpreferences.getString("email", "N/A")
                val nim = sharedpreferences.getString("nim", "N/A")
                val nama = sharedpreferences.getString("nama", "N/A")
                val kelas = sharedpreferences.getString("kelas", "N/A")

                Toast.makeText(this, "Login Successful!\nEmail: $email\nNIM: $nim\nName: $nama\nClass: $kelas", Toast.LENGTH_LONG).show()

                Toast.makeText(this, "Login Successful!\nEmail: $email\nNIM: $nim\nName: $nama\nClass: $kelas", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT)
            }


        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}