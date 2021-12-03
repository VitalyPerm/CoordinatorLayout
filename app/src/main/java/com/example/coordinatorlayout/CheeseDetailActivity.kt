package com.example.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coordinatorlayout.databinding.ActivityCheeseDetailBinding
import com.example.coordinatorlayout.databinding.FragmentCheeseListBinding

class CheeseDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheeseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheeseDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val intent = intent
        val cheeseName = intent.getStringExtra(EXTRA_NAME)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = binding.collapsingToolbar
        collapsingToolbar.title = cheeseName
        loadBackDrop()
    }
    private fun loadBackDrop() {
        Glide.with(binding.backdrop)
            .load(Cheeses.randomCheeseDrawable)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.backdrop)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_action, menu)
        return true
    }




    companion object {
        const val EXTRA_NAME = "cheese_name"
    }
}