package com.api.zorvanz.infra.errors;

import jakarta.validation.ValidationException;

public class ResourceNotFoundException extends ValidationException {
    public ResourceNotFoundException ( String s ) {
    }
}
