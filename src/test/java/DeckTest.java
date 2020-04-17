import logic.Card;
import logic.Deck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DeckTest {

    @Test
    void createDeck() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    void drawCard() {
        Deck deck = new Deck();
        Card cardExp = new Card('S', 13);

        Card cardAct = deck.draw();

        assertEquals(cardExp.toString(), cardAct.toString());
    }

    @Test
    void discardCard() {
        Deck deck = new Deck();
        deck.discard(deck.draw());

        assertEquals(52, deck.size());
    }

    @Test
    void isDeckEmpty() {
        Deck deck = new Deck();
        assertFalse(deck.isEmpty());

        while (!deck.isEmpty()){
            deck.draw();
        }
        assertTrue(deck.isEmpty());
    }

    @Test
    void flipDeck() {
        Deck deck = new Deck();
        Card cardExp = deck.draw();
        deck.discard(cardExp);
        int size = deck.size()-1;
        for (int i = 0; i < size; i++){
            deck.discard(deck.draw());
        }
        Card cardAct = deck.draw();

        assertEquals(cardExp.toString(), cardAct.toString());
    }

    @Test
    void shuffleDeck(){
        Deck deck = new Deck();
        deck.shuffle();
        Card cardUnexp = new Card('S', 13);

        Card cardAct = deck.draw();

        assertNotEquals(cardUnexp, cardAct);
    }
}