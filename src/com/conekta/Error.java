package com.conekta;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author mauricio
 */
public class Error extends Exception {

    public String message = "There was an exception in the library";
    public String message_to_purchaser = "Hubo un error al enviar los datos al sistema de pagos.";;
    public String type;
    public Integer code;
    public String params;

    public Error(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message);
        if (message != null)
            this.message = message;
        if (message_to_purchaser != null)
            this.message_to_purchaser = message_to_purchaser;
        this.type = type;
        this.code = code;
        this.params = params;
    }

    @Override
    public String toString() {
        return message;
    }

    static void errorHandler(JSONObject response, Integer responseCode) throws Error {
        String message = null;
        String message_to_purchaser = null;
        String type = null;
        String params = null;
        if (response.has("message")) {
            try {
                message = response.getString("message");
            } catch (JSONException ex) {
            }
        }
        if (response.has("message_to_purchaser")) {
            try {
                message_to_purchaser = response.getString("message_to_purchaser");
            } catch (JSONException ex) {
            }
        }
        if (response.has("type")) {
            try {
                type = response.getString("type");
            } catch (JSONException ex) {
            }
        }
        if (response.has("param")) {
            try {
                params = response.getString("param");
            } catch (JSONException ex) {
            }
        }
        switch (responseCode) {
            case 400:
                throw new MalformedRequestError(message, message_to_purchaser, type, responseCode, params);
            case 401:
                throw new AuthenticationError(message, message_to_purchaser, type, responseCode, params);
            case 402:
                throw new ProcessingError(message, message_to_purchaser, type, responseCode, params);
            case 404:
                throw new ResourceNotFoundError(message, message_to_purchaser, type, responseCode, params);
            case 422:
                throw new ParameterValidationError(message, message_to_purchaser, type, responseCode, params);
            case 500:
                throw new ApiError(message, message_to_purchaser, type, responseCode, params);
            default:
                throw new Error(message, message_to_purchaser, type, responseCode, params);
        }
    }
}
class ApiError extends Error {

    ApiError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class NoConnectionError extends Error {

    NoConnectionError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class AuthenticationError extends Error {

    AuthenticationError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class ParameterValidationError extends Error {

    ParameterValidationError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class ProcessingError extends Error {

    ProcessingError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class ResourceNotFoundError extends Error {

    ResourceNotFoundError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}

class MalformedRequestError extends Error {

    MalformedRequestError(String message, String message_to_purchaser, String type, Integer code, String params) {
        super(message, message_to_purchaser, type, code, params);
    }
}
