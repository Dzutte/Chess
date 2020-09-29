package com.NGS.Chess;

public class SimpleBoard
{
    protected Field[][] Fields;

    SimpleBoard()
    {
        Fields = new Field[8][8];
        for(int I = 0; I < 8; I++)
            for (int J = 0; J < 8; J++)
                Fields[I][J] = new Field();
    }

    SimpleBoard(SimpleBoard OtherBoard)
    {
        Fields = new Field[8][8];
        for(int I = 0; I < 8; I++)
            for (int J = 0; J < 8; J++)
                Fields[I][J] = new Field(OtherBoard.CheckPiece(I, J));
    }

    public boolean PlacePiece(int X, int Y, Piece NewPiece)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            Fields[X][Y].PutPiece(NewPiece);
            return(true);
        }
        else
            return(false);
    }

    public boolean CheckFieldEmpty(int X, int Y)
    {
        return(Fields[X][Y].Check());
    }

    public boolean CheckFieldEmpty(Coordinates FieldCoordinates)
    {
        return(CheckFieldEmpty(FieldCoordinates.GetX(), FieldCoordinates.GetY()));
    }

    public Piece CheckPiece(int X, int Y)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
            return(Fields[X][Y].CheckPiece());
        else
        {
            Piece NoPiece = new Piece();
            return(NoPiece);
        }
    }

    public Piece CheckPiece(Coordinates FieldCoordinates)
    {
        return(CheckPiece(FieldCoordinates.GetX(), FieldCoordinates.GetY()));
    }

    public boolean CheckOwnership(int X, int Y, PieceColor PlayerColor)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            return (Fields[X][Y].CheckPiece().GetColor() == PlayerColor);
        }
        else
            return(false);
    }

    public boolean CheckOwnership(Coordinates FieldCoordinates, PieceColor PlayerColor)
    {
        return(CheckOwnership(FieldCoordinates.GetX(), FieldCoordinates.GetY(), PlayerColor));
    }

    public boolean CheckClearVertical(Turn CurrentTurn)
    {
        if(Math.abs(CurrentTurn.GetVerticalMove()) == 1)
            return(true);
        else
        {
            boolean Emptiness = true;
            Turn FormattedTurn = new Turn(CurrentTurn);
            FormattedTurn.FormY();
            for(int I = FormattedTurn.GetFromY() + 1; I < FormattedTurn.GetToY(); I++)
                if(!CheckFieldEmpty(FormattedTurn.GetFromX(), I))
                    Emptiness = false;
            return(Emptiness);
        }
    }

    public boolean CheckClearHorizontal(Turn CurrentTurn)
    {
        if(Math.abs(CurrentTurn.GetHorizontalMove()) == 1)
            return(true);
        else
        {
            boolean Emptiness = true;
            Turn FormattedTurn = new Turn(CurrentTurn);
            FormattedTurn.FormX();
            for(int I = FormattedTurn.GetFromX() + 1; I < FormattedTurn.GetToX(); I++)
                if(!CheckFieldEmpty(I, FormattedTurn.GetFromY()))
                    Emptiness = false;
            return(Emptiness);
        }
    }

    public boolean CheckClearDiagonal(Turn CurrentTurn)
    {
        int Direction;
        if(CurrentTurn.IsRisingDiagonal())
            Direction = 1;
        else
            Direction = -1;
        if(Math.abs(CurrentTurn.GetHorizontalMove()) == 1)
            return(true);
        else
        {
            boolean Emptiness = true;
            Turn FormattedTurn = new Turn(CurrentTurn);
            FormattedTurn.FormX();
            for(int I = 1; I < FormattedTurn.GetHorizontalMove(); I++)
                if(!CheckFieldEmpty(FormattedTurn.GetFromX() + I, FormattedTurn.GetFromY() + (I * Direction)))
                    Emptiness = false;
            return(Emptiness);
        }
    }

    public boolean CastlingAvailable(Turn CastlingTurn)
    {
        if(((CastlingTurn.GetToY() == 0) & (CheckPiece(CastlingTurn.GetFrom()).GetColor() == PieceColor.White))
                |((CastlingTurn.GetToY() == 7) & (CheckPiece(CastlingTurn.GetFrom()).GetColor() == PieceColor.Black)))
        {
            if(CheckPiece(CastlingTurn.GetFrom()).CheckStillness())
            {
                int CastlingLine;
                if(CastlingTurn.GetToY() == 0)
                    CastlingLine = 0;
                else
                    CastlingLine = 7;
                Piece CastlingRoot;
                int CastlingRootX;
                if(CastlingTurn.GetToX() == 2)
                {
                    CastlingRoot = CheckPiece(0, CastlingLine);
                    CastlingRootX = 0;
                }
                else
                {
                    CastlingRoot = CheckPiece(7, CastlingLine);
                    CastlingRootX = 7;
                }
                return(CastlingRoot.CheckStillness()
                        & CheckClearHorizontal(new Turn(CastlingTurn.GetFromX(), CastlingLine, CastlingRootX, CastlingLine)));
            }
            else
                return(false);
        }
        else
            return(false);
    }

    public boolean PawnCrossedBoard()
    {
        for(int X = 0; X < 8; X++)
        {
            if(!CheckFieldEmpty(X, 0))
                if(CheckPiece(X, 0).GetType() == PieceType.Pawn)
                    return(true);
            if(!CheckFieldEmpty(X, 7))
                if(CheckPiece(X, 7).GetType() == PieceType.Pawn)
                    return(true);
        }
        return(false);
    }

    public Piece GetPawnFromEnd()
    {
        for(int X = 0; X < 8; X++)
        {
            if(!CheckFieldEmpty(X, 0))
                if(CheckPiece(X, 0).GetType() == PieceType.Pawn)
                    return(CheckPiece(X, 0));
            if(!CheckFieldEmpty(X, 7))
                if(CheckPiece(X, 7).GetType() == PieceType.Pawn)
                    return(CheckPiece(X, 7));
        }
        AppLogger.Log().error("No Pawn on the end of board");
        return(new Piece());
    }

    public void Move(int FromX, int FromY, int ToX, int ToY)
    {
        Fields[ToX][ToY].PutPiece(Fields[FromX][FromY].TakePiece());
        Fields[ToX][ToY].CheckPiece().SetMoved();
    }

    public void Move(Coordinates From, Coordinates To)
    {
        Move(From.GetX(), From.GetY(), To.GetX(), To.GetY());
    }

    public void Move(Turn T)
    {
        if(T.IsCastling())// Additional move Rook
        {
            if(T.GetToX() == 2)
                Move(0, T.GetFromY(), 3, T.GetToY());
            else
                Move(7, T.GetFromY(), 5, T.GetToY());
        }
        Move(T.GetFrom(), T.GetTo());
    }

    public boolean Remove(int X, int Y)
    {
        if((X < 8)&(X > -1)&(Y < 8)&(Y > -1))
        {
            Fields[X][Y].TakePiece();
            return(true);
        }
        else
            return(false);
    }

    public void PlaceCommonPieces()
    {
        for(int X = 0; X < 8; X++)
        {
            PlacePiece(X, 1, new Piece(PieceColor.White, PieceType.Pawn));
            PlacePiece(X, 6, new Piece(PieceColor.Black, PieceType.Pawn));
        }
        PlacePiece(0,0, new Piece(PieceColor.White, PieceType.Rook));
        PlacePiece(7,0, new Piece(PieceColor.White, PieceType.Rook));
        PlacePiece(0,7, new Piece(PieceColor.Black, PieceType.Rook));
        PlacePiece(7,7, new Piece(PieceColor.Black, PieceType.Rook));
        PlacePiece(1,0, new Piece(PieceColor.White, PieceType.Knight));
        PlacePiece(6,0, new Piece(PieceColor.White, PieceType.Knight));
        PlacePiece(1,7, new Piece(PieceColor.Black, PieceType.Knight));
        PlacePiece(6,7, new Piece(PieceColor.Black, PieceType.Knight));
        PlacePiece(2,0, new Piece(PieceColor.White, PieceType.Bishop));
        PlacePiece(5,0, new Piece(PieceColor.White, PieceType.Bishop));
        PlacePiece(2,7, new Piece(PieceColor.Black, PieceType.Bishop));
        PlacePiece(5,7, new Piece(PieceColor.Black, PieceType.Bishop));
        PlacePiece(3,0, new Piece(PieceColor.White, PieceType.Queen));
        PlacePiece(4,0, new Piece(PieceColor.White, PieceType.King));
        PlacePiece(3,7, new Piece(PieceColor.Black, PieceType.Queen));
        PlacePiece(4,7, new Piece(PieceColor.Black, PieceType.King));
    }

    private void DrawLine()
    {
        System.out.print("   ");
        for(int I = 0; I < 8; I++)
            System.out.print("+---");
        System.out.println("+");
    }

    public void Draw()
    {
        System.out.println("     A   B   C   D   E   F   G   H");
        DrawLine();
        for(int Y = 7; Y > -1; Y--)
        {
            System.out.print(" " + (Y + 1) + " ");
            for(int X = 0; X < 8; X++)
                System.out.print("|"+Fields[X][Y].ShowField());
            System.out.println("| " + (Y + 1));
            DrawLine();
        }
        System.out.println("     A   B   C   D   E   F   G   H");
    }
}
