package com.adrielcafe.dcxmarvel;

import android.app.Application;
import android.widget.Toast;

import com.adrielcafe.dcxmarvel.model.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

public class App extends Application {
    public static final String EXTRA_DECK = "deck";
    public static final String EXTRA_CARD = "card";
    public static final String EXTRA_MARVEL = "marvel";
    public static final String EXTRA_DC = "dc";

    public static List<Card> deckMarvel;
    public static List<Card> deckDC;

    @Override
    public void onCreate() {
        super.onCreate();
        deckMarvel = loadDeck(EXTRA_MARVEL);
        deckDC = loadDeck(EXTRA_DC);
    }

    private List<Card> loadDeck(String deck){
        String json = null;
        String path = null;
        switch (deck){
            case EXTRA_MARVEL:
                path = "db/deck_marvel.json";
                break;
            case EXTRA_DC:
                path = "db/deck_dc.json";
                break;
        }
        try {
            InputStream is = getApplicationContext().getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            return null;
        }
        return new Gson().fromJson(json, new TypeToken<List<Card>>(){}.getType());
    }
}
