package gr8data.controllers

import com.jayway.restassured.builder.RequestSpecBuilder
import com.jayway.restassured.specification.RequestSpecification
import org.springframework.restdocs.ManualRestDocumentation
import spock.lang.Specification

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration

class AbstractControllerSpec extends Specification {

    final ManualRestDocumentation restDocumentation = new ManualRestDocumentation('src/docs/generated-snippets')

    protected RequestSpecification documentationSpec

    void setup() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build()
        this.restDocumentation.beforeTest(getClass(), specificationContext.currentSpec.name)

    }

    void cleanup() {
        this.restDocumentation.afterTest()
    }

}
