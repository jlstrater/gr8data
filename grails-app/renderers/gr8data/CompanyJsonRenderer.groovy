package gr8data

import grails.converters.JSON
import grails.rest.render.AbstractRenderer
import grails.rest.render.RenderContext
import grails.web.mime.MimeType
import groovy.json.JsonBuilder

class CompanyJsonRenderer extends AbstractRenderer<Company> {
    CompanyJsonRenderer() {
        super(Company, [MimeType.JSON, MimeType.TEXT_JSON] as MimeType[])
    }

    void render(Company company, RenderContext context) {
        context.contentType = MimeType.JSON.name

        JsonBuilder builder = new JsonBuilder(id: company.id, name: company.name, country: company.country.name,
            source: company.source, lastUpdated: company.lastUpdated)
        builder.writeTo(context.writer)
    }
}
