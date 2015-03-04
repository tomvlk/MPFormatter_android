# MPFormatter_android
A ManiaPlanet Color Style parser and formatter for Android. Currently basicly only working with all the styles. But in future it can also strip styles, links and colors and have more options.
It will output a Spanned object that can be used in TextViews and more Android controls that support Spanned Strings. 

## Usage ##

To get the Spanned from a styled nickname for example, use:

    String nickname = "$F80$i$o$SToffe$z$06FSmurf $z$n$l[http://goo.gl/y4M9VK][App]$l";
    Spanned styledNickname = new MPFormatter().parseString(nickname);

This will result in:
![Example result from above code](https://raw.githubusercontent.com/tomvlk/MPFormatter_swift/master/example.png "Example result")


## Licence ##
See LICENCE file for MIT licence.
