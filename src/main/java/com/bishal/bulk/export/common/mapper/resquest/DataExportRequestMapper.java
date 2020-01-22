package com.bishal.bulk.export.common.mapper.resquest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataExportRequestMapper {

    private String databaseQuery;

    private String databaseUniqueKey;

    private String databaseName;

    private Long batchSizePerFile;

    private Boolean isDataNeedsToBeOrdered;

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

    public Boolean getIsDataNeedsToBeOrdered() {
        return isDataNeedsToBeOrdered;
    }

    public void setIsDataNeedsToBeOrdered(Boolean dataNeedsToBeOrdered) {
        isDataNeedsToBeOrdered = dataNeedsToBeOrdered;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
