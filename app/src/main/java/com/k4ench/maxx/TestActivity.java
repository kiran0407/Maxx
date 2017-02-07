package com.k4ench.maxx;

import android.content.Intent;
import android.view.View;

import com.luseen.verticalintrolibrary.VerticalIntro;
import com.luseen.verticalintrolibrary.VerticalIntroItem;

import static com.k4ench.maxx.R.color.color2;

public class TestActivity extends VerticalIntro {


    @Override
    protected void init() {
        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.colorAccent)
                .image(R.drawable.chat1)
                .title("Lorem Ipsum Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(color2)
                .image(R.drawable.four)
                .title("Lorem Ipsum Lorem Ipsum ")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.colorPrimary)
                .image(R.drawable.chat2)
                .title("Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.color3)
                .image(R.drawable.new_intro)
                .title("Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        setDoneText("FINISHED");
        setSkipText("GO GO");
        setSkipEnabled(true);
        setVibrateEnabled(true);
        setNextText("NEXT");
        setVibrateIntensity(20);



    }

    @Override
    protected Integer setLastItemBottomViewColor() {
        return R.color.color2;
    }

    @Override
    protected void onSkipPressed(View view) {

    }

    @Override
    protected void onFragmentChanged(int position) {

    }

    @Override
    protected void onDonePressed() {
        Intent intent =new Intent(TestActivity.this,Acc.class);
        startActivity(intent);
    }


}
