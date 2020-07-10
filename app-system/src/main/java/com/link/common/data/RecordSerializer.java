package com.link.common.data ;

import java.io.IOException ;
import java.util.Map ;

import org.springframework.util.Assert ;
 
import com.fasterxml.jackson.databind.DeserializationFeature ;
import com.fasterxml.jackson.databind.MapperFeature ;
import com.fasterxml.jackson.databind.ObjectMapper ;

public class RecordSerializer 
{
	protected final ObjectMapper	objectMapper ;

	public RecordSerializer()
	{
		this(new ObjectMapper()) ; 
	}

	public RecordSerializer(ObjectMapper objectMapper)
	{
		Assert.notNull(objectMapper, "'objectMapper' must not be null.") ;
		this.objectMapper = objectMapper ;
		this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false) ;
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;
	}

	public void configure(Map<String, ?> configs, boolean isKey)
	{
		// No-op
	}

	public byte[] serialize( Record data )
	{
		try
		{
			byte[] result = null ;
			if (data != null)
			{
				result = this.objectMapper.writeValueAsBytes(data) ;
			}
			return result ;
		}
		catch (IOException ex)
		{
			throw new RuntimeException("Can't serialize data [" + data + "]", ex) ;
		}
	}

	public void close()
	{
		// No-op
	}

}
