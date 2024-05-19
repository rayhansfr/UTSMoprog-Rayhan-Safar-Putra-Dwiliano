package pnj.uts.ti.rayhan_safar_putra_dwiliano.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pnj.uts.ti.rayhan_safar_putra_dwiliano.DetailAlumniActivity
import pnj.uts.ti.rayhan_safar_putra_dwiliano.R
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.AlumniModel

class AlumniAdapter(private val context: Context, private val items: List<AlumniModel>) :
    RecyclerView.Adapter<AlumniAdapter.AlumniViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumniViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_alumni, parent, false)
        return AlumniViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumniViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailAlumniActivity::class.java)
            intent.putExtra("selected_id", item.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class AlumniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNama: TextView = itemView.findViewById(R.id.textNama)
        private val textNim: TextView = itemView.findViewById(R.id.textNim)

        fun bind(alumni: AlumniModel) {
            textNama.text = alumni.nama
            textNim.text = alumni.nim
        }
    }
}
