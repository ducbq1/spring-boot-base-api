
package vn.vietinbank.evolve.base;

public abstract class BaseController<S extends BaseService> {
    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }
}
