package com.adrielcafe.dcxmarvel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public void marvelDeck(View v){
        Bundle bundle = new Bundle();
        bundle.putString(App.EXTRA_DECK, App.EXTRA_MARVEL);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void dcDeck(View v){
        Bundle bundle = new Bundle();
        bundle.putString(App.EXTRA_DECK, App.EXTRA_DC);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
