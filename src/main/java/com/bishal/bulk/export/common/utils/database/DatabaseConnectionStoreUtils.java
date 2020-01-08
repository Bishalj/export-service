package com.bishal.bulk.export.common.utils.database;

import com.bishal.bulk.export.database.model.DatabaseCredentials;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionStoreUtils {


    private final static Map<String, ReactiveMongoTemplate> databaseConnectionClientStore = new HashMap<String, ReactiveMongoTemplate>();

    public static Mono<ReactiveMongoTemplate> fetchDatabaseConnectionFromDatabaseStore(final String key){
        return Mono.just(databaseConnectionClientStore.get(key));

    }

    public static Mono<ReactiveMongoTemplate> getDatabaseConnectionFromDatabaseStore(DatabaseCredentials aDatabaseCredential, final String databaseUniqueKey) {

       return fetchDatabaseConnectionFromDatabaseStore(databaseUniqueKey)
                    .switchIfEmpty(createNewDatabaseConnection(aDatabaseCredential, databaseUniqueKey));
    }

    private static Mono<ReactiveMongoTemplate> createNewDatabaseConnection(DatabaseCredentials aDatabaseCredential, String databaseUniqueKey) {
        final MongoClient mongoClient = MongoClients.create("localhost:27017");
        ReactiveMongoTemplate reactiveMongoTemplate = new ReactiveMongoTemplate(mongoClient, aDatabaseCredential.getDatabaseName());
        return Mono.just(reactiveMongoTemplate);
    }

}
