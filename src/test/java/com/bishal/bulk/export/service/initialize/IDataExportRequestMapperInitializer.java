package com.bishal.bulk.export.service.initialize;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;

public interface IDataExportRequestMapperInitializer {

    DataExportRequestMapper getValidRequestDataForEntireDataInCollection();

    DataExportRequestMapper getInvalidRequestData_NoQueryPresent();

    DataExportRequestMapper getInvalidRequestData_NoBatchSizePerFilePresent();

    DataExportRequestMapper getInvalidRequestData_NoDatabaseUniqueKeyPresent();

    DataExportRequestMapper getInvalidRequestData_NoOrderedFieldPresent();
}
