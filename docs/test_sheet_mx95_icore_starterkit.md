---
board: starterkit
board_label: Starter Kit
module: mx95_icore
module_label: i.CoreMX95
---

# Test Sheet i.CoreMX95 Starterkit

**Test sheet**

**Version:** 1.0

**Preliminary**

Creation of engicam-evaluation-image-mx95 sdcard and boot for it by closing JM1.

---

**Board Type:** edimm 2.1 starterkit

**SOM Type:** i.CoreMX95

![image](images/starterkit_edimm2.png)

---

## U-BOOT

| Status | Test       | Note                                   |
|--------|------------|----------------------------------------|
| OK     | Ethernet 0 | Tested with ping                       |
| OK     | Ethernet 1 | Tested with ping                       |
| OK     | SDcard     | Boot system with jumper **JM1** closed |
| OK     | eMMC       | Boot system with jumper **JM1** opened |

## Kernel

| Status | Test                             | Note                                |
|--------|----------------------------------|-------------------------------------|
| OK     | Ethernet 0                       | Network Communication               |
| OK     | Ethernet 1                       | Network Communication               |
| OK     | USB2                             | Tested with usb stick on J8/J10     |
| OK     | USB3                             | Tested with usb stick on J11        |
| OK     | SD card                          | Boot system from sdcard. JM1 closed |
| OK     | eMMC                             | Boot system from emmc. JM1 open     |
| OK     | UART 232 (J21)                   | see [note1](#note-1)                |
| OK     | UART 485 (J22)                   | see [note2](#note-2)                |
| OK     | Linux Console                    | I/O on console (/dev/ttyLP0)        |
| OK     | Touchscreen                      | Tested manually                     |
| OK     | LVDS 0                           | Output on screen                    |
| OK     | Backlight Control                | see [note3](#note-3)                |
| OK     | AUDIO                            | see [note4](#note-4)                |
| OK     | PCI-e                            | see [note9](#note-9)                |
| OK     | WIFI                             | see [note5](#note-5)                |
| OK     | BLUETOOTH                        | see [note6](#note-6)                |
| OK     | MIPI-CSI                         | see [note7](#note-7)                |
| OK     | CAN_BUS_A / CAN_BUS_B (J23/J24)  | see [note8](#note-8)                |
| OK     | REBOOT                           |                                     |
| OK     | REBOOT BUTTON (S1)               |                                     |
| OK     | RTC                              |                                     |

---

## Note

### Note 1

On J21 rx and tx loopback connected. Tested with command:

```bash
test_serial ttyLP7
```

### Note 2

Tested with other board connected on J22 connector with command:

```bash
test_serial2 -d /dev/ttyLP4 -b 115200
```

### Note 3

Tested with command:

```bash
echo <n> > /sys/devices/platform/lvds_backlight/backlight/lvds_backlight/brightness

echo <n> > /sys/devices/platform/backlight/backlight/backlight/brightness
```

where n is an integer from 0 to 100

### Note 4

Record an audio file with command:

```bash
arecord -d 5 -f S16_LE /tmp/test-mic.wav
```

then play and check with command:

```bash
aplay /tmp/test-mic.wav
```

### Note 5

Tested with iwlist tool:

```bash
ifconfig wlan0 up
iw dev wlan0 scan | grep SSID
wpa_passphrase SSID PASSWORD > /etc/wpa_supplicant.conf
# wpa_supplicant -iwlan0 -Dnl80211 -c/etc/wpa_supplicant.conf -B
sdcsupp -i wlan0 -D nl80211 -c /etc/wpa_supplicant.conf -B
udhcpc -iwlan0
ping 8.8.8.8 -I wlan0
```

### Note 6

Tested with hciconfig:

```bash
brcm_patchram_plus --patchram /lib/firmware/brcm/BCM43430A1.hcd --enable_hci \
    --no2bytes --tosleep 50000 --baudrate 1000000 \
    --use_baudrate_for_download /dev/ttyUSB0 &
hciconfig -a
hciconfig hci0 up
hcitool scan
```

### Note 7

Check the cma size. It must be at least 512M, please add `cma=512M` at kernel command line.

Boot with engicam-image-demo-qt and launch pipeline:

```bash
gst-launch-1.0 v4l2src device=/dev/video3 ! waylandsink enable-tile=true sync=false
```

### Note 8

Tested with reclosing connectors J23 / J24:

```bash
# Configure the bit rate on target:
ip link set can0 type can bitrate 125000
ip link set can1 type can bitrate 125000
# Enable the interface on target:
ifconfig can0 up
ifconfig can1 up
# To receive a frame:
cantest can0 &
# To send a frame:
cantest can1 5A1#11.2233.44556677.88
# To receive a frame:
cantest can1 &
# To send a frame:
cantest can0 5A1#11.2233.44556677.88
```

### Note 9

Tested with pci-e ethernet:

```
00:00.0 PCI bridge: Synopsys, Inc. DWC_usb3 / PCIe bridge (rev 01)
01:00.0 PCI bridge: Pericom Semiconductor PI7C9X2G304 EL/SL PCIe2 3-Port/4-Lane Packet Switch (rev 05)
02:01.0 PCI bridge: Pericom Semiconductor PI7C9X2G304 EL/SL PCIe2 3-Port/4-Lane Packet Switch (rev 05)
02:02.0 PCI bridge: Pericom Semiconductor PI7C9X2G304 EL/SL PCIe2 3-Port/4-Lane Packet Switch (rev 05)
03:00.0 Ethernet controller: Realtek Semiconductor Co., Ltd. RTL8111/8168/8411 PCI Express Gigabit Ethernet Controller (rev 0c)
04:00.0 Ethernet controller: Realtek Semiconductor Co., Ltd. RTL8111/8168/8411 PCI Express Gigabit Ethernet Controller (rev 0c)
```
