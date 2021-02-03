import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = null;
        int lenCount= 0;
        ArrayList<String> edges = new ArrayList<>();
        while (true)
        {

            DpReceive = new DatagramPacket(receive, receive.length);

            ds.receive(DpReceive);
            System.out.println("Client:-" + data(receive));

            if(data(receive) != null && edges.size() < 3){
                String data = data(receive).toString();
                edges.add(data);
                int i = 0;
                while (i<edges.size()){
                    i++;
                }
            }

            if(edges.size() > 2){
                int a = Integer.parseInt(edges.get(0));
                int b = Integer.parseInt(edges.get(1));
                int c = Integer.parseInt(edges.get(2));

                if(isEdge(a,b,c))
                    sendToClient("It is triangle",ds);

                else
                    sendToClient("It is not triangle",ds);
            }


            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            receive = new byte[65535];
        }
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public static boolean isEdge(int a, int b, int c){

        if(a+b < c) return false;

        if(a+c < b) return false;

        if(b+c < a) return false;


        return true;
    }

    public static void sendToClient(String str, DatagramSocket ds) throws IOException {

           try {
               byte answer[] = null;
               answer = str.getBytes();
               DatagramPacket dp = new DatagramPacket(answer, answer.length, InetAddress.getLocalHost(), 5008);
               ds.send(dp);

           }catch (UnknownHostException exception){
               System.out.println(exception.getMessage());
           }
    }

}

