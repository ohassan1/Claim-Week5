package com.company;


import java.util.*;

public class Game {

    public LinkedList<Card> deck = new LinkedList<Card>();
    public Queue<Card> playerDeck = new LinkedList<Card>();
    public Queue<Card> computerDeck = new LinkedList<Card>();

    String playerName;
    String computerName = "Tiffanie";

    public void game() throws InterruptedException {

        makeDeck();

        Scanner sc = new Scanner(System.in);

        System.out.print("What is your name?: ");
        playerName = sc.next();

        makePlayerDecks();

        do {

            System.out.println();

            Card playerCard = playerDeck.poll();
            Card computerCard = computerDeck.poll();

            System.out.println(playerName + ", Card Count: " + (playerDeck.size()+1) + ", Card: " + playerCard.getFace() + playerCard.getSuit());
            System.out.println(computerName + ", Card Count: " + (computerDeck.size()+1) + ", Card: " + computerCard.getFace() + computerCard.getSuit());


            if (cardValues(playerCard.getFace()) == cardValues(computerCard.getFace())) {

                if (iDeeClareWar()) {

                    playerDeck.add(playerCard);
                    playerDeck.add(computerCard);
                } else {

                    computerDeck.add(playerCard);
                    computerDeck.add(computerCard);
                }

            } else if (cardValues(playerCard.getFace()) > cardValues(computerCard.getFace())) {

                System.out.println(playerName + " wins!");

                playerDeck.add(playerCard);
                playerDeck.add(computerCard);
            } else {

                System.out.println(computerName + " wins!");

                computerDeck.add(playerCard);
                computerDeck.add(computerCard);
            }

            Thread.sleep(5000);

        } while (playerDeck.size() > 0 || computerDeck.size() > 0);
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

    public int cardValues(String card) {

        if (card.charAt(0) == '1') {
            return 10;
        } else if (card.charAt(0) == 'J') {
            return 11;
        } else if (card.charAt(0) == 'Q') {
            return 12;
        } else if (card.charAt(0) == 'K') {
            return 13;
        } else if (card.charAt(0) == 'A') {
            return 14;
        } else {
            return Integer.parseInt(Character.toString(card.charAt(0)));
        }
    }

    public void makePlayerDecks() {

        int i = 1;
        for (Card card : deck) {

            if (i%2 == 0) {
                playerDeck.add(new Card(card.getFace(), card.getSuit()));
            } else {
                computerDeck.add(new Card(card.getFace(), card.getSuit()));
            }
            i++;
        }

    }

    public boolean iDeeClareWar() throws InterruptedException {

        Card p1 = playerDeck.poll();
        Card p2 = playerDeck.poll();
        Card p3 = playerDeck.poll();
        Card pFaceUp = playerDeck.poll();
        Card c1 = computerDeck.poll();
        Card c2 = computerDeck.poll();
        Card c3 = computerDeck.poll();
        Card cFaceUp = computerDeck.poll();

        System.out.println("I");
        Thread.sleep(2000);
        System.out.println("Dee");
        Thread.sleep(2000);
        System.out.println("Clare");
        Thread.sleep(2000);
        System.out.println("WAR!");
        Thread.sleep(2000);
        System.out.println();

        System.out.println(playerName + " your card is: " + pFaceUp.getFace() + pFaceUp.getSuit());
        System.out.println(computerName + "'s card is: " +cFaceUp.getFace() + cFaceUp.getSuit());


        if (cardValues(pFaceUp.getFace()) > cardValues(cFaceUp.getFace())) {

            System.out.println(playerName + " wins!");

            playerDeck.add(p1);
            playerDeck.add(p2);
            playerDeck.add(p3);
            playerDeck.add(pFaceUp);
            playerDeck.add(c1);
            playerDeck.add(c2);
            playerDeck.add(c3);
            playerDeck.add(cFaceUp);

            return true;

        } else if (cardValues(pFaceUp.getFace()) < cardValues(cFaceUp.getFace())) {

            System.out.println(computerName + " wins!");

            computerDeck.add(p1);
            computerDeck.add(p2);
            computerDeck.add(p3);
            computerDeck.add(pFaceUp);
            computerDeck.add(c1);
            computerDeck.add(c2);
            computerDeck.add(c3);
            computerDeck.add(cFaceUp);

            return false;

        } else if (cardValues(p1.getFace()) > cardValues(c1.getFace())) {

            System.out.println("A TIE! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p1.getFace() + p1.getSuit());
            System.out.println(computerName + "'s card is: " + c1.getFace() + c1.getSuit());
            System.out.println();
            System.out.println(playerName + " wins!");

            playerDeck.add(p1);
            playerDeck.add(p2);
            playerDeck.add(p3);
            playerDeck.add(pFaceUp);
            playerDeck.add(c1);
            playerDeck.add(c2);
            playerDeck.add(c3);
            playerDeck.add(cFaceUp);

            return true;

        } else if (cardValues(p1.getFace()) < cardValues(c1.getFace())) {

            System.out.println("A TIE! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p1.getFace() + p1.getSuit());
            System.out.println(computerName + "'s card is: " + c1.getFace() + c1.getSuit());
            System.out.println();
            System.out.println(computerName + " wins!");

            computerDeck.add(p1);
            computerDeck.add(p2);
            computerDeck.add(p3);
            computerDeck.add(pFaceUp);
            computerDeck.add(c1);
            computerDeck.add(c2);
            computerDeck.add(c3);
            computerDeck.add(cFaceUp);

            return false;

        } else if (cardValues(p2.getFace()) > cardValues(c2.getFace())) {

            System.out.println("A TIE again! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p2.getFace() + p2.getSuit());
            System.out.println(computerName + "'s card is: " + c2.getFace() + c2.getSuit());
            System.out.println();
            System.out.println(playerName + " wins!");

            playerDeck.add(p1);
            playerDeck.add(p2);
            playerDeck.add(p3);
            playerDeck.add(pFaceUp);
            playerDeck.add(c1);
            playerDeck.add(c2);
            playerDeck.add(c3);
            playerDeck.add(cFaceUp);

            return true;

        } else if (cardValues(p2.getFace()) < cardValues(c2.getFace())) {

            System.out.println("A TIE again! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p2.getFace() + p2.getSuit());
            System.out.println(computerName + "'s card is: " + c2.getFace() + c2.getSuit());
            System.out.println();
            System.out.println(computerName + " wins!");

            computerDeck.add(p1);
            computerDeck.add(p2);
            computerDeck.add(p3);
            computerDeck.add(pFaceUp);
            computerDeck.add(c1);
            computerDeck.add(c2);
            computerDeck.add(c3);
            computerDeck.add(cFaceUp);

            return false;

        } else if (cardValues(p3.getFace()) > cardValues(c3.getFace())) {

            System.out.println("A TIE again! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p3.getFace() + p3.getSuit());
            System.out.println(computerName + "'s card is: " + c3.getFace() + c3.getSuit());
            System.out.println();
            System.out.println(playerName + " wins!");

            playerDeck.add(p1);
            playerDeck.add(p2);
            playerDeck.add(p3);
            playerDeck.add(pFaceUp);
            playerDeck.add(c1);
            playerDeck.add(c2);
            playerDeck.add(c3);
            playerDeck.add(cFaceUp);

            return true;

        } else if (cardValues(p3.getFace()) < cardValues(c3.getFace())) {

            System.out.println("A TIE again! lets see those next cards.");

            System.out.println(playerName + " your card is: " + p3.getFace() + p3.getSuit());
            System.out.println(computerName + "'s card is: " + c3.getFace() + c3.getSuit());
            System.out.println();
            System.out.println(computerName + " wins!");

            computerDeck.add(p1);
            computerDeck.add(p2);
            computerDeck.add(p3);
            computerDeck.add(pFaceUp);
            computerDeck.add(c1);
            computerDeck.add(c2);
            computerDeck.add(c3);
            computerDeck.add(cFaceUp);

            return false;

        } else {
            if (iDeeClareWar()) {
                return true;
            } else {
                return false;
            }
        }

    }

}
