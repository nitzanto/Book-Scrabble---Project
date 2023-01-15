package test;

import java.util.ArrayList;
import java.util.Vector;

public class Board {

    private final int SIZE = 15;

    public Square[][] b = new Square[SIZE][SIZE];

    public ArrayList <Word> wordsArray = new ArrayList<Word>(); //Stores the words in the board

    private static Board _instance = null;

    private Board()
    {
        buildGameBoard();
    }


    //
    public void buildGameBoard()
    {
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                this.b[i][j] = new Square();



        /*
         * Star = 5
         * Cyan = 4
         * Blue = 3
         * Yellow = 2
         * Red = 1
         * NULL = 0
         */

        //Start square of Board - 5//
        b[7][7].color = 5;

        //Cyan Squares of Board - 4//
        b[0][3].color = 4;
        b[0][11].color = 4;
        b[2][6].color = 4;
        b[2][8].color = 4;
        b[3][0].color = 4;
        b[3][7].color = 4;
        b[3][14].color = 4;
        b[6][2].color = 4;
        b[6][6].color = 4;
        b[6][8].color = 4;
        b[6][12].color = 4;
        b[7][3].color = 4;
        b[7][11].color = 4;
        b[8][2].color = 4;
        b[8][6].color = 4;
        b[8][8].color = 4;
        b[8][12].color = 4;
        b[11][0].color = 4;
        b[11][7].color = 4;
        b[11][14].color = 4;
        b[12][6].color = 4;
        b[12][8].color = 4;
        b[14][3].color = 4;
        b[14][11].color = 4;

        //Blue Squares Of Board - 3//
        b[1][5].color = 3;
        b[1][9].color = 3;
        b[5][1].color = 3;
        b[5][5].color = 3;
        b[5][9].color = 3;
        b[5][13].color = 3;
        b[9][1].color = 3;
        b[9][5].color = 3;
        b[9][9].color = 3;
        b[9][13].color = 3;
        b[13][5].color = 3;
        b[13][9].color = 3;

        //Yellow Squares of Board - 2//
        b[1][1].color = 2;
        b[1][13].color = 2;
        b[2][2].color = 2;
        b[2][12].color = 2;
        b[3][3].color = 2;
        b[3][11].color = 2;
        b[4][4].color = 2;
        b[4][10].color = 2;
        b[10][4].color = 2;
        b[10][10].color = 2;
        b[11][3].color = 2;
        b[11][11].color = 2;
        b[12][2].color = 2;
        b[12][12].color = 2;
        b[13][1].color = 2;
        b[13][13].color = 2;

        //Red Squares of Board - 1//
        b[0][0].color = 1;
        b[0][7].color = 1;
        b[0][14].color = 1;
        b[7][0].color = 1;
        b[7][14].color = 1;
        b[14][0].color = 1;
        b[14][7].color = 1;
        b[14][14].color = 1;

    }

    //Returns as Tiles Matrix for check
    public Tile[][] getTiles()
    {
        Tile[][] tileMatrix = new Tile[SIZE][SIZE];

        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                tileMatrix[i][j] = this.b[i][j].getTile();

        return tileMatrix;
    }


    public static Board getBoard()
    {
        if(_instance == null)
        {
            _instance = new Board();
        }

        return _instance;
    }

    public boolean boardLegal(Word w) {
        if(w == null)
            return false;

        if(!w.isVertical()) return legalityHorizontalWord(w);

        return legalityVerticalWord(w);
    }

    public boolean legalityHorizontalWord(Word w)
    {
        int len = w.getTiles().length;

        if(w.getRow() < 0 || w.getCol() + len - 1 > 14 || w.getCol() < 0 || w.getRow() > 14)
            return false;

        //If it's the first word
        if(this.b[7][7].getTile() == null)
            return (w.getRow() == 7) && (w.getCol() <= 7) && (w.getCol() + len >= 7);



        for (int i = 0; i < len ;i++) {
            if(this.b[w.getRow()][w.getCol() + i].getTile() != null)
                if(w.getTiles()[i] != null && !(w.getTiles()[i] == this.b[w.getRow()][w.getCol() + i].getTile()))
                    return false;
        }


        if(((w.getCol() > 0) && (this.b[w.getRow()][w.getCol() - 1].getTile() != null)) ||
                (w.getCol() + len < 15 && this.b[w.getRow()][w.getCol() + len].getTile() != null))
            return true;

        for (int i = 0; i < len ; i++) {
            if((w.getRow() > 0) && (this.b[w.getRow() - 1][w.getCol() + i].getTile() != null) ||
                    (w.getRow() < 14 && this.b[w.getRow() + 1][w.getCol() + i].getTile() != null))
                return true;
        }


        return false;
    }

