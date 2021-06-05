package currency_on_day_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objectCurrency.Currency;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrencyOnDayTest {
    @Test
    public void currensyOnDayTest(){
        RestAssured.baseURI="https://www.nbrb.by/api/exrates/rates/298";
        RequestSpecification httpReguest = RestAssured.given().queryParam("ondate","2016-7-5");
        Response response = httpReguest.request(Method.GET);
        Currency currency = response.getBody().as(Currency.class);
        /*System.out.println(response.getBody().asString());*/
        Assert.assertEquals(3.1318, currency.cur_OfficialRate, "Currency rate is not correct");
    }
}
