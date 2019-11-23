package com.mashup.latte.ui.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.latte.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment (
        private val ct: Context
) : Fragment(), ItemsAdapter.ItemsAdapterListener {
    val list: MutableList<Item>
    lateinit var adapter: ItemsAdapter

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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemsAdapter(ct, list, this)
        val layoutManager = LinearLayoutManager(ct)
        sf_rv_search.layoutManager = layoutManager
        sf_rv_search.itemAnimator = DefaultItemAnimator()
        sf_rv_search.addItemDecoration(MyDividerItemDecoration(ct, DividerItemDecoration.VERTICAL, 36))
        sf_rv_search.adapter = adapter

        adapter.filter.filter("")

        sf_et.addTextChangedListener {
            Log.d("csh", "${it}")
            adapter.filter.filter(it)
        }


    }

    override fun onItemSelected(item: Item?) {
        Log.d("csh", "${item?.name} is selected")
    }

}

class RecentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

}

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}
