import student.TestCase;

public class HandTest extends TestCase {

    Hand hand;
    
    public void setUp(){
        hand = new Hand(14, 4, 12, 2);
    }

    public void testGetString(){
        
        assertEquals(hand.getString(), "As + Qh");
    }
}
