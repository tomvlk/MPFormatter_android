package net.tvalk.mpformatter;

import android.text.Editable;

/**
 * Created by tom on 4-3-2015.
 */
abstract class MPStyles {
    protected int start;
    protected int end = 0;

    public MPStyles(int startIndex) {
        this.start = startIndex;
    }

    public void apply(Editable spannable){
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int newEnd) {
        this.end = newEnd;
    }
}
