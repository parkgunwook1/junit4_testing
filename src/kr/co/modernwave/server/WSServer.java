package kr.co.modernwave.server;

import java.io.IOException;
import java.util.Map;
import kr.co.modernwave.log.Logger;

import fi.iki.elonen.NanoWSD.WebSocketFrame;
import fi.iki.elonen.NanoWSD.WebSocketFrame.CloseCode;
import kr.co.modernwave.nano.IWebSocketEvent;
import kr.co.modernwave.nano.WebSocketEx;

public class WSServer implements IWebSocketEvent {

	private int port;

	public WSServer(int port) {
		this.port = port;
	}

	@Override
	public void onClose(WebSocketEx conn, CloseCode arg1, String reason, boolean arg3) {

	}

	@Override
	public void onException(WebSocketEx conn, IOException ex) {

	}

	@Override
	public void onMessage(WebSocketEx conn, WebSocketFrame frame) {

	}

	@Override
	public void onOpen(WebSocketEx conn, String patternPath, Map<String, String> pathValues) {
		Logger.Write.info("WebServer Starting...");
	}

	@Override
	public void onPong(WebSocketEx arg0, WebSocketFrame arg1) {

	}
}
