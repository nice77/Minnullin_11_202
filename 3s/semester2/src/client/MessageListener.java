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
    private boolean created;
    private SendEventToServer sendEventToServer;
    private HandleControllerInput handleControllerInput;
    private String selectedCharacterName;


    public MessageListener(BufferedReader br,
                           ChangeStageScene changeStageScene,
                           SendEventToServer sendEventToServer,
                           String selectedCharacterName) {
        this.br = br;
        this.changeStageScene = changeStageScene;
        this.created = false;
        this.sendEventToServer = sendEventToServer;
        this.selectedCharacterName = selectedCharacterName;
    }

    public void setHandleControllerInput(HandleControllerInput handleControllerInput) {
        this.handleControllerInput = handleControllerInput;
    }

    @Override
    public void run() {
        String event;

        while (true) {
            try {
                event = this.br.readLine();
                Map<String, String> eventParsed = Service.parser(event);

                if (eventParsed.containsKey("created")) {
                    this.created = true;
                    this.sendEventToServer.sendEventToServer(this.selectedCharacterName);
                }
                else if (event.contains("player")) {
                    String characterName = event.split("=")[1];
                    if (eventParsed.containsKey("player0")) {
                        SelectedCharacters.mainPlayer = new Player("left", 30, Characters.valueOf(characterName));
                    }
                    else {
                        SelectedCharacters.otherPlayer = new Player("right", 570, Characters.valueOf(characterName));
                    }
                    checkNullablePlayersAndChangeScene();
                }
                else if (eventParsed.containsKey("playable") && !eventParsed.containsKey("key")) {
                    int playable = Integer.parseInt(event.split("=")[1]);
                    SelectedCharacters.playable = (playable == 0) ? SelectedCharacters.mainPlayer : SelectedCharacters.otherPlayer;
                    checkNullablePlayersAndChangeScene();
                }
                else {
                    this.handleControllerInput.handleControllerInput(event);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void checkNullablePlayersAndChangeScene() {
        if (SelectedCharacters.mainPlayer != null && SelectedCharacters.otherPlayer != null && SelectedCharacters.playable != null) {
            this.changeStageScene.changeStageScene(this);
        }
    }
}
