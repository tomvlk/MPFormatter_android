package net.tvalk.mpformatter;

import android.text.Editable;
import android.text.Spanned;
import android.text.style.URLSpan;

/**
 * MPLink Style Subclass.
 */
public class MPLink extends MPStyles {

    private String url;

    public MPLink(String link, int startIndex) {
        super(startIndex);
        this.setUrl(link);
    }

    @Override
    public void apply(Editable spannable) {
        if(super.end > 0) {
            spannable.setSpan(new URLSpan(this.url), super.start, super.end, Spanned.SPAN_COMPOSING);
        }
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String newUrl) {
        if (newUrl != null && !newUrl.startsWith("http://") && !newUrl.startsWith("https://")) {
            newUrl = "http://" + newUrl;
        }
        this.url = newUrl;
    }
}
