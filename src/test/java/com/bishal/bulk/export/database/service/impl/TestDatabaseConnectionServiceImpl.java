package com.bishal.bulk.export.database.service.impl;


import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactory;
import com.bishal.bulk.export.common.service.IExportServiceBeanFactoryTest;
import com.bishal.bulk.export.service.initialize.IDataExportRequestMapperInitializer;
import com.bishal.bulk.export.common.utils.database.DatabaseConnectionStoreUtils;
import com.bishal.bulk.export.database.initialize.IDatabaseCredentialInitializerService;
import com.bishal.bulk.export.database.utils.DatabaseCredentialUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@PrepareForTest(DatabaseCredentialUtils.class)
public class TestDatabaseConnectionServiceImpl {

    @Autowired
    private IExportServiceBeanFactory exportServiceBeanFactory;

    @Autowired
    private IExportServiceBeanFactoryTest exportServiceBeanFactoryTest;


    @Test
    public void setDatabaseConnectionClientInStore_ValidDatabaseConnectionClient_SuccessfullyStoredDatabaseConnectionClient(){
        final DataExportRequestMapper dataExportRequestMapper = exportServiceBeanFactoryTest
                                                                    .getInstantExportBeanFactoryTest()
                                                                    .getDataExportRequestMapperInitializer()
                                                                    .getValidRequestDataForEntireDataInCollection();

        Mono<ReactiveMongoTemplate> databaseConnectionClientStoreResponse = exportServiceBeanFactory
                .getDatabaseBeanFactory()
                .getDatabaseCredentialService()
                .getDatabaseCredentialDetails(dataExportRequestMapper)
                .flatMap( aDatabaseCredential -> DatabaseConnectionStoreUtils.getDatabaseConnectionFromDatabaseStore(aDatabaseCredential, dataExportRequestMapper.getDatabaseUniqueKey()));

        StepVerifier.create(databaseConnectionClientStoreResponse)
                    .expectSubscription()
                    .consumeRecordedWith( reactiveMongoTemplate -> Assert.assertNotNull(reactiveMongoTemplate))
                    .verifyComplete();
    }
}
