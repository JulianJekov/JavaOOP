package _01_WorkingWithAbstraction.Exercise.CardsWithPower;

public class Card {
    private String cardRank;
    private String cardSuit;
    private int cardPower;

    public Card(String cardRank, String cardSuit, int cardPower) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
        this.cardPower = cardPower;
    }

    public String getCardRank() {
        return cardRank;
    }


    public String getCardSuit() {
        return cardSuit;
    }


    public int getCardPower() {
        return cardPower;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d%n",getCardRank(),getCardSuit(),getCardPower());
    }
}
