@SuppressWarnings('DuplicateStringLiteral')
class UrlMappings {

    static mappings = {
        '/companies'(resources: 'company') {
            '/stats'(resources: 'genderStats')
        }

        '/v1/hello'(controller: 'hello', namespace: 'v1')
        '/v2/hello'(controller: 'hello', namespace: 'v2')

        '/aggregate'(controller: 'aggregateStats', action: 'index', readOnly: true)

        '/'(controller: 'index')
        '500'(controller: 'InternalServerError')
        '404'(controller: 'NotFound')
    }
}
