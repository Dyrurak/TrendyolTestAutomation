package test;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.glassfish.gmbal.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import response.Book;
import response.Error;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class ApiTest extends ApiTestCase {

    @Description("Verify that the API starts with an empty store")
    @Test
    public void checkBookListIsEmpty(){
        Response response = given()
                .when()
                .get(END_POINT);

        checkInternalServerError(response);

        List<Book> booksList = response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getList("", Book.class);
        assertEquals(booksList.size(), 0, "There is at least one book on the server!");
    }

    @Description("Verify that author is required field")
    @Parameters({"title"})
    @Test
    public void checkAuthorIsRequired(String title){
        Response response = given()
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Error error = response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(error.getError(), "Field 'Author' is required", "Error message for Author field is wrong!");
    }

    @Description("Verify that title is required field")
    @Parameters({"author"})
    @Test
    public void checkTitleIsRequired(String author){
        Response response = given()
                .params("Author", author)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Error error = response
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(error.getError(), "Field 'Title' is required", "Error message for Title field is wrong!");
    }

    @Description("Verify that author can not be empty")
    @Parameters({"title"})
    @Test
    public void checkAuthorCanNotBeEmpty(String title){
        Response response = given()
                .params("Author", "")
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Error error = response
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(error.getError(), "Field 'Author' cannot be empty", "Error message for empty author is wrong!");
    }

    @Description("Verify that title can not be empty")
    @Parameters({"author"})
    @Test
    public void checkTitleCanNotBeEmpty(String author){

        Response response = given()
                .param("Author", author)
                .param("Title", "")
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Error error = response
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(error.getError(), "Field 'Title' cannot be empty", "Error message for empty title is wrong!");
    }

    @Description("Verify that the id field is read−only")
    @Parameters({"1", "author", "title"})
    @Test
    public void checkIdCanNotBeAdded(int id, String author, String title){

        Response response = given()
                .params("Id", id)
                .params("Author", author)
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Error error = response
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(error.getError(), "The id field is read-only", "Error message for being read-only of id field is wrong!");

    }

    @Description("Verify that you can create a new book via PUT")
    @Parameters({"author", "title"})
    @Test
    public void checkBookCanBeAddedSuccessfully(String author, String title){
        Response response = given()
                .params("Author", author)
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Book responseAddingBook = response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Book.class);

        assertEquals(responseAddingBook.getTitle(), title);
        assertEquals(responseAddingBook.getAuthor(), author);
        assertTrue(responseAddingBook.getId() > 0);

        Response responseGet = given()
                .params("Id", responseAddingBook.getId())
                .when()
                .get(END_POINT);

        checkInternalServerError(responseGet);

        Book responseAddedBook = responseGet
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Book.class);

        assertEquals(responseAddedBook.getTitle(), responseAddingBook.getTitle());
        assertEquals(responseAddedBook.getAuthor(), responseAddingBook.getAuthor());
        assertEquals(responseAddedBook.getId(), responseAddingBook.getId());
    }

    @Description("Verify that you cannot create a duplicate book")
    @Parameters({"author", "title"})
    @Test
    public void checkBookWithSameAuthorAndTitleCanNotBeAdded(String author, String title){
        Response response = given()
                .params("Author", author)
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(response);

        Book responseAddingBook =response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Book.class);

        assertEquals(responseAddingBook.getTitle(), title);
        assertEquals(responseAddingBook.getAuthor(), author);
        assertTrue(responseAddingBook.getId() > 0);

        Response responseAddingBookAgain = given()
                .params("Author", author)
                .params("Title", title)
                .when()
                .put(END_POINT);

        checkInternalServerError(responseAddingBookAgain);

        Error responseError = responseAddingBookAgain
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(Error.class);

        assertEquals(responseError.getError(), "Another book with similar title and author already exists", "Duplicate book error is wrong");
    }
}