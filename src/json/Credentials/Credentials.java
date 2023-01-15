package json.Credentials;
import homepageautentificat.MoviesPage.Movies;
import java.util.ArrayList;
public final class Credentials {
    private ArrayList<String> subscribeGenres = new ArrayList<String>();
    private final Integer startNumFreePremiumMovies = 15;
    private Integer tokensCount = 0;
    private Integer numFreePremiumMovies = startNumFreePremiumMovies;
    private ArrayList<Movies> purchasedMovies = new ArrayList<>();
    private ArrayList<Movies> watchedMovies = new ArrayList<>();
    private ArrayList<Movies> likedMovies = new ArrayList<>();
    private ArrayList<Movies> ratedMovies = new ArrayList<>();
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;
    private ArrayList<Notifications> notifications = new ArrayList<Notifications>();
    public Credentials() {
        name = null;
        password = null;
        accountType = null;
        country = null;
        balance = null;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public Integer getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final Integer tokensCount) {
        this.tokensCount = tokensCount;
    }

    public Integer getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final Integer numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movies> getPurchasedMovies() {
        return purchasedMovies;
    }
    /**
     * @param purchasedMovies movie for add in List with purchased movies
     */
    public void setPurchasedMovies(final Movies purchasedMovies) {
        this.purchasedMovies.add(purchasedMovies);
    }

    public ArrayList<Movies> getWatchedMovies() {
        return watchedMovies;
    }
    /**
     * @param watchedMovies movie for add in List with watched movies
     */
    public void setWatchedMovies(final Movies watchedMovies) {
        this.watchedMovies.add(watchedMovies);
    }

    public ArrayList<Movies> getLikedMovies() {
        return likedMovies;
    }
    /**
     * @param likedMovies movie for add in List with liked movies
     */
    public void setLikedMovies(final Movies likedMovies) {
        this.likedMovies.add(likedMovies);
    }

    public ArrayList<Movies> getRatedMovies() {
        return ratedMovies;
    }
    /**
     * @param ratedMovies movie for add in List with rated movies
     */
    public void setRatedMovies(final Movies ratedMovies) {
        this.ratedMovies.add(ratedMovies);
    }
    public ArrayList<String> getSubscribeGenres() {
        return subscribeGenres;
    }
    /**
     * @param subscribeGenres array list with user subscribe genres
     */
    public void setSubscribeGenres(final String subscribeGenres) {
        this.subscribeGenres.add(subscribeGenres);
    }

    public ArrayList<Notifications> getNotifications() {
        return notifications;
    }

    /**
     * @param notifications user notifications
     */
    public void setNotifications(final Notifications notifications) {
        this.notifications.add(notifications);
    }
}
