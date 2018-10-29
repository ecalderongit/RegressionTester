package net.globalapp.common;

import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class SeleniumParameters {
	
	/** Literal vacio */
    private static final String STRING_EMPTY = "";
	/** Ruta para recuperar el archivo ApplicationResources.properties */
    private static final String APPLICATION_RESOURCES_FILE_PATH = "Parameters.properties";
    /** Instancia Singleton */
    private static SeleniumParameters instance = null;
    /** Instancia DEL properties */
    private static Properties properties;
   
    /**
     * Constructor
     */
    private SeleniumParameters() {}
    
    /**
     * Devuelve la instancia Singleton del ApplicationResourcesParameter
     */
    public static SeleniumParameters getInstance() {
        if (null == instance) {
            createInstance();
        }
        return instance;
    }

    /**
     * Se encarga de crear la instancia del ApplicationResourcesParameter
     */
    private synchronized static void createInstance() {
    	InputStream is;
    	
        if (null == instance) {

            // Se procede a cargar el archivo de propiedades            
            try {

            	is = Test.class.getClassLoader().getResourceAsStream(APPLICATION_RESOURCES_FILE_PATH);
            	
                properties = new Properties();
                properties.load(is);

                instance = new SeleniumParameters();


            } catch (Exception e) {
    			System.out.print(e);
            }
        }
    }
    
    /**
     * Metodo encargado de obtener del valor de un recursos apartir de su respectiva llava
     * @param key
     * @return message
     */
    public String getParam(String key) {
		
		String message = STRING_EMPTY;

		if (properties != null && properties.getProperty(key)!= null) {
			
			message = properties.getProperty(key);
		} else{
			
			message = STRING_EMPTY;
		}
		return message;
	}
    
}
