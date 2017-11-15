package co.nayt.photos;

/**
 * The base presenter needed for the contract.
 */
public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();

    void destroy();
}
