package client.assets.characters;

import java.util.HashMap;
import java.util.Map;

public enum Characters {
    SCORPION(
            "/client/assets/characters/sprites/scorpion.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {53, 110, 77, 17, 6});
                put("WALK", new int[] {50, 110, 10, 130, 9});
                put("BLOCK", new int[] {48, 108, 514, 17, 1});
            }}
    ),
    SUB_ZERO(
            "/client/assets/characters/sprites/sub-zero.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {70, 150, 106, 30, 10});
                put("WALK", new int[] {75, 162, 25, 200, 9});
                put("BLOCK", new int[] {77, 156, 975, 25, 1});
            }}
    ),
    JOHNNY_CAGE(
            "client/assets/characters/sprites/johnny_cage.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {67, 105, 110, 20, 7});
                put("WALK", new int[] {60, 106, 25, 135, 9});
                put("BLOCK", new int[] {62, 104, 613, 17, 1});
            }}

    ),
    KANO(
            "client/assets/characters/sprites/kano.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {54, 104, 81, 25, 7});
                put("WALK", new int[] {55, 103, 20, 153, 9});
                put("BLOCK", new int[] {48, 110, 476, 17, 1});
            }}
    ),
    RAIDEN(
            "client/assets/characters/sprites/raiden.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {59, 110, 97, 20, 10});
                put("WALK", new int[] {52, 105, 780, 142, 9});
                put("BLOCK", new int[] {64, 110, 12, 140, 1});
            }}
    ),
    LIU_KANG(
            "client/assets/characters/sprites/liu-kang.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {57, 98, 89, 18, 8});
                put("WALK", new int[] {45, 105, 19, 145, 9});
                put("BLOCK", new int[] {66, 102, 773, 18, 1});
            }}
    ),
    SONYA_BLADE(
            "client/assets/characters/sprites/sonya-blade.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {46, 105, 76, 15, 7});
                put("WALK", new int[] {46, 110, 20, 140, 9});
                put("BLOCK", new int[] {52, 102, 586, 20, 1});
            }}
    );

    private String spritePath;
    private Map<String, int[]> animationProperties;

    Characters(String spritePath, Map<String, int[]> animationProperties) {
        this.spritePath = spritePath;
        this.animationProperties = animationProperties;
    }

    public Map<String, int[]> getAnimationProperties() {
        return animationProperties;
    }

    public String getSpritePath() {
        return spritePath;
    }
}
