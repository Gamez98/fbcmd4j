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
	public static Scanner scanner = new Scanner(System.in);
	final static Logger logger = LogManager.getLogger(Main.class);
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "fbcmd4j.properties";
	public static int item_selected  = 0; 
	public static String status = "";
	public static String link = "";
	
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
		
		try 
		{
		
			while(true)
			{
				
				facebook = Utils.facebook_configuration(facebook_properties);
				System.out.println("Facebook Console Client\n\n");
				System.out.println("Options: \n");
				System.out.println("(1) NewsFeed \n");
				System.out.println("(2) Wall \n");
				System.out.println("(3) Post Status \n");
				System.out.println("(4) Post Link \n");
				System.out.println("(5) Exit \n\n");
				System.out.print("Select an Option: ");
			
			try 
			{
				
				item_selected = scanner.nextInt();
				scanner.nextLine();
				
				switch (item_selected) 
				{
				
				   case 1:
				   {
					   
					   System.out.println("-- Showing NewsFeed --");
					   ResponseList<Post> newsfeed = facebook.getFeed();
					   for(Post posts :  newsfeed) 
					   {
						   Utils.Print_Post_in_Wall(posts);
					   }
					   
					   break;
				   }
				   case 2:
				   {
					   
					   System.out.println("-- Showing Wall --");
					   ResponseList<Post> user_wall = facebook.getPosts();
					   for(Post posts : user_wall)
					   {
						   Utils.Print_Post_in_Wall(posts);
					   }
					   
				   }
				   case 3:
				   {
					   
					   System.out.println("Write a status... : ");
					   status = scanner.nextLine();
					   Utils.posting_Status(status, facebook);
	
				   }
				   case 4:
				   {
					
					   System.out.println("Post a Link: ");
					   link = scanner.nextLine();
					   Utils.posting_Status(link, facebook);
				   
				   }
				   case 5:
				   {
					
					   System.exit(0);
				   
				   }
				   default:
				   {
				
					   logger.info("Incorrect Option");   
				   
				   }
			    }
			} 
			catch (InputMismatchException e)
			{
				System.out.println("Error");
			}
			catch (FacebookException e) 
			{
				System.out.println("Error");
			}
			catch (Exception e)
			{
				System.out.println("Error");
			}
		}
	} 
	catch (Exception e)
	{ 
		System.out.println("Error");
	}
  }
}