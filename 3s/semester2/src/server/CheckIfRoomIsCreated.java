package server;

import java.util.UUID;

@FunctionalInterface
public interface CheckIfRoomIsCreated {
    public void checkIfRoomIsCreated(UUID uuid);
}
