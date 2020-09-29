package com.NGS.Chess;

public class Coordinates
{
    private int X;
    private int Y;

    Coordinates()
    {
        X = -1;
        Y = -1;
    }

    Coordinates(int NewX, int NewY)
    {
        X = NewX;
        Y = NewY;
    }

    Coordinates(Coordinates OtherCoordinates)
    {
        X = OtherCoordinates.GetX();
        Y = OtherCoordinates.GetY();
    }

    public void SetX(int NewX)
    {
        X = NewX;
    }

    public int GetX()
    {
        return(X);
    }

    public void SetY(int NewY)
    {
        Y = NewY;
    }

    public int GetY()
    {
        return(Y);
    }

    public void Set(Coordinates OtherCoordinates)
    {
        X = OtherCoordinates.GetX();
        Y = OtherCoordinates.GetY();
    }

    public boolean IsOnBoard()
    {
        return((X < 8)&(X > -1)&(Y < 8)&(Y > -1));
    }

    public static boolean IsOnBoard(int XCoord, int YCoord)
    {
        return((XCoord < 8)&(XCoord > -1)&(YCoord < 8)&(YCoord > -1));
    }

    public String toString()
    {
        return(String.valueOf(X) + " " + String.valueOf(Y));
    }
}
