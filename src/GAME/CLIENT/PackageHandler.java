package GAME.CLIENT;

import GAME.CLIENT.PACKAGES.*;

public class PackageHandler {

    private PackageListener listener;

    public void unpack(Object o) {
        if (o instanceof PlayerNamePackage namePackage) {
            listener.gameUpdateOccurred(1, namePackage);
        } else if (o instanceof OpponentNamePackage opponentName) {
            listener.gameUpdateOccurred(2, opponentName);
        } else if (o instanceof ClientMessage chatMessage) {
            listener.gameUpdateOccurred(3, chatMessage);
        } else if (o instanceof MovePackage move) {
            listener.gameUpdateOccurred(4, move);
        } else if (o instanceof ScorePackage score) {
            listener.gameUpdateOccurred(5, score);
        }
    }

    public void setListener(PackageListener listener) {
        this.listener = listener;
    }

}