package com.ugo.helpers;

import com.myzlab.k.KRow;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;

public class ValidatorHelper {

    public static void assertNotNull(
        final Object value,
        final String response
    ) {
        assertNotNull(value, HttpStatus.BAD_REQUEST, response);
    }
    
    public static void assertNotNull(
        final Object value,
        final HttpStatus status,
        final String response
    ) {
        
        if (value == null) {
            throw KExceptionHelper.createByHttpStatus(status, response);
        }
        
        if (value instanceof KRow) {
            if (((KRow) value).isNull()) {
                throw KExceptionHelper.createByHttpStatus(status, response);
            }
        }
    }
    
    public static void assertNotEmpty(
        final Object value,
        final String response
    ) {
        assertNotEmpty(value, HttpStatus.BAD_REQUEST, response);
    }
    
    public static void assertNotEmpty(
        final Object value,
        final HttpStatus status,
        final String response
    ) {
        if (value == null) {
            return;
        }
        
        if (value instanceof List) {
            final List<?> list = (List) value;
            
            if (list.isEmpty()) {
                throw KExceptionHelper.createByHttpStatus(status, response);
            }
            
            return;
        }
        
        if (value.toString().trim().isEmpty()) {
            throw KExceptionHelper.createByHttpStatus(status, response);
        }
    }
    
    public static void assertNotNullNotEmpty(
        final Object value,
        final String response
    ) {
        assertNotNullNotEmpty(value, HttpStatus.BAD_REQUEST, response);
    }
    
    public static void assertNotNullNotEmpty(
        final Object value,
        final HttpStatus status,
        final String response
    ) {
        assertNotNull(value, status, response);
        assertNotEmpty(value, status, response);
    }
    
    public static void assertPattern(
        final String value,
        final String pattern,
        final String response
    ) {
        assertPattern(value, pattern, HttpStatus.BAD_REQUEST, response);
    }

    public static void assertPattern(
        final String value,
        final String pattern,
        final HttpStatus status,
        final String response
    ) {

        if (pattern == null) {
            return;
        }

        final Pattern pat = Pattern.compile(pattern);
        final Matcher mat = pat.matcher(value);

        if (mat.matches()) {
            return;
        }

        throw KExceptionHelper.createByHttpStatus(status, response);
    }
}

