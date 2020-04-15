curl -XPUT 'localhost:9200/jobs?pretty' -H 'Content-Type: application/json' -d'
{
    "settings" : {
        "number_of_shards" : 1
    },
    "mappings" : {
        "job" : {
            "properties" : {
                "company" : { "type" : "keyword"},
                "location" : {"type" : "geo_point"},
                "jobTitle" : {"type" : "keyword"},
                "city" : {"type" : "keyword"},
                "snippet" : {"type" : "string"}
            }
        }
    }
}
'
