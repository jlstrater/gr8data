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

class GenericDocumentationSpec extends AbstractControllerSpec {
    void 'setup error when no data is posted to companies'() {
        expect:
        given(this.documentationSpec)
                .accept('application/json')
                .filter(document('error-example',
                preprocessRequest(modifyUris()
                        .host('api.gr8ladies.org')
                        .removePort()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath('errors').description('List of error messages'),
                        fieldWithPath('errors.[].object').description("The object that generated the error"),
                        fieldWithPath('errors.[].field').description('The field which generated the error'),
                        fieldWithPath('errors.[].rejected-value').description('The value which generated the error'),
                        fieldWithPath('errors.[].message').description('The readable message about the error'))))
                .when()
                .port(8080)
                .post('/companies')
                .then()
                .assertThat()
                .statusCode(is(422))
    }
}