    public boolean legalityVerticalWord(Word w)
    {
        int len = w.getTiles().length;

        if(w.getRow() < 0 || w.getRow() + len - 1 > 14 || w.getCol() < 0 || w.getCol() > 14)
            return false;

        //If it's the first word
        if(this.b[7][7].getTile() == null)
            return (w.getCol() == 7) && (w.getRow() <= 7) && (w.getRow() + len >= 7);



        for (int i = 0; i < len ;i++) {
            if(this.b[w.getRow() + i][w.getCol()].getTile() != null)
                if(w.getTiles()[i] != null && !(w.getTiles()[i] == this.b[w.getRow() + i][w.getCol()].getTile()))
                    return false;
        }


        if(((w.getRow() > 0) && (this.b[w.getRow() - 1][w.getCol()].getTile() != null)) ||
                ((w.getRow() + len < 15) && (this.b[w.getRow() + len][w.getCol()].getTile() != null)))
            return true;

        for (int i = 0; i < len ; i++) {
            if((w.getCol() > 0) && (this.b[w.getRow() + i][w.getCol() - 1].getTile() != null) ||
                    (w.getCol() < 14 && this.b[w.getRow() + i][w.getCol() + 1].getTile() != null))
                return true;

        }


        return false;
    }


    /*As requested*/
    public boolean dictionaryLegal(Word w)
    {
        return true;
    }

    //Finds the whole word in order to add to the ArrayList

    //Option 1: Word was found vertically
    public Word findWordVertically(int rowPosition, int colPosition, Tile t)
    {
        int tempRow = rowPosition;
        //In order to know the start of the word
        int startTileRow;

        //Checking letters above the given letter
        while(tempRow-1 >= 0 && this.b[tempRow-1][colPosition].getTile()!=null) tempRow -= 1;
        startTileRow = tempRow;
        if(this.b[tempRow][colPosition].getTile() == null) tempRow += 1;


        //Creating a vector to copy the word
        Vector<Tile> tempTiles = new Vector<Tile>();

        while(tempRow <= 14 && tempRow<rowPosition && this.b[tempRow][colPosition].getTile()!=null){
            tempTiles.add(this.b[tempRow][colPosition].getTile());
            tempRow += 1;
        }

        //In order to add the start letter of the word
        tempTiles.add(t);

        tempRow = ++rowPosition;

        //Checking letters below the given letter
        while(tempRow <= 14 && this.b[tempRow][colPosition].getTile()!=null){
            tempTiles.add(this.b[tempRow][colPosition].getTile());
            tempRow += 1;
        }

        Tile[] newTiles;
        //Copying vector content into a Tiles Array
        newTiles = tempTiles.toArray(new Tile[tempTiles.size()]);

        //Return the vertically found word
        return new Word(newTiles, startTileRow, colPosition, true);
    }

    //Option 2: Word was found horizontally
    public Word findWordHorizontally(int rowPosition,int colPosition, Tile t)
    {
        int tempCol = colPosition;

        //In order to know the start of the word
        int startTileCol;
        while(tempCol-1 >= 0 && this.b[rowPosition][tempCol-1].getTile()!=null) tempCol--;


        startTileCol = tempCol;

        if(this.b[rowPosition][tempCol].getTile() == null) tempCol++;

        //Creating a vector to copy the word
        Vector<Tile> tempTilesVec = new Vector<Tile>();

        while(tempCol <= 14 && tempCol<colPosition && this.b[rowPosition][tempCol].getTile()!=null){
            tempTilesVec.add(this.b[rowPosition][tempCol].getTile());
            tempCol += 1;
        }

        //In order to add the start letter of the word
        tempTilesVec.add(t);

        tempCol = ++colPosition;

        //In order to check if there's any other letter below the starting letter
        while(tempCol <= 14 && this.b[rowPosition][tempCol].getTile()!=null){
            tempTilesVec.add(this.b[rowPosition][tempCol].getTile());
            tempCol += 1;
        }

        Tile[] newTiles;
        //Copying vector content into a Tiles Array
        newTiles = tempTilesVec.toArray(new Tile[tempTilesVec.size()]);

        //Returning the found word
        return new Word(newTiles, rowPosition, startTileCol, false);
    }

