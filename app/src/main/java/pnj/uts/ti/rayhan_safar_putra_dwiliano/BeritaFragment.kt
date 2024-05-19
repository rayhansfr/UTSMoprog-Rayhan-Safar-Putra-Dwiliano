package pnj.uts.ti.rayhan_safar_putra_dwiliano

import pnj.uts.ti.rayhan_safar_putra_dwiliano.adapter.AdapterBerita
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import pnj.uts.ti.rayhan_safar_putra_dwiliano.model.BeritaModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BeritaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeritaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample news data
        val beritaModelLists = listOf(
            BeritaModel(
                getString(R.string.title_bayer_neverlusen_treble),
                R.drawable.neverlusen,
                getString(R.string.date_bayer_neverlusen_treble),
                getString(R.string.content_bayer_neverlusen_treble)
            ),
            BeritaModel(
                getString(R.string.title_turkish_clubs_mourinho),
                R.drawable.morinyo,
                getString(R.string.date_turkish_clubs_mourinho),
                getString(R.string.content_turkish_clubs_mourinho)
            ),
            BeritaModel(
                getString(R.string.title_bayern_bad_season),
                R.drawable.munchen,
                getString(R.string.date_bayern_bad_season),
                getString(R.string.content_bayern_bad_season)
            ),
            BeritaModel(
                getString(R.string.title_bayer_leverkusen_records),
                R.drawable.neverlusen2,
                getString(R.string.date_bayer_leverkusen_records),
                getString(R.string.content_bayer_leverkusen_records)
            ),
            BeritaModel(
                getString(R.string.title_gp_emilia_romagna_qualification),
                R.drawable.f1,
                getString(R.string.date_gp_emilia_romagna_qualification),
                getString(R.string.content_gp_emilia_romagna_qualification)
            ),
            BeritaModel(
                getString(R.string.title_ktm_marquez),
                R.drawable.marquez,
                getString(R.string.date_ktm_marquez),
                getString(R.string.content_ktm_marquez)
            ),
            BeritaModel(
                getString(R.string.title_inter_miami_dc_united),
                R.drawable.messi,
                getString(R.string.date_inter_miami_dc_united),
                getString(R.string.content_inter_miami_dc_united)
            ),
            BeritaModel(
                getString(R.string.title_arsenal_rehearsal),
                R.drawable.arsenal,
                getString(R.string.date_arsenal_rehearsal),
                getString(R.string.content_arsenal_rehearsal)
            ),
            BeritaModel(
                getString(R.string.title_premier_league_schedule),
                R.drawable.epl,
                getString(R.string.date_premier_league_schedule),
                getString(R.string.content_premier_league_schedule)
            ),
            BeritaModel(
                getString(R.string.title_kevin_marcus),
                R.drawable.kevin,
                getString(R.string.date_kevin_marcus),
                getString(R.string.title_kevin_marcus)
            ),
        )

        // Initialize the ListView
        val listViewBerita = view.findViewById<ListView>(R.id.listViewBerita)

        // Create and set the custom adapter
        val adapter = AdapterBerita(requireContext(), beritaModelLists)
        listViewBerita.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BeritaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BeritaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}