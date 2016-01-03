package gr8data

@SuppressWarnings('DuplicateMapLiteral')
class GenderStats {
    String name
    Integer numberOfMen
    Integer numberOfWomen

    static belongsTo = [company: Company]

    static constraints = {
        numberOfMen nullable: true
        numberOfWomen nullable: true
    }

    Integer getTotal() {
        (numberOfMen ?: 0) + (numberOfWomen ?: 0)
    }

    Double getPercentageWomen() {
        CalculationsService.percentage(numberOfWomen, total)
    }
}
