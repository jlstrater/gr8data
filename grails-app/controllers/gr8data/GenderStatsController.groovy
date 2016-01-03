package gr8data

import grails.converters.JSON

class GenderStatsController {

    def index() {
        Company company = Company.get(params.companyId)
        render company.stats as JSON
    }
}
