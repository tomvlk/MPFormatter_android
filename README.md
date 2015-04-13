[![Build Status](https://travis-ci.org/tomvlk/MPFormatter_android.png)](https://travis-ci.org/tomvlk/MPFormatter_android)

# MPFormatter_android
A ManiaPlanet Color Style parser and formatter for Android. Currently basicly only working with all the styles. But in future it can also strip styles, links and colors and have more options.
It will output a Spanned object that can be used in TextViews and more Android controls that support Spanned Strings. 

## Installation ##
You can use gradle to compile it, it's in the jCentral repository.

    dependencies {
        compile 'net.tvalk.mpformatter:mpformatter:1.2'
    }

If you want to stay updated, replace the '1.2' with '1.+'. But can break things when there is a new version.

## Usage ##

To get the Spanned from a styled nickname for example, use:

    String nickname = "$F80$i$o$SToffe$z$06FSmurf $z$n$l[http://goo.gl/y4M9VK][App]$l";
    Spanned styledNickname = new MPFormatter(nickname).parse().getSpanned();

This will result in:
![Example result from above code](https://raw.githubusercontent.com/tomvlk/MPFormatter_swift/master/example.png "Example result")

You can also strip the colors, links or styles with the functions stripColors(), stripLinks() and stripStyles() on the parse() return to strip it. You can also get a string without any styles, colors or links with getString() on the parse() return

For example, to strip the links in this nickname:

    String nickname = "$F80$i$o$SToffe$z$06FSmurf $z$n$l[http://goo.gl/y4M9VK][App]$l";
    Spanned styledNickname = new MPFormatter(nickname).parse().stripLinks().getSpanned();


## Licence ##
See LICENCE file for MIT licence.
