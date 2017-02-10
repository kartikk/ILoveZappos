package com.kartikk.zappos.ilovezappos;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kartikk.zappos.ilovezappos.databinding.CardSearchResultBinding;
import com.kartikk.zappos.ilovezappos.models.ZapposModel;
import com.kartikk.zappos.ilovezappos.models.ZapposResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kartikk on 2/4/2017.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {

    List<ZapposResult> zapposResultList;
    final String TAG = SearchRecyclerAdapter.class.getSimpleName();

    public SearchRecyclerAdapter(List<ZapposResult> zapposResultList) {
        this.zapposResultList = new ArrayList<>(zapposResultList);
    }

    public SearchRecyclerAdapter(ZapposModel zapposModel) {
        this.zapposResultList = new ArrayList<>(zapposModel.getResults());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardSearchResultBinding cardSearchResultBinding = DataBindingUtil
                .inflate(inflater, R.layout.card_search_result, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardSearchResultBinding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZapposResult zapposResult = zapposResultList.get(position);
        CardSearchResultBinding cardSearchResultBinding = DataBindingUtil.findBinding(holder.itemView);
        cardSearchResultBinding.setResult(zapposResult);
        cardSearchResultBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return zapposResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
