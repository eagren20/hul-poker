/**
 * Plays heads-up NL hold'em
 * 
 * @author eagren20
 *
 */
public class Merc {
    // the range of all of merc's possible hands
    private Card[] range;
    
    public Merc(){
        range = new Card[52];
    }
    
    
    /**
     * -1 = N/A, 0 = fold, 1 = check, 2 = call, 3 = raise
     * 0 = preflop, 2 = flop, 3 = turn, 4 = river
     * @return int the preflop action
     */
    public int act(int prevAction, int street, boolean button){
        return -1;
    }
}
