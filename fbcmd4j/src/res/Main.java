package res;

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
	public static int item_selected  = 0; 
	public static String status = "";
	public static String link = "";
	public static Scanner scanner = new Scanner(System.in);
	
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
		
			
			do {
				
				facebook = Utils.facebook_configuration(facebook_properties);
				System.out.println("Facebook Console Client\n");
				System.out.println("Options:");
				System.out.println("(1) NewsFeed");
				System.out.println("(2) Wall");
				System.out.println("(3) Post Status");
				System.out.println("(4) Post Link");
				System.out.println("(5) Exit");
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
					   Save("NewsFeed", newsfeed,scanner);
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
					   Save("Wall", user_wall, scanner);
					   break;
				   }
				   case 3:
				   {
					   
					   System.out.println("Write a status... : ");
					   status = scanner.nextLine();
					   Utils.posting_Status(status, facebook);
					   break;
				   }
				   case 4:
				   {	
					   System.out.println("Post a Link: ");
					   link = scanner.nextLine();
					   Utils.posting_Link(link, facebook);
					   break;
				   }
				   case 5:
				   {
					   System.exit(0);
				       break;
				   }
				   default:
				   {
				
					   logger.info("Incorrect Option");   
					   break;
				   }
			    }
			} 
			catch (InputMismatchException e)
			{
				logger.error(e);
			}
			catch (FacebookException e) 
			{
				logger.error(e);			
			}
			catch (Exception e)
			{
				logger.error(e);
			}
		} while (item_selected != 5);
	} 
	catch (Exception e)
	{ 
		logger.error(e);
	}
  }
	
	public static void  Save (String file_name, ResponseList<Post> posts, Scanner scanner)
	{
		System.out.println("¿Quieres guardar lo mostrado en un archivo txt?");
		String seleccion= scanner.nextLine();
		if (seleccion.contains("Si") || seleccion.contains("si")) 
		{
			List<Post> post = new ArrayList<>();
			int num = 0;
			while(num <= 0) 
			{
			try {
			System.out.println("¿Cuantas lineas quieres guaradar?");
			num = Integer.parseInt(scanner.nextLine());					
			if(num <= 0) 
			{
				System.out.println("Ingrese un numero valido!!!!!");
			} 
			else 
			{
				for(int i = 0; i<num; i++)
				{
					if(i>posts.size()-1) 
						break;
					post.add(posts.get(i));
				}			
			}
		}
			catch(NumberFormatException e) 
			{
			logger.error(e);
			}
		}
			Utils.Save_Post(file_name, post);
		}
	}
}