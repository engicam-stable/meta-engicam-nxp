---
board: evb
board_label: Evaluation Board
module: mx95_smarcore
module_label: SmarCore MX95
---

# Test sheet SmarCore MX95 Evaluation Board

## Test sheet

## Version: 1.0

## Preliminary

Creation of engicam-evaluation-image-mx95 image for sdcard booting and same image for eMMC programming.

--------------------------------------------------------------------------------------------------------

## Board Type: Smarcore Evaluation Board

## SOM Type: SmarCore MX95

![image](images/smarc_evb.png)

--------------------------------------------------------------------------------------------------------

## U-boot tests

|                                       Test                    | Status  |
|---------------------------------------------------------------|---------|
| [eMMC Enviroment saving](#emmc-environment-saving)            |   OK    |
| [Sdcard Enviroment saving](#sdcard-environment-saving)        |   OK    |
| [Ethernet](#ethernet)                                         |   OK    |
| [Boot from eMMC](#boot-from-emmc)                             |   OK    |
| [Boot from sdcard](#boot-from-sdcard)                         |   OK    |
| [USB](#usb)                                                   |   OK    |
| [Serial Download](#serial-download)                           |   OK    |

## Test Notes:

### eMMC Environment saving

```bash
setenv serverip 192.168.2.93
saveenv
reset board
printenv serverip
```

### Sdcard environment saving

Close the connector JM1:

```bash
setenv serverip 192.168.2.93
saveenv
reset board
printenv serverip
```

### Ethernet

Once the serverip has been saved connect the board
at J25:

```bash
setenv ipaddr 192.168.2.70
ping 192.168.2.161
```

The output should be:

```bash
Using enetc-0 device
host 192.168.2.161 is alive
```

Do the same test on port J26. The output should be:

```bash
Using enetc-1 device
host 192.168.2.161 is alive
```

### Boot from eMMC

All switches positioned on OFF:

```bash
saveenv
```

The output should be:

```bash
Saving Environment to MMC... Writing to MMC(0)... OK
```

### Boot from sdcard

Close the switch :

```bash
saveenv
```

The output should be:

```bash
Saving Environment to MMC... Writing to MMC(1)... OK
```

### USB

Plug USB storage devices in J70/J71 connectors:

```bash
usb start
```

The output should be:

```bash
starting USB...
Bus usb@4c100000: Register 2000140 NbrPorts 2
Starting the controller
USB XHCI 1.10
Bus usb@4c200000: Register 2000140 NbrPorts 2
Starting the controller
USB XHCI 1.10
scanning bus usb@4c100000 for devices... 2 USB Device(s) found
scanning bus usb@4c200000 for devices... cannot reset port 3!?
4 USB Device(s) found
       scanning usb for storage devices... 3 Storage Device(s) found
```

```bash
usb tree
```

The output should be (for example):

```bash
USB device tree:
  1  Hub (5 Gb/s, 0mA)
  |  U-Boot XHCI Host Controller 
  |
  +-2  Mass Storage (480 Mb/s, 100mA)
       Lexar USB Flash Drive AAGQFTGHNCK9GDXE
     
  1  Hub (5 Gb/s, 0mA)
  |  U-Boot XHCI Host Controller 
  |
  +-2  Hub (480 Mb/s, 2mA)
  | |
  | +-3  Mass Storage (480 Mb/s, 200mA)
  |      Verbatim STORE N GO 072125AD020B8F58
  |    
  +-4  Mass Storage (5 Gb/s, 224mA)
        USB  SanDisk 3.2Gen1 04010b53be303fea317bff34a9d8b86
```

Once done do:

```bash
usb stop
```

```bash
stopping USB..
```

to safely remove storage devices.

### Serial Download

Close switch 4 to enable serial download. Connect
the board with the source machine (i.e. the machine you are
downloading the images from) by using the board's OTG header (N/A).

Power on the board.

From source machine check if an USB device for serial download
is detected:

```bash
uuu -lsusb
```

Output:

```bash
uuu (Universal Update Utility) for nxp imx chips -- lib1.5.141

Connected Known USB Devices
        Path     Chip    Pro     Vid     Pid     BcdVersion      Serial_no
        ====================================================================
        3:344    MX95    SDPS:   0x1FC9 0x015D   0x0002  1E0751C8AA2342A8

```

Here we see a device is detected. Then type:

```bash
uuu -v -b emmc_all imx-boot engicam-evaluation-image-mx95-imx95-smarcore.rootfs.wic
```

And wait for the download and flash of eMMC to finish.


--------------------------------------------------------------------------------------------------------

## Kernel Linux tests

| Status |              Test             | Note
|:------:|:-----------------------------:|--------------------------------
|   OK   |        Ethernet 0 (J68)       | see [note1](#note-1)
|   OK   |        Ethernet 1 (J69)       | see [note1](#note-1)
|   OK   |          USB 2.0 (J71)        | tested with USB stick
|   OK   |          USB 3.0 (J70)        | tested with USB stick 
|   OK   |      SD card (Switch 2 ON)    | boot system from SD card
|   OK   |      eMMC card                | boot system from eMMC
|   OK   |      UART 232 ttyLP4  (J14)   | see [note2](#note-2)
|   OK   |         Linux Console (J15)   |
|   OK   |              WIFI             | see [note3](#note-3)
|   OK   |           BLUETOOTH           | see [note4](#note-4)
|   OK   |              RTC              | see [note5](#note-5)
|   OK   |            Reboot             |
|   OK   |              LVDS             | 
|   OK   |           Backlight           | see [note6](#note-6)
|   OK   |          Touchscreen          | see [note7](#note-7)
|   N/A  |             HDMI              |
|   OK   |             Audio             | see [note8](#note-8)
|   OK   |       CAN 0/CAN 1 (J75-J76)   | see [note9](#note-9)
|   N/A  |     M.2 Key E PCIe (Wifi/BT)  | 
|   OK   |     M.2 Key B PCIe (J52)      | see [note10](#note-10)
|   OK   |      MIPI-CSI (Camera CSI0)   | see [note11](#note-11)
|   N/A  |      MIPI-CSI (Camera CSI1)   | see [note11](#note-11)


--------------------------------------------------------------------------------------------------------

## Note

### Note 1

Connect board's ethernet with your machine's one.

On your machine launch iperf3 server by typing:

```bash
iperf3 -s
```

On board launch iperf3 in client mode:

```bash
iperf3 -c 192.168.10.1
```

Using the IP address of your server machine.

Output:

```bash
Connecting to host 192.168.10.1, port 5201
[  5] local 192.168.10.85 port 47390 connected to 192.168.10.1 port 5201
[ ID] Interval           Transfer     Bitrate         Retr  Cwnd
[  5]   0.00-1.00   sec  12.2 MBytes   103 Mbits/sec    0    188 KBytes       
[  5]   1.00-2.00   sec  11.1 MBytes  93.3 Mbits/sec    0    188 KBytes       
[  5]   2.00-3.00   sec  11.0 MBytes  92.3 Mbits/sec    0    188 KBytes       
[  5]   3.00-4.00   sec  11.5 MBytes  96.5 Mbits/sec    0    188 KBytes       
[  5]   4.00-5.00   sec  10.9 MBytes  91.2 Mbits/sec    0    188 KBytes       
[  5]   5.00-6.00   sec  11.2 MBytes  94.4 Mbits/sec    0    188 KBytes       
[  5]   6.00-7.00   sec  11.4 MBytes  95.4 Mbits/sec    0    188 KBytes       
[  5]   7.00-8.00   sec  10.9 MBytes  91.2 Mbits/sec    0    188 KBytes       
[  5]   8.00-9.00   sec  11.2 MBytes  94.4 Mbits/sec    0    188 KBytes       
[  5]   9.00-10.01  sec  11.2 MBytes  93.1 Mbits/sec    0    188 KBytes       
- - - - - - - - - - - - - - - - - - - - - - - - -
[ ID] Interval           Transfer     Bitrate         Retr
[  5]   0.00-10.01  sec   113 MBytes  94.7 Mbits/sec    0            sender
[  5]   0.00-10.03  sec   112 MBytes  94.1 Mbits/sec                  receiver

iperf Done.
```

### Note 2

Connect TX/RX PIN connector J14 to the UART 232 port
From terminal launch command:

```bash
test_serial ttyLP4
```

Verify that the characters written from keyboard are echoed in terminal.


### Note 3

```bash
ifconfig wlan0 up
iw dev wlan0 scan | grep SSID
wpa_passphrase SSID PASSWORD > /etc/wpa_supplicant.conf
sdcsupp -iwlan0 -Dnl80211 -c/etc/wpa_supplicant.conf -B
udhcpc -iwlan0
```

### Note 4

```bash
echo "1" > /sys/kernel/debug/ieee80211/phy0/cc33xx/ble_enable
hciconfig -a
hciconfig hci0 up
hciconfig -a
hciconfig hci0 up
[bluetooth]# scan on
```

###  Note 5

Set a time and date to the clock:

```bash
date -s "2024-01-01 12:00:00"
hwclock -w -f /dev/rtc1
```

Turn off the system and power it back on. Once it booted check that the date is the same:

```bash
hwclock -r -f /dev/rtc1
```

### Note 6

Tested with command:

```bash  
echo <n> > /sys/class/backlight/backlight/brightness
```

where n is an integer from 0 to 100

### Note 7

Tested with command:

```bash
evtest /dev/input/event1
```

### Note 8

Record an audio file with command:

```bash
arecord -d 5 -f S16_LE /tmp/test-mic.wav
```

then play and check with command:

```bash
aplay /tmp/test-mic.wav
```

### Note 9

Connect J75 or J76 header to another board CAN header (or simply connect
together J75 and J76 on the same board) and configure related can links
on both ends of the connection:

End A:

```bash
ip link set can0 type can bitrate 125000
```

End B (for example on other board, can1):

```bash
ip link set can1 type can bitrate 125000
```

Enable interfaces on both ends:

End A:

```bash
ifconfig can0 up
```

End B (for example on other board, can1):

```bash
ifconfig can1 up
```

Make End A listen:

```bash
cantest can0
```

Send a frame from End B:

```bash
cantest can1 5A1#11.2233.44556677.88
```

Check output on End A:

```bash
read 16 bytes
5A1  [8] 11 22 33 44 55 66 77 88
```

Do the same from B to A (make B listen and A send a frame).
When done on same board the command sequence is:

```bash
ip link set can0 type can bitrate 125000
ip link set can1 type can bitrate 125000
ifconfig can0 up
ifconfig can1 up
cantest can0 &
cantest can1 5A1#11.2233.44556677.88
sleep 1
cantest can1 &
cantest can0 5A1#11.2233.44556677.88
```

### Note 10

Teted with WiFi/Bt PCIe device. Check with lspci:

```bash
lspci
```

Output:

```bash
00:00.0 PCI bridge: Synopsys, Inc. DWC_usb3 / PCIe bridge (rev 01)
01:00.0 <WiFi/Bt device description>
```

### Note 11

Commands for compressed file creation and playback.

```bash
MIPI_CSI-1 640x480:

media-ctl -l "'ov5640 3-003c':0->'csidev-4ad40000.csi':0 [1]"
media-ctl -l "'csidev-4ad40000.csi':1 -> '4ac10000.syscon:formatter@120':0 [1]"
media-ctl -V "'ov5640 3-003c':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'csidev-4ad40000.csi':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'4ac10000.syscon:formatter@120':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'crossbar':3 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.0':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.1':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.2':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.3':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.4':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.5':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.6':0 [fmt: UYVY8_1X16/640x480 field:none]"
media-ctl -V "'mxc_isi.7':0 [fmt: UYVY8_1X16/640x480 field:none]"
gst-launch-1.0 v4l2src device=/dev/video1 ! video/x-raw,width=640,height=480,format=YUY2 ! autovideosink

MIPI-CSI-1 1280x720:

media-ctl -l "'ov5640 3-003c':0->'csidev-4ad40000.csi':0 [1]"
media-ctl -l "'csidev-4ad40000.csi':1 -> '4ac10000.syscon:formatter@120':0 [1]"
media-ctl -V "'ov5640 3-003c':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'csidev-4ad40000.csi':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'4ac10000.syscon:formatter@120':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'crossbar':3 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.0':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.1':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.2':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.3':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.4':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.5':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.6':0 [fmt: UYVY8_1X16/1280x720 field:none]"
media-ctl -V "'mxc_isi.7':0 [fmt: UYVY8_1X16/1280x720 field:none]"
gst-launch-1.0 v4l2src device=/dev/video1 ! video/x-raw,width=1280,height=720,format=YUY2 ! autovideosink

MIPI-CSI-1 1920x1080:

media-ctl -l "'ov5640 3-003c':0->'csidev-4ad40000.csi':0 [1]"
media-ctl -l "'csidev-4ad40000.csi':1 -> '4ac10000.syscon:formatter@120':0 [1]"
media-ctl -V "'ov5640 3-003c':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'csidev-4ad40000.csi':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'4ac10000.syscon:formatter@120':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'crossbar':3 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.0':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.1':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.2':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.3':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.4':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.5':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.6':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
media-ctl -V "'mxc_isi.7':0 [fmt: UYVY8_1X16/1920x1080 field:none]"
gst-launch-1.0 v4l2src device=/dev/video1 ! video/x-raw,width=1920,height=1080,format=YUY2 ! autovideosink
```
