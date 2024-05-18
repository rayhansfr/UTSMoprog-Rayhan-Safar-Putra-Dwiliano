// NewsAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import pnj.uts.ti.rayhan_safar_putra_dwiliano.News
import pnj.uts.ti.rayhan_safar_putra_dwiliano.R

class NewsAdapter(context: Context, private val newsList: List<News>) :
    ArrayAdapter<News>(context, 0, newsList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val currentItem = newsList[position]

        val imageNews = itemView?.findViewById<ImageView>(R.id.gambarBerita)
        val textTitle = itemView?.findViewById<TextView>(R.id.titleBerita)
        val textDescription = itemView?.findViewById<TextView>(R.id.descBerita)

        imageNews?.setImageResource(currentItem.imageResId)
        textTitle?.text = currentItem.title
        textDescription?.text = currentItem.description

        return itemView!!
    }
}
