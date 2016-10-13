package com.clubcard.rest;

import com.clubcard.rest.model.ErrorResponse;
import com.clubcard.rest.model.ResponseModel;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Cutch errors
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

//    @Override
    public Response toResponse(Throwable ex) {

        ErrorResponse errorMessage = new ErrorResponse();
        setHttpStatus(ex, errorMessage);
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setCode(ApplicationException.E_4009);

        ex.printStackTrace();

        return Response.status(errorMessage.getCode())
                .entity(errorMessage)
                .build();
    }

    private void setHttpStatus(Throwable ex, ErrorResponse errorResponse) {
        if(ex instanceof WebApplicationException) {
            errorResponse.setStatus(ResponseModel.Status.error);
        } else {
            errorResponse.setStatus(ResponseModel.Status.error); //defaults to internal server error 500
        }
    }
}