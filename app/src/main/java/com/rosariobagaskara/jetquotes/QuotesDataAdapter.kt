package com.rosariobagaskara.jetquotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class QuotesDataAdapter(private val c: Context, private val quotesList: ArrayList<QuotesData>): RecyclerView.Adapter<QuotesDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_quotes,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = quotesList[position]
        holder.quotes.text = currentItem.quote.replace("""[\p{P}\p{S}&&[^.]]+""".toRegex(), "")
        holder.author.text = "- ${currentItem.author.trim()}"
        holder.cardView.setOnClickListener { view ->
            if(c is MainActivity){
                c.quotesDetail(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val quotes = itemView.findViewById<TextView>(R.id.quotes)
        val author = itemView.findViewById<TextView>(R.id.author)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
    }

}