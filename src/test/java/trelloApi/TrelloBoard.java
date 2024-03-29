package trelloApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.Logger;
import java.util.Map;

public class TrelloBoard {

    private final String apiKey;
    private final String token;
    private String boardId;
    private String listId;
    private String firstCardId;
    private String secondCardId;

    public TrelloBoard(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
        this.boardId = null;
        this.listId = null;
        this.firstCardId = null;
        this.secondCardId = null;
    }

    public void createBoard(String boardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", boardName);

        Response response = sendPostRequest("https://api.trello.com/1/boards/",
                Map.of("name", boardName, "key", apiKey, "token", token));

        handleResponse("Board has been created!", response, expectedBody);

        boardId = response.jsonPath().getString("id");
        Logger.info("Board ID: " + boardId);
    }

    public void createList(String listName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", listName);

        Response response1 = sendPostRequest("https://api.trello.com/1/lists",
                Map.of("name", listName, "idBoard", boardId, "key", apiKey, "token", token));

        handleResponse("List has been created!", response1, expectedBody);

        listId = response1.jsonPath().getString("id");
        Logger.info("List ID: " + listId);
    }

    public void createLists(String[] listNames) {
        for (String listName : listNames) {
            createList(listName);
        }
    }

    public void createCard(String cardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", cardName);

        Response response1 = sendPostRequest("https://api.trello.com/1/cards",
                Map.of("name", cardName, "idList", listId, "key", apiKey, "token", token));

        handleResponse("Cart has been created!", response1, expectedBody);

        if (firstCardId == null) {
            firstCardId = response1.jsonPath().getString("id");

            Logger.info("First Card ID: " + firstCardId);
        } else {
            secondCardId = response1.jsonPath().getString("id");

            Logger.info("Second Card ID: " + secondCardId);
        }


    }

    public void createCards(String[] cardNames) {
        for (String cardName : cardNames) {
            createCard(cardName);
        }
    }

    public void updateCardName(String updatedCardName) {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", updatedCardName);

        Response response = sendPutRequest("https://api.trello.com/1/cards/" + firstCardId,
                Map.of("key", apiKey, "token", token),
                "{\"name\":\"" + updatedCardName + "\"}");

        handleResponse("Card updated!", response, expectedBody);
    }

    public void deleteAllCards() {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", null);

        Response response1 = sendDeleteRequest("https://api.trello.com/1/cards/" + firstCardId,
                Map.of("key", apiKey, "token", token));
        Response response2 = sendDeleteRequest("https://api.trello.com/1/cards/" + secondCardId,
                Map.of("key", apiKey, "token", token));

        handleResponse("First Card deleted!", response1, expectedBody);
        handleResponse("Second Card deleted!", response2, expectedBody);
    }

    public void deleteBoard() {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put("name", null);

        Response response = sendDeleteRequest("https://api.trello.com/1/boards/" + boardId,
                Map.of("key", apiKey, "token", token));

        handleResponse("Board deleted!", response, expectedBody);
    }

    private Response sendPostRequest(String uri, Map<String, ?> params) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                contentType(ContentType.JSON).
                post();
    }

    private Response sendPutRequest(String uri, Map<String, ?> params, String body) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                header("Content-Type", "application/json").body(body).
                put();
    }

    private Response sendDeleteRequest(String uri, Map<String, ?> params) {
        return RestAssured.
                given().
                when().
                baseUri(uri).
                queryParams(params).
                header("Content-Type", "application/json").
                delete();
    }

    private void handleResponse(String successMessage, Response response, JSONObject expected) {
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            Logger.assertWithLogging("Error! HTTP Status Code: " + statusCode);
        }
        checkResponseBody(response, expected);
        Logger.info(successMessage);
    }

    private void checkResponseBody(Response responses, JSONObject jsonObject) {
        JsonPath actualBody = responses.jsonPath();
        Logger.assertEquals(jsonObject.get("name"), actualBody.get("name"));
    }
}