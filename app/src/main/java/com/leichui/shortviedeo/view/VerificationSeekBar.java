package com.leichui.shortviedeo.view;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by Administrator on 2017/8/19.
 */

public class VerificationSeekBar extends AppCompatSeekBar {
    //这两个值为用算法使用的2空间复杂度
    private int index = 150;
    private boolean k = true;

    public VerificationSeekBar(Context context) {
        super(context);
    }

    public VerificationSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerificationSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            k = true;
            if (x - index > 100) {
                k = false;
                return true;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (!k) {
                return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }
}

