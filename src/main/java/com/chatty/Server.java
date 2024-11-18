package com.chatty;

import javafx.application.Platform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private final Selector selector;

    private final SocketChannel socketChannel;

    private final UserInterfaceService userInterfaceService;

    public Server(UserInterfaceService userInterfaceService) {
        try {
            this.userInterfaceService = userInterfaceService;
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.connect(new InetSocketAddress("localhost", 1234));
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            this.handleIncomingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        this.socketChannel.write(byteBuffer);
    }

    private void handleIncomingData() {
        new Thread(() -> {
            try {
                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(256);
                            SocketChannel serverChannel = (SocketChannel) selectionKey.channel();
                            serverChannel.read(buffer);
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            Platform.runLater(() -> {
                                userInterfaceService.addMessageInMessageContainer(new String(data).trim(), true);
                            });
                            System.out.println("Received: " + new String(data).trim());

                            buffer.clear();
                        }
                    }
                    selectionKeys.clear();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
