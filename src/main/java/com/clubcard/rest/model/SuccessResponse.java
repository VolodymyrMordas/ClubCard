package com.clubcard.rest.model;

import java.io.Serializable;

/**
 * Created by volodymyrmordas on 12/25/15.
 */
public class SuccessResponse extends ResponseModel implements Serializable {
    public SuccessResponse() {
        super(Status.success);
    }
}
