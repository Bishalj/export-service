package com.bishal.bulkExample.export.database.utils;

import com.bishal.bulk.export.database.model.DatabaseCredential;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseCredentialUtilsTest {

    @Mock
    public DatabaseCredentialUtils databaseCredentialUtils;


    @Test
    public void getDatabaseCredentialTest_ValidData_SuccessfullyGetDatabaseCredentials(){
        String databsaeUniqueKey = "LOCAL";
        String databaseName = "LOCAL";
        String databasePortKey =  "DATABASE_PORT_LOCAL";
        String databaseUsername = "DATABASE_USERNAME_LOCAL";
        String databasePassword = "DATABASE_PASSWORD_LOCAL";
        String databaseHostUrl = "DATABASE_HOST_URL_LOCAL";
        when(databaseCredentialUtils.getDataFromEnvironmentVariable(databasePortKey)).thenReturn("27017");
        when(databaseCredentialUtils.getDataFromEnvironmentVariable(databaseUsername)).thenReturn("bishal");
        when(databaseCredentialUtils.getDataFromEnvironmentVariable(databasePassword)).thenReturn("bishal");
        when(databaseCredentialUtils.getDataFromEnvironmentVariable(databaseHostUrl)).thenReturn("localhost");
        Mono<DatabaseCredential> databaseCredentialMono = databaseCredentialUtils.getDatabaseCredential(databsaeUniqueKey, databaseName);

        StepVerifier.create(databaseCredentialMono)
                .expectSubscription()
                .consumeNextWith(databaseCredential -> isDatabaseCredentialDetailsValid(databaseCredential));

    }

    private void isDatabaseCredentialDetailsValid(final DatabaseCredential databaseCredential) {
        assertNotNull(databaseCredential);
        assertNotNull(databaseCredential.getHostUrl());
        assertNotNull(databaseCredential.getPortNumber());
        assertNotNull(databaseCredential.getUsername());
        assertNotNull(databaseCredential.getPassword());
    }
}
