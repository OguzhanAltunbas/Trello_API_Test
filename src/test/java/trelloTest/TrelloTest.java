package trelloTest;

import org.junit.Test;


public class TrelloTest extends TrelloConfig {

    @Test
    public void trelloTest() {
        trelloBoard.createBoard("New_Pano");

        trelloBoard.createLists(new String[]{"New_List1", "New_List2"});

        trelloBoard.createCards(new String[]{"New_Card1", "New_Card2"});

        trelloBoard.updateCardName("Updated_Card");

        trelloBoard.deleteAllCards();

        trelloBoard.deleteBoard();
    }

}