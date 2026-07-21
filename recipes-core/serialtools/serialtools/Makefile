#CFLAGS=-I.

test_serial: test_serial2.c test_serial.c serial_lib.c test_gps.c
	$(CC) $(CFLAGS) $(LDFLAGS) -c -Wall test_gps.c -o test_gps.o
	$(CC) $(CFLAGS) $(LDFLAGS) -c -Wall test_serial.c -o test_serial.o
	$(CC) $(CFLAGS) $(LDFLAGS) -c -Wall test_serial2.c -o test_serial2.o
	$(CC) $(CFLAGS) $(LDFLAGS) -c -Wall serial_lib.c -o serial_lib.o
	$(CC) $(CFLAGS) $(LDFLAGS) serial_lib.o test_serial.o -o test_serial
	$(CC) $(CFLAGS) $(LDFLAGS) serial_lib.o test_gps.o -o test_gps
	$(CC) $(CFLAGS) $(LDFLAGS) test_serial2.o -o test_serial2
clean:
	-rm *.o test_serial test_gps test_serial2

