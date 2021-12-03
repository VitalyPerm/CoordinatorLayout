package com.example.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coordinatorlayout.databinding.ListItemBinding
import java.util.ArrayList

class CheeseListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rv = inflater.inflate(R.layout.fragment_cheese_list, container, false) as RecyclerView
        //may be problems
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = CheeseRecyclerViewAdapter(Cheeses.STRINGS.randomSublist(30))
        return rv
    }
}


class CheeseRecyclerViewAdapter(private val values: List<String>) : RecyclerView.Adapter<CheeseRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var boundString: String? = null
        val binding = ListItemBinding.bind(view)
        override fun toString(): String {
            return super.toString() + " '" + binding.text1.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.boundString = values[position]
        holder.binding.text1.text = values[position]
        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, CheeseDetailActivity::class.java)
            intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.boundString)
            context.startActivity(intent)
        }
        Glide.with(holder.binding.avatar).load(Cheeses.randomCheeseDrawable).apply(RequestOptions().fitCenter()).into(holder.binding.avatar)
    }

    override fun getItemCount(): Int = values.size
}

private fun <T> Array<T>.randomSublist(amount: Int): List<T> {
    val newList = ArrayList<T>(amount)
    while (newList.size < amount) {
        newList.add(random())
    }
    return newList
}