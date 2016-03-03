package gr8data

import grails.converters.JSON
import grails.rest.RestfulController

class CompanyController extends RestfulController {
    static responseFormats = ['json']

    CompanyController() {
        super(Company)
    }

    @Override
    def getObjectToBind() {
        Map paramsObj = JSON.parse(request)
        if (paramsObj.country) {
            paramsObj.country = Country.findByAbbreviation(paramsObj.country)
        }
        paramsObj
    }
}
