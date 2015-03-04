package net.tvalk.mpformatter;

import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;

/**
 * Created by tom on 4-3-2015.
 */
public class MPStyle extends MPStyles {

    private MPFontStyle style;

    public MPStyle(MPFontStyle style, int startIndex) {
        super(startIndex);
        this.style = style;
    }

    @Override
    public void apply(Editable spannable) {
        if(super.end != 0){
            // Apply on the spannable

            CharacterStyle characterStyle = new StyleSpan(Typeface.NORMAL);

            if(this.style == MPFontStyle.Italic){
                characterStyle = new StyleSpan(Typeface.ITALIC);
            }else if(this.style == MPFontStyle.Bold){
                characterStyle = new StyleSpan(Typeface.BOLD);
            }else if(this.style == MPFontStyle.Small) {
                characterStyle = new StyleSpan(Typeface.NORMAL);
            }else if(this.style == MPFontStyle.Wide) {
                characterStyle = new StyleSpan(Typeface.BOLD);
            }

            spannable.setSpan(characterStyle, super.start, super.end, Spanned.SPAN_COMPOSING);
        }
    }

    public MPFontStyle getStyle() {
        return this.style;
    }
}
