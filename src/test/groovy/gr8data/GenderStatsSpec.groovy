package gr8data

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(GenderStats)
class GenderStatsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test totalEmployees for inputs #totalWomen and #totalMen"() {
        given:
        Country country = new Country(name: 'United States of America', continent: 'North America', abbreviation: 'USA')
        Company company = new Company(
                name: 'Test',
                source: 'test data',
                country: country
        )
        GenderStats stat = new GenderStats(name: 'test', numberOfMen: totalMen, numberOfWomen: totalWomen, company: company)

        expect:
        stat.total == expectedTotal
        stat.percentageWomen == expectedPercentage

        where:
        totalWomen | totalMen | expectedTotal | expectedPercentage
        ''         | ''       | 0             | 0
        0          | 10       | 10            | 0
        10         | 0        | 10            | 100
        5          | 5        | 10            | 50
        2          | 4        | 6             | 33.3
    }
}