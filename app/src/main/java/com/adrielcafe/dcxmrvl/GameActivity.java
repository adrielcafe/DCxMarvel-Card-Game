package com.adrielcafe.dcxmrvl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adrielcafe.dcxmrvl.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends Activity {
    private Intent intent;

    private int round;

    private int p1Score;
    private List<Card> p1Deck;
    private TextView p1ScoreTitle;
    private ImageView p1BoardCard;
    private TextView p1BoardCardAttr;
    private ImageView p1DeckCard1;
    private ImageView p1DeckCard2;
    private ImageView p1DeckCard3;
    private ImageView p1DeckCard4;
    private ImageView p1DeckCard5;

    private int p2Score;
    private List<Card> p2Deck;
    private TextView p2ScoreTitle;
    private ImageView p2BoardCard;
    private TextView p2BoardCardAttr;
    private ImageView p2DeckCard1;
    private ImageView p2DeckCard2;
    private ImageView p2DeckCard3;
    private ImageView p2DeckCard4;
    private ImageView p2DeckCard5;

    private Button resetGame;

    private List<Integer> p2CardsUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        intent = getIntent();
        p2CardsUsed = new ArrayList<>();

        p1ScoreTitle = (TextView) findViewById(R.id.p1_score);
        p1BoardCard = (ImageView) findViewById(R.id.p1_board_card);
        p1BoardCardAttr = (TextView) findViewById(R.id.p1_board_card_attr);
        p1DeckCard1 = (ImageView) findViewById(R.id.p1_deck_card1);
        p1DeckCard2 = (ImageView) findViewById(R.id.p1_deck_card2);
        p1DeckCard3 = (ImageView) findViewById(R.id.p1_deck_card3);
        p1DeckCard4 = (ImageView) findViewById(R.id.p1_deck_card4);
        p1DeckCard5 = (ImageView) findViewById(R.id.p1_deck_card5);

        p2ScoreTitle = (TextView) findViewById(R.id.p2_score);
        p2BoardCard = (ImageView) findViewById(R.id.p2_board_card);
        p2BoardCardAttr = (TextView) findViewById(R.id.p2_board_card_attr);
        p2DeckCard1 = (ImageView) findViewById(R.id.p2_deck_card1);
        p2DeckCard2 = (ImageView) findViewById(R.id.p2_deck_card2);
        p2DeckCard3 = (ImageView) findViewById(R.id.p2_deck_card3);
        p2DeckCard4 = (ImageView) findViewById(R.id.p2_deck_card4);
        p2DeckCard5 = (ImageView) findViewById(R.id.p2_deck_card5);

        resetGame = (Button) findViewById(R.id.reset_game);

        resetGame(null);
    }

    public void resetGame(View v){
        switch (intent.getStringExtra(App.EXTRA_DECK)){
            case App.EXTRA_MARVEL:
                p1Deck = getRandomDeck(App.deckMarvel);
                p2Deck = getRandomDeck(App.deckDC);
                break;
            case App.EXTRA_DC:
                p1Deck = getRandomDeck(App.deckDC);
                p2Deck = getRandomDeck(App.deckMarvel);
                break;
        }

        resetGame.setVisibility(View.INVISIBLE);
        p2CardsUsed = new ArrayList<>();
        round = 0;

        p1Score = 0;
        p1ScoreTitle.setText(getText(R.string.score) + ": 0");
        p1BoardCard.setImageBitmap(null);
        p1BoardCard.setVisibility(View.INVISIBLE);
        p1BoardCardAttr.setVisibility(View.INVISIBLE);
        try {
            p1DeckCard1.setImageDrawable(Drawable.createFromStream(getAssets().open(p1Deck.get(0).imagePath), null));
            p1DeckCard2.setImageDrawable(Drawable.createFromStream(getAssets().open(p1Deck.get(1).imagePath), null));
            p1DeckCard3.setImageDrawable(Drawable.createFromStream(getAssets().open(p1Deck.get(2).imagePath), null));
            p1DeckCard4.setImageDrawable(Drawable.createFromStream(getAssets().open(p1Deck.get(3).imagePath), null));
            p1DeckCard5.setImageDrawable(Drawable.createFromStream(getAssets().open(p1Deck.get(4).imagePath), null));
        } catch (Exception e){ }
        p1DeckCard1.setVisibility(View.VISIBLE);
        p1DeckCard2.setVisibility(View.VISIBLE);
        p1DeckCard3.setVisibility(View.VISIBLE);
        p1DeckCard4.setVisibility(View.VISIBLE);
        p1DeckCard5.setVisibility(View.VISIBLE);

        p2Score = 0;
        p2ScoreTitle.setText(getText(R.string.score) + ": 0");
        p2BoardCard.setImageBitmap(null);
        p2BoardCard.setVisibility(View.INVISIBLE);
        p2BoardCardAttr.setVisibility(View.INVISIBLE);
        try {
            p2DeckCard1.setImageDrawable(Drawable.createFromStream(getAssets().open("decks/default_card.png"), null));
            p2DeckCard2.setImageDrawable(Drawable.createFromStream(getAssets().open("decks/default_card.png"), null));
            p2DeckCard3.setImageDrawable(Drawable.createFromStream(getAssets().open("decks/default_card.png"), null));
            p2DeckCard4.setImageDrawable(Drawable.createFromStream(getAssets().open("decks/default_card.png"), null));
            p2DeckCard5.setImageDrawable(Drawable.createFromStream(getAssets().open("decks/default_card.png"), null));
        } catch (Exception e){ }
        p2DeckCard1.setVisibility(View.VISIBLE);
        p2DeckCard2.setVisibility(View.VISIBLE);
        p2DeckCard3.setVisibility(View.VISIBLE);
        p2DeckCard4.setVisibility(View.VISIBLE);
        p2DeckCard5.setVisibility(View.VISIBLE);
    }

    private List<Card> getRandomDeck(List<Card> originalDeck){
        Random random = new Random();
        List<Card> deck = new ArrayList<>(originalDeck);
        List<Card> randomDeck = new ArrayList<>();
        while(randomDeck.size() != 5){
            try {
                int i = random.nextInt(deck.size() - 1);
                randomDeck.add(deck.get(i));
                deck.remove(i);
            } catch (Exception e){ }
        }
        return randomDeck;
    }

    public void showCard(View v){
        switch (v.getId()){
            case R.id.p1_deck_card1:
                showCardDialog(0, p1Deck.get(0));
                break;
            case R.id.p1_deck_card2:
                showCardDialog(1, p1Deck.get(1));
                break;
            case R.id.p1_deck_card3:
                showCardDialog(2, p1Deck.get(2));
                break;
            case R.id.p1_deck_card4:
                showCardDialog(3, p1Deck.get(3));
                break;
            case R.id.p1_deck_card5:
                showCardDialog(4, p1Deck.get(4));
                break;
        }
    }

    private void showCardDialog(final int cardIndex, final Card card){
        View v = getLayoutInflater().inflate(R.layout.fragment_card, null);

        ImageView cardImage = (ImageView) v.findViewById(R.id.card_image);
        TextView cardName = (TextView) v.findViewById(R.id.card_name);
        TextView cardAttack = (TextView) v.findViewById(R.id.card_attack);
        TextView cardDefense = (TextView) v.findViewById(R.id.card_defense);

        try {
            cardImage.setImageDrawable(Drawable.createFromStream(this.getAssets().open(card.imagePath), null));
        } catch (Exception e){ }
        cardName.setText(card.name);
        cardAttack.setText(getString(R.string.attack) + ": " + card.attack);
        cardDefense.setText(getString(R.string.defense) + ": " + card.defense);

        new AlertDialog.Builder(this)
                .setView(v)
                .setPositiveButton(R.string.use, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startRound(cardIndex, card);
                    }
                })
                .setNeutralButton(R.string.buy, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(card.link));
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton(R.string.back, null)
                .show();
    }

    public void showBoardCardName(View v){
        switch (v.getId()){
            case R.id.p1_board_card:
                Toast.makeText(this, p1BoardCard.getTag().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.p2_board_card:
                Toast.makeText(this, p2BoardCard.getTag().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startRound(int p1CardIndex, Card p1Card){
        // P1
        switch (p1CardIndex){
            case 0:
                p1DeckCard1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                p1DeckCard2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                p1DeckCard3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                p1DeckCard4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                p1DeckCard5.setVisibility(View.INVISIBLE);
                break;
        }

        try {
            p1BoardCard.setImageDrawable(Drawable.createFromStream(this.getAssets().open(p1Card.imagePath), null));
        } catch (Exception e){ }
        p1BoardCardAttr.setText(getString(R.string.atk) + ": " + p1Card.attack + "  " + getString(R.string.def) + ": " + p1Card.defense);
        p1BoardCard.setVisibility(View.VISIBLE);
        p1BoardCard.setTag(p1Card.name);
        p1BoardCardAttr.setVisibility(View.VISIBLE);

        // P2
        Random random = new Random();
        Card p2Card = null;
        int p2CardIndex = -1;
        while (p2CardIndex < 0){
            int index = random.nextInt(p2Deck.size());
            if(!p2CardsUsed.contains(index)){
                p2CardsUsed.add(index);
                p2Card = p2Deck.get(index);
                p2CardIndex = index;
            }
        }
        switch (p2CardIndex){
            case 0:
                p2DeckCard1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                p2DeckCard2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                p2DeckCard3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                p2DeckCard4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                p2DeckCard5.setVisibility(View.INVISIBLE);
                break;
        }
        try {
            p2BoardCard.setImageDrawable(Drawable.createFromStream(this.getAssets().open(p2Card.imagePath), null));
        } catch (Exception e){ }
        p2BoardCardAttr.setText(getString(R.string.atk) + ": " + p2Card.attack + "  " + getString(R.string.def) + ": " + p2Card.defense);
        p2BoardCard.setVisibility(View.VISIBLE);
        p2BoardCard.setTag(p2Card.name);
        p2BoardCardAttr.setVisibility(View.VISIBLE);

        finishRound(p1Card, p2Card);
    }

    private void finishRound(Card p1Card, Card p2Card){
        round++;

        if(p1Card.attack > p2Card.defense && p2Card.attack > p1Card.defense){
            // draw
        } else if(p1Card.attack < p2Card.defense && p2Card.attack < p1Card.defense){
            // draw
        } else if(p1Card.attack > p2Card.defense && p2Card.attack < p1Card.defense){
            // p1 wins
            p1Score++;
            p1ScoreTitle.setText(getText(R.string.score) + ": " + p1Score);
        } else if(p1Card.attack < p2Card.defense && p2Card.attack > p1Card.defense){
            // p2 wins
            p2Score++;
            p2ScoreTitle.setText(getText(R.string.score) + ": " + p2Score);
        }

        if(round == 5) {
            // end game
            String result;
            if(p1Score == p2Score){
                result = getString(R.string.draw);
            } else if(p1Score > p2Score){
                result = getString(R.string.you_won);
            } else {
                result = getString(R.string.you_lost);
            }
            resetGame.setVisibility(View.VISIBLE);
        }
    }
}