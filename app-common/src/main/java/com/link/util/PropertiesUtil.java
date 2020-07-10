package com.link.util;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.IOException ;
import java.io.InputStream ;
import java.net.URL ;
import java.util.Properties ;

public class PropertiesUtil
{
	public static String getPropertiesFilePath(final String path)
	{
		URL resource = PropertiesUtil.class.getResource(path);
		
		if (resource == null)
		{
			throw new RuntimeException(path + " not in classpath");
		}
		
		return resource.toString();
	}

	public static Properties loadProperties(String path)
	{
		InputStream inputStream =ClassUtils.getClassLoader(PropertiesUtil.class).getResourceAsStream(path);
		
		if (inputStream == null)
		{
			throw new RuntimeException(path + " not in classpath");
		}
		
		final Properties properties = new Properties();
		
		try
		{
			properties.load(inputStream);
			return properties;
		}
		catch (final IOException e)
		{
			throw new RuntimeException("unable to load "+path, e);
		}
		finally
		{
			if( inputStream != null )
			{
				try
                {
	                inputStream.close();
                }
                catch (IOException e)
                {
	                e.printStackTrace();
                }
			}
			
			inputStream = null ;
		}
		
	}

	public static Properties loadProperties(final File file)
	{
		final Properties properties = new Properties();
		
		FileInputStream inputStream =  null ;
		
		try
		{
			inputStream = new FileInputStream(file);
			
			properties.load(inputStream);
			
			return properties;
		}
		catch (final IOException e)
		{
			throw new RuntimeException("unable to load "+file.getName(), e);
		}
		finally
		{
			if( inputStream != null )
			{
				try
                {
	                inputStream.close();
                }
                catch (IOException e)
                {
	                e.printStackTrace();
                }
			}
			
			inputStream = null ;
		}
	}

}
