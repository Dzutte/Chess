package com.NGS.Chess;

public class Board extends SimpleBoard
{
    Board()
    {
        super();
        //Do nothing
    }

    Board(Board OtherBoard)
    {
        Fields = new Field[8][8];
        for(int I = 0; I < 8; I++)
            for (int J = 0; J < 8; J++)
                Fields[I][J] = new Field(OtherBoard.CheckPiece(I, J));
    }

    public Coordinates FindKing(PieceColor CurrentPlayer)
    {
        Coordinates KingCoordinates = new Coordinates(-1,-1);
        for(int X = 0; X < 8; X++)
            for(int Y = 0; Y < 8; Y++)
                if(!CheckFieldEmpty(X, Y))
                    if(CheckPiece(X, Y).GetType() == PieceType.King)
                        if(CheckPiece(X, Y).GetColor() == CurrentPlayer)
                        {
                            KingCoordinates.SetX(X);
                            KingCoordinates.SetY(Y);
                            return(KingCoordinates);
                        }
        return(KingCoordinates);
    }

    public boolean CheckEnemyAt(int X, int Y, PieceType RequiredType, PieceColor AllyColor)
    {
        if(Coordinates.IsOnBoard(X, Y))
            if(!CheckFieldEmpty(X, Y))
                if(CheckPiece(X, Y).GetType() == RequiredType)
                    if(CheckPiece(X + 1, Y).GetColor() != AllyColor)
                        return(true);
        return(false);
    }

    public boolean CheckEnemyAt(int X, int Y, PieceType RequiredType1, PieceType RequiredType2, PieceColor AllyColor)
    {
        if(!CheckFieldEmpty(X, Y))
            if((CheckPiece(X, Y).GetType() == RequiredType1) | (CheckPiece(X, Y).GetType() == RequiredType2))
                if(CheckPiece(X + 1, Y).GetColor() != AllyColor)
                    return(true);
        return(false);
    }

    public boolean CheckPawnAttack(int PieceX, int PieceY)
    {
        if(CheckPiece(PieceX, PieceY).GetColor() == PieceColor.White)
        {
            if(CheckEnemyAt(PieceX + 1, PieceY + 1, PieceType.Pawn, PieceColor.White))
                return(true);
            if(CheckEnemyAt(PieceX - 1, PieceY + 1, PieceType.Pawn, PieceColor.White))
                return(true);
        }
        else
        {
            if(CheckEnemyAt(PieceX + 1, PieceY - 1, PieceType.Pawn, PieceColor.Black))
                return(true);
            if(CheckEnemyAt(PieceX - 1, PieceY - 1, PieceType.Pawn, PieceColor.Black))
                return(true);
        }
        return(false);
    }

