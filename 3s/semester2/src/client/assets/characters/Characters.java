package client.assets.characters;

import java.util.HashMap;
import java.util.Map;

public enum Characters {
    SCORPION(
            "/client/assets/characters/sprites/scorpion.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {53, 110, 77, 17, 6});
            }}
    ),
    SUB_ZERO(
            "/client/assets/characters/sprites/sub-zero.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {(int) (46 * 1.5) + 1, 150, (int) (74 * 1.5) - 5, 30, 10});
            }}
    ),
    JOHNNY_CAGE(
            "client/assets/characters/sprites/johnny_cage.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {67, 105, 110, 20, 7});
            }}

    ),
    KANO(
            "client/assets/characters/sprites/kano.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {54, 104, 81, 25, 7});
            }}
    ),
    RAIDEN(
            "client/assets/characters/sprites/raiden.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {59, 110, 97, 20, 10});
            }}
    ),
    LIU_KANG(
            "client/assets/characters/sprites/liu-kang.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {57, 98, 89, 18, 8});
            }}
    ),
    SONYA_BLADE(
            "client/assets/characters/sprites/sonya-blade.png",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("idle", new int[] {46, 105, 76, 15, 7});
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
