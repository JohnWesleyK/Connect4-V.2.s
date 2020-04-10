public interface Connect4Grid {

    public void emptyGrid();
    public String toString();
    public boolean isValidColumn(int column);
    public boolean isColumnFull(int column);
    public void dropPiece(ConnectPlayer player, int column);
    public boolean didLastPieceConnect4();
    public boolean isGridFull();

    public static final int BOARD_HEIGHT = 6;
    public static final int BOARD_WIDTH = 7;
    public static final char BLANK = ' ';

}