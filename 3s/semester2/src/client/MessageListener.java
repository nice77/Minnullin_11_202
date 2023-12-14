package client;

import client.assets.characters.Characters;
import client.controller.fightScreen.Player;
import client.view.ChangeStageScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class MessageListener extends Thread {

    private BufferedReader br;
    private ChangeStageScene changeStageScene;
    private SendEventToServer sendEventToServer;
    private HandleControllerInput handleControllerInput;
    private String selectedCharacterName;


    public MessageListener(BufferedReader br,
                           ChangeStageScene changeStageScene,
                           SendEventToServer sendEventToServer,
                           String selectedCharacterName) {
        this.br = br;
        this.changeStageScene = changeStageScene;
        this.sendEventToServer = sendEventToServer;
        this.selectedCharacterName = selectedCharacterName;
        this.setDaemon(true);
    }

    public void setHandleControllerInput(HandleControllerInput handleControllerInput) {
        this.handleControllerInput = handleControllerInput;
    }

    @Override
    public void run() {
        String event;

        loop: while (true) {
            try {
                event = this.br.readLine();
                System.out.println(event);
                Map<String, String> eventParsed = Service.parser(event);
                System.out.println(eventParsed);
                ActionTypes actionType = ActionTypes.valueOf(eventParsed.get("actionType"));

                switch (actionType) {
                    case CREATED:
                        this.sendEventToServer.sendEventToServer(this.selectedCharacterName);
                        break;
                    case PLAYER_SELECTED:
                        String characterName = eventParsed.get("player");
                        if (eventParsed.get("position").equals("0")) {
                            SelectedCharacters.mainPlayer = new Player("left", 30, Characters.valueOf(characterName));
                        }
                        else {
                            SelectedCharacters.otherPlayer = new Player("right", 570, Characters.valueOf(characterName));
                        }
                        checkNullablePlayersAndChangeScene();
                        break;
                    case PLAYER_CURRENT_SELECTED:
                        int playable = Integer.parseInt(eventParsed.get("playable"));
                        SelectedCharacters.playable = (playable == 0) ? SelectedCharacters.mainPlayer : SelectedCharacters.otherPlayer;
                        checkNullablePlayersAndChangeScene();
                        break;
                    case KEY_PRESSED:
                        this.handleControllerInput.handleControllerInput(event);
                        break;
                    case GAME_OVER:
                        System.out.println("Received GAME_OVER");
                        this.handleControllerInput.handleControllerInput(event);
                        break loop;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("Joining listener");
            this.join();
            System.out.println("Joined listener");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkNullablePlayersAndChangeScene() {
        if (SelectedCharacters.mainPlayer != null && SelectedCharacters.otherPlayer != null && SelectedCharacters.playable != null) {
            this.changeStageScene.changeStageScene(this);
        }
    }
}
