package com.company;

import java.util.ArrayList;


public class Hand {

    private ArrayList<Card> dealerHand = new ArrayList<Card>();
    private ArrayList<Card> playerHand = new ArrayList<Card>();

    public Hand(Card d1, Card d2, Card p1, Card p2) {
        this.dealerHand.add(d1);
        this.dealerHand.add(d2);

        this.playerHand.add(p1);
        this.playerHand.add(p2);
    }

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
}
