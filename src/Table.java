import java.util.concurrent.ThreadLocalRandom;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Sets up the poker game blinds: 10, 20 2,000 chip stacks (100bb), reloaded
 * every hand maximum 4 bets each street
 * 
 * @author eagren20
 * @version 1.0 1/10/17
 */
public class Table {

    private Hand playerHand;
    private Hand mercHand;

    private Merc merc;

    private int playerBalance;
    private int mercBalance;
    private int playerNet;
    private int mercNet;
    private int pot;

    Scanner scanner;

    boolean fold = false;
    int street = 0;
    int bets = 0;
    int playerAction;
    int mercAction;
    String input;

    private Card[] cards;
    private Card[] board;

    public Table() {

        playerBalance = 2000;
        mercBalance = 2000;
        playerNet = 0;
        mercNet = 0;
        pot = 0;

        fold = false;
        street = 0;
        bets = 0;
        playerAction = -1;
        mercAction = -1;
        input = "N/A";

        scanner = new Scanner(new InputStreamReader(System.in));

        merc = new Merc();

        cards = new Card[9];
        board = new Card[5];
    }

    public static void main(String args[])
    {

        int numHands = Integer.parseInt(args[0]);

        Table table = new Table();
        for (int i = 0; i < numHands; i++) {
            table.playHand(i);
        }

    }

    public void playHand(int round)
    {

        this.dealHand();

        if (playPre(round)) {

            playPost(round);
        }

    }

    public boolean playPre(int round)
    {
        // merc is button
        if (round % 2 == 0) {

            // post blinds
            if (street == 0) {
                mercBalance -= 10;
                playerBalance -= 20;
                pot += 30;
            }

            System.out.println(getInfo());

            
        }

        // player is button
        else {

            // post blinds
            if (street == 0) {
                mercBalance -= 20;
                playerBalance -= 10;
                pot += 30;
            }

            System.out.println(getInfo());
            mercAction = merc.act(playerAction, street, false);

            switch (mercAction) {
            case 0:
                fold = true;
                System.out.println("merc folds");
                break;

            case 1:
                System.out.println("merc checks");
                break;
            case 2:
                System.out.println("merc calls");
                break;
            case 3:
                System.out.println("merc raises");
                break;
            default:
                System.out.println("error: invalid merc action");
                return false;
            }
        }

    }

    public boolean playPost(int round)
    {

        while (!fold) {

            System.out.println("Your hand: " + playerHand.getString());

            switch (street) {
            case 0:
                break;
            case 1:

                System.out.println("Flop: " + getFlop());
                break;
            case 2:
                System.out.println("Turn: " + getTurn());
                break;
            case 3:
                System.out.println("River: " + getRiver());
                break;
            }

            // merc is button
            if (round % 2 == 0) {

                // post blinds
                if (street == 0) {
                    mercBalance -= 10;
                    playerBalance -= 20;
                    pot += 30;
                }

                System.out.println(getInfo());

                // merc acts
                mercAction = merc.act(playerAction, street, true);

                switch (mercAction) {
                case 0:
                    fold = true;
                    System.out.println("merc folds");
                    break;

                case 1:
                    System.out.println("merc checks");
                    break;
                case 2:
                    System.out.println("merc calls");
                    break;
                case 3:
                    System.out.println("merc raises");
                    break;
                default:
                    System.out.println("error: invalid merc action");
                    return false;
                }

                // you act

                while (true) {
                    System.out.print("Please enter your action: ");
                    input = scanner.nextLine();

                    if (input.equals("fold") || input.equals("check")
                            || input.equals("call") || input.equals("raise")) {
                        break;

                    }
                    else {
                        System.out.println(
                                "Invalid input, please enter \"fold\", \"check\", "
                                        + "\"call\", or \"raise\"");
                    }
                }

                switch (input) {

                case "fold":
                    playerAction = 0;
                    break;
                case "check":
                    playerAction = 1;
                    break;
                case "call":
                    playerAction = 2;
                    break;
                case "raise":
                    playerAction = 3;
                    break;
                default:
                    System.out.println("Error: invalid player action");
                    return false;
                }

                // update pot/balances

            }

            // player is button
            else {

                // post blinds
                if (street == 0) {
                    mercBalance -= 20;
                    playerBalance -= 10;
                    pot += 30;
                }

                System.out.println(getInfo());
                mercAction = merc.act(playerAction, street, false);

                switch (mercAction) {
                case 0:
                    fold = true;
                    System.out.println("merc folds");
                    break;

                case 1:
                    System.out.println("merc checks");
                    break;
                case 2:
                    System.out.println("merc calls");
                    break;
                case 3:
                    System.out.println("merc raises");
                    break;
                default:
                    System.out.println("error: invalid merc action");
                    return false;
                }
            }
            street++;
        }
    }

    public boolean playPreMerc()
    {
     // merc acts
        mercAction = merc.act(playerAction, street, true);

        switch (mercAction) {
        case 0:
            fold = true;
            System.out.println("merc folds");
            break;

        case 1:
            System.out.println("merc checks");
            break;
        case 2:
            System.out.println("merc calls");
            break;
        case 3:
            System.out.println("merc raises");
            break;
        default:
            System.out.println("error: invalid merc action");
            return false;
        }

        // you act

        while (true) {
            System.out.print("Please enter your action: ");
            input = scanner.nextLine();

            if (input.equals("fold") || input.equals("check")
                    || input.equals("call") || input.equals("raise")) {
                break;

            }
            else {
                System.out.println(
                        "Invalid input, please enter \"fold\", \"check\", "
                                + "\"call\", or \"raise\"");
            }
        }

        switch (input) {

        case "fold":
            playerAction = 0;
            break;
        case "check":
            playerAction = 1;
            break;
        case "call":
            playerAction = 2;
            break;
        case "raise":
            playerAction = 3;
            break;
        default:
            System.out.println("Error: invalid player action");
            return false;
        }
        
        

        // update pot/balances

    }

    public boolean playPrePlayer()
    {

    }

    public boolean playPostMerc()
    {

    }

    public boolean playPostPlayer()
    {

    }

    /**
     * Deals random cards to the two player hands and board 2-14 are deuce-ace 1
     * = diamonds, 2 = hearts, 3 = clubs, 4 = spades
     */
    public void dealHand()
    {

        for (int i = 0; i < 9; i++) {

            int value = ThreadLocalRandom.current().nextInt(2, 15);
            int suit = ThreadLocalRandom.current().nextInt(1, 5);

            for (int j = i; j >= 0; j--) {

                Card temp = cards[j];
                if (value == temp.getValue() && suit == temp.getSuit()) {
                    i--;
                    j = -1;
                }
            }

            cards[i].setCard(value, suit);
        }

        for (int i = 0; i < 5; i++) {
            board[i] = cards[i + 4];
        }

        playerHand.setHand(cards[0], cards[1]);
        mercHand.setHand(cards[2], cards[3]);

    }

    public String getFlop()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString();
    }

    public String getTurn()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString() + " + " + cards[7].getString();
    }

    public String getRiver()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString() + " + " + cards[7].getString() + " + "
                + cards[8].getString();
    }

    /**
     * Outputs your hand, the values of both player balances, and the pot
     */
    public String getInfo()
    {
        return "Your stack: " + playerBalance + "\nMerc's stack: " + mercBalance
                + "\nPot: " + pot + "\n\nYour hand: " + playerHand.getString();
    }

}
