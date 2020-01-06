package com.bishal.bulk.export.database.utils;

import com.bishal.bulk.export.database.model.DatabaseCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DatabaseCredentialUtils {

    @Value("${env.database.host.url}")
    private String hostUrlEnvironemntVariableKey;

    @Value("${env.database.port}")
    private String portNumberEnvironemntVariableKey;

    @Value("${env.database.username}")
    private String usernameEnvironemntVariableKey;

    @Value("${env.database.password}")
    private String passwordEnvironemntVariableKey;

    public Mono<DatabaseCredentials> getDatabaseCredential(final String databaseUniqueKey){
        try {
            final DatabaseCredentials databaseCredentials = new DatabaseCredentials();
            databaseCredentials.setHostUrl(fetchDatabaseHostUrl(databaseUniqueKey));
            databaseCredentials.setPortNumber(fetchDatabasePortNumber(databaseUniqueKey));
            databaseCredentials.setUsername(fetchDatabaseUsername(databaseUniqueKey));
            databaseCredentials.setPassword(fetchDatabasePassword(databaseUniqueKey));
            return Mono.just(databaseCredentials);
        }catch (Exception ex){
            return Mono.error(ex);
        }
    }


    private String fetchDatabaseHostUrl(final String databaseUniqueKey){
        final String DATABASE_HOST_URL_KEY = String.format(hostUrlEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_HOST_URL_KEY);
    }

    private Integer fetchDatabasePortNumber(final String databaseUniqueKey){
        final String DATABASE_PORT_NUMBER_KEY = String.format(portNumberEnvironemntVariableKey, databaseUniqueKey);
        return Integer.valueOf(getDataFromEnvironmentVariable(DATABASE_PORT_NUMBER_KEY));
    }

    private String fetchDatabaseUsername(final String databaseUniqueKey){
        final String DATABASE_USERNAME_KEY = String.format(usernameEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_USERNAME_KEY);
    }

    private String fetchDatabasePassword(final String databaseUniqueKey){
        final String DATABASE_PASSWORD_KEY = String.format(passwordEnvironemntVariableKey, databaseUniqueKey);
        return getDataFromEnvironmentVariable(DATABASE_PASSWORD_KEY);
    }

    private String getDataFromEnvironmentVariable(final String environmentVariableKey){
        return System.getenv(environmentVariableKey);
    }
}
