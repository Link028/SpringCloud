package com.link.common;

import java.io.File ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.io.OutputStream ;
import java.io.OutputStreamWriter ;
import java.io.Reader ;
import java.io.Serializable ;
import java.io.Writer ;
import java.net.URL ;
import java.util.Enumeration ;
import java.util.HashMap ;
import java.util.HashSet ;
import java.util.Iterator ;
import java.util.List ;
import java.util.ListIterator ;
import java.util.Map ;
import java.util.Properties ;
import java.util.Set ;
import java.util.concurrent.CopyOnWriteArrayList ;

import javax.xml.parsers.DocumentBuilder ;
import javax.xml.parsers.DocumentBuilderFactory ;
import javax.xml.parsers.ParserConfigurationException ;
import javax.xml.transform.Transformer ;
import javax.xml.transform.TransformerFactory ;
import javax.xml.transform.dom.DOMSource ;
import javax.xml.transform.stream.StreamResult ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;
import org.w3c.dom.DOMException ;
import org.w3c.dom.Document ;
import org.w3c.dom.Element ;
import org.w3c.dom.Node ;
import org.w3c.dom.NodeList ;
import org.w3c.dom.Text ;
import org.xml.sax.SAXException ;

import com.link.util.ClassUtils;

 
public class Configuration implements Iterable<Map.Entry<String, String>>, Serializable
{
	protected static final Log _LOG = LogFactory.getLog(Configuration.class);

	private boolean quietModel = true;

	private ClassLoader classLoader = null;

	private List<Object> resources = new CopyOnWriteArrayList<Object>();

	private Properties properties = null;
 
	public static  Configuration loadConf(String classpathResource)
	{
		_LOG.info("开始加载配置信息：" + classpathResource );
        
		Configuration configuration = new Configuration( false );
	    configuration.addResource( classpathResource );
	    configuration.reloadConfiguration(); 
	        
	    configuration.writeXml( System.out );  
	        
	    _LOG.info("配置信息加载结束");
		
	    return configuration ;
	}
	
	public static  Configuration loadConf(InputStream resource)
	{
		_LOG.info("开始加载配置信息：" + resource);
        
		Configuration configuration = new Configuration( false );
	    configuration.addResource( resource );
	    configuration.reloadConfiguration(); 
	        
	    //configuration.writeXml( System.out );  
	        
	    _LOG.info("配置信息加载结束");
		
	    return configuration ;
	}
	
	public Configuration()
	{
		this(true);
	}

	public Configuration(boolean mode)
	{
		this.quietModel = mode;

		classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null)
		{
			this.classLoader = Configuration.class.getClassLoader();
		}

