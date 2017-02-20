
/**
 * Contains the board cards
 * 
 * @author eagren20
 */
public class Board {

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    
    public Board(){
        
    }
    
    public void setBoard(Card one, Card two, Card three, Card four, Card five){
        
        card1 = one;
        card2 = two;
        card3 = three;
        card4 = four;
        card5 = five;
    }
    
    public String getFlop(){
        
        return card1.getString() + " + " + card2.getString() + " + " + card3.getString();
    }
    
    public String getTurn(){
        
        return card1.getString() + " + " + card2.getString() + " + " + card3.getString()
        + " + " + card4.getString();   
    }
    
    public String getRiver(){
        
        return card1.getString() + " + " + card2.getString() + " + " + card3.getString()
        + " + " + card4.getString() + " + " + card5.getString();   
    }
    
    
}
