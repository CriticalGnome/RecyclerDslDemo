package com.criticalgnome.recyclerdsldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.criticalgnome.recycler_view_dsl.model
import com.criticalgnome.recycler_view_dsl.recyclerViewAdapter
import com.criticalgnome.recyclerdsldemo.databinding.ActivityMainBinding
import com.criticalgnome.recyclerdsldemo.databinding.ItemMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = "Screen title"
        binding.header.text = title

        binding.mainRecycler.adapter = recyclerViewAdapter {
            define()
                .viewHolderOf(ItemMainBinding::inflate)
                .couldBeBoundTo<String> { string ->
                    itemText.text = string
                }
        }.apply {
            model = (1..100).map { "$it: ${UUID.randomUUID()}" }
        }
    }
}
