package gr8data

import grails.rest.Resource

@Resource(uri='/countries', readOnly = true, formats = ['json', 'xml'])
class Country {
    String name
    String abbreviation
    String continent
}
