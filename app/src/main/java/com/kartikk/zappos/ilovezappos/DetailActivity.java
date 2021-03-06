package com.kartikk.zappos.ilovezappos;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.kartikk.zappos.ilovezappos.databinding.ItemDetailBinding;
import com.kartikk.zappos.ilovezappos.models.ZapposResult;
import com.squareup.picasso.Picasso;

/**
 * Created by Kartikk on 2/10/2017.
 */

public class DetailActivity extends AppCompatActivity {

    ZapposResult result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemDetailBinding itemDetailBinding = DataBindingUtil.setContentView(this, R.layout.item_detail);
        setSupportActionBar(itemDetailBinding.detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        result = getIntent().getParcelableExtra("result");
        itemDetailBinding.setResult(result);
        if (result.getOriginalPrice().equals(result.getPrice())) {
            itemDetailBinding.detailOrgPriceText.setVisibility(View.GONE);
            itemDetailBinding.detailDiscountText.setVisibility(View.GONE);
        }
        itemDetailBinding.detailOrgPriceText
                .setPaintFlags(
                        itemDetailBinding.detailOrgPriceText.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
        final FloatingActionButton fab = itemDetailBinding.fab;
        SpringSystem springSystem = SpringSystem.create();
        SpringConfig springConfig = new SpringConfig(800, 15);
        final Spring spring = springSystem.createSpring();
        spring.setSpringConfig(springConfig);
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.5f);
                fab.setScaleX(scale);
                fab.setScaleY(scale);
            }
        });
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(1f);
                        return true;
                    case MotionEvent.ACTION_UP:
                        spring.setEndValue(0f);
                        return true;
                }
                return false;
            }
        });

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("DetailActivity")
                .putContentType("String")
                .putContentId("11")
                .putCustomAttribute("name", result.getProductName())
                .putCustomAttribute("id", result.getProductId()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.menu_share_icon:
                shareItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public void shareItem() {
        if(result!=null){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, result.getProductUrl());
            startActivity(Intent.createChooser(shareIntent, "Share link using"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }
}
