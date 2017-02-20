import student.TestCase;


/**
 * @author eagren20
 *
 */
public class CardTest extends TestCase {
    
    Card card;
    Card card2;
    
    public void setUp()
    {
        card = new Card(14, 4);
        card2 = new Card(12, 2);
    }
    
    public void testGetString(){
        
        assertEquals(card.getString(), "As");
        assertEquals(card2.getString(), "Qh");
    }

}
