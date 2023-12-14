package server;

import java.util.UUID;

@FunctionalInterface
public interface DestroyRoom {
    public void destroyRoom(UUID uuid);
}
