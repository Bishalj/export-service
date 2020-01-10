package com.bishal.bulk.export.service.initialize.impl;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;
import com.bishal.bulk.export.service.initialize.IDataExportRequestMapperInitializer;
import org.springframework.stereotype.Service;

@Service
public class DataExportRequestMapperInitializeImpl implements IDataExportRequestMapperInitializer {

    @Override
    public DataExportRequestMapper getValidRequestDataForEntireDataInCollection() {
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setBatchSizePerFile(100000l);
        dataExportRequestMapper.setDatabaseQuery("{}");
        dataExportRequestMapper.setDatabaseUniqueKey("LOCAL");
        dataExportRequestMapper.setIsDataNeedsToBeOrdered(false);
        return dataExportRequestMapper;
    }

    @Override
    public DataExportRequestMapper getInvalidRequestData_NoBatchSizePerFilePresent() {
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setDatabaseQuery("{}");
        dataExportRequestMapper.setDatabaseUniqueKey("LOCAL");
        dataExportRequestMapper.setIsDataNeedsToBeOrdered(false);
        return dataExportRequestMapper;
    }

    @Override
    public DataExportRequestMapper getInvalidRequestData_NoDatabaseUniqueKeyPresent() {
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setBatchSizePerFile(100000l);
        dataExportRequestMapper.setDatabaseQuery("{}");
        dataExportRequestMapper.setIsDataNeedsToBeOrdered(false);
        return dataExportRequestMapper;
    }

    @Override
    public DataExportRequestMapper getInvalidRequestData_NoQueryPresent() {
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setBatchSizePerFile(100000l);
        dataExportRequestMapper.setDatabaseUniqueKey("LOCAL");
        dataExportRequestMapper.setIsDataNeedsToBeOrdered(false);
        return dataExportRequestMapper;
    }

    @Override
    public DataExportRequestMapper getInvalidRequestData_NoOrderedFieldPresent() {
        final DataExportRequestMapper dataExportRequestMapper = new DataExportRequestMapper();
        dataExportRequestMapper.setBatchSizePerFile(100000l);
        dataExportRequestMapper.setDatabaseQuery("{}");
        dataExportRequestMapper.setDatabaseUniqueKey("LOCAL");
        return dataExportRequestMapper;
    }
}
