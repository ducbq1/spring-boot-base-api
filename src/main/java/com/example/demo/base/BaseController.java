
package com.example.demo.base;

public abstract class BaseController<S extends BaseService> {
    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }
}
