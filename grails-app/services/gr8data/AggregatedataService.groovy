package gr8data

class AggregateDataService {

    CalculationsService calculationsService

    def getAllAggregates() {
        List<GenderStats> stats = GenderStats.findAll()

        def statNames = stats.name.unique()
        def sums = statNames.collect { name ->
            Integer total = stats.findAll { it.name == name }.total.sum()
            Integer numberOfWomen = stats.findAll { it.name == name }.numberOfWomen.sum()
            Integer numberOfMen = stats.findAll { it.name == name }.numberOfMen.sum()
            ["${name}": [
                'total'          : total,
                'numberOfWomen'  : numberOfWomen,
                'numberOfMen'    : numberOfMen,
                'percentageWomen': calculationsService.percentage(numberOfWomen, total)
            ]]
        }
        sums
    }
}
