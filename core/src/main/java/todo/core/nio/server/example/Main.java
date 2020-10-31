package todo.core.nio.server.example;

import todo.core.nio.server.Server;
import todo.core.nio.server.http.HttpMessageReaderFactory;
import todo.core.nio.server.message.IMessageProcessor;
import todo.core.nio.server.message.Message;

import java.io.IOException;

/**
 * fork from jenkov/nioserver [https://github.com/jjenkov/java-nio-server]
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 38\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body>Hello World!</body></html>";

        byte[] httpResponseBytes = httpResponse.getBytes("UTF-8");

        IMessageProcessor messageProcessor = (request, writeProxy) -> {
            System.out.println("Message Received from socket: " + request.socketId);

            Message response = writeProxy.getMessage();
            response.socketId = request.socketId;
            response.writeToMessage(httpResponseBytes);

            writeProxy.enqueue(response);
        };

        Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);

        server.start();
    }
}
