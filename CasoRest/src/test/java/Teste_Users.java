import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringWriter;

import static org.hamcrest.Matchers.*;

public class Teste_Users {

    public static RequestSpecification requisicaoBase;

    @BeforeClass
    public static void prepararVariaveis(){
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.log(LogDetail.ALL);
        requisicaoBase = reqBuilder.build();

        RestAssured.requestSpecification = requisicaoBase;
    }

    @Test
    public void carregarTodosUsuarios(){
        RestAssured.
            given().
                log().all().
            when()
                .get("/users")
            .then()
                .statusCode(200)
                .body("",hasSize(10))
                .body("id", hasItems(1,2,3,4,5,6,7,8,9,10));
    }

    @Test
    public void valiarJsonSchemaUsuarios(){
        RestAssured.
            given().
                log().all().
            when()
                .get("/users")
            .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
    }

    @Test
    public void carregarUsuario1(){
        RestAssured.
            given().
            when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Leanne Graham"))
                .body("address.street", is("Kulas Light"));
    }

    private JSONObject criaNovoUsuarioJson(int idUsuario){
        JSONObject corpoRequisicao = new JSONObject();
        JSONObject endereco = new JSONObject();

        endereco.put("street", "Rua exemplo");

        corpoRequisicao.put("id", idUsuario);
        corpoRequisicao.put("name", "João Exemplo");
        corpoRequisicao.put("address", endereco);

        return corpoRequisicao;
    }
    @Test
    public void criarNovoUsuario(){
        JSONObject corpoRequisicao = criaNovoUsuarioJson(11);

        System.out.println(corpoRequisicao.toString());

        RestAssured.
            given()
                .contentType(ContentType.JSON)
                .body(corpoRequisicao.toString())
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .body("id", is(11))
                .body("name", is("João Exemplo"))
                .body("address.street", is("Rua exemplo"))
                .log().all();
    }

    @Test
    public void criarNovoUsuarioValidacaoCompleta(){
        int IdUsuario = 11;

        JSONObject corpoRequisicao = criaNovoUsuarioJson(IdUsuario);

        ResponseSpecBuilder respBuilder = new ResponseSpecBuilder();
        respBuilder.expectBody("id", is(IdUsuario));
        respBuilder.expectBody("name", is("João Exemplo"));
        respBuilder.expectBody("address.street", is("Rua exemplo"));
        ResponseSpecification validacoesNovoUsuario = respBuilder.build();


        System.out.println(corpoRequisicao.toString());

        RestAssured.
            given()
                .contentType(ContentType.JSON)
                .body(corpoRequisicao.toString())
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .spec(validacoesNovoUsuario)
                .log().all();

        RestAssured.
                given()
                    .pathParam("idUsuario", IdUsuario)
                .when()
                    .get("/user/{idUsuario}")
                .then()
                .statusCode(200)
                .spec(validacoesNovoUsuario);
    }

    @Test
    public void alterarUsuario4(){
        JSONObject corpoRequisicao = criaNovoUsuarioJson(4);

        System.out.println(corpoRequisicao.toString());

        RestAssured.
            given()
                .contentType(ContentType.JSON)
                .body(corpoRequisicao.toString())
            .when()
                .put("/users/4")
            .then()
                .statusCode(200)
                .body("id", is(4))
                .body("name", is("João Exemplo"))
                .body("address.street", is("Rua exemplo"))
                .log().all();
    }

    @Test
    public void deleteUsuario(){
        RestAssured.
            given()
            .when()
                .delete("/users/4")
            .then()
                .statusCode(200)
                .log().all();
    }

}

