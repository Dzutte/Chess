package com.NGS.Chess;

public class Player
{
    private final PieceColor Color;
    private TurnDecider TurnController;

    Player(PieceColor NewColor, ControlType Control)
    {
        Color = NewColor;
        AppLogger.Log().info(Color.toString() + " " + Control.toString());
        switch(Control)
        {
            case LocalHumanControl:
                TurnController = new LocalTurnDecider();
                break;
            case RemoteHumanControl:
                System.out.println("Remote");//WIP
                break;
            case AIControl:
                System.out.println("AI");//WIP
                break;
            default:
                AppLogger.Log().error("Unselected control");
        }
        TurnController.Initialization();
    }

    public PieceColor GetColor()
    {
        return(Color);
    }

    public void DoTurn(Board CurrentBoard)
    {
        TurnController.DoTurn(CurrentBoard, Color);
    }

    public void ChangePawn(Piece PawnPiece)
    {
        PieceType TMPType = TurnController.ChangePawn();
        AppLogger.Log().info("Changed " + Color.toString() + " Pawn to " + TMPType.toString());
        PawnPiece.SetType(TMPType);
    }
}
