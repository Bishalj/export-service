package com.bishal.bulk.export.common.mapper.response;

import com.bishal.bulk.export.common.model.AbstractBaseResponse;
import reactor.core.publisher.Flux;

public class FileDetailsResponseMapper extends AbstractBaseResponse {

    private Flux<FileMetaDetails> fileMetaDetails;

    public Flux<FileMetaDetails> getFileMetaDetails() {
        return fileMetaDetails;
    }

    public void setFileMetaDetails(Flux<FileMetaDetails> fileMetaDetails) {
        this.fileMetaDetails = fileMetaDetails;
    }
}
