package homepageautentificat.MoviesPage;

public final class Filters {
    private final Contains contains = new Contains();
    private final Sort sort = new Sort();
    public Contains getContains() {
        return contains;
    }
    public Sort getSort() {
        return sort;
    }
}
