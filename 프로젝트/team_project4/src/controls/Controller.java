package controls;

import java.util.Map;

public interface Controller {
	public String execute(Map<String, Object> model) throws Exception;
}
