package com.fadli.catcare.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadli.catcare.ui.article.Article
import com.fadli.catcare.ui.article.ArticleAdapter
import com.fadli.catcare.ui.article.DetailArticle
import com.fadli.catcare.ui.menuCare.Care
import com.fadli.catcare.ui.menuCare.CareAdapter
import com.fadli.catcare.R
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    companion object{
        const val EXTRA_ARTICLE = "extra_article"
    }

    private lateinit var rvMenuCare : RecyclerView
    private lateinit var rvArticle : RecyclerView
    private val list1 = kotlin.collections.ArrayList<Care>()
    private val list2 = kotlin.collections.ArrayList<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //Menu Care
        rvMenuCare = view.findViewById(R.id.rv_menuCare)
        rvMenuCare.setHasFixedSize(true)

        list1.addAll(cares)

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        rvMenuCare = view.findViewById(R.id.rv_menuCare)

        rvMenuCare.layoutManager = layoutManager1
        val careAdapter = CareAdapter(list1)
        rvMenuCare.adapter = careAdapter


        //Article
        rvArticle = view.findViewById(R.id.rv_article)
        rvArticle.setHasFixedSize(true)

        list2.addAll(listArticles)

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.VERTICAL
        rvArticle = view.findViewById(R.id.rv_article)

        rvArticle.layoutManager = layoutManager2
        val articleAdapter = ArticleAdapter(list2)
        rvArticle.adapter = articleAdapter

        articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val intent = Intent(context, DetailArticle::class.java)
                intent.putExtra(EXTRA_ARTICLE, data)
                startActivity(intent)
            }

        })

        return view
    }

    private val cares: ArrayList<Care>
        get() {
            val nameCare = resources.getStringArray(R.array.name_menu_care)
            val imgCare = resources.obtainTypedArray(R.array.img_menu_care)
            val care = ArrayList<Care>()
            for (i in nameCare.indices) {
                val listCare = Care(nameCare[i], imgCare.getResourceId(i, -1))
                care.add(listCare)
            }
            return care
        }

    private val listArticles: ArrayList<Article>
        get() {
            val JudulArticle = resources.getStringArray(R.array.judul_article)
            val DescriptionArticle = resources.getStringArray(R.array.description_article)
            val imgArticle = resources.obtainTypedArray(R.array.img_article)
            val article = ArrayList<Article>()
            for (i in JudulArticle.indices) {
                val listArticle = Article(JudulArticle[i],DescriptionArticle[i], imgArticle.getResourceId(i, -1))
                article.add(listArticle)
            }
            return article
        }

}