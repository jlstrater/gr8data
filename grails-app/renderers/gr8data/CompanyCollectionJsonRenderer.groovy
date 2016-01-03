package gr8data

import grails.rest.render.ContainerRenderer
import grails.rest.render.RenderContext
import grails.web.mime.MimeType
import groovy.json.JsonBuilder

class CompanyCollectionJsonRenderer implements ContainerRenderer<List, Company> {
    Class<List> targetType =  List

    Class<Company> componentType = Company

    MimeType[] getMimeTypes() { [MimeType.JSON, MimeType.TEXT_JSON] as MimeType[] }

    void render(List companies, RenderContext context) {
        context.contentType = MimeType.JSON.name

        JsonBuilder builder = new JsonBuilder(
                companies.collect { company ->
                    [id: company.id, name: company.name, country: company.country.name]
                })
        builder.writeTo(context.writer)
    }
}
