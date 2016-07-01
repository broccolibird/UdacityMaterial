package com.example.xyzreader.ui;

/**
 * Created by Kat on 11/12/15.
 */

import com.example.xyzreader.R;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kat on 7/27/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class FadingImageView extends RelativeLayout {

    private static final int ANIMATION_DURATION = 600;

    private final AnimatorSet m0To1Animator = new AnimatorSet();

    private final AnimatorSet m1To0Animator = new AnimatorSet();

    private final AnimatorSet m0ToErrorAnimator = new AnimatorSet();

    private final AnimatorSet m1ToErrorAnimator = new AnimatorSet();

    private ImageView mImageView0;

    private ImageView mImageView1;

    private int mCurrImageView = 0;

    public FadingImageView(Context context) {
        super(context);
        init();
    }

    public FadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_fading_image, this);

        mImageView0 = (ImageView) findViewById(R.id.image1);
        mImageView1 = (ImageView) findViewById(R.id.image2);

    }

    public void setImageBitmap(Bitmap bitmap) {
        if (mCurrImageView == 0) {
            mImageView1.setImageBitmap(bitmap);
            transition();
        } else {
            mImageView0.setImageBitmap(bitmap);
            transition();
        }

    }

    private void transition() {
        if (mCurrImageView == 0) {

            if (m1To0Animator.isRunning()) {
                m1To0Animator.cancel();
            }

            ObjectAnimator animate1Off = ObjectAnimator
                    .ofFloat(mImageView0, "alpha", mImageView0.getAlpha(), -.2f);
            ObjectAnimator animate2On = ObjectAnimator.ofFloat(mImageView1, "alpha", -.2f, 1.2f);

            m0To1Animator.playTogether(animate1Off, animate2On);

            m0To1Animator.setDuration(ANIMATION_DURATION);
            mCurrImageView = 1;

            m0To1Animator.start();
        } else {

            if (m0To1Animator.isRunning()) {
                m0To1Animator.cancel();
            }

            ObjectAnimator animate1On = ObjectAnimator.ofFloat(mImageView0, "alpha", -.2f, 1.2f);
            ObjectAnimator animate2Off = ObjectAnimator
                    .ofFloat(mImageView1, "alpha", mImageView1.getAlpha(), -.2f);

            m1To0Animator.playTogether(animate2Off, animate1On);
            m1To0Animator.setDuration(ANIMATION_DURATION);

            mCurrImageView = 0;

            m1To0Animator.start();
        }

    }

    private void transitionToError() {
        if (mCurrImageView == 0) {

            if (m1To0Animator.isRunning()) {
                m1To0Animator.cancel();
            }

            ObjectAnimator animate1Off = ObjectAnimator.ofFloat(mImageView0, "alpha", 1.2f, -.2f);

            m0ToErrorAnimator.playTogether(animate1Off);

            m0ToErrorAnimator.setDuration(ANIMATION_DURATION);
//            mCurrImageView = 1;

            m0ToErrorAnimator.start();
        } else {

            if (m0To1Animator.isRunning()) {
                m0To1Animator.cancel();
            }

            ObjectAnimator animate2Off = ObjectAnimator.ofFloat(mImageView1, "alpha", 1.2f, -.2f);

            m1ToErrorAnimator.playTogether(animate2Off);
            m1ToErrorAnimator.setDuration(ANIMATION_DURATION);

//            mCurrImageView = 0;

            m1ToErrorAnimator.start();
        }
    }
}

