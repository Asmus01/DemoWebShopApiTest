import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.type;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest {

    String body = "addtocart_43.EnteredQuantity=5",
    cookieValue = "D6035E4DFAD9DD4255AD89BEBEE4B89BB84E1C3EBAB38DBCB80CC" +
            "F1F0C661B7E03B56AFA33620846459B2FBAD403AD40E4820FB01211083E3BF" +
            "4B86784AA2E728FBAD3675E171B0376CFAE4A32C16B84ADB97FC5740DC99367" +
            "E081D4D5CD44E9AB4A4DB0F35F7F1579A8AA946912011D448B7A2B24F97B7BBDEF" +
            "D37C83DC6DD035EF98D3DB65EC09530E7E0720C83A14"  ;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";

    }

    @Test
    void apiTestOfDemoWebShop() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(body)
                .when()
                .post("/addproducttocart/details/43/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));


    }

    @Test
    void apiAnonimTestOfDemoWebShop() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(body)
                .when()
                .post("/addproducttocart/details/43/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(5)"));


    }
}
