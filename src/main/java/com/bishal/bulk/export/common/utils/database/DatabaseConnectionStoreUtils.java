package com.bishal.bulk.export.common.utils.database;

import com.bishal.bulk.export.database.model.DatabaseCredentials;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionStoreUtils {


    private final static Map<String, ReactiveMongoTemplate> databaseConnectionClientStore = new HashMap<String, ReactiveMongoTemplate>();

    public static ReactiveMongoTemplate fetchDatabaseConnectionFromDatabaseStore(final String key){
        return databaseConnectionClientStore.get(key);

    }

    public static ReactiveMongoTemplate getDatabaseConnectionFromDatabaseStore(DatabaseCredentials aDatabaseCredential, final String databaseUniqueKey) {

        final ReactiveMongoTemplate reactiveMongoTemplate = fetchDatabaseConnectionFromDatabaseStore(databaseUniqueKey);
        if(ObjectUtils.isEmpty(reactiveMongoTemplate))
            return createNewDatabaseConnection(aDatabaseCredential);
        return reactiveMongoTemplate;
    }

    private static ReactiveMongoTemplate createNewDatabaseConnection(DatabaseCredentials aDatabaseCredential) {
        final MongoClient mongoClient = MongoClients.create("mongodb://" + aDatabaseCredential.getHostUrl() + ":" + aDatabaseCredential.getPortNumber());
        ReactiveMongoTemplate reactiveMongoTemplate = new ReactiveMongoTemplate(mongoClient, aDatabaseCredential.getDatabaseName());
        return reactiveMongoTemplate;
    }

}
