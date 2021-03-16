package context;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

public class ApplicationContext {

	Hashtable<String, Object> objTable = new Hashtable<String, Object>();
	
	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
//		prepareAnnotationObjects();
	}
	
	
//	private void prepareAnnotationObjects() {
//		Reflections reflector = new Reflections("");
//		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
//		String key = null;
//		for(Class<?> clazz : list) {
//			key = clazz.getAnnotation(Component.class).value();
//			objTable.put(key, clazz.newInstance());
//		}
//	}
}
