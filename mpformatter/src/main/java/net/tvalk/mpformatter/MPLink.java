package net.tvalk.mpformatter;

import android.text.Editable;
import android.text.Spanned;
import android.text.style.URLSpan;

/**
 * Created by tom on 4-3-2015.
 */
public class MPLink extends MPStyles {

    private String url;

    public MPLink(String link, int startIndex) {
        super(startIndex);
        this.url = link;
    }

    @Override
    public void apply(Editable spannable) {
        if(super.end > 0) {
            spannable.setSpan(new URLSpan(this.url), super.start, super.end, Spanned.SPAN_COMPOSING);
        }
    }
}
