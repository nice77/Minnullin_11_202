package server;

import java.util.UUID;

@FunctionalInterface
public interface SendEventToOther {
    public void sendMessage(UUID uuid, String message);
}
