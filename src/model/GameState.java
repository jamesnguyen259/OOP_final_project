package model;
// khai bao cac thuoc tinh trong game
public class GameState {
    public static final int SECOND = 100; // use for couting down
    public static final int CURRENT_LEVEL = 1; // starting level
    public static final int CURRENT_SCORE = 0; // starting score
    public static final int WIDTH_IMAGE = 54; // pokemon image
    public static final int HEIGHT_IMAGE = 48;
    public static final int WIDTH_FRAME = WIDTH_IMAGE * 18;
    public static final int HEIGHT_FRAME = HEIGHT_IMAGE * 11;
    public static final int POKEMON_PIECES = 36; // number of pokemon's image in images folder
    public static final int NUMBER_OF_BGR = 10; // number of background image

    public static final int MAX_ROWS = 18;
    public static final int MAX_COLUMNS = 11;
    public static final int POKEMON_ROWS = 10; // matrix pokemon
    public static final int POKEMON_COLUMNS = 6;
}
