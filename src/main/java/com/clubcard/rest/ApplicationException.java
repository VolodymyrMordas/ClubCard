package com.clubcard.rest;

public class ApplicationException extends Exception {


    private int code;

    public static int E_4001 = 4001;
    public static int E_4002 = 4002;
    public static int E_4003 = 4003;
    public static int E_4004 = 4004;
    public static int E_4005 = 4005;
    public static int E_4006 = 4006;
    public static int E_4007 = 4007;
    public static int E_4008 = 4008;
    public static int E_4009 = 4009;
    public static int E_4010 = 4010;

    public enum Error {

        E_CDNT_FND_OBJECT(E_4001),E_EMPT_RQST_BODY(E_4002),E_WRNG_MEDIA_TYPE(E_4003),
        E_ISNOTAUTH(E_4004),E_REQUEST_WRNG_DATA_KEY(E_4005),E_REQUEST_WRNG_AUTH_KEY(E_4006),E_PERM_DENY(E_4007),
        E_BEAN(E_4008),E_REQUEST_WRNG(E_4009),E_CAN_FIND_VERIFICATION_CODE(E_4010);

        private int errorCode;

        Error(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }

    /**
     * @param code
     * @param message
     */
    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(Error error) {
        super(error.toString());
        this.code = error.errorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}