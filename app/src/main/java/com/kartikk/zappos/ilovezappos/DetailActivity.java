package com.kartikk.zappos.ilovezappos;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.kartikk.zappos.ilovezappos.databinding.ItemDetailBinding;
import com.kartikk.zappos.ilovezappos.models.ZapposResult;
import com.squareup.picasso.Picasso;

/**
 * Created by Kartikk on 2/10/2017.
 */

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemDetailBinding itemDetailBinding = DataBindingUtil.setContentView(this, R.layout.item_detail);
        ZapposResult result = getIntent().getParcelableExtra("result");
        itemDetailBinding.setResult(result);
        if (result.getOriginalPrice().equals(result.getPrice())) {
            itemDetailBinding.detailOrgPriceText.setVisibility(View.GONE);
            itemDetailBinding.detailDiscountText.setVisibility(View.GONE);
        }
        itemDetailBinding.detailOrgPriceText
                .setPaintFlags(
                        itemDetailBinding.detailOrgPriceText.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
