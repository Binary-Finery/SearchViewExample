package spencerstudios.com.searchviewexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dataList  = mutableListOf<String>()
    private val filteredList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addData()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = DataAdapter(dataList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.search_view_hint)
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextChange(entry: String): Boolean {

        filteredList.clear()

        (0 until dataList.size)
                .filter { dataList[it].toLowerCase().contains(entry.toLowerCase()) }
                .mapTo(filteredList) { dataList[it] }

        recycler_view.adapter = DataAdapter(filteredList)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    private fun addData() {

        dataList.add("Nintendo")
        dataList.add("Sega")
        dataList.add("3DO")
        dataList.add("Amiga")
        dataList.add("CD-i")
        dataList.add("Arcade")
        dataList.add("Sony")
        dataList.add("Atari")
    }
}
