package com.sinosoft.aspect.api;

import lombok.Data;

@Data
public class AgentError {

    private Integer errorCode;
    private String errorMsg;
    private String localized;

    public AgentError(Integer errorCode, String errorMsg, String localized) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.localized = localized;
    }
}
