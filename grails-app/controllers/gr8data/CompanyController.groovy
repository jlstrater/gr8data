package gr8data


import grails.rest.*
import grails.converters.*

class CompanyController extends RestfulController {
    static responseFormats = ['json']

    CompanyController() {
        super(Company)
    }
}
