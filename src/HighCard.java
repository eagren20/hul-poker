/**
 * An object representing a high card hand
 * @author eagren20
 *
 */
public class HighCard implements PokerHand<HighCard> {

    /**
     * the high cards of this hand
     * [0] = highest card, [1] = second highest card, etc
     */
    private int[] highestCards;
    
    public HighCard() {
        highestCards = new int[5];
        for (int i = 0; i < 5; i++){
            highestCards[i] = -1;
        }
    }
    @Override
    public int compare(HighCard compareTo){
        for (int i = 0; i < 5; i++){
            if (this.highestCards[i] > compareTo.getCards()[i]){
                return 1;
            }
            else if (this.highestCards[i] < compareTo.getCards()[i]){
                return -1;
            }
        }
        //tie
        return 0;
    }
    
    public String getString(){
        switch (highestCards[0]) {
        case 2:
            return "Two High";
        case 3:
            return "Three High";
        case 4:
            return "Four High";
        case 5:
            return "Five High";
        case 6:
            return "Six High";
        case 7:
            return "Seven High";
        case 8:
            return "Eight High";
        case 9:
            return "Nine High";
        case 10:
            return "Ten High";
        case 11:
            return "Jack High";
        case 12:
            return "Queen High";
        case 13:
            return "King High";
        case 14:
            return "Ace High";
        default:
            System.out.println("invalid high card");
            return null;

        }
    }
    /**
     * Returns the rank of High Card, 0
     */
    public int getRank(){
        return 0;
    }
    
    public int[] getCards(){
        return highestCards;
    }

}
