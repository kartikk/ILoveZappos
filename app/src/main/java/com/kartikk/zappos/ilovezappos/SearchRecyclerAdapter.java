package com.kartikk.zappos.ilovezappos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    Context context;
    final String TAG = SearchRecyclerAdapter.class.getSimpleName();

    public SearchRecyclerAdapter(List<ZapposResult> zapposResultList) {
        this.zapposResultList = new ArrayList<>(zapposResultList);
    }

    public SearchRecyclerAdapter(ZapposModel zapposModel) {
        this.zapposResultList = new ArrayList<>(zapposModel.getResults());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchResultView = inflater.inflate(R.layout.card_search_result, parent, false);
        ViewHolder viewHolder = new ViewHolder(searchResultView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZapposResult zapposResult = zapposResultList.get(position);
        Picasso.with(context).load(zapposResult.getThumbnailImageUrl()).into(holder.itemImageView);
        holder.nameTextView.setText(zapposResult.getProductName());
        holder.brandTextView.setText(zapposResult.getBrandName());
        holder.origPriceTextView.setText(zapposResult.getOriginalPrice());
        holder.currPriceTextView.setText(zapposResult.getPrice());
        holder.discountTextView.setText(zapposResult.getPercentOff());
    }

    @Override
    public int getItemCount() {
        return zapposResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView nameTextView, brandTextView, origPriceTextView, currPriceTextView, discountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView) itemView.findViewById(R.id.card_search_image_view);
            nameTextView = (TextView) itemView.findViewById(R.id.card_search_name_text);
            brandTextView = (TextView) itemView.findViewById(R.id.card_search_brand_text);
            origPriceTextView = (TextView) itemView.findViewById(R.id.card_search_org_price_text);
            currPriceTextView = (TextView) itemView.findViewById(R.id.card_search_price_text);
            discountTextView = (TextView) itemView.findViewById(R.id.card_search_discount_text);
        }
    }
}
