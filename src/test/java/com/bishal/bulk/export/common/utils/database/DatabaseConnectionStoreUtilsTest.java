package com.bishal.bulk.export.common.utils.database;

import com.bishal.bulk.export.database.model.DatabaseCredential;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseConnectionStoreUtilsTest {

    @Test
    void testFetchDatabaseConnectionFromDatabaseStore() {
        // Setup
        final String key = "key";
        final ReactiveMongoTemplate expectedResult = null;

        // Run the test
        final ReactiveMongoTemplate result = DatabaseConnectionStoreUtils.fetchDatabaseConnectionFromDatabaseStore(key);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDatabaseConnectionFromDatabaseStore() {
        // Setup
        final DatabaseCredential aDatabaseCredential = null;
        final String databaseUniqueKey = "databaseUniqueKey";
        final ReactiveMongoTemplate expectedResult = null;

        // Run the test
        final ReactiveMongoTemplate result = DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, databaseUniqueKey);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
