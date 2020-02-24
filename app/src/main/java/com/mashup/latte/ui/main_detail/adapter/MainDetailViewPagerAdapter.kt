package com.mashup.latte.ui.main_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.api.load
import com.mashup.latte.R
import com.mashup.latte.ui.main_detail.data.MainDetailImages
import kotlinx.android.synthetic.main.fragment_main_detail_image.view.*

/*
class MainDetailViewPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    private val fragmentList: ArrayList<Fragment>
) : androidx.fragment.app.FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment = fragmentList[position]
    override fun getCount(): Int = fragmentList.size
    override fun getPageWidth(position: Int): Float {
        return pageWidth
    }

    companion object {
        const val pageWidth = 0.8f
    }
}
*/

class MainDetailViewPagerAdapter(context: Context) : PagerAdapter() {
    private val mContext = context

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.fragment_main_detail_image, container, false)
        view.imgMainDetailViewPager.load(MainDetailImages().imageResource)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object` as View)
    }

}