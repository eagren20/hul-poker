
/**
 * Represents one playing card; suit and value are represented using integers
 * 
 * @author eagren20
 *
 */

public class Card {

    // 2-14 are deuce-ace
    private int value;
    // 1 = diamonds, 2 = hearts, 3 = clubs, 4 = spades
    private int suit;
    
    public Card(){
        value = 0;
        suit = 0;
    }
    
    public Card(int x, int y){
        
        value = x;
        suit = y;
    }
   
    public void setCard(int newVal, int newSuit){
        value = newVal;
        suit = newSuit;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public int getSuit(){
        return this.suit;
    }
    
    public String getString(){
        
        String cardString = "";
        
        switch (value) {
        case 2 : 
            cardString = "2";
            break;
            
        case 3 : 
            cardString = "3";
            break;
            
        case 4 : 
            cardString = "4";
            break;
            
        case 5 : 
            cardString = "5";
            break;
            
        case 6 : 
            cardString = "6";
            break;
            
        case 7 : 
            cardString = "7";
            break;
            
        case 8 : 
            cardString = "8";
            break;
            
        case 9 : 
            cardString = "9";
            break;
            
        case 10 : 
            cardString = "T";
            break;
            
        case 11 : 
            cardString = "J";
            break;
            
        case 12 : 
            cardString = "Q";
            break;
             
        case 13 : 
            cardString = "K";
            break;
            
        case 14 : 
            cardString = "A";
            break;
            
        default : 
            cardString = "invalid";
            
        }
        
        switch (suit) {
        
        case 1 : 
            cardString += "d";
            break;
            
        case 2 : 
            cardString += "h";
            break;
             
        case 3 : 
            cardString += "c";
            break;
            
        case 4 : 
            cardString += "s";
            break;
            
        default : 
            cardString += "invalid";
        }
        
        return cardString;
            
            
            
            
            
            
            
       
    }
}
