package com.company;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public ArrayList<Card> deck = new ArrayList<Card>();
    public String[] dealerHand;
    public String[] playerHand;

    public int dealerScore;
    public int playerScore;

    public void getNames() {

        ArrayList<String> playerNames = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);

        Players dealer = new Players();

        char rerun = ' ';

        boolean playerBust = false;
        boolean dealerBust = false;




        System.out.print("What is your name? ");
        playerNames.add(sc.next());
        System.out.println();

        makeDeck();
        dealHand();

        do {
            dealerScore = 0;
            playerScore = 0;

            dealerScore = (cardValues(dealerHand[0]) + cardValues(dealerHand[1]));
            playerScore = (cardValues(playerHand[0]) + cardValues(playerHand[1]));


            System.out.print(playerNames.get(0) + ": " + (playerScore));


            System.out.print(", Would you like another card? Y or N: ");

            char hitMe = sc.next().toLowerCase().charAt(0);

            while (hitMe == 'y') {
                playerScore = playerScore + hitMe();

                System.out.print(playerNames.get(0) + ": " + (playerScore));

                if (playerScore > 21) {
                    System.out.println(", BUST!");
                    playerBust = true;
                    break;
                }

                System.out.print(", Would you like another card? Y or N: ");
                hitMe = sc.next().toLowerCase().charAt(0);
                System.out.println();
            }

            if (!playerBust) {

                while (dealerScore < 17 && dealerScore < playerScore) {

                    System.out.println(dealer.getDealerName() + ": " + dealerScore);

                    dealerScore = dealerScore + dealerHitMe();

                    if (dealerScore > 21) {
                        System.out.println("Dealer BUST!");
                        dealerBust = true;
                        break;
                    }
                }

                if (dealerScore == playerScore) {
                    System.out.println("Split the pot!");
                }
            }

            if ((playerScore > dealerScore && !playerBust) || dealerBust ) {

                if (!dealerBust) { System.out.println("Dealer FOLD!"); }

                System.out.println(playerNames.get(0) + " you win! Bet you wont win again though? Yes, or No");
                rerun = sc.next().charAt(0);
            } else {
                System.out.println(playerNames.get(0) + " you lose! Bet you wont win again though? Yes, or No");
                rerun = sc.next().charAt(0);
            }



        } while (rerun == 'y');

    }

    public void makeDeck() {

        for (int s = 1; s <= 4; s++) {
            for (int f = 2; f <= 14; f++) {

                Card card = new Card(numToFace(f), numToSuit(s));

                deck.add(card);
            }
        }


        Collections.shuffle(deck);
    }

    private String numToSuit (int num) {

        if (num == 1) { return "Diamonds"; }
        else if (num == 2) { return "Spades"; }
        else if (num == 3) { return"Clubs"; }
        return "Hearts";
    }

    private String numToFace(int num) {

        if (num <= 10) { return Integer.toString(num) + "-"; }
        else if (num == 11){ return "J-"; }
        else if (num == 12){ return "Q-"; }
        else if (num == 13){ return "K-"; }
        return "A-";
    }

    public void dealHand() {

        dealerHand = new String[]{ deck.get(0).getFace() + deck.get(0).getSuit(), deck.get(1).getFace() + " of " + deck.get(1).getSuit() };
        playerHand = new String[]{ deck.get(2).getFace() + deck.get(2).getSuit(), deck.get(3).getFace() + " of " + deck.get(3).getSuit() };

        deck.remove(0);
        deck.remove(1);
        deck.remove(2);
        deck.remove(3);
    }

    public int hitMe() {

        System.out.println("Your card is a " + deck.get(0).getFace() + deck.get(0).getSuit());

        int cardVal = cardValues(deck.get(0).getFace());

        deck.remove(0);

        return cardVal;


    }

    public int dealerHitMe() {

        System.out.println("Dealer draws a " + deck.get(0).getFace() + deck.get(0).getSuit());

        int cardVal = cardValues(deck.get(0).getFace());

        deck.remove(0);

        return cardVal;
    }

    public int cardValues(String card) {

        if (card.charAt(0) == '1') {
            return 10;
        } else if (card.charAt(0) == 'J') {
            return 10;
        } else if (card.charAt(0) == 'Q') {
            return 10;
        } else if (card.charAt(0) == 'K') {
            return 10;
        } else if (card.charAt(0) == 'A') {

            Scanner sc = new Scanner(System.in);

            if ((playerScore + 11 > 21) || (dealerScore + 11 > 21)) {
                return 1;
            }

            System.out.println("Press 1 if you want your ace to count for '1', or 2 for '11'? ");
            if (sc.nextInt() == 1) { return 1; }
            else { return 11; }


        } else {
            return Integer.parseInt(Character.toString(card.charAt(0)));
        }
    }

}