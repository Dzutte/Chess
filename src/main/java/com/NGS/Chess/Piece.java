package com.NGS.Chess;

public class Piece
{
    private PieceColor Color;
    private PieceType Type;
    private boolean NotMoved;

    Piece()
    {
        Color = PieceColor.WrongColor;
        Type = PieceType.WrongType;
        NotMoved = true;
    }

    Piece(PieceColor NewColor, PieceType NewType)
    {
        Color = NewColor;
        Type = NewType;
        NotMoved = true;
    }

    Piece(Piece OtherPiece)
    {
        Color = OtherPiece.GetColor();
        Type = OtherPiece.GetType();
        NotMoved = OtherPiece.CheckStillness();
    }

    public PieceColor GetColor()
    {
        return(Color);
    }

    public void SetType(PieceType NewType)
    {
        Type = NewType;
    }

    public PieceType GetType()
    {
        return(Type);
    }

    public void SetMoved()
    {
        NotMoved = false;
    }

    public boolean CheckStillness()
    {
        return(NotMoved);
    }

    public boolean IsEnemy(Piece OtherPiece)
    {
        return(OtherPiece.GetColor() != Color);
    }

    private String ColorToString()
    {
        switch(Color)
        {
            case Black:
                return("B");
            case White:
                return("W");
            default:
                return("E");
        }
    }

    private String TypeToString()
    {
        switch(Type)
        {
            case Pawn:
                return("Pn");
            case Knight:
                return("Kn");
            case Bishop:
                return("Bs");
            case Rook:
                return("Rk");
            case Queen:
                return("Qn");
            case King:
                return("Kg");
            default:
                return("Er");
        }
    }

    public String GetDesignation()
    {
        return(ColorToString() + TypeToString());
    }
}
