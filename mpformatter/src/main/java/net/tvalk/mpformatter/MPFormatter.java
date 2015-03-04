package net.tvalk.mpformatter;

import android.text.Editable;
import android.text.Spannable;

import java.util.ArrayList;

/**
 * Formatter and parser for the Maniaplanet Styles
 * Created by tom on 4-3-2015.
 * @author Tom Valk
 */
public class MPFormatter {
    private ArrayList<MPStyle> styles;
    private ArrayList<MPLink> links;
    private ArrayList<MPColor> colors;

    private ArrayList<Integer> ignore;

    private String input;
    private Editable styledString;

    /**
     * Init the formatter with the input.
     * @param input String of the unparsed style string
     */
    public MPFormatter(String input) {
        this.styles = new ArrayList<>();
        this.links = new ArrayList<>();
        this.colors = new ArrayList<>();
        this.ignore = new ArrayList<>();

        this.input = input;

        this.styledString = Editable.Factory.getInstance().newEditable("");
    }

    public class MPParsedString {
        private Editable unstyledEditable;

        private boolean parseColors = true;
        private boolean parseStyles = true;
        private boolean parseLinks = true;

        public MPParsedString(Editable unstyledString) {
            this.unstyledEditable = unstyledString;
        }

        /**
         * Strip colors ($fff etc)
         */
        public MPParsedString stripColors() {
            this.parseColors = false;
            return this;
        }

        /**
         * Strip styles ($o $w $i $n)
         */
        public MPParsedString stripStyles() {
            this.parseStyles = false;
            return this;
        }

        /**
         * Strip the links ($l and $m)
         */
        public MPParsedString stripLinks() {
            this.parseLinks = false;
            return this;
        }

        /**
         * Get spannable
         * @return Spannable
         */
        public Spannable getSpannable() {
            Editable styledSpannable = this.unstyledEditable;

            if(this.parseStyles) {
                for (MPStyle style : styles) {
                    if (style.getEnd() > 0) {
                        style.apply(styledSpannable);
                    }
                }
            }

            if(this.parseColors) {
                for (MPColor color : colors) {
                    if (color.getEnd() > 0) {
                        color.apply(styledSpannable);
                    }
                }
            }

            if(this.parseLinks) {
                for (MPLink link : links) {
                    if (link.getEnd() > 0) {
                        link.apply(styledSpannable);
                    }
                }
            }

            return styledSpannable;
        }

        /**
         * Get string
         * @return String unstyled
         */
        public String getString() {
            return this.unstyledEditable.toString();
        }
    }

    /**
     * Call this after init the class
     */
    public MPParsedString parse() {

        // Loop tru the input
        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // If dollar is found, and input is > current char (is not at end)
            if(ch == '$' && input.length()  > i) {
                char type = input.charAt(i+1);

                // Ignore next char
                this.ignore.add(i+1);

                // For type, do stuff
                switch(type) {
                    case 'l':
                    case 'L':
                    case 'm':
                    case 'M':
                        // Parse link
                        this.stopAllLinks(styledString.length());

                        // Check if url can be after the dollar and type char
                        if(input.length() > i + 2){
                            char after = input.charAt(i+2);

                            // Link invisible url
                            if(after == '['){
                                // Get start and end of link
                                int urlStop = input.indexOf("]", i + 2);
                                if(urlStop > -1 && input.length() > i + 3) {
                                    String url = input.substring(i + 3, urlStop - 1);

                                    MPLink link = new MPLink(url, styledString.length());
                                    this.links.add(link);

                                    // Ignore all the url parts
                                    for(int s = 0; s <= url.length() + 2; s++){
                                        ignore.add(i + 2 + s);
                                    }
                                }
                            }else{
                                // Link is visible, ignore it
                            }
                        }else{
                            // Last one link stopper, so ignore
                        }

                        break;

                    case 'n':
                    case 'N':
                        // Small style
                        this.stopAllStyles(styledString.length(), MPFontStyle.Small);

                        this.styles.add(new MPStyle(MPFontStyle.Small, styledString.length()));
                        break;

                    case 'i':
                    case 'I':
                        // Italic style
                        this.stopAllStyles(styledString.length(), MPFontStyle.Italic);

                        this.styles.add(new MPStyle(MPFontStyle.Italic, styledString.length()));
                        break;

                    case 'o':
                    case 'O':
                        // Bold style
                        this.stopAllStyles(styledString.length(), MPFontStyle.Bold);

                        this.styles.add(new MPStyle(MPFontStyle.Bold, styledString.length()));
                        break;

                    case 's':
                    case 'S':
                        // Shadow style
                        this.stopAllStyles(styledString.length(), MPFontStyle.Shadow);

                        this.styles.add(new MPStyle(MPFontStyle.Shadow, styledString.length()));
                        break;

                    case 'w':
                    case 'W':
                        // Wide style
                        this.stopAllStyles(styledString.length(), MPFontStyle.Wide);

                        this.styles.add(new MPStyle(MPFontStyle.Wide, styledString.length()));
                        break;

                    case 'g':
                    case 'G':
                        // Stop colors
                        this.stopAllColors(styledString.length());

                        break;

                    case 'z':
                    case 'Z':
                        // Stop styles and colors
                        this.stopAllColors(styledString.length());
                        this.stopAllLinks(styledString.length());
                        this.stopAllStyles(styledString.length());
                        break;

                    case '>':
                    case '<':
                        // Ignore for now

                        break;

                    default:
                        // Color?
                        if(input.length() > i+3) {
                            this.stopAllColors(styledString.length());

                            String colorString = input.substring(i+1, i+4);

                            MPColor color = new MPColor(colorString, styledString.length());
                            this.colors.add(color);

                            ignore.add(i+2);
                            ignore.add(i+3);
                            //ignore.add(i+4);
                        }
                        break;
                }
            }else{
                if(ignore.indexOf(i) > -1){
                    // Skip
                }else{
                    styledString.append(ch);
                }
            }

            if(i+1 == input.length()) {
                this.stopAllLinks(styledString.length());
                this.stopAllStyles(styledString.length());
                this.stopAllColors(styledString.length());
            }
        }

        // Make the parsed class and return it
        return new MPParsedString(styledString);
    }

    /**
     * Stop all the links
     * @param stopIndex Current position in output string
     */
    private void stopAllLinks(int stopIndex) {
        for(MPLink link: links){
            if(link.getEnd() == 0){
                link.setEnd(stopIndex);
            }
        }
    }

    /**
     * Stop all the styles
     * @param stopIndex Current position in output string
     */
    private void stopAllStyles(int stopIndex) {
        stopAllStyles(stopIndex, null);
    }

    /**
     * Stop all the specified styles of the type
     * @param stopIndex Current position in output string
     * @param style MPFontStyle of the style to stop {@link net.tvalk.mpformatter.MPFontStyle}
     */
    private void stopAllStyles(int stopIndex, MPFontStyle style) {
        for(MPStyle styl: styles){
            if(styl.getEnd() == 0 && (style != null && styl.getStyle() == style)){
                styl.setEnd(stopIndex);
            }
        }
    }

    /**
     * Stop all the colors
     * @param stopIndex Current position in output string
     */
    private void stopAllColors(int stopIndex) {
        for(MPColor color: colors){
            if(color.getEnd() == 0){
                color.setEnd(stopIndex);
            }
        }
    }

}
