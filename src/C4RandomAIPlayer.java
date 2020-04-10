import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {
    char playerPiece;
    public C4RandomAIPlayer(char piece)
    {
        this.playerPiece = piece;
    }

    @Override
    public int columnToPlay() {
        Random generator = new Random();
        return generator.nextInt(7);
    }

    @Override
    public char getPiece() {
        return this.playerPiece;
    }

}
