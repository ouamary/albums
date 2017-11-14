package co.nayt.photos;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();

    void destroy();
}
