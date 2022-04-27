package com.example.sampleui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleui.BR
import com.example.sampleui.RvClickListener
import com.example.sampleui.utils.Helper

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 06/04/2022 12:57 AM
 */
class GlobalAdapter<T>(
    private val layoutId: Int,
    var mutableList: MutableList<T>,
    private val br: Int,
    private var clickListener: RvClickListener,
    private val brs: Map<Int, Any>,
) : RecyclerView.Adapter<GlobalAdapter<T>.ViewHolder>() {
    inner class ViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(br, mutableList[holder.adapterPosition])

        brs.forEach {
            binding.setVariable(it.key, it.value)
        }

        holder.binding.setVariable(
            BR.click,
            View.OnClickListener { v ->
                    clickListener.click(
                        v,
                        mutableList[holder.adapterPosition],
                        holder.adapterPosition,
                        this as GlobalAdapter<Any>
                    )
            })

        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = mutableList.size

    fun setList(mutableList: MutableList<T>) {
        this.mutableList = mutableList
        notifyDataSetChanged()
    }
}


