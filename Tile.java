package test;

import java.util.Objects;
import java.util.Random;

public class Tile {
    //Variables
    public final char letter;
    public final int score;

    //Private C'tor - not anyone can create tiles
    private Tile(char _letter, int _score) {
        this.letter = _letter;
        this.score = _score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    //Only the bag class can create tiles and access the tile constructor
    public static class Bag {

        private static Bag _instance = null; //Checks the bag if it exists
        public int[] scoreOfLetterArr;
        public Tile[] tiles; //Tiles array

        public int[] occurrenceLetter;

        public final int[] defaultLetters; //Saves the original number of Letters in the bag

        public int numOfTiles; //Num of overall tiles in the bag

        private Bag() {

            this.scoreOfLetterArr = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

            //Setting up the Tiles Array
            char c;
            int index = 0;
            final int size = 26;

            this.tiles = new Tile[size];

            for(c = 'A'; c <= 'Z'; c++, index++)
            {
                this.tiles[index] = new Tile(c, this.scoreOfLetterArr[index]);
            }

            this.occurrenceLetter = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            this.defaultLetters = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            this.numOfTiles = 98;

        } //Private ctor

        public static Bag getBag()
        {
            if(_instance == null)
            {
                _instance = new Bag();
            }

            return _instance;
        }

        public Tile getRand() {
            if (_instance == null) return null; //Bag is empty

            //Only works if number of Tiles isn't 0
            if (this.numOfTiles >= 0)
            {
                while(true)
                {
                    int randomNum = new Random().nextInt(tiles.length - 1);
                    if(this.occurrenceLetter[randomNum] >=  0)
                    {
                        this.occurrenceLetter[randomNum] = this.occurrenceLetter[randomNum] - 1;
                        this.numOfTiles--;
                        return this.tiles[randomNum];
                    }
                }
            }

            return null; //If it doesn't work
        }

        public Tile getTile(char _letter)
        {

            //invalid char
            if(!(_letter >= 'A' && _letter <= 'Z')) return null;

            //valid char
            if(this.numOfTiles >= 0)
            {
                for(int i = 0 ; i < tiles.length; i++)
                {
                    if(this.occurrenceLetter[i] >= 0)
                    if (tiles[i].letter == _letter)
                        return tiles[i];
                }
            }

            //no such char exists
            return null;
        }

        //given a letter get index in array
        public int indexOfTile(char c)
        {
            for(int i = 0; i < tiles.length ; i++)
            {
                if(tiles[i].letter == c)
                    return i;
            }

            return 0;
        }



        public void put(Tile t)
        {
           int index = indexOfTile(t.letter);

            if(this.size() < 98)
            {
                this.occurrenceLetter[index]++;
                this.numOfTiles++;
            }
        }

        public int size()
        {
            return numOfTiles;
        }

        public int[] getQuantities()
        {
            int[] copiedArray = new int[this.occurrenceLetter.length];
            System.arraycopy(this.occurrenceLetter,0, copiedArray, 0, this.occurrenceLetter.length);
            return copiedArray;
        }


    }
}
