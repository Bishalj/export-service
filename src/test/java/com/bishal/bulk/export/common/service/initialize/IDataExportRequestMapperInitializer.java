package com.bishal.bulk.export.common.service.initialize;

import com.bishal.bulk.export.common.mapper.resquest.DataExportRequestMapper;

public interface IDataExportRequestMapperInitializer {

    DataExportRequestMapper getRequestForEntireDataInCollection();

    DataExportRequestMapper getInvalidRequestData_NoQueryPresent();

    DataExportRequestMapper getInvalidRequestData_NoBatchSizePerFilePresent();

    DataExportRequestMapper getInvalidRequestData_NoDatabaseUniqueKeyPresent();

    DataExportRequestMapper getInvalidRequestData_NoOrderedFieldPresent();
}
