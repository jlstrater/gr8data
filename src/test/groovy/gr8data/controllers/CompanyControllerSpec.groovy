package gr8data.controllers

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.is
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration

import com.jayway.restassured.builder.RequestSpecBuilder
import com.jayway.restassured.specification.RequestSpecification

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

import org.springframework.restdocs.ManualRestDocumentation

import spock.lang.Specification

@Integration
@Rollback
class CompanyControllerSpec extends Specification {

    final ManualRestDocumentation restDocumentation = new ManualRestDocumentation('src/docs/generated-snippets')

    private RequestSpecification documentationSpec

    void setup() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build()
        this.restDocumentation.beforeTest(getClass(), specificationContext.currentSpec.name)
    }

    void cleanup() {
        this.restDocumentation.afterTest()
    }

    void 'companies list with bootstrapped data'() {

        when:
        def results = given(this.documentationSpec)
                .accept('application/json')
                .filter(document('companies-list-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('[].id').description('The id for the company'),
                        fieldWithPath('[].name').description("The company's name"),
                        fieldWithPath('[].country').description('The full name of the country where the' +
                                ' company is located'))))
                .when()
                .port(8080)
                .get('/companies')
                .then()
                .assertThat()
                .statusCode(is(200))
        then:
        results
    }
}
