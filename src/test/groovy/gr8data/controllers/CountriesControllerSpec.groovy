package gr8data.controllers

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.is
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

@Integration
@Rollback
class CountriesControllerSpec extends AbstractControllerSpec {
    void 'country list with bootstrapped data'() {

        expect:
        given(this.documentationSpec)
                .accept('application/json')
                .filter(document('countries-list-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('[].class').description('class of object'),
                        fieldWithPath('[].id').description('id of the country'),
                        fieldWithPath('[].abbreviation').description('three character abbreviation of the country'),
                        fieldWithPath('[].continent').description('continent of the country'),
                        fieldWithPath('[].name').description('full name of the country in English'),
                )))
                .when()
                .port(8080)
                .get('/countries')
                .then()
                .assertThat()
                .statusCode(is(200))
    }

    void 'get individual country'() {

        expect:
        given(this.documentationSpec)
                .accept('application/json')
                .filter(document('countries-get-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('class').description('class of object'),
                        fieldWithPath('id').description('id of the country'),
                        fieldWithPath('abbreviation').description('three character abbreviation of the country'),
                        fieldWithPath('continent').description('continent of the country'),
                        fieldWithPath('name').description('full name of the country in English'),
                )))
                .when()
                .port(8080)
                .get('/countries/1')
                .then()
                .assertThat()
                .statusCode(is(200))
    }
}
