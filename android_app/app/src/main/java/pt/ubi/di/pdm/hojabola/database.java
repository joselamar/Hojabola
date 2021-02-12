package pt.ubi.di.pdm.hojabola;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.SecureRandom;
import java.util.Random;

import pt.ubi.di.pdm.hojabola.Adapter.stadiumSliderAdapter;

public class database extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "hojabola" ;


    private static final String Game = "CREATE TABLE IF NOT EXISTS Game (gScore char(5) DEFAULT NULL, Competition VARCHAR(30) NOT NULL, gDate DATETIME NOT NULL, gRound INTEGER NOT NULL, homeClub VARCHAR(30) NOT NULL, awayClub VARCHAR(30) NOT NULL, Coordinates VARCHAR(45) NOT NULL,stadium INTEGER NOT NULL, PRIMARY KEY (gDate,homeClub,awayClub))";
    private static final String Stadium = "CREATE TABLE IF NOT EXISTS Stadium(`idStadium` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`sName` VARCHAR(60) NOT NULL, `sClub` VARCHAR(45) NOT NULL, `sAdress` VARCHAR(45) NOT NULL, `sYear` VARCHAR(4) NOT NULL, `sCapacity` VARCHAR(6) NOT NULL, `sCoordinates` VARCHAR(45) NOT NULL, `sHistory` VARCHAR(1000) NOT NULL)";


    public database(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(Game);
            db.execSQL(Stadium);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        //super(,"hojabola",null,2);
    }

}
