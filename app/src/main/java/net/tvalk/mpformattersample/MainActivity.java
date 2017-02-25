package net.tvalk.mpformattersample;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.tvalk.mpformatter.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView label = (TextView)findViewById(R.id.testLabel);

        StringBuilder nickname = new StringBuilder();
        nickname.append("$F80$i$S$oToffe$z$06FSmu$h[testing]rf$h $z$n$l[goo.gl/y4M9VK][App]$l");
        nickname.append("$z$l");
        nickname.append("$l");

        Spanned nn = new MPFormatter(nickname.toString()).parse().getSpannable();

        label.setText(nn);
        label.setLinksClickable(true);
        label.setFocusable(true);
        label.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
