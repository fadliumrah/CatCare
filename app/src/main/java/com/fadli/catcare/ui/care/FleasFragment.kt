package com.fadli.catcare.ui.care

import com.fadli.catcare.ui.care.adapter.Fleas
import com.fadli.catcare.ui.care.adapter.FleasAdapter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNREACHABLE_CODE")
class FleasFragment : Fragment() {

    private lateinit var rvListFleas : RecyclerView
    private var theList = kotlin.collections.ArrayList<Fleas>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fleas, container, false)

        theList = java.util.ArrayList() // initialize you list

        //Menu Care
        rvListFleas = view.findViewById(R.id.rv_fleas)
        rvListFleas.setHasFixedSize(true)

        theList.addAll(nails)

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL
        rvListFleas = view.findViewById(R.id.rv_fleas)

        rvListFleas.layoutManager = layoutManager1
        val careAdapter = FleasAdapter(theList)
        rvListFleas.adapter = careAdapter



        return view
    }


    private val nails: ArrayList<Fleas>
        get() {
            val listTitle = resources.getStringArray(R.array.str_title_fleas)
            val listDesc = resources.getStringArray(R.array.str_desc_kuku)
            val listMonth = resources.getStringArray(R.array.str_month_kuku)
            val listDay = resources.getStringArray(R.array.str_day_kuku)
            val listDate = resources.getStringArray(R.array.str_date_kuku)
            val listValueDose = resources.getStringArray(R.array.str_dose_fleas)
            val listValueAge = resources.getStringArray(R.array.str_age_fleas)
            val nails = ArrayList<Fleas>()
            for (i in listTitle.indices) {
                val listNails = Fleas(listTitle[i], listDesc[i], listMonth[i],listDay[i],listDate[i], listValueDose[i], listValueAge[i]  )
                nails.add(listNails)
            }
            return nails
        }
}