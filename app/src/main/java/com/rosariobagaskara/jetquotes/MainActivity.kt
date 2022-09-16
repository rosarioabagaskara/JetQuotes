package com.rosariobagaskara.jetquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var quotesDataAdapter: QuotesDataAdapter
    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_title_layout)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getQuotes().enqueue(object: Callback<ArrayList<QuotesData>>{
            override fun onResponse(call: Call<ArrayList<QuotesData>>, response: Response<ArrayList<QuotesData>>) {
                var value = response.body()
                if (value != null) {
                    if(value.size > 0){
                        recyclerView.visibility = View.VISIBLE
                        recyclerView.layoutManager = layoutManager
                        recyclerView.setHasFixedSize(true)
                        quotesDataAdapter = this@MainActivity?.let { QuotesDataAdapter(it,value) }!!
                        recyclerView.adapter = quotesDataAdapter
                    }else{
                        recyclerView.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<QuotesData>>, t: Throwable) {
            }

        })
    }

    fun quotesDetail(quotesData: QuotesData){
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("quotes",quotesData.quote)
        intent.putExtra("author",quotesData.author)
        startActivity(intent)
    }
}