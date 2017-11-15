package com.vinh.dictionary_1.screen.bookmarkedwordlist.wordlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.vinh.dictionary_1.R;
import com.vinh.dictionary_1.data.model.Word;
import com.vinh.dictionary_1.databinding.ItemBookmarkedWordListBinding;
import com.vinh.dictionary_1.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 01/11/2017.
 */

public class BookmarkedWordListAdapter
        extends BaseRecyclerViewAdapter<BookmarkedWordListAdapter.ItemViewHolder> {
    private List<Word> mWords;
    private OnRecyclerViewItemClickListener<Word> mItemClickListener;

    BookmarkedWordListAdapter(@NonNull Context context, List<Word> words) {
        super(context);
        mWords = new ArrayList<>();
        if (words != null) {
            mWords.addAll(words);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBookmarkedWordListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_bookmarked_word_list, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Word> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    void appendDataSet(List<Word> words) {
        if (words != null) {
            mWords.addAll(words);
        }
        notifyDataSetChanged();
    }

    void clearDataSet() {
        mWords.clear();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemBookmarkedWordListBinding mBinding;
        private OnRecyclerViewItemClickListener<Word> mItemClickListener;

        ItemViewHolder(ItemBookmarkedWordListBinding binding,
                OnRecyclerViewItemClickListener<Word> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Word word) {
            mBinding.setItem(word);
            mBinding.setItemListener(mItemClickListener);
            mBinding.executePendingBindings();
        }
    }
}
