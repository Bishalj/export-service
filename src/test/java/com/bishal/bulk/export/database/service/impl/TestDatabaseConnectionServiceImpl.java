package com.bishal.bulk.export.database.service.impl;


import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.common.utils.database.DatabaseConnectionStoreUtils;
import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.service.IDatabaseConnectionService;
import com.bishal.bulk.export.database.service.IDatabaseCredentialService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@EnableAutoConfiguration
@ActiveProfiles("test")
public class TestDatabaseConnectionServiceImpl {

    @Autowired
    private IDatabaseConnectionService databaseConnectionService;

    @Autowired
    private IDatabaseCredentialInitializerService databaseCredentialInitializerService;

    @Autowired
    private IDataExportRequestMapperInitializer dataExportRequestMapperInitializer;

    @Autowired
    private IDatabaseCredentialService databaseCredentialService;

    @Test
    public void setDatabaseConnectionClientInStore_ValidDatabaseConnectionClient_SuccessfullyStoredDatabaseConnectionClient(){
        final DataExportRequestMapper dataExportRequestMapper = dataExportRequestMapperInitializer.getValidRequestDataForEntireDataInCollection();

        Mono<ReactiveMongoTemplate> databaseConnectionClientStoreResponse = databaseCredentialService
                .getDatabaseCredentialDetails(dataExportRequestMapper)
                .flatMap( aDatabaseCredential -> DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, dataExportRequestMapper.getDatabaseUniqueKey()));

        StepVerifier.create(databaseConnectionClientStoreResponse)
                    .expectSubscription()
                    .consumeRecordedWith( reactiveMongoTemplate -> Assert.assertNotNull(reactiveMongoTemplate))
                    .verifyComplete();
    }
}
