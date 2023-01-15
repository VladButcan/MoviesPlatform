package json.Users;
import json.Credentials.Credentials;

public final class Users {
    private Credentials credentials = new Credentials();
    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
}
