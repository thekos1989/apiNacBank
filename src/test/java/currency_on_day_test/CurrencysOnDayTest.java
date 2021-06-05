package currency_on_day_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objectCurrency.Currency;
import objectCurrency.CurrencyOll;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CurrencysOnDayTest {
    @DataProvider(name = "data-provider1")
    public Object[][] dpMethod1() {
        return new Object[][]{
                {298, "2021-6-4", 3.4644},
                {298, "2021-6-3", 3.4547},
                {298, "2021-6-2", 3.4518},
                {145, "2021-6-4", 2.5374},
                {292, "2021-6-4", 3.0927}};
    }

    @Test(dataProvider = "data-provider1")
    public void currencysOnDayTest(int id, String ondate, double curRate) {
        try (InputStream input = new FileInputStream("D:\\apiNacBank\\src\\main\\resources\\url.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("base.url");
            RestAssured.baseURI = "https://www.nbrb.by/api/exrates/rates/" + id;
            RequestSpecification httpReguest = RestAssured.given().queryParam("ondate", ondate);
            Response response = httpReguest.request(Method.GET);
            CurrencyOll currency = response.getBody().as(CurrencyOll.class);
            double curOfficialRate = currency.cur_OfficialRate;
            Assert.assertEquals(curOfficialRate, curRate, "Currency rate is not correct");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
