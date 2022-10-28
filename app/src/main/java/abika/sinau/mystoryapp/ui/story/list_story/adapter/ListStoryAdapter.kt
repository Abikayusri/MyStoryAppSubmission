package abika.sinau.mystoryapp.ui.story.list_story.adapter

import abika.sinau.core.R
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.DateUtils.DD_MMMM_YYYY_HH_MM
import abika.sinau.core.utils.DateUtils.UTCT_FORMAT_4
import abika.sinau.core.utils.DateUtils.convertDateFromTo
import abika.sinau.core.utils.loadImage
import abika.sinau.mystoryapp.databinding.ItemListStoryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


/**
 * @author by Abika Chairul Yusri on 9/28/2022
 */
class ListStoryAdapter : PagingDataAdapter<StoryListResponse, ListStoryAdapter.ListStoryViewHolder>(
    ListStoryDiffUtil
) {
    private var callbacks: OnClickListener? = null

    object ListStoryDiffUtil : DiffUtil.ItemCallback<StoryListResponse>() {
        override fun areItemsTheSame(
            oldItem: StoryListResponse,
            newItem: StoryListResponse
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: StoryListResponse,
            newItem: StoryListResponse
        ) = oldItem == newItem

    }

    override fun onBindViewHolder(holder: ListStoryViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListStoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListStoryBinding.inflate(inflater, parent, false)

        return ListStoryViewHolder(binding)
    }

    inner class ListStoryViewHolder(
        private val binding: ItemListStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoryListResponse) {
            binding.apply {
                tvItemName.text = item.name
                ivItemPhoto.loadImage(
                    item.photoUrl.toString(),
                    R.mipmap.img_placeholder,
                    R.mipmap.img_placeholder
                )
                tvItemDate.text =
                    convertDateFromTo(item.createdAt, UTCT_FORMAT_4, DD_MMMM_YYYY_HH_MM)

                root.setOnClickListener {
                    callbacks?.onClickItem(item)
                }
            }
        }
    }

    fun setOnItemClickListener(callbacks: OnClickListener) {
        this.callbacks = callbacks
    }

    interface OnClickListener {
        fun onClickItem(item: StoryListResponse)
    }
}