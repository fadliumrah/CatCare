package com.fadli.catcare.ui.care


import com.fadli.catcare.ui.care.adapter.Litterbox
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R
import com.fadli.catcare.ui.care.adapter.LitterboxAdapter
import com.fadli.catcare.ui.care.adapter.Weight
import com.fadli.catcare.ui.care.adapter.WeightAdapter
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNREACHABLE_CODE")
class LitterboxFragment : Fragment() {

    private lateinit var rvListLitterbox : RecyclerView
    private var theList = kotlin.collections.ArrayList<Litterbox>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_litterbox, container, false)

        theList = java.util.ArrayList() // initialize you list

        //Menu Care
        rvListLitterbox = view.findViewById(R.id.rv_litterbox)
        rvListLitterbox.setHasFixedSize(true)

        theList.addAll(nails)

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL
        rvListLitterbox = view.findViewById(R.id.rv_litterbox)

        rvListLitterbox.layoutManager = layoutManager1
        val careAdapter = LitterboxAdapter(theList)
        rvListLitterbox.adapter = careAdapter



        return view
    }


    private val nails: ArrayList<Litterbox>
        get() {
            val listTitle = resources.getStringArray(R.array.str_title_litterbox)
            val listDesc = resources.getStringArray(R.array.str_desc_kuku)
            val listMonth = resources.getStringArray(R.array.str_month_kuku)
            val listDay = resources.getStringArray(R.array.str_day_kuku)
            val listDate = resources.getStringArray(R.array.str_date_kuku)
            val nails = ArrayList<Litterbox>()
            for (i in listTitle.indices) {
                val listNails = Litterbox(listTitle[i], listDesc[i], listMonth[i],listDay[i],listDate[i] )
                nails.add(listNails)
            }
            return nails
        }
}