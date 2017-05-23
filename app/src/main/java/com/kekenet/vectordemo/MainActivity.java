package com.kekenet.vectordemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image_view2)
    ImageView mImageView2;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.text)
    EditText mText;
    private AnimatedVectorDrawableCompat search2Bar;
    private AnimatedVectorDrawableCompat bar2Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Drawable drawable = mImageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        Drawable drawable2 = mImageView2.getDrawable();
        if (drawable2 instanceof Animatable) {
            ((Animatable) drawable2).start();
        }

        search2Bar = AnimatedVectorDrawableCompat.create(this, R.drawable.animation_search2bar);
        bar2Search = AnimatedVectorDrawableCompat.create(this, R.drawable.animation_bar2search);

        //默认显示搜索按钮
        mSearch.setImageDrawable(bar2Search);
        bar2Search.start();
        mText.setVisibility(View.GONE);
    }

    @OnClick({R.id.search, R.id.root_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (search2Bar.equals(mSearch.getDrawable())) return;

                mSearch.setImageDrawable(search2Bar);
                search2Bar.start();
                mText.setVisibility(View.VISIBLE);
                mText.setFocusable(true);
                mText.requestFocus();
                showSoftKeyBoard(this);
                break;
            case R.id.root_view:
                if (bar2Search.equals(mSearch.getDrawable())) return;
                mSearch.setImageDrawable(bar2Search);
                bar2Search.start();
                mText.clearFocus();
                mText.setVisibility(View.GONE);
                hideSoftKeyboard();
                break;
        }
    }

    private void hideSoftKeyboard() {
        View focusView = getCurrentFocus();
        if (focusView == null) {
            focusView = mText;
        }
        ((InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                focusView.getWindowToken(), 0);
    }

    public static void showSoftKeyBoard(Activity act) {
        try {
            InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(act.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
