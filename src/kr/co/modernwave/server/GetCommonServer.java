package kr.co.modernwave.server;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;
import kr.co.modernwave.log.Logger;
import kr.co.modernwave.nano.DefaultMethodContext;
import kr.co.modernwave.nano.RestRequest;

public class GetCommonServer extends DefaultMethodContext {

	private Map<String, String> urlPath;
	private Gson gson = new Gson();
	public static String value;

	public GetCommonServer() {
		this.urlPath = new HashMap<>();
	}

	public String getUrlPath() {
		return gson.toJson(urlPath, Map.class);
	}

	public static String getValue() {
		return value;
	}

	@Override
	public Response GET(RestRequest request) {
		Logger.Write.info("GetCommonServer - start...");

		for (Map.Entry<String, String> i : request.getPathValues().entrySet()) {
			String key = i.getKey().replace("{", "").replace("}", "");
			String value = i.getValue();
			this.value = value;

			System.out.printf("PathValue %s:$s%n", key, value);
			urlPath.put(key, value);
		}

		return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", "success");
	}

	@Override
	public Response POST(RestRequest request) {
		Logger.Write.info("GetCommonServer - start...");
		return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", "success");
	}

}
