package com.xiaohei.materdesigndemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FruitAdapter(var fruitList: List<Fruit>, var mContext: Context) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val fruit: Fruit = fruitList.get(position)
        holder.fruitName.setText(fruit.name)
        Glide.with(mContext).load(fruit.imageId).into(holder.fruitImage)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(view)
    }

    /***
     * ViewHolder() 需要的是一个包含一个View的构造器
     */
    class ViewHolder(item: View) :
        RecyclerView.ViewHolder(item) {
        var cardView: CardView
        var fruitName: TextView
        var fruitImage: ImageView

        init {
            cardView = item as CardView
            fruitImage = item.findViewById(R.id.fruit_image)
            fruitName = item.findViewById(R.id.fruit_name)
        }
    }
}