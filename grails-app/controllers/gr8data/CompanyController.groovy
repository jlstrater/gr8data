package gr8data

import grails.rest.RestfulController

class CompanyController extends RestfulController {
    static responseFormats = ['json']

    CompanyController() {
        super(Company)
    }
}
