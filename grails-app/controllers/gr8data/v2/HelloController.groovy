package gr8data.v2


import grails.rest.*
import grails.converters.*

class HelloController {
    static namespace = "v2"

    def index() {
        render "Hello, world (v2)"
    }
}
