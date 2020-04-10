public class Connect4Grid2DArray implements Connect4Grid{

    char[][] grid;
    Connect4Grid2DArray()
    {
        grid =  new char[BOARD_HEIGHT][BOARD_WIDTH];
        emptyGrid();
    }
    @Override
    public String toString() {
        String gridString = "";
        for(int row = 0;row<BOARD_HEIGHT;row++)
        {
            gridString+="\n|";
            for(int column = 0;(column<BOARD_WIDTH); column++)
            {
                gridString+=(grid[row][column]);
                gridString+=("|");
            }
        }
        gridString+=("\n---------------");
        return gridString+=("\n 1 2 3 4 5 6 7 \n");
    }

    @Override
    public void emptyGrid()
    {
        {
            for(int row=0; row<BOARD_HEIGHT;row++)
            {
                for(int column= 0; column<BOARD_WIDTH;column++)
                {
                    grid[row][column]=BLANK;
                }
            }

        }
    }

    @Override
    public boolean isValidColumn(int column) {
        if((column>=0)&&(column<BOARD_WIDTH))
        {
            return !isColumnFull(column);
        }
        return false;
    }

    @Override
    public boolean isColumnFull(int column) {
        for (char[] chars : grid) {
            if (chars[column] == BLANK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void dropPiece(ConnectPlayer player, int column) {
        // TODO Auto-generated method stub
        int lastIndex = 0;
        for(int row=0;row<BOARD_HEIGHT;row++)
        {
            // If we found something, which means if the character is not
            // zero character
            if (grid[row][column] != BLANK) {
                // Put the disk on top of the current one.
                grid[row-1][column] = player.getPiece();
                return;
            }
        }
        grid[5][column] = player.getPiece();
    }

    @Override
    public boolean didLastPieceConnect4() {
        for (int row = 0; row < BOARD_HEIGHT; row++)
        {
            for (int col = 0; col < BOARD_WIDTH; col++)
            {
                char connect4SlotToCheck = grid[row][col];
                if (connect4SlotToCheck == BLANK)
                {
                    continue;
                }
                if (col + 3 < BOARD_WIDTH &&
                        connect4SlotToCheck == grid[row][col+1] &&
                        connect4SlotToCheck == grid[row][col+2] &&
                        connect4SlotToCheck == grid[row][col+3])
                {
                    return true;
                }
                if (row + 3 < BOARD_HEIGHT)
                {
                    if (connect4SlotToCheck == grid[row+1][col] &&
                            connect4SlotToCheck == grid[row+2][col] &&
                            connect4SlotToCheck == grid[row+3][col])
                    {
                        return true;
                    }
                    if (col + 3 < BOARD_WIDTH &&
                            connect4SlotToCheck == grid[row+1][col+1] &&
                            connect4SlotToCheck == grid[row+2][col+2] &&
                            connect4SlotToCheck == grid[row+3][col+3])
                    {
                        return true;
                    }
                    if (col - 3 >= 0 &&
                            connect4SlotToCheck == grid[row+1][col-1] &&
                            connect4SlotToCheck == grid[row+2][col-2] &&
                            connect4SlotToCheck == grid[row+3][col-3])
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isGridFull() {
        //Check for the presence of a blank character, if present, grid is not full, else return true;
        for(int col = 0; col<grid[0].length;col++)
        {
            if(grid[0][col]==BLANK)
            {
                return false;
            }
        }
        return true;
    }

}