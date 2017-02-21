
/**
 * Contains a player's hole cards
 * 
 * @author eagren20
 *
 */
public class Hand {

    private Card card1;
    private Card card2;
    

    /**
     * Constructor for the Hand class
     */
    public Hand() {

    }

    /**
     * Alternate constructor for creating a specific hand
     * 
     * @param a
     *            the first card's value
     * @param b
     *            the first card's suit
     * @param c
     *            the second card's value
     * @param d
     *            the second card's suit
     */
    public Hand(int a, int b, int c, int d) {
        card1 = new Card(a, b);
        card2 = new Card(c, d);
    }

    /**
     * Sets the contents of the Hand to two specified Card objects
     * 
     * @param first
     *            first card
     * @param second
     *            second card
     */
    public void setHand(Card first, Card second)
    {

        card1 = first;
        card2 = second;
    }

    /**
     * Returns the string representation of the hand
     * 
     * @return the Hand's string
     */
    public String getString()
    {

        return card1.getString() + " + " + card2.getString();
    }
}
