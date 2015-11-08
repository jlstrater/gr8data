package gr8data.v1


import grails.rest.*
import grails.converters.*

class HelloController {
    static namespace = "v1"

    def index() {
        render 'Hello, v1'
    }
}
