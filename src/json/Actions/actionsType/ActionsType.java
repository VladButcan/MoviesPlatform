package json.Actions.actionsType;

import json.Actions.Actions;

public final class ActionsType {
    /**
     * Function for return class that implements TypeOfActions
     * @param actions actions node from json input
     */
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
