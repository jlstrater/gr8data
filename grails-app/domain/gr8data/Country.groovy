package gr8data

import grails.rest.Resource

@Resource(uri='/countries', readOnly = false, formats = ['json', 'xml'])
class Country {
    String name
    String abbreviation
    String continent
}
