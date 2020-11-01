//package com.example.androidpractice2020.cardview
//
//import android.content.Context
//import android.content.DialogInterface
//import android.content.Intent
//import android.graphics.ColorSpace.Model
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.viewpager.widget.PagerAdapter
//import com.example.androidpractice2020.R
//
//
//class Adapter : PagerAdapter() {
//    private var models: List<Model>? = null
//    private var layoutInflater: LayoutInflater? = null
//    private var context: Context? = null
//
//    fun Adapter(models: List<Model>?, context: Context?) {
//        this.models = models
//        this.context = context
//    }
//
//    override fun getCount(): Int {
//        return models!!.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view.equals(`object`)
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        layoutInflater = LayoutInflater.from(context)
//        val view: View = layoutInflater.inflate(R.layout.item, container, false)
//        val imageView: ImageView
//        val title: TextView
//        val desc: TextView
//        imageView = view.findViewById(R.id.image)
//        title = view.findViewById(R.id.title)
//        desc = view.findViewById(R.id.desc)
//        imageView.setImageResource(models!![position].getImage())
//        title.setText(models!![position].getTitle())
//        desc.setText(models!![position].getDesc())
//        view.setOnClickListener(object : DialogInterface.OnClickListener() {
//            fun onClick(v: View?) {
//                // finish();
//            }
//        })
//        container.addView(view, 0)
//        return view
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//}