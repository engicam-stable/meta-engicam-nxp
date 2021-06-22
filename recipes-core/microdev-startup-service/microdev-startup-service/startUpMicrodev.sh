#!/bin/sh
echo 1 > /sys/class/gpio/UMTS_ON/value
sleep 1
echo 0 > /sys/class/gpio/UMTS_ON/value
sleep 1

/usr/bin/brcm_patchram_plus --patchram /lib/firmware/brcm/BCM4373A0.hcd --enable_hci --no2bytes --tosleep 50000 --baudrate 3000000 --use_baudrate_for_download /dev/ttymxc7
