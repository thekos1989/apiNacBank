import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objectCurrency.Currency;


public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://www.nbrb.by/api/exrates/currencies/10";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET);
        Currency currency = response.getBody().as(Currency.class);
        System.out.println(response.getBody().asString());

    }
}
