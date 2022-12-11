package com.fadli.catcare.ui.care

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.R
import com.fadli.catcare.ui.care.adapter.Kuku
import com.fadli.catcare.ui.care.adapter.KukuAdapter
import java.util.*


@Suppress("UNREACHABLE_CODE")
class KukuFragment : Fragment() {

    private lateinit var rvListNail : RecyclerView
    private var theList = kotlin.collections.ArrayList<Kuku>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_kuku, container, false)

        theList = ArrayList() // initialize you list

        //Menu Care
        rvListNail = view.findViewById(R.id.rv_nail)
        rvListNail.setHasFixedSize(true)

        theList.addAll(nails)
        nails.clear()

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.VERTICAL
        rvListNail = view.findViewById(R.id.rv_nail)

        rvListNail.layoutManager = layoutManager1
        val careAdapter = KukuAdapter(theList)
        rvListNail.adapter = careAdapter



        return view
    }


    private val nails: ArrayList<Kuku>
        get() {

            val listTitle = resources.getStringArray(R.array.str_title_kuku)
            val listDesc = resources.getStringArray(R.array.str_desc_kuku)
            val listMonth = resources.getStringArray(R.array.str_month_kuku)
            val listDay = resources.getStringArray(R.array.str_day_kuku)
            val listDate = resources.getStringArray(R.array.str_date_kuku)
            val nails = ArrayList<Kuku>()

            for (i in listTitle.indices) {
                val listNails = Kuku(listTitle[i], listDesc[i], listMonth[i],listDay[i],listDate[i] )
                nails.add(listNails)
            }

            return nails
        }


}