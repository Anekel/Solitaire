package logic;

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
    }

    @Test
    void discardCard() {
    }

    @Test
    void isDeckEmpty() {
    }
}