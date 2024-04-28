package _01_WorkingWithAbstraction.Exercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cardName = scanner.nextLine();
        String cardSuit = scanner.nextLine();
        Card card = new Card(cardName,cardSuit,
                (CardRank.valueOf(cardName).getRankPower() +CardSuit.valueOf(cardSuit).getPower()));
        System.out.println(card);

    }
}
