/**
 * 
 */
package src;

import src.res.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;

/**
 * @author Gamez
 *
 */
public class Main 
{

	/**
	 * @param args
	 */
	final static Logger logger = LogManager.getLogger(Main.class);
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "fbcmd4j.properties";
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Facebook facebook = null;
		Properties facebook_properties = null;
		/**
		 * 
		 */
		try 
		{
			facebook_properties = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
		}
		catch (IOException e)
		{
			logger.error(e);
		}
		
	}
}
