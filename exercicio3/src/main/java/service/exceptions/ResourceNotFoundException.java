package service.exceptions;

import aj.org.objectweb.asm.commons.SerialVersionUIDAdder;

public class ResourceNotFoundException extends RuntimeException{
    private static final long SerialVersionUID =1L;

    public ResourceNotFoundException(Object id) {
    super("Resource not found Id "+ id);
    }
}
