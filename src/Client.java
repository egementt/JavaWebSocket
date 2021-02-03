import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;



public class Client {



    public static void main(String[] args) throws IOException {
        int i = 0;

        Scanner sc = new Scanner(System.in);

        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[], ans[] = null;


        while (true)
        {
            String inp = sc.nextLine();

            buf = inp.getBytes();

            DatagramPacket DpSend =
                    new DatagramPacket(buf, buf.length, ip, 1234);


            ds.send(DpSend);



            if (inp.equals("bye"))
                break;

            if(i >= 2){
                receiveData();
                i = 0;
            }


            i++;
        }


    }

    public static void receiveData() throws SocketException {
        byte ans[] = new byte[545454];

        DatagramSocket datagramSocket = new  DatagramSocket(5008);

        DatagramPacket DpReceive =
                new DatagramPacket(ans, ans.length);


        try {
            datagramSocket.receive(DpReceive);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = new String(DpReceive.getData(),0,DpReceive.getLength());
        System.out.println(str);

        ans = new byte[545454];

        


    }


}
