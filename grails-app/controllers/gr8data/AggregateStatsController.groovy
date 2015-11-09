package gr8data

import grails.converters.JSON

class AggregateStatsController {
    static allowedMethods = [index: 'GET']

    AggregateDataService aggregateDataService

    def index() {
        render aggregateDataService.getAllAggregates() as JSON
    }
}