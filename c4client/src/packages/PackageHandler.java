package packages;

public class PackageHandler {

    private PackageListener listener;

    public void unpack(Object o) {
        if (o instanceof PlayerPackage playerPackage) {
            listener.gameUpdateOccurred(1, playerPackage);
        } else if (o instanceof TeamPackage teamPackage) {
            listener.gameUpdateOccurred(3, teamPackage);
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