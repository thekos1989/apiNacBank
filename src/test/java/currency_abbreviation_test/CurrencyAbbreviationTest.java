package currency_abbreviation_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objectCurrency.Currency;
import objectCurrency.CurrencyOll;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CurrencyAbbreviationTest {

    @DataProvider(name = "data-provider")
    public Object[][]dpMethod(){
        return new Object[][]{
                {298,"RUB"},
                {1,"ALL"},
                {145,"USD"}};
    }

        @Test(dataProvider = "data-provider")
        public void currencyAbbreviationTest ( int id, String cur_Abbreviation){
            RestAssured.baseURI = "https://www.nbrb.by/api/exrates/currencies/" + id;
            RequestSpecification httpReguest = RestAssured.given();
            Response response = httpReguest.request(Method.GET);
            CurrencyOll currencyOll = response.getBody().as(CurrencyOll.class);
            String resultAbbreviation = currencyOll.cur_Abbreviation;
            Assert.assertEquals(resultAbbreviation, cur_Abbreviation, "Currency rate is not correct");
        }
    }
