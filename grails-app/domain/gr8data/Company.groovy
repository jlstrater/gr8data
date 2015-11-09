package gr8data

class Company {
    String name
    String source
    Country country
    Date dateCreated
    Date lastUpdated

    static hasMany = [stats: GenderStats]

    static constraints = {
    }

    static mappings = {
    }
}
