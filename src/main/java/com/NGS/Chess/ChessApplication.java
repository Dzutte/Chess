package com.NGS.Chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplication
{
	private static void GameProcess(Board GameBoard, Player Player1,Player Player2)
	{
		Player CurrentPlayer = Player1;
		do
		{
			GameBoard.Draw();
			CurrentPlayer.DoTurn(GameBoard);
			if(GameBoard.PawnCrossedBoard())
				CurrentPlayer.ChangePawn(GameBoard.GetPawnFromEnd());
			if(CurrentPlayer == Player1)
				CurrentPlayer = Player2;
			else
				CurrentPlayer = Player1;
		}
		while(GameBoard.CheckGameStatus(CurrentPlayer.GetColor()) == GameStatus.PlayOn);
	}

	private static void GameInitialization()
	{
		Board GameBoard = new Board();
		GameBoard.PlaceCommonPieces();
		Player Player1 = new Player(PieceColor.White, ControlType.LocalHumanControl);
		Player Player2 = new Player(PieceColor.Black, ControlType.LocalHumanControl);
		GameProcess(GameBoard, Player1, Player2);
	}

	public static void main(String[] args)
	{
		AppLogger.Initialization();
		AppLogger.Log().info("App started");
		SpringApplication.run(ChessApplication.class, args);
		GameInitialization();
	}
}
