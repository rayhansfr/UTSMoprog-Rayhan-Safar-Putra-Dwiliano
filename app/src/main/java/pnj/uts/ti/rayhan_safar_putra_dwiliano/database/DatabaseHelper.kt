package pnj.uts.ti.rayhan_safar_putra_dwiliano.database

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.AlumniModel

class DatabaseHelper (context: Context) : SQLiteOpenHelper (context, DB_NAME, null, DB_VERSION){

    companion object {
        private val DB_NAME = "data_uts"
        private val DB_VERSION = 1

        private val TABLE_NAME = "data_alumni"
        private val ID = "id"
        private val NIM = "nim"
        private val NAMA = "nama"
        private val TEMPAT_LAHIR = "tempat_lahir"
        private val TANGGAL_LAHIR = "tanggal_lahir"
        private val ALAMAT = "alamat"
        private val AGAMA = "agama"
        private val TELEPON = "telepon"
        private val TAHUN_MASUK = "tahun_masuk"
        private val TAHUN_LULUS = "tahun_lulus"
        private val PEKERJAAN = "pekerjaan"
        private val JABATAN = "jabatan"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NIM TEXT," +
                "$NAMA TEXT," +
                "$TEMPAT_LAHIR TEXT," +
                "$TANGGAL_LAHIR TEXT," +
                "$ALAMAT TEXT," +
                "$AGAMA TEXT," +
                "$TELEPON TEXT," +
                "$TAHUN_MASUK TEXT," +
                "$TAHUN_LULUS TEXT," +
                "$PEKERJAAN TEXT," +
                "$JABATAN TEXT" +
                ")"

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun getAllDataAlumni(): List<AlumniModel> {
        val listAlumni = ArrayList<AlumniModel>()
        val db = writableDatabase
        val SELECT_DATA = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(SELECT_DATA, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val data = AlumniModel()
                    data.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
                    data.nim = cursor.getString(cursor.getColumnIndexOrThrow(NIM))
                    data.nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA))
                    data.tempatLahir = cursor.getString(cursor.getColumnIndexOrThrow(TEMPAT_LAHIR))
                    data.tanggalLahir = cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL_LAHIR))
                    data.alamat = cursor.getString(cursor.getColumnIndexOrThrow(ALAMAT))
                    data.agama = cursor.getString(cursor.getColumnIndexOrThrow(AGAMA))
                    data.telepon = cursor.getString(cursor.getColumnIndexOrThrow(TELEPON))
                    data.tahunMasuk = cursor.getString(cursor.getColumnIndexOrThrow(TAHUN_MASUK))
                    data.tahunLulus = cursor.getString(cursor.getColumnIndexOrThrow(TAHUN_LULUS))
                    data.pekerjaan = cursor.getString(cursor.getColumnIndexOrThrow(PEKERJAAN))
                    data.jabatan = cursor.getString(cursor.getColumnIndexOrThrow(JABATAN))

                    listAlumni.add(data)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return listAlumni
    }

    fun addDataAlumni(data : AlumniModel): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIM, data.nim)
        values.put(NAMA, data.nama)
        values.put(TEMPAT_LAHIR, data.tempatLahir)
        values.put(TANGGAL_LAHIR, data.tanggalLahir)
        values.put(ALAMAT, data.alamat)
        values.put(AGAMA, data.agama)
        values.put(TELEPON, data.telepon)
        values.put(TAHUN_MASUK, data.tahunMasuk)
        values.put(TAHUN_LULUS, data.tahunLulus)
        values.put(PEKERJAAN, data.pekerjaan)
        values.put(JABATAN, data.jabatan)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun getDataAlumni(_id : Int) : AlumniModel {
        val data = AlumniModel()
        val db = writableDatabase
        val SELECT_DATA = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(SELECT_DATA, null)

        cursor?.moveToFirst()
        data.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
        data.nim = cursor.getString(cursor.getColumnIndexOrThrow(NIM))
        data.nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA))
        data.tempatLahir = cursor.getString(cursor.getColumnIndexOrThrow(TEMPAT_LAHIR))
        data.tanggalLahir = cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL_LAHIR))
        data.alamat = cursor.getString(cursor.getColumnIndexOrThrow(ALAMAT))
        data.agama = cursor.getString(cursor.getColumnIndexOrThrow(AGAMA))
        data.telepon = cursor.getString(cursor.getColumnIndexOrThrow(TELEPON))
        data.tahunMasuk = cursor.getString(cursor.getColumnIndexOrThrow(TAHUN_MASUK))
        data.tahunLulus = cursor.getString(cursor.getColumnIndexOrThrow(TAHUN_LULUS))
        data.pekerjaan = cursor.getString(cursor.getColumnIndexOrThrow(PEKERJAAN))
        data.jabatan = cursor.getString(cursor.getColumnIndexOrThrow(JABATAN))

        cursor.close()

        return data
    }

    fun deleteDataAlumni(_id: Int) : Boolean {
        val db = writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()

        return (Integer.parseInt("$_success") != -1)
    }

    fun updateDataAlumni(data: AlumniModel) : Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIM, data.nim)
        values.put(NAMA, data.nama)
        values.put(TEMPAT_LAHIR, data.tempatLahir)
        values.put(TANGGAL_LAHIR, data.tanggalLahir)
        values.put(ALAMAT, data.alamat)
        values.put(AGAMA, data.agama)
        values.put(TELEPON, data.telepon)
        values.put(TAHUN_MASUK, data.tahunMasuk)
        values.put(TAHUN_LULUS, data.tahunLulus)
        values.put(PEKERJAAN, data.pekerjaan)
        values.put(JABATAN, data.jabatan)

        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(data.id.toString())).toLong()
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

}