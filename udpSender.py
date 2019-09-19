# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
numberOfMessage = sys.argv[3]

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)
count = 0


while count < int(numberOfMessage):
    count += 1
    data = "Message" + str(count)
#    s.sendall(data.encode('utf-8'))
    s.sendto(data.encode('utf-8'), server_address)
    
    buf, address = s.recvfrom(port)
    if not len(buf):
        break
    print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))

#s.shutdown(1)