    //A word is contained in a bigger word//
    /*
     * Finding the contained word
     * Option 1: Word is placed horizontally
     * Option 2: Word is placed vertically
     */

    //Option 1: Horizontally
    public Word findContainedWord_H(Word w, int rowPosition, int colPosition)
    {
        Tile t = w.getTiles()[0];
        Word biggerWord = findWordHorizontally(rowPosition,colPosition, t);

        if(w.getTiles().length == biggerWord.getTiles().length)
            return w; // <-- Return the same word because it isn't contained
        else return biggerWord; // <-- Word is contained return the bigger word

    }

    //Option 2: Vertically
    public Word findContainedWord_V(Word w, int rowPosition, int colPosition)
    {
        Tile t = w.getTiles()[0];
        Word biggerWord = findWordVertically(rowPosition,colPosition, t);

        if(w.getTiles().length == biggerWord.getTiles().length)
            return w; // <-- Return the same word because it isn't contained
        else return biggerWord; // <-- Word is contained return the bigger word

    }

    public ArrayList<Word> getWords(Word w)
    {
        ArrayList<Word> tempList = new ArrayList<Word>();
        int length = w.getTiles().length;
        ArrayList<Word> validWords = new ArrayList<Word>(); //Stores valid Words after checking

        //The actual word which is part of the bigger word, Actual Word: Phone, Word: Telephone

        //If a first word in the matrix
        if(boardLegal(w))
            tempList.add(w);

        //Checking possible additional connected word to the current word

        //the Word is placed horizontally
        if(!w.isVertical()){

            //Word is placed horizontally, therefore check top and bottom tiles to find possible connected words.
            //looping to find possible connected words to w
            for(int i = 0; i < length; i++)
                if (this.b[w.getRow() + 1][w.getCol() + i].getTile() != null || this.b[w.getRow() - 1][w.getCol() + i].getTile() != null)
                    tempList.add(findWordVertically(w.getRow(), w.getCol() + i, w.getTiles()[i]));
            //Checking if w is horizontally contained in a bigger word
            if(w.getCol() + w.getTiles().length < 15) {
                if(this.b[w.getRow()][w.getCol() + w.getTiles().length].getTile() != null || this.b[w.getRow()][w.getCol()-1].getTile() != null) {
                    Word tempWord = new Word(findContainedWord_H(w, w.getRow(), w.getCol()));

                    //Part of a bigger word therefore it's contained so add it to the list
                    if (!tempWord.equals(w)) {
                        tempList.add(tempWord);
                    }
                }
            }
        }
        else //Word is placed vertically
        {
            //Word is placed vertically, checking left and right to find possible connected words
            for(int i = 0; i<length; i++){
                if(this.b[w.getRow()+i][w.getCol()-1].getTile() != null || this.b[w.getRow()+i][w.getCol()+1].getTile() != null)
                    tempList.add(findWordHorizontally(w.getRow() + i, w.getCol(), w.getTiles()[i]));

                //Checking if w is vertically contained in a bigger word

                if(w.getRow() + w.getTiles().length < 15) {
                    if (this.b[w.getRow() + w.getTiles().length][w.getCol()].getTile() != null || this.b[w.getRow() - 1][w.getCol()].getTile() != null) {
                        Word tempWord = new Word(findContainedWord_V(w, w.getRow(), w.getCol()));

                        //Part of a bigger word therefore it's contained so add it to the list
                        if (!tempWord.equals(w)) {
                            tempList.add(tempWord);
                        }
                    }
                }
            }
        }

        //Additional Checks
        //Adding words that aren't in
        for(Word obj: tempList)
            if(!wordsArray.contains(obj))
                validWords.add(obj);

        //After checking store all the valid words
        wordsArray.addAll(validWords);
        return validWords;
    }

    //**Score Calculation**//

    /*
     * Star = 5 (Double Points Word)
     * Cyan = 4 (Double Points Letter)
     * Blue = 3 (Triple Points Letter)
     * Yellow = 2 (Double Points Word)
     * Red = 1 (Triple Points Word)
     * NULL = 0
     */

