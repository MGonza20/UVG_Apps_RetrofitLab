package com.example.retrofitlab_uvg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlab_uvg.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArticleAdapter
    private val articleList = mutableListOf<Articles>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        searchNew("business")
    }

    private fun initRecyclerView(){
        adapter = ArticleAdapter(articleList)
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = adapter
    }

    private fun searchNew(category:String){
        val api = Retrofit2()
        CoroutineScope(Dispatchers.IO).launch {
            val call = api.getService()?.getNewsByCategory("us", category, "a8886212f99148db85e099a6f655886a")
            val news: NewsResponse? = call?.body()

            runOnUiThread{
                if (call!!.isSuccessful) {
                    if(news?.status.equals("ok")){
                        val articles = news?.articles ?: emptyList()
                        articleList.clear()
                        articleList.addAll(articles)
                        adapter.notifyDataSetChanged()
                    }else{
                        showMessage("Error en en webservices")
                    }

                }else{
                    showMessage("Error en retrofit")
                }
            }
        }
    }

    private fun showMessage(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}