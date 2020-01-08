package com.bishal.bulk.export.database.model;

public class DatabaseCredentials {

    private String hostUrl;
    private Integer portNumber;
    private String username;
    private String password;
    private String databaseName;

    public String getHostUrl() {
        return hostUrl;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
