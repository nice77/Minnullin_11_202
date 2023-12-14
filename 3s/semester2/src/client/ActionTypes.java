package client;

public enum ActionTypes {
    CREATED("CREATED"),
    KEY_PRESSED("KEY_PRESSED"),
    PLAYER_SELECTED("PLAYER_SELECTED"),
    PLAYER_CURRENT_SELECTED("PLAYER_CURRENT_SELECTED"),
    GAME_OVER("GAME_OVER");

    private String actionType;

    ActionTypes(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }
}
