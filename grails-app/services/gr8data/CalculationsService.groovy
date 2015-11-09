package gr8data

class CalculationsService {

    Double percentage(numerator, denominator){
        if(!numerator && !denominator) return 0.0
        ((numerator ?: 0.0).div(denominator) * 100).toDouble().round(1)
    }

}
