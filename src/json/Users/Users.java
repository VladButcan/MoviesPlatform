package json.Users;
import json.Credentials.Credentials;
import json.UserCredentials;

public final class Users implements UserCredentials {
    private Credentials credentials = new Credentials();
    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
}
