package org.monitoring.stream.analytics;

import java.io.IOException;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonParseException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;

public class ETLTransformer {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static String syntheticTransform(String json) throws JsonParseException, JsonMappingException, IOException {
		
		String syntheticResultString = "{}";
		JsonNode jsonNode = convertToJSON(json);
		JsonNode productId = jsonNode.get("id");
		JsonNode jsonResult = jsonNode.get("test_results");
		if (jsonResult != null && jsonResult.isArray()){
			JsonNode syntheticResult = jsonResult.get(0);
			if (syntheticResult.isObject() || syntheticResult != null){
				((ObjectNode) syntheticResult).put("productId", productId.asText());
				syntheticResultString = convertToString(syntheticResult);
			}
		}
		return syntheticResultString;
	}

	public static String gaTransform(String json) {

		return json;

	}
	
	public static String convertToString(final JsonNode obj) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
			System.out.println(ex);
		}
		return jsonString;
	}
	
	public static JsonNode convertToJSON(final String json) {
		JsonNode root = null;
		try {
			root = mapper.readTree(json);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return root;
	}

}
