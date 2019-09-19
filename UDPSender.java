import java.net.*;
import java.util.Scanner;

public class UDPSender {
	private static int PACKETSIZE = 100 ;
	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port numberOfMessages" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      DatagramSocket receiver = null;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
	         int numberOfMessages = Integer.parseInt ( args[2] );
	         int count = 0;
	         socket = new DatagramSocket() ;
	         String message = null;
	         while ( count < numberOfMessages)
	         {
	        	 count++;
	    		 message = "Message" + count;
	        	 byte [] data = message.getBytes() ;
	    		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	    		 socket.send( packet ) ;
	    		 DatagramPacket recievedPacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
		            socket.receive( recievedPacket ) ;
		            System.out.println( recievedPacket.getAddress() + " " + recievedPacket.getPort() + ": " + new String(recievedPacket.getData()).trim() ) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

