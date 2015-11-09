package gr8data

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(CalculationsService)
class CalculationsServiceSpec extends Specification {
    CalculationsService calculationsService

    def setup() {
        calculationsService = new CalculationsService()
    }

    @Unroll
    void "test totalEmployees for inputs #totalWomen and #totalMen"() {
        given:
        def results = calculationsService.percentage(totalWomen, total)

        expect:
        results == percentage

        where:
        totalWomen | total | percentage
        ''         | 0     | 0
        0          | 10    | 0
        10         | 10    | 100
        5          | 10    | 50
        2          | 6     | 33.3
    }
}
