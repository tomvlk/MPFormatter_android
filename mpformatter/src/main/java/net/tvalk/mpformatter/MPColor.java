package net.tvalk.mpformatter;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

/**
 * Created by tom on 4-3-2015.
 */
public class MPColor extends MPStyles {

    private String colorString;
    private int color;

    public MPColor(String colorString, int startIndex) {
        super(startIndex);
        this.colorString = colorString;
        if(colorString.length() == 3){
            try{
                this.color = Color.parseColor("#" + colorString.charAt(0) + colorString.charAt(0) + colorString.charAt(1) + colorString.charAt(1) + colorString.charAt(2) + colorString.charAt(2));
            }catch(IllegalArgumentException e){
                this.color = Color.BLACK;
            }
        }
    }

    @Override
    public void apply(Editable spannable) {
        if(super.end > 0) {
            spannable.setSpan(new ForegroundColorSpan(this.color), super.start, super.end, Spannable.SPAN_COMPOSING);
        }
    }
}
