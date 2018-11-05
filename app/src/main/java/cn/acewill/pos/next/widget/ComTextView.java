package cn.acewill.pos.next.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import cn.acewill.pos.R;
import cn.acewill.pos.next.utils.DensityUtils;
import cn.acewill.pos.next.utils.ScreenUtil;

/**
 * Created by aqw on 2016/11/15.
 */
public class ComTextView extends TextView {

    public ComTextView(Context context) {
        super(context,null);
    }

    public ComTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.CheckText);

        float textSize = typedArray.getFloat(R.styleable.CheckText_textSize,27);
        boolean setHeight = typedArray.getBoolean(R.styleable.CheckText_setHeight,true);

        if(setHeight){
            int screenHeight = ScreenUtil.getScreenSize(context)[1];
            this.setHeight(screenHeight/15);
        }

        this.setTextSize(DensityUtils.px2sp(context,textSize));
    }



}
