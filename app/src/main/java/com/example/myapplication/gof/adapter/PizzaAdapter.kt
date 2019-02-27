//package com.example.myapplication.gof.adapter
//
//import android.content.Context
//import android.databinding.DataBindingUtil
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import com.example.myapplication.R
//import com.example.myapplication.databinding.ItemPizzaBinding
//import com.example.myapplication.gof.IPizza
//
//class PizzaAdapter(
//    private val context: Context,
//    private val pClickListener: OnItemClickListener
//) : RecyclerView.Adapter<PizzaAdapter.UserViewHolder>() {
//
//    private var mPizzas: MutableList<IPizza> = mutableListOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        return UserViewHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(context),
//                R.layout.item_pizza,
//                parent,
//                false
//            ), pClickListener
//        )
//    }
//
//    override fun getItemId(position: Int): Long {
//        return mPizzas[position].id
//    }
//
//    override fun getItemCount(): Int {
//        return mPizzas.size
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bind(mPizzas[position])
//    }
//
//    fun updateUsers(pPizzas: List<IPizza>) {
//        this.mPizzas.clear()
//        this.mPizzas.addAll(pPizzas)
//
//        notifyDataSetChanged()
//    }
//
//    fun removeUser(id: Long) {
//        val position = getPositionById(id)
//
//        this.mPizzas.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    private fun getPositionById(id: Long): Int {
//        for (i in mPizzas.indices) {
//            if (mPizzas[i].id == id) {
//                return i
//            }
//        }
//
//        return -1
//    }
//
//    class UserViewHolder(private val binding: ItemPizzaBinding, private val pClickListener: OnItemClickListener) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(pPizza: IPizza) {
//            binding.pizza = pPizza
//            binding.clickListener = pClickListener
//            binding.executePendingBindings()
//        }
//    }
//}