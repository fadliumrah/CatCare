package com.fadli.catcare.ui.care

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R
import com.fadli.catcare.ui.care.adapter.Weight
import com.fadli.catcare.ui.care.adapter.WeightAdapter
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNREACHABLE_CODE")
class WeightFragment : Fragment() {

    private lateinit var rvListWeight : RecyclerView
    private var theList = kotlin.collections.ArrayList<Weight>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_weight, container, false)

        theList = java.util.ArrayList() // initialize you list

        //Menu Care
        rvListWeight = view.findViewById(R.id.rv_weight)
        rvListWeight.setHasFixedSize(true)

        theList.addAll(nails)

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL
        rvListWeight = view.findViewById(R.id.rv_weight)

        rvListWeight.layoutManager = layoutManager1
        val careAdapter = WeightAdapter(theList)
        rvListWeight.adapter = careAdapter



        return view
    }


    private val nails: ArrayList<Weight>
        get() {
            val listTitle = resources.getStringArray(R.array.str_title_weight)
            val listDesc = resources.getStringArray(R.array.str_desc_kuku)
            val listMonth = resources.getStringArray(R.array.str_month_kuku)
            val listDay = resources.getStringArray(R.array.str_day_kuku)
            val listDate = resources.getStringArray(R.array.str_date_kuku)
            val listValueWeight = resources.getStringArray(R.array.str_value_weight)
            val nails = ArrayList<Weight>()
            for (i in listTitle.indices) {
                val listNails = Weight(listTitle[i], listDesc[i], listMonth[i],listDay[i],listDate[i], listValueWeight[i] )
                nails.add(listNails)
            }
            return nails
        }
}