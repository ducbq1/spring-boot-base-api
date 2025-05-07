
package vn.vietinbank.evolve.base;

public abstract class BaseService<R extends BaseRepository> {
    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }
}
