# Gr8Data

In progress API for Gr8Ladies Data Project

See the charts and contribute at http://jlstrater.github.io/gr8ladies-d3/

The data is bootstrapped from the gr8ladies-d3 repository on startup.

##Run: 

  `gvm use grails 3.0.9`
  
  `grails> run-app`
  
Internet is required to bootstrap data!!!

##Endpoints

####/companies

list of companies who have contributed data.
returns in a format like:
    
    {
      "id": 1,
      "name": "Object Partners, Inc.",
      "country": "United States of America"
    }
  
####/companies/$companyId/stats

stats for the company queried

    {
      "company": "Object Partners, Inc.",
      "name": "Leadership",
      "total": 13,
      "percentageWomen": 38.5,
      "numberOfWomen": 5,
      "numberOfMen": 8
    }
    
####/aggregate
aggregate data for all companies

    [
      {
        "Total": {
          "total": 373,
          "numberOfWomen": 55,
          "numberOfMen": 318,
          "percentageWomen": 14.7
        }
      },
      {
        "Leadership": {
          "total": 48,
          "numberOfWomen": 11,
          "numberOfMen": 37,
          "percentageWomen": 22.9
      }
      },
      {
        "Developers": {
          "total": 206,
          "numberOfWomen": 10,
          "numberOfMen": 196,
          "percentageWomen": 4.9
        }
      },
      {
        "QA": {
          "total": 17,
          "numberOfWomen": 9,
          "numberOfMen": 8,
          "percentageWomen": 52.9
        }
      }
    ]

##Next Steps:

1. Persistent Data Store
2. Security
3. Documentation
