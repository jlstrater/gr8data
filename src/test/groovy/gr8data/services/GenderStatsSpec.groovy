package gr8data.services

import gr8data.Company
import gr8data.Country
import gr8data.GenderStats
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(GenderStats)
class GenderStatsSpec extends Specification {

    @Unroll
    void "test totalEmployees for inputs #totalWomen and #totalMen"() {
        given:
        Country country = new Country(name: 'United States of America', continent: 'North America', abbreviation: 'USA')
        Company company = new Company(
                name: 'Test',
                source: 'test data',
                country: country
        )
        GenderStats stat = new GenderStats(name: 'test', numberOfMen: totalMen, numberOfWomen: totalWomen,
                company: company)

        expect:
        stat.total == total

        where:
        totalWomen | totalMen | total
        ''         | ''       | 0
        0          | 10       | 10
        10         | 0        | 10
        5          | 5        | 10
        2          | 4        | 6
    }
}
