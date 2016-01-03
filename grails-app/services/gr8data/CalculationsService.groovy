package gr8data

class CalculationsService {
    static final Double ZERO = 0.0

    static Double percentage(numerator, denominator) {
        if (!numerator && !denominator) {
            return ZERO
        }
        ((numerator ?: ZERO) / denominator * 100).toDouble().round(1)
    }
}
