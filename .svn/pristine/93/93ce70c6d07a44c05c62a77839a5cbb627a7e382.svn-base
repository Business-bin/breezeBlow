package com.brb.brbcom.foundation.property;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.collections.ExtendedProperties;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;


/**
 * 
 * @author back
 *
 */
public class PropertyServiceImpl implements PropertyService, InitializingBean, DisposableBean, ResourceLoaderAware  {
	private ExtendedProperties dempProperties = null;
	private ResourceLoader resourceLoader = null;

	@SuppressWarnings("unused")
	private MessageSource messageSource;
	@SuppressWarnings("rawtypes")
	private Set extFileName;
	@SuppressWarnings("rawtypes")
	private Map properties;

	
	public boolean getBoolean(String name) {
		return getConfiguration().getBoolean(name);
	}

	

	public boolean getBoolean(String name, boolean def) {
		return getConfiguration().getBoolean(name, def);
	}

	
	public double getDouble(String name) {
		return getConfiguration().getDouble(name);
	}

	
	public double getDouble(String name, double def) {
		return getConfiguration().getDouble(name, def);
	}

	public float getFloat(String name) {
		return getConfiguration().getFloat(name);
	}

	public float getFloat(String name, float def) {
		return getConfiguration().getFloat(name, def);
	}


	public int getInt(String name) {
		return getConfiguration().getInt(name);
	}

	public int getInt(String name, int def) {
		return getConfiguration().getInt(name, def);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterator getKeys() {
		return getConfiguration().getKeys();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator getKeys(String prefix) {
//		getConfiguration().values();
		return getConfiguration().getKeys(prefix);
	}

	
	public long getLong(String name) {
		return getConfiguration().getLong(name);
	}

	public long getLong(String name, long def) {
		return getConfiguration().getLong(name, def);
	}

	public String getString(String name) {
		return getConfiguration().getString(name);
	}

	public String getString(String name, String def) {
		return getConfiguration().getString(name, def);
	}

	
	public String[] getStringArray(String name) {
		return getConfiguration().getStringArray(name);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getVector(String name) {
		return getConfiguration().getVector(name);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getVector(String name, Vector def) {
		return getConfiguration().getVector(name, def);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection getAllKeyValue(){		
		return getConfiguration().values();
	}

	
	private ExtendedProperties getConfiguration() {
		return dempProperties;
	}

	
	@SuppressWarnings({ "rawtypes" })
	public void refreshPropertyFiles() throws Exception  {
		
        String fileName = null;
        
		try {			
			
			Iterator it = extFileName.iterator();
			
			while (it != null && it.hasNext()) {
		        // Get element
		        Object element = it.next();
		        String enc = null;

		        if ( element instanceof Map ) {
	        		Map ele = (Map)element ;
		        	enc = (String) ele.get("encoding");
		        	fileName = (String) ele.get("filename");
		        }
		        else{
		        	fileName = (String)element;
		        }
				loadPropertyResources( fileName , enc );	        	
		    }

		} catch (Exception e) {
			if (PropertyService.LOGGER.isErrorEnabled()) {
				PropertyService.LOGGER.error("error.properties.refresh.files" + " " +  fileName);
				PropertyService.LOGGER.error("error", e);
			}
			throw new Exception();

		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void afterPropertiesSet()  {
		try {
			
			dempProperties = new ExtendedProperties();
			
			// 외부파일이 정의되었을때
			if ( extFileName != null ){			
				refreshPropertyFiles();
			}
	
			if(properties==null)  return;
			
			Iterator it = properties.entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry entry = (Map.Entry)it.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
        		
				if (PropertyService.LOGGER.isDebugEnabled()) {
					PropertyService.LOGGER.debug(key + " : "+value);
				}

				if (key == null || key.equals("")) {
					if (PropertyService.LOGGER.isErrorEnabled())
						PropertyService.LOGGER.error("key is null");
					throw new Exception("key is null");
				}
				
				dempProperties.put(key, value);
			}

		} catch (Exception e) {
				if (PropertyService.LOGGER.isErrorEnabled())
					PropertyService.LOGGER.error("properties error ", e);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void setExtFileName(Set extFileName) {
		this.extFileName = extFileName;
	}	
	
	@SuppressWarnings("rawtypes")
	public void setProperties(Map properties) {
		this.properties = properties;
	}	

	public void destroy() {
		dempProperties = null ;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}


	private void loadPropertyResources(String location, String encoding)
			throws Exception {

		if (resourceLoader instanceof ResourcePatternResolver) {
			try {
				Resource[] resources = ((ResourcePatternResolver) resourceLoader)
						.getResources(location);
				
				loadPropertyLoop(resources, encoding);
			} catch (IOException ex) {
				throw new BeanDefinitionStoreException(
						"Could not resolve Properties resource pattern ["
								+ location + "]", ex);
			}
		} else {
			
			Resource resource = resourceLoader.getResource(location);
			loadPropertyRes(resource, encoding);
		}

	}

	private void loadPropertyLoop(Resource[] resources, String encoding)
			throws Exception {
		Assert.notNull(resources, "Resource array must not be null");
		for (int i = 0; i < resources.length; i++) {
			loadPropertyRes(resources[i], encoding);
		}
	}

	
	private void loadPropertyRes(Resource resource, String encoding)
			throws Exception {
		if (PropertyService.LOGGER.isDebugEnabled()) {
			PropertyService.LOGGER.debug("loadPropertyRes : "+resource.getFilename());
		}
		ExtendedProperties egovProperty = new ExtendedProperties();
		egovProperty.load(resource.getInputStream(), encoding);
		dempProperties.combine(egovProperty);
	}
	
	
	
	public String getPath(String name)  {
		try {
			String location = getConfiguration().getString(name);
			return resourceLoader.getResource(location).getFile().getPath();
		} catch (IOException e) {
			
		}
		return name;
	}
}

