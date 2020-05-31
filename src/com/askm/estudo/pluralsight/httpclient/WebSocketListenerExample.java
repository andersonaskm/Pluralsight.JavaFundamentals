package com.askm.estudo.pluralsight.httpclient;

import org.apache.log4j.Logger;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebSocketListenerExample implements WebSocket.Listener {

    CountDownLatch receiveLatch;
    private static final Logger logger = Logger.getLogger(WebSocketListenerExample.class);

    public WebSocketListenerExample(CountDownLatch receiveLatch) {
        this.receiveLatch = receiveLatch;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        logger.debug("Web Socket open ");
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        logger.debug("onText "+ data);
        receiveLatch.countDown();
        return null;
    }
}