		properties = new Properties();
	}
 
	public Configuration(Configuration other)
	{
		this.quietModel = other.quietModel;

		synchronized (other)
		{
			if (other.properties != null)
			{
				this.properties = (Properties) other.properties.clone();
			}
		}

		this.resources.addAll(other.resources);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////

	public void addResource(String name)
	{
		addResourceObject(name);
	}

	public void addResource(URL url)
	{
		addResourceObject(url);
	}

	public void addResource(InputStream ins)
	{
		addResourceObject(ins);
	}

	public void addResourceObject(Object resource)
	{
		resources.add(resource);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	public synchronized void reloadConfiguration()
	{

		if (properties != null)
		{
			properties.clear();
		}
		properties = null;

		properties = new Properties(  );

		loadResources(properties, resources, quietModel);
	}

	public void loadResources( Properties properties, List<Object> resources, boolean quiet )
	{
		for (Object resource : resources)
		{
			loadResource(properties, resource, quiet);
		}
	}

	private void loadResource(Properties properties, Object name, boolean quiet)
	{
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			// ignore all comments inside the xml file
			docBuilderFactory.setIgnoringComments(true);

			// allow includes in the xml file
			docBuilderFactory.setNamespaceAware(true);
			try
			{
				docBuilderFactory.setXIncludeAware(true);
			}
			catch (UnsupportedOperationException e)
			{
				_LOG.error("Failed to set setXIncludeAware(true) for parser " + docBuilderFactory + ":" + e, e);
			}

			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();

			Document doc = null;
			Element root = null;

			if (name instanceof URL)
			{ // an URL resource
				URL url = (URL) name;
				if (url != null)
				{
					if (!quiet)
					{
						_LOG.debug("parsing " + url);
					}
					doc = builder.parse(url.toString());
				}
			}
			else if (name instanceof String)
			{
				URL url = getResource((String) name);
				if (url != null)
				{
					if (!quiet)
					{
						_LOG.debug("parsing " + url);
					}
					doc = builder.parse(url.toString());
				}
			}
			else if (name instanceof InputStream)
			{
				try
				{
					doc = builder.parse((InputStream) name);
				}
				finally
				{
					((InputStream) name).close();
				}
			}
			else if (name instanceof Element)
			{
				root = (Element) name;
			}

			if (doc == null && root == null)
			{
				if (quiet)
					return;
				throw new RuntimeException(name + " not found");
			}

			if (root == null)
			{
				root = doc.getDocumentElement();
			}

			if (!"configuration".equals(root.getTagName()))
				_LOG.fatal("bad conf file: top-level element not <configuration>");

			NodeList props = root.getChildNodes();
			for (int i = 0; i < props.getLength(); i++)
			{
				Node propNode = props.item(i);
				if (!(propNode instanceof Element))
					continue;
				Element prop = (Element) propNode;

				if ("configuration".equals(prop.getTagName()))
				{
					loadResource(properties, prop, quiet);

					continue;
				}

				if (!"property".equals(prop.getTagName()))
					_LOG.warn("bad conf file: element not <property>");

				NodeList fields = prop.getChildNodes();

				String attr = null;
				String value = null;

				for (int j = 0; j < fields.getLength(); j++)
				{
					Node fieldNode = fields.item(j);

					if (!(fieldNode instanceof Element))
						continue;

					Element field = (Element) fieldNode;

					if ("name".equals(field.getTagName()) && field.hasChildNodes())
					{
						attr = ((Text) field.getFirstChild()).getData().trim();
					}

					if ("value".equals(field.getTagName()) && field.hasChildNodes())
					{
						value = ((Text) field.getFirstChild()).getData();
					}
				}

				if (attr != null)
				{
					loadProperty(properties, name, attr, value);
				}
			}
		}
		catch (IOException e)
		{
			_LOG.fatal("error parsing conf file: " + e);
			throw new RuntimeException(e);
		}
		catch (DOMException e)
		{
			_LOG.fatal("error parsing conf file: " + e);
			throw new RuntimeException(e);
		}
		catch (SAXException e)
		{
			_LOG.fatal("error parsing conf file: " + e);
			throw new RuntimeException(e);
		}
		catch (ParserConfigurationException e)
		{
			_LOG.fatal("error parsing conf file: " + e);
			throw new RuntimeException(e);
		}
	}

	private void loadProperty(Properties properties, Object name, String attr, String value)
	{
		if (value != null)
		{
			properties.setProperty(attr, value.trim());
		}
	}
	
	public void setAll(Configuration other)
	{
		this.quietModel = other.quietModel;

		synchronized (other)
		{
			if (other.properties != null)
			{
				//this.properties = (Properties) other.properties.clone();
				this.properties.putAll( other.properties ) ;
			}
		}
		this.resources.addAll(other.resources);
	}
	
	public void set(String name, String value)
	{
		getProps().setProperty(name, value);
	}

	public void setIfUnset(String name, String value)
	{
		if (get(name) == null)
		{
			set(name, value);
		}
	}
	
	public String get(String name)
	{
		return getProps().getProperty(name);
	}

	public String get(String name, String defaultValue)
	{
		String value = getProps().getProperty(name, defaultValue);

		if (value == null)
		{
			return defaultValue;
		}

		return value;
	}

	public int getInt(String name, int defaultValue)
	{
		String valueString = get(name);

		if (valueString == null)
			return defaultValue;
		try
		{
			String hexString = getHexDigits(valueString);
			if (hexString != null)
			{
				return Integer.parseInt(hexString, 16);
			}
			return Integer.parseInt(valueString);
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	protected void setInt(String name, int value)
	{
		set(name, Integer.toString(value));
	}

	public long getLong(String name, long defaultValue)
	{
		String valueString = get(name);

		if (valueString == null)
			return defaultValue;

		try
		{
			String hexString = getHexDigits(valueString);

			if (hexString != null)
			{
				return Long.parseLong(hexString, 16);
			}

			return Long.parseLong(valueString);
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	protected void setLong(String name, long value)
	{
		set(name, Long.toString(value));
	}

	private String getHexDigits(String value)
	{
		boolean negative = false;
		String str = value;
		String hexString = null;
		if (value.startsWith("-"))
		{
			negative = true;
			str = value.substring(1);
		}
		if (str.startsWith("0x") || str.startsWith("0X"))
		{
			hexString = str.substring(2);
			if (negative)
			{
				hexString = "-" + hexString;
			}
			return hexString;
		}
		return null;
	}

	public float getFloat(String name, float defaultValue)
	{
		String valueString = get(name);
		if (valueString == null)
			return defaultValue;
		try
		{
			return Float.parseFloat(valueString);
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	protected void setFloat(String name, float value)
	{
		set(name, Float.toString(value));
	}

	public boolean getBoolean(String name, boolean defaultValue)
	{
		String valueString = get(name);
		if ("true".equals(valueString))
			return true;
		else if ("false".equals(valueString))
			return false;
		else
			return defaultValue;
	}

	protected void setBoolean(String name, boolean value)
	{
		set(name, Boolean.toString(value));
	}

	protected void setBooleanIfUnset(String name, boolean value)
	{
		setIfUnset(name, Boolean.toString(value));
	}

	public <T extends Enum<T>> T getEnum(String name, T defaultValue)
	{
		final String val = get(name);
		return null == val ? defaultValue : Enum.valueOf(defaultValue.getDeclaringClass(), val);
	}

	protected <T extends Enum<T>> void setEnum(String name, T value)
	{
		set(name, value.toString());
	}

	public Class<?> getClass(final String key)
	{
		final String className = get(key);
		return ClassUtils.forName(className, Object.class);
	}

	public Class<?> getClass(final String key, Class<?> defaultValue)
	{
		final String className = get(key, defaultValue.getName());
		return ClassUtils.forName(className, Object.class);
	}

	public File getFile(final String key)
	{
		return new File(get(key));
	}

	public Iterator<Map.Entry<String, String>> iterator()
	{
		Map<String, String> result = new HashMap<String, String>();

		for (Map.Entry<Object, Object> item : getProps().entrySet())
		{
			if (item.getKey() instanceof String && item.getValue() instanceof String)
			{
				result.put((String) item.getKey(), (String) item.getValue());
			}
		}

		return result.entrySet().iterator();
	}

	protected int size()
	{
		return getProps().size();
	}

	protected void clear()
	{
		getProps().clear();
	}

	public boolean containsProperty(final String key)
	{
		return properties.containsKey(key);
	}

	public Set<String> getKeys()
	{
		Set<String> keys = new HashSet<String>();
		
		for (Iterator<Object> iterator = properties.keySet().iterator(); iterator.hasNext();)
		{
			String key = iterator.next().toString() ;
			keys.add( key ) ;
		}
		return keys ;
		
		//return properties.stringPropertyNames();
	}

	public synchronized Properties getProps()
	{
		if (properties == null)
		{
			properties = new Properties();
		}

		return properties;
	}

	public URL getResource(String name)
	{
		return classLoader.getResource(name);
	}

	public InputStream getConfResourceAsInputStream(String name)
	{
		try
		{
			URL url = getResource(name);

			if (url == null)
			{
				_LOG.info(name + " not found");
				return null;
			}
			else
			{
				_LOG.info("found resource " + name + " at " + url);
			}

			return url.openStream();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public Reader getConfResourceAsReader(String name)
	{
		try
		{
			URL url = getResource(name);

			if (url == null)
			{
				_LOG.info(name + " not found");
				return null;
			}
			else
			{
				_LOG.info("found resource " + name + " at " + url);
			}

			return new InputStreamReader(url.openStream());
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("配置信息:");
		sb.append("\n Configuration files:");
		sb.append("\n");

		ListIterator<Object> i = resources.listIterator();
		while (i.hasNext())
		{
			if (i.nextIndex() != 0)
			{
				sb.append(", ");
			}

			sb.append("\t\t");
			sb.append(i.next());
			sb.append("\n");
		}

		sb.append(" quietModel:");
		sb.append("\n\t\t");
		sb.append(quietModel);

		sb.append("\n <configuration>");

		for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();)
		{
			String name = (String) e.nextElement();
			Object object = properties.get(name);
			String value = null;
			if (object != null)
			{
				value = object.toString();
			}

			sb.append("\n\t<property>");

			sb.append("\n\t\t<name>");
			sb.append(name);
			sb.append("</name>");

			sb.append("\n\t\t<value>");
			sb.append(value);
			sb.append("</value>");

			sb.append("\n\t</property>");
		}
		sb.append("\n </configuration>");
		sb.append("\n");

		return sb.toString();
	}

	public <T> void toString(List<T> resources, StringBuilder sb)
	{
		ListIterator<T> i = resources.listIterator();
		while (i.hasNext())
		{
			if (i.nextIndex() != 0)
			{
				sb.append(", ");
			}

			sb.append(i.next());
		}
	}

	public void writeXml(OutputStream out)
	{
		writeXml(new OutputStreamWriter(out));
	}

	public synchronized void writeXml(Writer out)
	{
		Properties properties = getProps();
		try
		{

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

			Element conf = doc.createElement("configuration");

			doc.appendChild(conf);

			conf.appendChild(doc.createTextNode("\n"));

			for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();)
			{
				String name = (String) e.nextElement();
				Object object = properties.get(name);
				String value = null;
				if (object instanceof String)
				{
					value = (String) object;
				}
				else
				{
					continue;
				}
				Element propNode = doc.createElement("property");
				conf.appendChild(propNode);

				Element nameNode = doc.createElement("name");
				nameNode.appendChild(doc.createTextNode(name));
				propNode.appendChild(nameNode);

				Element valueNode = doc.createElement("value");
				valueNode.appendChild(doc.createTextNode(value));
				propNode.appendChild(valueNode);

				conf.appendChild(doc.createTextNode("\n"));
			}

			DOMSource source = new DOMSource(doc);

			out.write("\n");

			StreamResult result = new StreamResult(out);

			TransformerFactory transFactory = TransformerFactory.newInstance();

			Transformer transformer = transFactory.newTransformer();

			transformer.transform(source, result);

			out.write("\n\n");
			out.flush();

		}
		catch (Exception e)
		{
			e.printStackTrace() ;
		}
	}

 
	private static final long serialVersionUID = 6827389558112941477L;


	public static void main1(String[] args)
	{
		Configuration configuration = new Configuration( false );
	    configuration.addResource( "msg/kafka.xml" );
	    configuration.reloadConfiguration(); 
	        
	    configuration.writeXml(System.out);
	    
	    System.out.println( configuration.getProps() ) ;
	    
	}
	
}
