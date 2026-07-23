---
board: microdev
module: mx8ulp_ugea
---

# Test sheet MicroGea MX8Ulp

## Test sheet

## Version: 1.0

## Preliminary

Creation of engicam-evaluation-image-mx8 image for sdcard booting and same image for eMMC programming.

--------------------------------------------------------------------------------------------------------

## Board Type: Microdev 2.0 (left image), Microdev Rev 3 (right image)

## SOM Type: MicroGEA MX8ULP

![image](images/microdev.png){width=45%}
![image](images/microdev3.png){width=45%}

--------------------------------------------------------------------------------------------------------

## U-boot tests

|                                       Test                                    | Status  |
|-------------------------------------------------------------------------------|---------|
| [eMMC Enviroment saving](#emmc-environment-saving)                            |   OK    |
| [Sdcard Enviroment saving](#sdcard-environment-saving)                        |   OK    |
| [Ethernet](#ethernet)                                                         |   OK    |
| [Boot from eMMC](#boot-from-emmc)                                             |   OK    |
| [Boot from sdcard](#boot-from-sdcard)                                         |   OK    |
| [Linux Console](#linux-console)                                               |   OK    |
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

Plug USB storage devices in J10:

```bash
usb start
```

The output should be:

```bash
starting USB...
Bus usb@29900000: USB EHCI 1.00
Bus usb@29920000: USB EHCI 1.00
scanning bus usb@29900000 for devices... 2 USB Device(s) found
scanning bus usb@29920000 for devices... 2 USB Device(s) found
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
  +-2  Vendor specific (480 Mb/s, 2mA)
       Qualcomm CDMA Technologies MSM QHSUSB__BULK

  1  Hub (480 Mb/s, 0mA)
  |  u-boot EHCI Host Controller
  |
  +-2  Mass Storage (480 Mb/s, 100mA)
       Lexar USB Flash Drive AAGQFTGHNCK9GDXE
```

Once done do:

```bash
usb stop
```

```bash
stopping USB..
```

to safely remove storage devices.


--------------------------------------------------------------------------------------------------------

## Kernel Linux tests

| Status |              Test             | Note
|--------|-------------------------------|-----------------------------
|  OK    |            Ethernet           | see [note1](#note-1)
|  OK    |              USB              |
|  OK    |            MMC card           |
|  TBT   |         M33 DEBUG J31         |
|  OK    |      UART 485 ttyLP2 J32      | see [note2](#note-2)
|  OK    |         Linux Console J8      |
|  OK    |             WIFI              | see [note3](#note-3)
|  N/A   |          BLUETOOTH            | see [note4](#note-4)
|  OK    |             UMTS              | see [note5](#note-5)
|  OK    |             RTC               | see [note6](#note-6)
|  TBT   |            CAN J32            | see [note7](#note-7)

## Only for Microdev 2.0

| Status |              Test             | Note
|--------|-------------------------------|-----------------------------
|  OK    |             LVDS              | see [note8](#note-8)
|  OK    |           Backlight           | see [note9](#note-9)
|  OK    |          Touchscreen          | see [note10](#note-10)
|  OK    |              GPU              | see [note11](#note-11)

--------------------------------------------------------------------------------------------------------

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

Tested with other RS485 device connected with command:

```bash
test_serial2 -d /dev/ttyLP2 -b 115200
```

This is supported only if the board lacks a CAN transceiver
bound to J32 connector.

### Note 3

Simple script for wifi testing:

```bash
ifconfig eth0 down
echo "nameserver 8.8.8.8" > /etc/resolv.conf
ifconfig wlan0 up
iw dev wlan0 scan | grep SSID
wpa_passphrase SSID password > /etc/wpa_supplicant.conf
wpa_supplicant -iwlan0 -Dnl80211 -c/etc/wpa_supplicant.conf -B
udhcpc -i wlan0
```

On walnascar wpa_supplicant 2.11 doen't work properly with brcmfmac driver.
If you want to use wpa_supplicant include the package:

```
    brcmfmac-fix \
```

in your image.

Alternatively you can use iwd. Remember to uncomment the line

```
WIRELESS_DAEMON = "iwd"
```

inside recipe `/recipes-core/packagegroups/packagegroup.bbappend`.

At first use type:

```bash
$ iwctl
[iwd]# device list
                           Devices
--------------------------------------------------------------------------------
  Name                  Address               Powered     Adapter     Mode
--------------------------------------------------------------------------------
  wlan0                 ff:ff:ff:ff:ff:ff     on          phy0        station

[iwd]# device wlan0 set-property Powered on
[iwd]# device phy0 set-property Powered on
[iwd]# station wlan0 scan
[iwd]# station wlan0 get-networks
                                 Available Networks
--------------------------------------------------------------------------------
    Network name                         Security                Signal
--------------------------------------------------------------------------------
>   <SSID>                               psk                     ****

[iwd]# station wlan0 connect SSID
...passphrase will be reqested...
[iwd]# quit
$ udhcpc -i wlan0
$ ping www.google.com
PING www.google.com (216.58.209.36) 56(84) bytes of data.
64 bytes from mil07s12-in-f4.1e100.net (216.58.209.36): icmp_seq=1 ttl=114 time=37.1 ms
64 bytes from mil07s12-in-f4.1e100.net (216.58.209.36): icmp_seq=2 ttl=114 time=44.4 ms
64 bytes from mil07s12-in-f4.1e100.net (216.58.209.36): icmp_seq=3 ttl=114 time=46.4 ms
64 bytes from mil07s12-in-f4.1e100.net (216.58.209.36): icmp_seq=4 ttl=114 time=44.6 ms
64 bytes from mil07s12-in-f4.1e100.net (216.58.209.36): icmp_seq=5 ttl=114 time=44.3 ms
...
```

iwd automatically stores network passphrases in the /var/lib/iwd
directory and uses them to auto-connect in the future.

### Note 4

Bluetooth is not available at the moment for kernel lacks rpmsg
driver to talk with lpuart0 device on M33.

Simple script for bluetooth testing in Microdev 3.0:

```bash
systemctl start bluetooth
brcm_patchram_plus \
  --patchram /etc/firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd \
  --enable_hci --no2bytes --tosleep 50000 \
  --baudrate 3000000 --use_baudrate_for_download \
  /dev/ttyLPx &

hciconfig hci0 up
bluetoothctl
agent on
scan on
```

And for Microdev 2.0:

```bash
systemctl start bluetooth
brcm_patchram_plus \
  --patchram /etc/firmware/BCM43430A1.1DX.hcd \
  --enable_hci --no2bytes --tosleep 50000 \
  --baudrate 3000000 --use_baudrate_for_download \
  /dev/ttyLPx &

hciconfig hci0 up
bluetoothctl
agent on
scan on
```

###  Note 5

```bash
echo 0 > /sys/class/gpio/UMTS_RESET/value
sleep 0.5
echo 1 > /sys/class/gpio/UMTS_ON/value
sleep 0.5
echo 0 > /sys/class/gpio/UMTS_ON/value
sleep 0.5

# result after 5 seconds:

usb 1-1: New USB device found, idVendor=1e0e, idProduct=9001, bcdDevice= 3.18
usb 1-1: New USB device strings: Mfr=1, Product=2, SerialNumber=3
usb 1-1: Product: SimTech, Incorporated
usb 1-1: Manufacturer: SimTech, Incorporated
usb 1-1: SerialNumber: 0123456789ABCDEF
option 1-1:1.0: GSM modem (1-port) converter detected
usb 1-1: GSM modem (1-port) converter now attached to ttyUSB0
option 1-1:1.1: GSM modem (1-port) converter detected
usb 1-1: GSM modem (1-port) converter now attached to ttyUSB1
option 1-1:1.2: GSM modem (1-port) converter detected
usb 1-1: GSM modem (1-port) converter now attached to ttyUSB2
option 1-1:1.3: GSM modem (1-port) converter detected
usb 1-1: GSM modem (1-port) converter now attached to ttyUSB3
option 1-1:1.4: GSM modem (1-port) converter detected
usb 1-1: GSM modem (1-port) converter now attached to ttyUSB4
qmi_wwan 1-1:1.5: cdc-wdm0: USB WDM device
qmi_wwan 1-1:1.5 wwan0: register 'qmi_wwan' at usb-ci_hdrc.0-1, WWAN/QMI device, 1a:64:34:bc:ca:93
```

###  Note 6

Set a time and date to the clock:

```bash
date -s "2000-01-01"
hwclock -w && sync
```

Turn off the system and power it back on. Once it booted check that the date is the same:

```bash
hwclock
```

### Note 7

Test with two Microdev 3.0 via M33 example app flexcan ping_pong_buffer_transfer.
A specific mount option with CAN transceiver is required for CAN support on Microdev 3.0.

On Microdev 2.0 CAN is only supported on boards with specific mount option
where a CAN transceiver is bound to J32 connector.
But Soc MX8ULP does not have linux kernel driver for FlexIO simulating CAN at
the moment.

### Note 9

Tested with command:

```bash
gst-launch-1.0 videotestsrc ! capsfilter caps="video/x-raw, width=1024, height=600" ! autovideosink
```

### Note 10

Tested with command:

```bash  
echo <n> > /sys/class/backlight/backlight/brightness
```

where n is an integer from 0 to 100

### Note 11

Tested with command:

```bash
evtest /dev/input/event0
```

### Note 12

Use benchmark tool Glmark2:

```bash
glmark2-es2-wayland
```
