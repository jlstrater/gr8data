package gr8data

import grails.converters.JSON
import grails.rest.RestfulController

class GenderStatsController extends RestfulController {
    static responseFormats = ['json']

    GenderStatsController() {
        super(Company)
    }

    def index() {
        Company company = Company.get(params.companyId)
        render company.stats as JSON
    }
}
