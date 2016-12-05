package com.qixiu.common.zhuojinniao.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lipple-server on 16/12/4.
 */

public class MarqueeTextView extends TextView {
    public MarqueeTextView(Context context) {
        //super(context);
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        //super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        //super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }

    @Override
    public boolean isFocused() {
        //return super.isFocused();
        return true;
    }
}
