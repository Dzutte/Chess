package com.NGS.Chess;

import java.util.Scanner;
import java.util.stream.Stream;

public class LocalTurnDecider implements TurnDecider
{
    @Override
    public void Initialization()
    {
        //Do nothing
    }

    private boolean isNumber(char ch)
    {
        return Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').anyMatch(c -> c == ch);
    }

    private int NumberToInt(char Ch)
    {
        if(isNumber(Ch))
        {
            switch(Ch)
            {
                case '1':
                    return(0);
                case '2':
                    return(1);
                case '3':
                    return(2);
                case '4':
                    return(3);
                case '5':
                    return(4);
                case '6':
                    return(5);
                case '7':
                    return(6);
                case '8':
                    return(7);
            }
        }
        return(-1);
    }

    private int CharToInt(char Ch)
    {
        switch(Ch)
        {
            case 'A':
            case 'a':
                return(0);
            case 'B':
            case 'b':
                return(1);
            case 'C':
            case 'c':
                return(2);
            case 'D':
            case 'd':
                return(3);
            case 'E':
            case 'e':
                return(4);
            case 'F':
            case 'f':
                return(5);
            case 'G':
            case 'g':
                return(6);
            case 'H':
            case 'h':
                return(7);
        }
        return(-1);
    }

    private Turn StringToTurn(String S)
    {
        Turn CurrentTurn = new Turn();
        if(S.length() == 5)
        {
            CurrentTurn.SetFromX(CharToInt(S.charAt(0)));
            CurrentTurn.SetFromY(NumberToInt(S.charAt(1)));
            CurrentTurn.SetToX(CharToInt(S.charAt(3)));
            CurrentTurn.SetToY(NumberToInt(S.charAt(4)));
        }
        return(CurrentTurn);
    }

    private boolean AcceptableTurn(Board CurrentBoard, PieceColor PlayerColor, Turn CurrentTurn)
    {
        if(CurrentTurn.GetFrom().IsOnBoard() & CurrentTurn.GetTo().IsOnBoard() & (!CurrentTurn.IsFake()))
        {
            if (CurrentBoard.CheckOwnership(CurrentTurn.GetFrom(), PlayerColor))
            {
                if (!CurrentBoard.CheckOwnership(CurrentTurn.GetTo(), PlayerColor))
                    return (CurrentBoard.PieceCanDoThis(CurrentTurn));
                else
                    return (false);
            }
            else
                return (false);
        }
        else
            return (false);
    }

    private String ColorToString(PieceColor Color)
    {
        switch (Color)
        {
            case White:
                return("White");
            case Black:
                return("Black");
            default:
                return("Error");
        }
    }

    @Override
    public void DoTurn(Board CurrentBoard, PieceColor PlayerColor)
    {
        Scanner sc = new Scanner(System.in);
        Turn CurrentTurn;
        do
        {
            System.out.println(ColorToString(PlayerColor));
            CurrentTurn = StringToTurn(sc.nextLine());
            AppLogger.Log().info(PlayerColor.toString() + " " + CurrentTurn.toString());
        }
        while(!AcceptableTurn(CurrentBoard, PlayerColor, CurrentTurn));
        CurrentBoard.Move(CurrentTurn);
    }

    @Override
    public PieceType ChangePawn()
    {
        System.out.println("Pawn transform to...");
        Scanner sc = new Scanner(System.in);
        String S;
        while(true)
        {
            S = sc.nextLine();
            if(S.equals("Knight"))
                return(PieceType.Knight);
            if(S.equals("Bishop"))
                return(PieceType.Bishop);
            if(S.equals("Rook"))
                return(PieceType.Rook);
            if(S.equals("Queen"))
                return(PieceType.Queen);
        }
    }
}