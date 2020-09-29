package com.NGS.Chess;

public interface TurnDecider
{
    void Initialization();
    void DoTurn(Board CurrentBoard, PieceColor PlayerColor);
    PieceType ChangePawn();
}
