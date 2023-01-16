package json.Actions;

public final class ActionsType {
    public TypeOfActions returnType(final Actions actions) {
        switch (actions.getType()) {
            case "change page":
                return new ChangePage();
            case "on page":
                return new OnPage();
            case "database":
                return new Database();
            case "back":
                return new Back();
            default:
                return null;
        }
    }
}
