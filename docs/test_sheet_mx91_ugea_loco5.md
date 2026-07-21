---
board: loco5
board_label: Loco 5
module: mx91_ugea
module_label: MicroGea MX91
---

# Test sheet MicroGea MX91 Loco 5

## Test sheet

## Version: 1.0

## Preliminary

Creation of engicam-evaluation-image-mx91 image for sdcard booting and
same image for eMMC programming.

------------------------------------------------------------------------

## Board Type: Loco 5

## SOM Type: MicroGEA MX91

![image](images/loco5.png)

------------------------------------------------------------------------

## U-boot tests

|                                       Test                                    | Status  |
|-------------------------------------------------------------------------------|---------|
| [eMMC Enviroment saving](#emmc-environment-saving)                            |   OK    |
| [Sdcard Enviroment saving](#sdcard-environment-saving)                        |   OK    |
| [Ethernet](#ethernet)                                                         |   OK    |
| [Boot from eMMC](#boot-from-emmc)                                             |   OK    |
| [Boot from sdcard](#boot-from-sdcard)                                         |   OK    |
| [USB](#usb)                                                                   |   OK    |

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

Once the serverip has been saved:

```bash
setenv ipaddr 192.168.2.70
ping 192.168.2.161
```

The output should be:

```bash
Using ethernet@2188000 device
host 192.168.2.161 is alive
```

### Boot from eMMC

```bash
saveenv
```

The output should be:

```bash
Saving Environment to MMC... Writing to MMC(0)... OK
```

### Boot from sdcard

Close the connector JM1:

```bash
saveenv
```

The output should be:

```bash
Saving Environment to MMC... Writing to MMC(1)... OK
```

### USB

Plug USB storage devices in J9:

```bash
usb start
```

The output should be:

```bash
starting USB...
Bus usb@4c100000: Port not available.
Bus usb@4c200000: USB EHCI 1.00
scanning bus usb@4c200000 for devices... 2 USB Device(s) found
       scanning usb for storage devices... 1 Storage Device(s) found
```

```bash
usb tree
```

The output should be (for example):

```bash
USB device tree:
  1  Hub (480 Mb/s, 0mA)
  |  u-boot EHCI Host Controller 
  |
  +-2  Mass Storage (480 Mb/s, 100mA)
       USB 2.0 Flash Disk AB00007000005248
```

Once done do:

```bash
usb stop
```

```bash
stopping USB..
```

to safely remove storage devices.

------------------------------------------------------------------------

## Kernel Linux tests

| Status |              Test             | Note
|--------|-------------------------------|-----------------------------
|  OK    |          Ethernet J10         | see [note1](#note-1)
|  OK    |              USB              |
|  OK    |            MMC card           |
|  OK    |      UART 232 ttyLP1 J16      | see [note2](#note-2)
|  OK    |      UART 485 ttyLP2 J14      | see [note3](#note-3)
|  OK    |        Linux Console J7       |
|  OK    |             WIFI              | see [note4](#note-4)
|  OK    |          BLUETOOTH            | see [note5](#note-5)
|  OK    |             RTC               | see [note6](#note-6)
|  OK    |             LVDS              |
|  OK    |           Backlight           | see [note7](#note-7)
|  OK    |          Touchscreen          | see [note8](#note-8)
|  OK    |          Can Bus J11          | see [note9](#note-9)
|  TBT   |            Audio              | see [note10](#note-10)

------------------------------------------------------------------------

## Note

### Note 1

```bash
ping -c4 10.24.0.1
PING 10.24.0.1 (10.24.0.1) 56(84) bytes of data.
64 bytes from 10.24.0.1: icmp_seq=1 ttl=254 time=41.0 ms
64 bytes from 10.24.0.1: icmp_seq=2 ttl=254 time=41.1 ms
64 bytes from 10.24.0.1: icmp_seq=3 ttl=254 time=40.9 ms
64 bytes from 10.24.0.1: icmp_seq=4 ttl=254 time=40.9 ms

--- 10.24.0.1 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3005ms
rtt min/avg/max/mdev = 40.850/40.939/41.064/0.080 ms
```

### Note 2

Connect TX/RX PIN connector J16 to the UART 232 port
Type command:

```bash
test_serial ttyLP1
```

Verify that the characters written from keyboard are echoed in terminal.

### Note 3

Tested with other RS485 device connected with command:

```bash
test_serial2 -d /dev/ttyLP4 -b 115200
```

### Note 4

Simple script for wifi testing:

```bash
modprobe moal mod_para=nxp/wifi_mod_para.conf
ifconfig mlan0 up
iw dev mlan0 scan | grep SSID
wpa_passphrase SSID PASSWORD > /etc/wpa_supplicant.conf
wpa_supplicant -imlan0 -Dnl80211 -c/etc/wpa_supplicant.conf -B
udhcpc -imlan0
echo "nameserver 8.8.8.8" > /etc/resolv.conf
```

### Note 5

Simple script for bluetooth testing:

```bash
hciattach -b /dev/ttyLP3 any 3000000 flow
hciconfig hci0 up
hcitool scan
```

### Note 6

Set a time and date to the clock:

```bash
date -s "2024-07-23"
hwclock -w && sync
```

Turn off the system and power it back on. Once it booted check that the
date is the same:

```bash
hwclock
```

### Note 7

Tested with command:

```bash
echo <n> > /sys/class/backlight/backlight/brightness
```

where n is an integer from 0 to 100

### Note 8

Tested with command:

```bash
evtest /dev/input/event0
```

### Note 9

Tested with command:

```bash
ip link set can0 type can bitrate 125000
ifconfig can0 up
# To receive a frame:
cantest can0
# To send a frame:
cantest can0 5A1#11.2233.44556677.88
```

### Note 10

Add these packages to the recipe:

```
alsa-utils \
packagegroup-fsl-tools-audio \
packagegroup-fsl-gstreamer1.0 \
packagegroup-fsl-gstreamer1.0-full \
```

Tested with command:

```bash
gst-launch-1.0 filesrc location=/usr/share/sounds/alsa/Side_Right.wav ! wavparse ! audioconvert ! audioresample ! alsasink device=hw:0,0
```
