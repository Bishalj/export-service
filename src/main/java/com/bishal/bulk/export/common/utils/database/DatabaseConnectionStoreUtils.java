package com.bishal.bulk.export.common.utils.database;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionStoreUtils {


    private final static Map<String, ReactiveMongoTemplate> databaseConnectionClientStore = new HashMap<String, ReactiveMongoTemplate>();
}
