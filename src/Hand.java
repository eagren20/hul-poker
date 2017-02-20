

/**
 * Contains a player's hole cards
 * 
 * @author eagren20
 *
 */
public class Hand {

    private Card card1;
    private Card card2;
    
    public Hand(){
        
    }
    
    public Hand(int a, int b, int c, int d){
        card1 = new Card(a, b);
        card2 = new Card(c, d);
    }
    
    public void setHand(Card first, Card second){
        
        card1 = first;
        card2 = second;
    }
    /**
     *Returns the string representation of the hand
     */
    public String getString(){
        
        return card1.getString() + " + " + card2.getString();
    }
}
