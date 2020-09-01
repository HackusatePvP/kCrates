package cc.fatenetwork.kcrates.server;

import lombok.Data;

@Data
public class ServerManager {
    private ServerState serverState = ServerState.KITPVP;

}
