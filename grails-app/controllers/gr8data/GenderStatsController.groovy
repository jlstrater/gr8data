package gr8data

import grails.rest.*
import grails.converters.*

class GenderStatsController {

    def index() {
        Company company = Company.get(params.companyId)
        render company.stats as JSON
    }
}