    public boolean CheckKnightAttack(int PieceX, int PieceY)
    {
        if(CheckEnemyAt(PieceX - 1, PieceY - 2, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX - 2, PieceY - 1, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 1, PieceY - 2, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 2, PieceY - 1, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX - 1, PieceY + 2, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX - 2, PieceY + 1, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 1, PieceY + 2, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 2, PieceY + 1, PieceType.Knight, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        return(false);
    }

    public boolean CheckBishopAttack(int PieceX, int PieceY)
    {
        int Move;
        for(Move = 1; (PieceX + Move < 8) & (PieceY + Move < 8); Move++)
            if(!CheckFieldEmpty(PieceX + Move, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY + Move))
            if(CheckEnemyAt(PieceX + Move, PieceY + Move, PieceType.Bishop, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX - Move > -1) & (PieceY + Move < 8); Move++)
            if(!CheckFieldEmpty(PieceX - Move, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY + Move))
            if(CheckEnemyAt(PieceX - Move, PieceY + Move, PieceType.Bishop, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX + Move < 8) & (PieceY - Move > -1); Move++)
            if(!CheckFieldEmpty(PieceX + Move, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY - Move))
            if(CheckEnemyAt(PieceX + Move, PieceY - Move, PieceType.Bishop, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; (PieceX - Move > -1) & (PieceY - Move > -1); Move++)
            if(!CheckFieldEmpty(PieceX - Move, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY - Move))
            if(CheckEnemyAt(PieceX - Move, PieceY - Move, PieceType.Bishop, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        return(false);
    }

    public boolean CheckRookAttack(int PieceX, int PieceY)
    {
        int Move;
        for(Move = 1; PieceX + Move < 8; Move++)
            if(!CheckFieldEmpty(PieceX + Move, PieceY))
                break;
        if(Coordinates.IsOnBoard(PieceX + Move, PieceY))
            if(CheckEnemyAt(PieceX + Move, PieceY, PieceType.Rook, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceX - Move > -1; Move++)
            if(!CheckFieldEmpty(PieceX - Move, PieceY))
                break;
        if(Coordinates.IsOnBoard(PieceX - Move, PieceY))
            if(CheckEnemyAt(PieceX - Move, PieceY, PieceType.Rook, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceY + Move < 8; Move++)
            if(!CheckFieldEmpty(PieceX, PieceY + Move))
                break;
        if(Coordinates.IsOnBoard(PieceX, PieceY + Move))
            if(CheckEnemyAt(PieceX, PieceY + Move, PieceType.Rook, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        for(Move = 1; PieceY - Move > -1; Move++)
            if(!CheckFieldEmpty(PieceX, PieceY - Move))
                break;
        if(Coordinates.IsOnBoard(PieceX, PieceY - Move))
            if(CheckEnemyAt(PieceX, PieceY - Move, PieceType.Rook, PieceType.Queen, CheckPiece(PieceX, PieceY).GetColor()))
                return(true);
        return(false);
    }

    public boolean CheckKingAttack(int PieceX, int PieceY)
    {
        if(CheckEnemyAt(PieceX - 1, PieceY, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX - 1, PieceY - 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX, PieceY - 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 1, PieceY - 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 1, PieceY, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX + 1, PieceY + 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX, PieceY + 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        if(CheckEnemyAt(PieceX - 1, PieceY + 1, PieceType.King, CheckPiece(PieceX, PieceY).GetColor()))
            return(true);
        return(false);
    }

    public boolean UnderAttack(int PieceX, int PieceY)
    {
        if(CheckPawnAttack(PieceX, PieceY))
            return(true);
        if(CheckKnightAttack(PieceX, PieceY))
            return(true);
        if(CheckBishopAttack(PieceX, PieceY))
            return(true);
        if(CheckRookAttack(PieceX, PieceY))
            return(true);
        if(CheckKingAttack(PieceX, PieceY))
            return(true);
        return(false);
    }

    public boolean UnderAttack(Coordinates PieceCoordinates)
    {
        return(UnderAttack(PieceCoordinates.GetX(), PieceCoordinates.GetY()));
    }

    public boolean CheckTurnOpensKing(Turn CurrentTurn)
    {
        Board FutureBoard = new Board(this);
        FutureBoard.Move(CurrentTurn);
        return(FutureBoard.UnderAttack(FutureBoard.FindKing(CheckPiece(CurrentTurn.GetFrom()).GetColor())));
    }

    private static int ColorDirection(PieceColor Color)
    {
        if(Color == PieceColor.White)
            return(1);
        else
            return(-1);
    }

    public boolean PawnCanDoThis(Turn CurrentTurn)
    {
        Piece CurrentPiece = CheckPiece(CurrentTurn.GetFrom());
        if(CurrentTurn.IsVertical())// Move
        {
            if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 1)
                return(CheckFieldEmpty(CurrentTurn.GetTo()));
            else if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 2)
            {
                if(CheckClearVertical(CurrentTurn))
                    return (CurrentPiece.CheckStillness() & CheckFieldEmpty(CurrentTurn.GetTo()));
                else
                    return(false);
            }
            else
                return(false);
        }
        else// Attack
        {
            if(Math.abs(CurrentTurn.GetHorizontalMove()) == 1)
            {
                if((CurrentTurn.GetVerticalMove()) * ColorDirection(CurrentPiece.GetColor()) == 1)
                    return(!CheckFieldEmpty(CurrentTurn.GetTo()));
                else
                    return(false);
            }
            else
                return(false);
        }
    }

    public boolean KnightCanDoThis(Turn CurrentTurn)
    {
        if((Math.abs(CurrentTurn.GetVerticalMove()) == 2) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 1))
            return(true);
        else if((Math.abs(CurrentTurn.GetVerticalMove()) == 1) & (Math.abs(CurrentTurn.GetHorizontalMove()) == 2))
            return(true);
        else
            return(false);
    }

    public boolean BishopCanDoThis(Turn CurrentTurn)
    {
        if(CurrentTurn.IsDiagonal())
            return(CheckClearDiagonal(CurrentTurn));
        else
            return(false);
    }

    public boolean RookCanDoThis(Turn CurrentTurn)
    {
        if(CurrentTurn.IsVertical())
            return(CheckClearVertical(CurrentTurn));
        else if(CurrentTurn.IsHorizontal())
            return(CheckClearHorizontal(CurrentTurn));
        else
            return(false);
    }

    public boolean KingCanDoThis(Turn CurrentTurn)
    {
        if(Math.abs(CurrentTurn.GetVerticalMove()) < 2)
        {
            if(Math.abs(CurrentTurn.GetHorizontalMove()) < 2)
                return(true);
            else //Castling check
            {
                if(CurrentTurn.IsCastling())
                    return(CastlingAvailable(CurrentTurn));
                else
                    return(false);
            }
        }
        else
            return(false);
    }

    public boolean PieceCanDoThis(Turn CurrentTurn)
    {
        switch (CheckPiece(CurrentTurn.GetFromX(), CurrentTurn.GetFromY()).GetType())
        {
            case Pawn:
                return(PawnCanDoThis(CurrentTurn));
            case Knight:
                return(KnightCanDoThis(CurrentTurn));
            case Bishop:
                return(BishopCanDoThis(CurrentTurn));
            case Rook:
                return(RookCanDoThis(CurrentTurn));
            case Queen:
                return(RookCanDoThis(CurrentTurn) | BishopCanDoThis(CurrentTurn));
            case King:
                return(KingCanDoThis(CurrentTurn));
            default:
                return(false);
        }
    }

    private boolean CanMovePawn(int X, int Y)
    {
        if(CheckPiece(X, Y).GetColor() == PieceColor.White)
        {
            if(Coordinates.IsOnBoard(X + 1, Y + 1))
                if(PawnCanDoThis(new Turn(X, Y, X + 1, Y + 1)))
                    if(!CheckTurnOpensKing(new Turn(X, Y, X + 1, Y + 1)))
                        return(true);
            if(Coordinates.IsOnBoard(X - 1, Y + 1))
                if(PawnCanDoThis(new Turn(X, Y, X - 1, Y + 1)))
                    if(!CheckTurnOpensKing(new Turn(X, Y, X - 1, Y + 1)))
                        return(true);
            if(CheckFieldEmpty(X, Y + 1))
            {
                if (!CheckTurnOpensKing(new Turn(X, Y, X, Y + 1)))
                    return (true);
                if(CheckFieldEmpty(X, Y + 2))
                    if (!CheckTurnOpensKing(new Turn(X, Y, X, Y + 2)))
                        return (true);
            }
            return(false);
        }
        else
        {
            if(Coordinates.IsOnBoard(X + 1, Y - 1))
                if(PawnCanDoThis(new Turn(X, Y, X + 1, Y - 1)))
                    if(!CheckTurnOpensKing(new Turn(X, Y, X + 1, Y - 1)))
                        return(true);
            if(Coordinates.IsOnBoard(X - 1, Y - 1))
                if(PawnCanDoThis(new Turn(X, Y, X - 1, Y - 1)))
                    if(!CheckTurnOpensKing(new Turn(X, Y, X - 1, Y - 1)))
                        return(true);
            if(CheckFieldEmpty(X, Y - 1))
            {
                if (!CheckTurnOpensKing(new Turn(X, Y, X, Y - 1)))
                    return (true);
                if(CheckFieldEmpty(X, Y - 2))
                    if (!CheckTurnOpensKing(new Turn(X, Y, X, Y - 2)))
                        return (true);
            }
            return(false);
        }
    }

    private boolean CheckFieldAccess(int FromX, int FromY, int ToX, int ToY)
    {
        if(Coordinates.IsOnBoard(ToX, ToY))
            if(CheckPiece(FromX, FromY).IsEnemy(CheckPiece(ToX, ToY)))
                if(!CheckTurnOpensKing(new Turn(FromX, FromY, ToX, ToY)))
                    return(true);
        return(false);
    }

    private boolean CanMoveKnight(int X, int Y)
    {
        if(CheckFieldAccess(X, Y, X + 1, Y + 2))
            return(true);
        if(CheckFieldAccess(X, Y, X + 2, Y + 1))
            return(true);
        if(CheckFieldAccess(X, Y, X - 1, Y + 2))
            return(true);
        if(CheckFieldAccess(X, Y, X + 2, Y - 1))
            return(true);
        if(CheckFieldAccess(X, Y, X + 1, Y - 2))
            return(true);
        if(CheckFieldAccess(X, Y, X - 2, Y + 1))
            return(true);
        if(CheckFieldAccess(X, Y, X - 1, Y - 2))
            return(true);
        if(CheckFieldAccess(X, Y, X - 2, Y - 1))
            return(true);
        return(false);
    }

    private boolean CanMoveBishop(int X, int Y)
    {
        for(int Move = 1; (X + Move < 8) & (Y + Move < 8); Move++)
            if(RookCanDoThis(new Turn(X, Y, X + Move, Y + Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X + Move, Y + Move)))
                    return(true);
        for(int Move = 1; (X - Move > -1) & (Y + Move < 8); Move++)
            if(RookCanDoThis(new Turn(X, Y, X - Move, Y + Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X - Move, Y + Move)))
                    return(true);
        for(int Move = 1; (X + Move < 8) & (Y - Move > -1); Move++)
            if(RookCanDoThis(new Turn(X, Y, X + Move, Y - Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X + Move, Y - Move)))
                    return(true);
        for(int Move = 1; (X - Move > -1) & (Y - Move > -1); Move++)
            if(RookCanDoThis(new Turn(X, Y, X - Move, Y - Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X - Move, Y - Move)))
                    return(true);
        return(false);
    }

    private boolean CanMoveRook(int X, int Y)
    {
        for(int Move = 1; X + Move < 8; Move++)
            if(RookCanDoThis(new Turn(X, Y, X + Move, Y)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X + Move, Y)))
                    return(true);
        for(int Move = 1; X - Move > -1; Move++)
            if(RookCanDoThis(new Turn(X, Y, X - Move, Y)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X - Move, Y)))
                    return(true);
        for(int Move = 1; Y + Move < 8; Move++)
            if(RookCanDoThis(new Turn(X, Y, X, Y + Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X, Y + Move)))
                    return(true);
        for(int Move = 1; Y - Move > -1; Move++)
            if(RookCanDoThis(new Turn(X, Y, X, Y - Move)))
                if(!CheckTurnOpensKing(new Turn(X, Y, X, Y - Move)))
                    return(true);
        return(false);
    }

    private boolean CanMoveQueen(int X, int Y)
    {
        return(CanMoveBishop(X, Y) | CanMoveRook(X, Y));
    }

    private boolean CanMoveKing(int X, int Y)
    {
        if(CheckFieldAccess(X, Y, X + 1, Y))
            return(true);
        if(CheckFieldAccess(X, Y, X + 1, Y + 1))
            return(true);
        if(CheckFieldAccess(X, Y, X, Y + 1))
            return(true);
        if(CheckFieldAccess(X, Y, X - 1, Y + 1))
            return(true);
        if(CheckFieldAccess(X, Y, X - 1, Y))
            return(true);
        if(CheckFieldAccess(X, Y, X - 1, Y - 1))
            return(true);
        if(CheckFieldAccess(X, Y, X, Y - 1))
            return(true);
        if(CheckFieldAccess(X, Y, X + 1, Y - 1))
            return(true);
        return(false);
    }

    private boolean CanMove(int PieceX, int PieceY)
    {
        switch(CheckPiece(PieceX, PieceY).GetType())
        {
            case Pawn:
                return(CanMovePawn(PieceX, PieceY));
            case Knight:
                return(CanMoveKnight(PieceX, PieceY));
            case Bishop:
                return(CanMoveBishop(PieceX, PieceY));
            case Rook:
                return(CanMoveRook(PieceX, PieceY));
            case Queen:
                return(CanMoveQueen(PieceX, PieceY));
            case King:
                return(CanMoveKing(PieceX, PieceY));
        }
        return(false);
    }

    private boolean CanDoTurn(PieceColor CurrentPlayer)
    {
        for(int X = 0; X < 8; X++)
            for(int Y = 0; Y < 8; Y++)
                if(!CheckFieldEmpty(X, Y))
                    if(CheckPiece(X, Y).GetColor() == CurrentPlayer)
                        if(CanMove(X, Y))
                            return(true);
        return(false);
    }

    public GameStatus CheckGameStatus(PieceColor CurrentPlayer)
    {
        if(CanDoTurn(CurrentPlayer))
            return(GameStatus.PlayOn);
        else
        {
            if(UnderAttack(FindKing(CurrentPlayer)))
                return(GameStatus.Checkmate);
            else
                return(GameStatus.Stalemate);
        }
    }
}
