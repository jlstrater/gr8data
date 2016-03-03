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
class AggregateControllerSpec extends AbstractControllerSpec {
    void 'aggregate data list with bootstrapped data'() {

        expect:
        given(this.documentationSpec)
                .accept('application/json')
                .filter(document('aggregates-list-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('[].Total').description('Aggregates for the reported total number of employees.'),
                        fieldWithPath('[].Total.total').description('The total of all reported employees.'),
                        fieldWithPath('[].Total.numberOfWomen').description('The total of all reported employees who ' +
                                'identify as a woman.'),
                        fieldWithPath('[].Total.numberOfMen').description('The total of all reported employees who' +
                                ' identify as a man.'),
                        fieldWithPath('[].Total.percentageWomen').description('The calculated percentage of all ' +
                                'reported employees who identify as a woman.'),
                        fieldWithPath('[].Leadership').description('The aggregate of all reported employees who' +
                                ' manager other employees.'),
                        fieldWithPath('[].Developers').description('The aggregate of all reported employees who' +
                                ' identify as developers, engineers, devops, or other similar titles.'),
                        fieldWithPath('[].QA').description('The aggregate of all reported employees who' +
                                ' identify as QA including manual QA and software engineers in test.'),

                )))
                .when()
                .port(8080)
                .get('/aggregates')
                .then()
                .assertThat()
                .statusCode(is(200))
    }
}
