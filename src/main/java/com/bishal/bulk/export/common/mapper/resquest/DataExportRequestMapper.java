package com.bishal.bulk.export.common.mapper.resquest;


public class DataExportRequestMapper {

    private String databaseQuery;

    private String databaseUniqueKey;

    private Long batchSizePerFile;

    public String getDatabaseQuery() {
        return databaseQuery;
    }

    public void setDatabaseQuery(String databaseQuery) {
        this.databaseQuery = databaseQuery;
    }

    public String getDatabaseUniqueKey() {
        return databaseUniqueKey;
    }

    public void setDatabaseUniqueKey(String databaseUniqueKey) {
        this.databaseUniqueKey = databaseUniqueKey;
    }

    public Long getBatchSizePerFile() {
        return batchSizePerFile;
    }

    public void setBatchSizePerFile(Long batchSizePerFile) {
        this.batchSizePerFile = batchSizePerFile;
    }
}