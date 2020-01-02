package com.bishal.bulk.export.database.utils;

import com.bishal.bulk.export.database.model.DatabaseCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;


@SpringBootTest
@Profile("test")
public class DatabaseCredentialUtils {

    @Value("${env.database.host.url}")
    private static String hostUrlEnvironemntVariableKey;

    @Value("${env.database.port}")
    private static String portNumberEnvironemntVariableKey;

    @Value("${env.database.username}")
    private static String usernameEnvironemntVariableKey;

    @Value("${env.database.password}")
    private static String passwordEnvironemntVariableKey;

    public static DatabaseCredentials getDatabaseCredential(final String databaseUniqueKey){
        final DatabaseCredentials databaseCredentials = new DatabaseCredentials();
        databaseCredentials.setHostUrl(fetchDatabaseHostUrl(databaseUniqueKey));
        databaseCredentials.setPortNumber(fetchDatabasePortNumber(databaseUniqueKey));
        databaseCredentials.setUsername(fetchDatabaseUsername(databaseUniqueKey));
        databaseCredentials.setPassword(fetchDatabasePassword(databaseUniqueKey));
        return databaseCredentials;
    }


    private static String fetchDatabaseHostUrl(final String databaseUniqueKey){
        final String DATABASE_HOST_URL_KEY = String.format(hostUrlEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_HOST_URL_KEY);
    }

    private static Integer fetchDatabasePortNumber(final String databaseUniqueKey){
        final String DATABASE_PORT_NUMBER_KEY = String.format(portNumberEnvironemntVariableKey, databaseUniqueKey);
        return Integer.valueOf(getDataFromEnvironmentVariable(DATABASE_PORT_NUMBER_KEY));
    }

    private static String fetchDatabaseUsername(final String databaseUniqueKey){
        final String DATABASE_USERNAME_KEY = String.format(usernameEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_USERNAME_KEY);
    }

    private static String fetchDatabasePassword(final String databaseUniqueKey){
        final String DATABASE_PASSWORD_KEY = String.format(passwordEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_PASSWORD_KEY);
    }

    private static String getDataFromEnvironmentVariable(final String environmentVariableKey){
        return System.getenv(environmentVariableKey);
    }
}
