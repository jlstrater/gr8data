package gr8data.controllers

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.is
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType

@Integration
@Rollback
class CompanyControllerSpec extends AbstractControllerSpec {

    void 'companies list with bootstrapped data'() {

        expect:
        given(this.documentationSpec)
                .accept(MediaType.APPLICATION_JSON.toString())
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
                .accept(MediaType.APPLICATION_JSON.toString())
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
                        fieldWithPath('source').description('A short note about the data submitted ' +
                                '(i.e I worked there or the website url if the employees are publicly listed'),
                        fieldWithPath('lastUpdated').description('The date time stamp in UTC when the' +
                                ' data was updated'),
                )))
                .when()
                .port(8080)
                .get('/companies/1')
                .then()
                .assertThat()
                .statusCode(is(200))
    }

    void 'post a new company'() {

        when:
        def whenStatement = given(this.documentationSpec)
                .accept(MediaType.APPLICATION_JSON.toString())
                .contentType(MediaType.APPLICATION_JSON.toString())
                .filter(document('companies-create-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath('name').description('the name of the company'),
                        fieldWithPath('country').description('the abbreviation of the country'),
                        fieldWithPath('source').description('A short note about the data submitted' +
                                '(i.e I worked there or the website url if the employees are publicly listed')
                ),
                responseFields(
                        fieldWithPath('id').description('The id for the company'),
                        fieldWithPath('name').description("The company's name"),
                        fieldWithPath('country').description('The full name of the country where the' +
                                ' company is located'),
                        fieldWithPath('source').description('A short note about the data submitted' +
                                '(i.e I worked there or the website url if the employees are publicly listed'),
                        fieldWithPath('lastUpdated').description('The date time stamp in UTC when the ' +
                                'data was updated'),
                )))
                .body('{"name":"New Company", "country":"USA", "source":"Documentation Test"}')
                .when()
                .port(8080)
                .post('/companies')
        then:
        whenStatement.then()
            .assertThat()
            .statusCode(is(201))
    }

    void 'update a company'() {
        when:
        def whenStatement = given(this.documentationSpec)
                .accept(MediaType.APPLICATION_JSON.toString())
                .contentType(MediaType.APPLICATION_JSON.toString())
                .filter(document('companies-update-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath('name').description('Updated name'),
                        fieldWithPath('country').type(JsonFieldType.STRING).
                                description('String Abbreviation for the country'),
                        fieldWithPath('source').type(JsonFieldType.STRING)
                                .description('Source of the information (ie. I work there.)')
                ),
                responseFields(
                        fieldWithPath('id').description('The id for the company'),
                        fieldWithPath('name').description("The company's name"),
                        fieldWithPath('country').description('The full name of the country where the' +
                                ' company is located'),
                        fieldWithPath('source').description('A short note about the data submitted(i.e I worked there' +
                                ' or the website url if the employees are publicly listed'),
                        fieldWithPath('lastUpdated').description('The date time stamp in UTC when the ' +
                                'data was updated'),
                )))
                .body('{"name":"New Company", "country":"USA", "source":"Documentation Test"}')
                .when()
                .port(8080)
                .put('/companies/1')
        then:
        whenStatement.then()
            .assertThat()
            .statusCode(is(200))
    }

    void 'send a patch for a value on a company'() {
        when:
        def whenStatement = given(this.documentationSpec)
                .accept(MediaType.APPLICATION_JSON.toString())
                .contentType(MediaType.APPLICATION_JSON.toString())
                .filter(document('companies-partial-update-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                requestFields(
                        fieldWithPath('name').description('Updated name').optional(),
                        fieldWithPath('country').type(JsonFieldType.STRING).
                                description('String Abbreviation for the country').optional(),
                        fieldWithPath('source').type(JsonFieldType.STRING)
                                .description('Source of the information (ie. I work there.)').optional()
                ),
                responseFields(
                        fieldWithPath('id').description('The id for the company'),
                        fieldWithPath('name').description("The company's name"),
                        fieldWithPath('country').description('The full name of the country where the' +
                                ' company is located'),
                        fieldWithPath('source').description('A short note about the data submitted(i.e I worked there' +
                                ' or the website url if the employees are publicly listed'),
                        fieldWithPath('lastUpdated').description('The date time stamp in UTC when the ' +
                                'data was updated'),
                )))
                .body('{"name":"Updated Company"}')
                .when()
                .port(8080)
                .patch('/companies/1')
        then:
        whenStatement.then()
            .assertThat()
            .statusCode(is(200))
    }
}
