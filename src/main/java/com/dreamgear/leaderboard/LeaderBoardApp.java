package com.dreamgear.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dreamgear.http.HttpServer;

/**
 * Hello world!
 *
 */
public class LeaderBoardApp 
{
	private static final Logger logger = LoggerFactory.getLogger(World.class);
	
    public static void main( String[] args )
    {
    	World.getInstance().start();
    	
    	//关闭回调
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
          public void run()
          {
            try
            {
            	HttpServer.getInstance().stop();
            	logger.info("HttpServer stop!");
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
          }

        });
    	logger.info("服务器启动完成");
    }
}
