package sample;

import java.io.IOException;
import java.net.*;

public class UdpBroadcastServer implements Runnable{
    private boolean broadcast = true;
    private DatagramSocket socket;
    private int portBroadcast;
    public String message = "Echoserver is ready on port 7000";
    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    @Override
    public void run() {
        broadcastLoop();
    }

    public void broadcastLoop()
    {
        do {
            try {
                Thread.sleep((3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (broadcast) broadcastMessage(message);
        }
        while (broadcast);

    }

    private void broadcastMessage(String message)
    {
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), 7007);
            socket.send(packet);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
