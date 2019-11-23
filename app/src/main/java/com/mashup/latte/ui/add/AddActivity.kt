package com.mashup.latte.ui.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.mashup.latte.R
import kotlinx.android.synthetic.main.activity_add.*



class AddActivity : AppCompatActivity() {
    lateinit var searchFragment : SearchFragment
    lateinit var recentFragment : RecentFragment
    lateinit var favoriteFragment : FavoriteFragment

    val list: MutableList<Item>
    lateinit var adapter: ItemsAdapter
    //lateinit var adapter: ItemsAdapter

    init {
        list = ArrayList<Item>()
        list.add(Item("aa"))
        list.add(Item("aabaaa"))
        list.add(Item("aac"))
        list.add(Item("d"))
        list.add(Item("e"))
        list.add(Item("eadsasdad"))
        list.add(Item("eaaaa"))
        list.add(Item("eaa"))
        list.add(Item("easdasd"))
        list.add(Item("ewww"))
        list.add(Item("easdsada"))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        adapter = ItemsAdapter(this, list, this)
//        val layoutManager = LinearLayoutManager(applicationContext)
//        rv_search.layoutManager = layoutManager
//        rv_search.itemAnimator = DefaultItemAnimator()
//        rv_search.addItemDecoration(MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36))
//        rv_search.adapter = adapter
//
//        adapter.filter.filter("")
//
//        et.addTextChangedListener {
//            Log.d("csh", "${it}")
//            adapter.filter.filter(it)
//        }
        searchFragment = SearchFragment(applicationContext)
        recentFragment = RecentFragment()
        favoriteFragment = FavoriteFragment()


        /* Tab */
        tl.addTab(tl.newTab().setText("검색"))
        tl.addTab(tl.newTab().setText("최근 검색어"))
        tl.addTab(tl.newTab().setText("즐겨찾기"))
        tl.tabGravity = TabLayout.GRAVITY_FILL

        val pagerAdapter = TabPagerAdapter(supportFragmentManager, tl.tabCount, searchFragment, recentFragment, favoriteFragment)
        vp.adapter = pagerAdapter
        vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl))

        tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.apply {
                    vp.setCurrentItem(position)
                }
            }

        })



    }


}
