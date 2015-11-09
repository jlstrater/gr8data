package gr8data

class GenderStats {
    String name
    Integer numberOfMen
    Integer numberOfWomen

    static belongsTo = [company: Company]

    static constraints = {
        numberOfMen nullable: true
        numberOfWomen nullable: true
    }

    def calculationsService

    Integer getTotal() {
        (numberOfMen ?: 0) + (numberOfWomen ?: 0)
    }

    Double getPercentageWomen() {
        calculationsService.percentage(numberOfWomen, total)
    }
}
