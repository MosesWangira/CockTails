package com.mosesaltruism.cocktails.presentation.byname.adapters

import com.mosesaltruism.cocktails.R
import com.mosesaltruism.cocktails.core.common.base.BaseAdapters
import com.mosesaltruism.cocktails.core.common.base.BaseViewHolder
import com.mosesaltruism.cocktails.databinding.SearchedCocktailItemBinding
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem


class SearchAdapter : BaseAdapters(){
    override fun getLayoutView(): Int = R.layout.searched_cocktail_item
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val layoutBinder = holder.viewDataBinding as SearchedCocktailItemBinding
        layoutBinder.also {
            it.searchResponse = baseItems[position] as SearchedCockTailItem?

            //change backgrounds
        }
    }
}