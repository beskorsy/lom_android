package lom.lom_android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;
import lom.lom_android.service.ResultModel;

public class CustomViewPager extends ScrollerViewPager {

    private boolean isPagingEnabled = true;

    private ResultModel model;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.getPagingEnabled() && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.getPagingEnabled() && super.onInterceptTouchEvent(event);
    }


    @Override
    public void setCurrentItem(int item) {
        if (this.getPagingEnabled()) {
            super.setCurrentItem(item);
        }
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    private boolean getPagingEnabled() {
        if (model == null) return false;

        int currentItem = getCurrentItem();
        switch (currentItem) {
            case 0:
                return isPagingEnabled && model.isContactsValid();
            case 1:
                return isPagingEnabled;
            case 2:
                return isPagingEnabled;
        }
        return isPagingEnabled;
    }

    public void setModel(ResultModel model) {
        this.model = model;
    }
}
