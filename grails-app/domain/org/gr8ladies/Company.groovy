package org.gr8ladies

import grails.rest.Resource

@Resource(uri='/companies')
class Company {

    String name
    Integer totalMen
    Integer totalWomen
    Integer leadershipWomen
    Integer leadershipMen
    Integer developersWomen
    Integer developersMen
    Integer qaWomen
    Integer qaMen
    Date lastUpdated
    String source
    String country

    static constraints = {
        totalMen(nullable: true)
        totalWomen(nullable: true)
        leadershipWomen(nullable: true)
        leadershipMen(nullable: true)
        developersMen(nullable: true)
        developersWomen(nullable: true)
        qaWomen(nullable: true)
        qaMen(nullable: true)
    }
}
