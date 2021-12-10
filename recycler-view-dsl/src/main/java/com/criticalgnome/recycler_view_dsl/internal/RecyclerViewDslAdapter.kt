package com.criticalgnome.recycler_view_dsl.internal

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

internal class RecyclerViewDslAdapter(
    private val viewHolderFactories: List<RecyclerViewDslViewHolder.Factory<*, *>>
) : RecyclerView.Adapter<RecyclerViewDslViewHolder<*, *>>() {

    var model: List<Any> = listOf()
        set(value) {
            DiffUtil
                .calculateDiff(object : DiffUtil.Callback() {
                    override fun getOldListSize(): Int = field.size
                    override fun getNewListSize(): Int = value.size
                    override fun areItemsTheSame(old: Int, new: Int): Boolean = field[old] === value[new]
                    override fun areContentsTheSame(old: Int, new: Int): Boolean = field[old] == value[new]
                })
                .also { diffResult ->
                    field = value
                    diffResult.dispatchUpdatesTo(this)
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewDslViewHolder<*, *> {
        return viewHolderFactories[viewType].create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewDslViewHolder<*, *>, position: Int) {
        model[position].let(holder::bind)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun getItemViewType(position: Int): Int {
        return model[position]
            .let {
                viewHolderFactories
                    .withIndex()
                    .first { (_, viewHolderFactory) -> viewHolderFactory.couldBeBoundTo(it) }
            }
            .index
    }
}
