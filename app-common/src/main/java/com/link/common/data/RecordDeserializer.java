package com.link.common.data ;

import java.io.IOException ;
import java.util.Arrays ;
import java.util.Map ;

import org.springframework.core.ResolvableType ;
import org.springframework.util.Assert ;

import com.fasterxml.jackson.databind.DeserializationFeature ;
import com.fasterxml.jackson.databind.MapperFeature ;
import com.fasterxml.jackson.databind.ObjectMapper ;
import com.fasterxml.jackson.databind.ObjectReader ;

public class RecordDeserializer 
{
	protected final ObjectMapper	objectMapper ;

	protected final Class<Record>		targetType ;

	private volatile ObjectReader	reader ;

	protected RecordDeserializer()
	{
		this((Class<Record>) null) ;
	}

	@SuppressWarnings("deprecation")
	protected RecordDeserializer(ObjectMapper objectMapper)
	{
		this(null, objectMapper) ;
		
		if (this.reader == null)
		{
			this.reader = this.objectMapper.reader(this.targetType) ;
		}
	}

	public RecordDeserializer(Class<Record> targetType)
	{
		this(targetType, new ObjectMapper()) ;
		
		this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false) ;
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;
	}

	@SuppressWarnings("unchecked")
	public RecordDeserializer(Class<Record> targetType, ObjectMapper objectMapper)
	{
		Assert.notNull(objectMapper, "'objectMapper' must not be null.") ;
		this.objectMapper = objectMapper ;
		if (targetType == null)
		{
			targetType = (Class<Record>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0) ;
		}
		Assert.notNull(targetType, "'targetType' cannot be resolved.") ;
		this.targetType = targetType ;
	}

	public void configure(Map<String, ?> configs, boolean isKey)
	{
		// No-op
	}

	public Record deserialize(  byte[] data)
	{ 
		try
		{
			Record result = null ;
			if (data != null)
			{
				result = this.reader.readValue(data) ;
			}
			return result ;
		}
		catch (IOException e)
		{
			throw new RuntimeException("Can't deserialize data [" + Arrays.toString(data) + "]", e) ;
		}
	}

	public void close()
	{
		// No-op
	}

}
