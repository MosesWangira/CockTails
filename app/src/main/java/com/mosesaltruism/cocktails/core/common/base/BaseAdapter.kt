package com.mosesaltruism.cocktails.core.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapters : RecyclerView.Adapter<BaseViewHolder>() {

    var baseItems: List<Any> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val withDataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutView(),
            parent,
            false
        )

        return BaseViewHolder(withDataBinding)
    }

    override fun getItemCount() = baseItems.size


    abstract fun getLayoutView(): Int


}

class BaseViewHolder(val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root)