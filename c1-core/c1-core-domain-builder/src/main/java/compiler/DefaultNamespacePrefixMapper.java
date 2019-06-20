package compiler;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper {
	 
	private Map<String, String> namespaceMap = new HashMap<>();
 
	/**
	 * Create mappings.
	 */
	public DefaultNamespacePrefixMapper() {
		namespaceMap.put("urn:next:table", "");
		namespaceMap.put("urn:next:core", "core");
	}
 
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		return namespaceMap.getOrDefault(namespaceUri, suggestion);
	}
}