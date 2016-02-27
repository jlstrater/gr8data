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

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

@Integration
@Rollback
class CompanyControllerSpec extends AbstractControllerSpec {

    void 'companies list with bootstrapped data'() {

        expect:
        given(this.documentationSpec)
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
    }

    void 'get statistics from a single company'() {

        expect:
        given(this.documentationSpec)
                .accept('application/json')
                .filter(document('companies-get-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('id').description('The id for the company'),
                        fieldWithPath('name').description("The company's name"),
                        fieldWithPath('country').description('The full name of the country where the' +
                                ' company is located'),
                        fieldWithPath('source').description('A short note about the data submitted(i.e I worked there' +
                                ' or the website url if the employees are publicly listed'),
                        fieldWithPath('lastUpdated').description('The date time stamp in UTC when the data was updated'),
                )))
                .when()
                .port(8080)
                .get('/companies/1')
                .then()
                .assertThat()
                .statusCode(is(200))
    }
}
