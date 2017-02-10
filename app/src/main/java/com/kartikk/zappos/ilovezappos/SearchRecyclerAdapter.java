package com.kartikk.zappos.ilovezappos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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
        final Context context = holder.itemView.getContext();
        final ZapposResult zapposResult = zapposResultList.get(position);
        final CardSearchResultBinding cardSearchResultBinding = DataBindingUtil.findBinding(holder.itemView);
        cardSearchResultBinding.setResult(zapposResult);
        cardSearchResultBinding.executePendingBindings();
//        if (!zapposResult.getOriginalPrice().equals(zapposResult.getPrice())) {
        cardSearchResultBinding.cardSearchOrgPriceText
                .setPaintFlags(
                        cardSearchResultBinding.cardSearchOrgPriceText.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailPageIntent = new Intent(context, DetailActivity.class);
                detailPageIntent.putExtra("result", zapposResult);
                Pair<View, String> p1 = Pair.create((View) cardSearchResultBinding.cardSearchNameText, "product_name");
                Pair<View, String> p2 = Pair.create((View) cardSearchResultBinding.cardSearchBrandText, "brand_name");
                Pair<View, String> p3 = Pair.create((View) cardSearchResultBinding.cardSearchOrgPriceText, "org_price");
                Pair<View, String> p4 = Pair.create((View) cardSearchResultBinding.cardSearchPriceText, "price");
                Pair<View, String> p5 = Pair.create((View) cardSearchResultBinding.cardSearchDiscountText, "discount");
                Pair<View, String> p6 = Pair.create((View) cardSearchResultBinding.cardSearchImageView, "image");
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity) context, p1, p2, p3, p4, p5, p6);
                context.startActivity(detailPageIntent, activityOptionsCompat.toBundle());
            }
        });
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
