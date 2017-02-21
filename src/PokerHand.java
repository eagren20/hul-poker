
/**
 * Class representing a poker hand, i.e. a pair, two pair, trips, etc
 * 
 * @author eagren20
 * 
 */
public interface PokerHand<T> {

    /**
     * compares two PokerHands, returns an int depending on 
     * which is greater
     * 
     * 1 = caller beats compareTo
     * -1 = caller loses to compareTo
     * 0 = tie
     * 
     * @param compareTo the hand you are comparing
     * @return the corresponding int
     */
    public int compare(T compareTo);

    /**
     * Returns a string representation of the poker hand i.e. a pair of kings
     * with an ace kicker would return "Pair of Kings, Ace kicker"
     * 
     * @return the string representation
     */
    public String getString();

    /**
     * Returns the rank of a poker hand 0 = high card, 1 = pair, 2 = two pair, 3
     * = three of a kind, 4 = straight, 5 = flush, 6 = full house, 7 = 4 of a
     * kind, 9 = straight flush
     */
    public int getRank();   

}
