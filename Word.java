package test;

import java.util.Arrays;

public class Word {

    private final boolean vertical;

private final int row; //Position of First Tile of the Word
private final int col; //Position of First Tile of the Word
private final Tile[] tiles;

    //Constructor//
    public Word(Tile[] arr,int _row, int _col, boolean _vertical) {

        //Copying the array content
        this.tiles = new Tile[arr.length];
        System.arraycopy(arr, 0 ,tiles, 0, arr.length);

        //Setting the positioning
        this.row = _row;
        this.col = _col;

        this.vertical = _vertical;
    }

    public Word(Word other)
    {
        this.tiles = new Tile[other.getTiles().length];
        System.arraycopy(other.getTiles(), 0,tiles,0, other.getTiles().length);
        this.row = other.row;
        this.col = other.col;
        this.vertical = other.vertical;
    }

    //Methods//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return vertical == word.vertical && row == word.row && col == word.col && Arrays.equals(tiles, word.tiles);
    }


    public boolean isVertical() {
        return vertical;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
