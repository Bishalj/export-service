package com.bishal.bulk.export.common.dao.impl;

import com.bishal.bulk.export.common.dao.ITestDataDao;
import com.bishal.bulk.export.instant.controller.model.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public class TestDataDaoImpl implements ITestDataDao {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public void insertDummyData() throws Exception {
        initializeDatabase();
        displayFirstThreeData();
    }

    @Override
    public void displayFirstThreeData() throws Exception {
        reactiveMongoTemplate
                .findAll(TestData.class)
                .subscribe(data -> System.out.println(data.get_id()));
    }


    private Flux<TestData> getTestDatas(){
        Flux<TestData> testDatas = Flux.just(
                new TestData("Bishal", 25, 5000l, new Date()),
                new TestData("Akash", 27, 10000l, new Date()),
                new TestData("Sagar", 18, 500l, new Date())
        );
        return testDatas;
    }

    private void initializeDatabase(){
        Flux<TestData> dataFlux = getTestDatas();
        reactiveMongoTemplate.remove(new Query(), TestData.class )
                .thenMany(dataFlux)
                .flatMap(data -> reactiveMongoTemplate.save(data, "TestData"))
                .doOnNext(data -> System.out.println("Name: "+data.getName() +" ,Age: " + data.getAge() + " ,Salary: "+ data.getSalary()))
                .blockLast();
    }
}
