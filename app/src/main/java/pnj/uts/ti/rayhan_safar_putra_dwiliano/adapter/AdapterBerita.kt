package pnj.uts.ti.rayhan_safar_putra_dwiliano.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.BeritaModel
import pnj.uts.ti.rayhan_safar_putra_dwiliano.R
import pnj.uts.ti.rayhan_safar_putra_dwiliano.DetailBeritaActivity

class AdapterBerita(context: Context, private val beritaModelList: List<BeritaModel>) :
    ArrayAdapter<BeritaModel>(context, 0, beritaModelList) {

    // Update getView() method in AdapterBerita
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_berita, parent, false)
        }

        val currentItem = beritaModelList[position]

        val imageNews = itemView?.findViewById<ImageView>(R.id.gambarBerita)
        val textTitle = itemView?.findViewById<TextView>(R.id.titleBerita)
        val textDescription = itemView?.findViewById<TextView>(R.id.descBerita)

        imageNews?.setImageResource(currentItem.imageResId)
        textTitle?.text = currentItem.title
        textDescription?.text = currentItem.date

        // Set click listener to navigate to detail activity
        itemView?.setOnClickListener {
            val intent = Intent(context, DetailBeritaActivity::class.java).apply {
                putExtra("berita_title", currentItem.title)
                putExtra("berita_date", currentItem.date)
                putExtra("berita_description", currentItem.description)
                putExtra("berita_image_res_id", currentItem.imageResId) // Pass image resource ID
            }
            context.startActivity(intent)
        }

        return itemView!!
    }

}
