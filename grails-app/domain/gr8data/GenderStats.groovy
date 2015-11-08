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

    Integer getTotal() {
        (numberOfMen ?: 0) + (numberOfWomen ?: 0)
    }

    Double getPercentageWomen() {
        ratio(numberOfWomen, total)
    }

    Double ratio(numerator, denominator){
        if(!numerator && !denominator) return 0.0
        ((numerator ?: 0.0).div(denominator) * 100).toDouble().round(1)
    }
}
