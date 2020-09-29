package com.NGS.Chess;

public class Turn
{
    private Coordinates From;
    private Coordinates To;

    Turn()
    {
        From = new Coordinates();
        To = new Coordinates();
    }

    Turn(int FromX, int FromY, int ToX, int ToY)
    {
        From = new Coordinates(FromX, FromY);
        To = new Coordinates(ToX, ToY);
    }

    Turn(Turn OtherTurn)
    {
        From = new Coordinates(OtherTurn.GetFrom());
        To = new Coordinates(OtherTurn.GetTo());
    }

    public void SetFromX(int X)
    {
        From.SetX(X);
    }

    public int GetFromX()
    {
        return(From.GetX());
    }

    public void SetFromY(int Y)
    {
        From.SetY(Y);
    }

    public int GetFromY()
    {
        return(From.GetY());
    }

    public void SetFrom(Coordinates NewCoordinates)
    {
        From = NewCoordinates;
    }

    public Coordinates GetFrom()
    {
        return(From);
    }

    public void SetToX(int X)
    {
        To.SetX(X);
    }

    public int GetToX()
    {
        return(To.GetX());
    }

    public void SetToY(int Y)
    {
        To.SetY(Y);
    }

    public int GetToY()
    {
        return(To.GetY());
    }

    public void SetTo(Coordinates NewCoordinates)
    {
        To = NewCoordinates;
    }

    public Coordinates GetTo()
    {
        return(To);
    }

    public boolean IsVertical()
    {
        return(From.GetX() == To.GetX());
    }

    public boolean IsHorizontal()
    {
        return(From.GetY() == To.GetY());
    }

    public boolean IsDiagonal()
    {
        return(Math.abs(GetVerticalMove()) == Math.abs(GetHorizontalMove()));
    }

    public boolean IsRisingDiagonal()
    {
        Turn TMP = new Turn(this);
        TMP.FormX();
        return(TMP.GetVerticalMove() > 0);
    }

    public boolean IsFake()
    {
        return((From.GetX() == To.GetX()) & (From.GetY() == To.GetY()));
    }

    public boolean IsCastling()
    {
        return(IsHorizontal() & (From.GetX() == 4) & ((To.GetX() == 2)|(To.GetX() == 6)));
    }

    public int GetVerticalMove()
    {
        return(To.GetY() - From.GetY());
    }

    public int GetHorizontalMove()
    {
        return(To.GetX() - From.GetX());
    }

    public void Revers(Turn OtherTurn)
    {
        From = OtherTurn.GetTo();
        To = OtherTurn.GetFrom();
    }

    public void FormX()
    {
        if (From.GetX() > To.GetX()) {
            Coordinates TMP = new Coordinates(To);
            To.Set(From);
            From.Set(TMP);
        }
    }

    public void FormY()
    {
        if(From.GetY() > To.GetY())
        {
            Coordinates TMP = new Coordinates(To);
            To.Set(From);
            From.Set(TMP);
        }
    }

    public String toString()
    {
        return(From.toString() + "-" + To.toString());
    }
}
