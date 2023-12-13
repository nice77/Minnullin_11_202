package client.assets.characters;

import java.util.HashMap;
import java.util.Map;

public enum Characters {
    SCORPION(
            "/client/assets/characters/sprites/scorpion.png",
            "src/client/assets/characters/announcers/scorpion.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {53, 110, 77, 17, 6});
                put("WALK", new int[] {50, 110, 10, 130, 9});
                put("BLOCK", new int[] {48, 108, 514, 17, 1});
                put("HIT", new int[] {60, 110, 800, 247, 2});
                put("INJURED", new int[] {58, 105, 10, 915, 2});
                put("VICTORY", new int[] {50, 110, 983, 130, 2});
                put("DEAD", new int[] {85, 97, 0, 1029, 5});
            }}
    ),
    SUB_ZERO(
            "/client/assets/characters/sprites/sub-zero.png",
            "src/client/assets/characters/announcers/sub-zero.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {70, 150, 106, 30, 10});
                put("WALK", new int[] {75, 162, 25, 200, 9});
                put("BLOCK", new int[] {77, 156, 975, 25, 1});
                put("HIT", new int[] {89, 166, 1200, 372, 2});
                put("INJURED", new int[] {80, 150, 15, 1373, 2});
                put("VICTORY", new int[] {77, 187, 1480, 190, 2});
                put("DEAD", new int[] {126, 144, 0, 1544, 5});
            }}
    ),
    JOHNNY_CAGE(
            "client/assets/characters/sprites/johnny_cage.png",
            "src/client/assets/characters/announcers/johnny-cage.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {67, 105, 110, 20, 7});
                put("WALK", new int[] {60, 106, 25, 135, 9});
                put("BLOCK", new int[] {62, 104, 613, 17, 1});
                put("HIT", new int[] {65, 107, 245, 370, 3});
                put("INJURED", new int[] {70, 105, 10, 860, 4});
                put("VICTORY", new int[] {60, 107, 1245, 135, 12});
                put("DEAD", new int[] {81, 111, 0, 974, 7});
            }}
    ),
    KANO(
            "client/assets/characters/sprites/kano.png",
            "src/client/assets/characters/announcers/kano.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {54, 104, 81, 25, 7});
                put("WALK", new int[] {55, 103, 20, 153, 9});
                put("BLOCK", new int[] {48, 110, 476, 17, 1});
                put("HIT", new int[] {70, 110, 618, 283, 3});
                put("INJURED", new int[] {60, 110, 10, 630, 3});
                put("VICTORY", new int[] {75, 120, 1220, 135, 5});
                put("DEAD", new int[] {83, 105, 0, 748, 5});
            }}
    ),
    RAIDEN(
            "client/assets/characters/sprites/raiden.png",
            "src/client/assets/characters/announcers/raiden.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {59, 110, 97, 20, 10});
                put("WALK", new int[] {52, 105, 780, 142, 9});
                put("BLOCK", new int[] {64, 110, 12, 140, 1});
                put("HIT", new int[] {65, 105, 650, 393, 3});
                put("INJURED", new int[] {65, 105, 10, 758, 4});
                put("VICTORY", new int[] {81, 122, 4, 259, 5});
                put("DEAD", new int[] {85, 91, 0, 885, 6});
            }}
    ),
    LIU_KANG(
            "client/assets/characters/sprites/liu-kang.png",
            "src/client/assets/characters/announcers/liu-kang.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {57, 98, 89, 18, 8});
                put("WALK", new int[] {45, 105, 19, 145, 9});
                put("BLOCK", new int[] {66, 102, 773, 18, 1});
                put("HIT", new int[] {66, 112, 250, 387, 3});
                put("INJURED", new int[] {55, 110, 10, 855, 3});
                put("VICTORY", new int[] {81, 120, 775, 250, 13});
                put("DEAD", new int[] {79, 106, 0, 973, 8});
            }}
    ),
    SONYA_BLADE(
            "client/assets/characters/sprites/sonya-blade.png",
            "src/client/assets/characters/announcers/sonya-blade.wav",
            new HashMap<String, int[]>() {{
                // width, height, offsetX, offsetY, count
                put("IDLE", new int[] {46, 105, 76, 15, 7});
                put("WALK", new int[] {46, 110, 20, 140, 9});
                put("BLOCK", new int[] {52, 102, 586, 20, 1});
                put("HIT", new int[] {58, 105, 598, 260, 3});
                put("INJURED", new int[] {50, 100, 10, 630, 3});
                put("VICTORY", new int[] {67, 111, 1045, 132, 8});
                put("DEAD", new int[] {84, 92, 0, 739, 7});
            }}
    );

    private String spritePath;
    private String announcerPath;
    private Map<String, int[]> animationProperties;

    Characters(String spritePath, String announcerPath, Map<String, int[]> animationProperties) {
        this.spritePath = spritePath;
        this.announcerPath = announcerPath;
        this.animationProperties = animationProperties;
    }

    public Map<String, int[]> getAnimationProperties() {
        return animationProperties;
    }

    public String getSpritePath() {
        return spritePath;
    }
    public String getAnnouncerPath() {
        return announcerPath;
    }
}
