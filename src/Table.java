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

    // the size of a bet in this format
    private static final int bet = 20;
    // the size of the big bet
    private static final int bigBet = 40;
    Scanner scanner;

    boolean fold = false;
    int street = 0;
    int bets = 0;
    int playerAction;
    int mercAction;
    String input;

    private Card[] cards;
    private Card[] board;

    /**
     * Constructor for the table class
     */
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

    /**
     * Takes the number of hands to be played as input and begins the game
     * 
     * @param args the command line input
     */
    public static void main(String args[])
    {

        int numHands = Integer.parseInt(args[0]);

        Table table = new Table();
        for (int i = 0; i < numHands; i++) {
            table.playHand(i);
        }

    }

    /**
     * Deals and plays one hand
     * 
     * @param round an int corresponding to the current hand number i.e. the
     *            51st hand would be 51
     */
    public void playHand(int round)
    {

        fold = false;

        this.dealHand();

        // merc is on button
        if (round % 2 == 0) {
            System.out.println("Merc is on the button");

            if (playPreMerc(0)) {

                playPostMerc(0);
            }
        }
        else {
            System.out.println("You are on the button");
            if (playPrePlayer(0)) {
                playPostPlayer(0);
            }
        }

        // update balances/net

        // reset merc's range
        merc.reset();

    }

    /**
     * Preflop action when merc is on the button
     * 
     * @return true if hand continues, false if someone folded
     * @param bets the number of bets this stree (max = 4)
     */
    public boolean playPreMerc(int bets)
    {
        street = 0;
        playerBalance -= bet;
        pot += bet;

        // merc acts
        mercAction = merc.act(playerAction, street, true);
        this.processMercAction(street);
        if (mercAction == 0) {
            if (bets == 0) {
                mercBalance -= bet / 2;
            }
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

        this.processPlayerAction(input, street);

        // update pot/balances

        return true;
    }

    /**
     * Preflop action when player is on the button
     * 
     * @return true if hand continues, false if someone folded
     * @param bets the number of bets this street (max is 4)
     */
    public boolean playPrePlayer(int bets)
    {
        street = 0;
        // TODO if
        return false;
    }

    /**
     * Postflop action if merc is on the button
     * 
     * @return true if hand continues, false if someone folded
     * @param bets the number of bets this street (max is 4)
     */
    public boolean playPostMerc(int bets)
    {
        // TODO implement
        return false;
    }

    /**
     * Postflop action if player is on the button
     * 
     * @return true if hand continues, false if someone folded
     * @param bets the number of bets this street (max is 4)
     */
    public boolean playPostPlayer(int bets)
    {
        // TODO implement
        return false;
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

    /**
     * Returns a string containing the flop
     * 
     * @return the flop
     */
    public String getFlop()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString();
    }

    /**
     * Returns a string containing the flop + turn
     * 
     * @return the flop + turn
     */
    public String getTurn()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString() + " + " + cards[7].getString();
    }

    /**
     * Returns a string containing the flop + turn + river
     * 
     * @return the flop + turn + river
     */
    public String getRiver()
    {

        return cards[4].getString() + " + " + cards[5].getString() + " + "
                + cards[6].getString() + " + " + cards[7].getString() + " + "
                + cards[8].getString();
    }

    /**
     * 
     * @param street the street of betting
     * @return the board fields corresponding Card objects
     */
    public Card[] getBoard(int street)
    {
        Card[] temp;
        // returns the flop
        if (street == 1) {
            temp = new Card[3];
            for (int i = 0; i < 3; i++) {
                temp[i] = board[i];
            }
        }
        // returns the flop+turn
        else if (street == 2) {
            temp = new Card[4];
            for (int i = 0; i < 4; i++) {
                temp[i] = board[i];
            }
        }
        // returns the flop+turn+river
        else if (street == 3) {
            temp = new Card[5];
            for (int i = 0; i < 5; i++) {
                temp[i] = board[i];
            }
        }
        System.out.println("INVALID STREET: returning null");
        return null;

    }

    /**
     * Returns the player hand, the values of both player balances, and the pot
     * 
     * @return the current info
     */
    public String getInfo()
    {
        return "Your stack: " + playerBalance + "\nMerc's stack: " + mercBalance
                + "\nPot: " + pot + "\n\nYour hand: " + playerHand.getString();
    }

    /**
     * Processes the player action, i.e. sets the playerAction field, updates
     * balances, and calls appropriate methods
     * 
     * @param action the most recent player action
     * @param street the street of betting
     */
    public void processPlayerAction(String action, int street)
    {
        switch (action) {

        case "fold":
            playerAction = 0;
            break;
        case "check":
            playerAction = 1;
            break;
        case "call":
            playerAction = 2;
            if (street <= 1) {
                playerBalance -= bet;
                pot += bet;
            }
            else {
                playerBalance -= bigBet;
                pot += bigBet;
            }
            break;
        case "raise":
            playerAction = 3;
            if (street <= 1) {
                playerBalance -= bet * 2;
                pot += bet * 2;
            }
            else {
                playerBalance -= bigBet * 2;
                pot += bigBet * 2;
            }
            // merc has another chance to act now
            playPreMerc(1);
            break;
        default:
            System.out.println("Error: invalid player action");
        }
    }

    /**
     * Processes the most recent merc action, i.e. outputs that action and
     * updates balances
     * 
     * @param street the street of betting
     */
    public void processMercAction(int street)
    {

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
            if (street <= 1) {
                mercBalance -= bet;
                pot += bet;
            }
            else {
                mercBalance -= bigBet;
                pot += bigBet;
            }
            break;
        case 3:
            System.out.println("merc raises");
            if (street <= 1) {
                mercBalance -= bet * 2;
                pot += bet * 2;
            }
            else {
                mercBalance -= bigBet * 2;
                pot += bigBet * 2;
            }

        default:
            System.out.println("error: invalid merc action");
        }
    }

}
