package com.NGS.Chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger
{
    private static Logger logger;

    public static void Initialization()
    {
        logger = LoggerFactory.getLogger(ChessApplication.class);
    }

    public static Logger Log()
    {
        return(logger);
    }
}
