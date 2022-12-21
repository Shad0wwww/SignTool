package dk.signtool.shadow.listeners;

import dk.signtool.shadow.Signtool;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QuitListener implements Consumer<ServerData> {
    private Signtool addon;

    public QuitListener(Signtool addon) {
        this.addon = addon;
    }

    @Override
    public void accept(ServerData serverData) {
        Signtool.connectedToServer = false;

    }

}
