package com.NGS.Chess;

public class Field
{
    private boolean Empty;
    private Piece LocalPiece;

    Field()
    {
        Empty = true;
    }

    Field(Piece NewPiece)
    {
        if(NewPiece.GetType() == PieceType.WrongType)
            Empty = true;
        else
        {
            Empty = false;
            LocalPiece = new Piece(NewPiece);
        }
    }

    public void PutPiece(Piece NewPiece)
    {
        Empty = false;
        LocalPiece = NewPiece;
    }

    public boolean Check()
    {
        return(Empty);
    }

    public Piece CheckPiece()
    {
        if(Empty)
            return(new Piece());
        else
            return(LocalPiece);
    }

    public Piece TakePiece()
    {
        Piece TMPPiece = LocalPiece;
        LocalPiece = null;
        Empty = true;
        return(TMPPiece);
    }

    public String ShowField()
    {
        if(Empty)
            return("   ");
        else
            return(LocalPiece.GetDesignation());
    }
}