    public int getScore(Word w)
    {
        int length = w.getTiles().length;
        int redOccurrence = 0;
        int yellowOccurrence = 0;

        int score = 0;

        //Horizontally placed word check
        if(!w.isVertical())
        {
            for(int i = 0; i < length; i++)
            {
                switch(this.b[w.getRow()][w.getCol() + i].getColor())
                {

                    case 1: //Color is Red (Triple the word)
                        redOccurrence++;
                        score = score + w.getTiles()[i].score;
                        break;

                    case 2: //Color is Yellow (Double the word)
                        yellowOccurrence++;
                        score = score + w.getTiles()[i].score;
                        break;

                    case 3: //Color is Blue (Triple the letter)
                        score = score + w.getTiles()[i].score * 3;
                        break;

                    case 4: //Color is Cyan (Double the letter)
                        score = score + w.getTiles()[i].score * 2;
                        break;

                    case 5: //Star (Double the word)
                        this.b[7][7].setColor(0);//Can only be calculated once the score of the word
                        yellowOccurrence = yellowOccurrence + 1;
                        score = score + w.getTiles()[i].score;
                        break;

                    default:
                        score += w.getTiles()[i].score;
                        break;
                }
            }
        }
        else //Word is placed vertically
        {

            for(int i = 0; i < length; i++)
            {
                switch(this.b[w.getRow() + i][w.getCol()].getColor())
                {

                    case 1: //Color is Red
                        redOccurrence++;
                        score = score + w.getTiles()[i].score;
                        break;
                    case 2: //Color is Yellow
                        yellowOccurrence++;
                        score = score + w.getTiles()[i].score;
                        break;
                    case 3: //Color is Blue
                        score = score + w.getTiles()[i].score * 3;
                        break;
                    case 4: //Color is Cyan
                        score = score + w.getTiles()[i].score * 2;
                        break;
                    case 5: //Star
                        this.b[7][7].setColor(0); //To correct the score
                        yellowOccurrence = yellowOccurrence + 1;
                        score = score + w.getTiles()[i].score;
                        break;
                    default:
                        score += w.getTiles()[i].score;
                        break;
                }
            }
        }

        if(redOccurrence !=0)
            score = score * redOccurrence * 3;

        else if(yellowOccurrence != 0)
            score = score * yellowOccurrence * 2;

        return score;
    }


    //Placement in board//

    //Main function to calc the score for a valid word
    public int tryPlaceWord(Word w)
    {
        int score = 0;

        //Before checking if the word is valid we'll have to check if it's complete and not missing any letters
        w = checkIfCompleteWord(w);

        if(!boardLegal(w))
            return 0; //Invalid word cannot place it on the board

        //All the created new words from the placement
        ArrayList<Word> words = getWords(w);

        //Checking if the words are valid in dictionary
        for(Word t: words)
            if(!dictionaryLegal(t))
                return 0;

        //After all the checks and calc score place the word on the board
        placeTheWord(w);


        //Calc the score for the new created placed words
        for(Word t: words)
            score = score + getScore(t);

        return score;
    }


    //A function to check if the world is complete
    //Could be missing a letter
    public Word checkIfCompleteWord(Word w)
    {
        int length = w.getTiles().length;

        Vector <Tile> tiles = new Vector<Tile>();

        if(!w.isVertical()) {
            for (int i = 0; i < length; i++)
                if (w.getTiles()[i] == null)
                    tiles.add(this.b[w.getRow()][w.getCol() + i].getTile());
                else
                    tiles.add(w.getTiles()[i]);
        }
        else
            for(int i = 0; i < length; i++)
                if(w.getTiles()[i] == null)
                    tiles.add(this.b[w.getRow() + i][w.getCol()].getTile());
                else
                    tiles.add(w.getTiles()[i]);

        Tile[] t;
        t = tiles.toArray(new Tile[tiles.size()]);

        if(w.isVertical())  return new Word(t, w.getRow(), w.getCol(),true);
        else return new Word(t, w.getRow(), w.getCol(),false);
    }

    //Placing the word on the board if all checks are indeed valid
    public void placeTheWord(Word w)
    {
        int length = w.getTiles().length;

        //Horizontally placed Word
        if(!w.isVertical())
            for(int i = 0; i < length ; i++) {
                if(w.getTiles()[i] != null)
                    this.b[w.getRow()][w.getCol() + i].setTile(w.getTiles()[i]);
            }

        else //Vertically placed Word
            for(int i = 0; i < length ; i++) {
                if(w.getTiles()[i] != null)
                    this.b[w.getRow() + i][w.getCol()].setTile(w.getTiles()[i]);
            }
    }


    //Setting up an inner class for Board
    /*Squares of the GameBoard (Matrix)
     *Contains tiles, color
     * Each square in the matrix holds information
     */

    public static class Square{

        public Tile tile = null;
        public int color = 0;

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tile) {
            this.tile = tile;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }

}
