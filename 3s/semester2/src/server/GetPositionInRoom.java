package server;

import java.util.UUID;

@FunctionalInterface
public interface GetPositionInRoom {
    public int getPositionInRoom(UUID uuid, ServerConnection serverConnection);
}
