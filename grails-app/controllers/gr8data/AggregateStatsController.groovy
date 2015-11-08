package gr8data

class AggregateStatsController {
    def index() {
        List<GenderStats> stats = GenderStats.findAll()

        def statNames = stats.name.unique()
        statNames.each {
        }
    }
}