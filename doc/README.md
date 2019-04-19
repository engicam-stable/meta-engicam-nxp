
imx8
======

Yocto
======

repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-morty -m imx-4.9.51-8mq_ga.xml
repo sync -j4

MACHINE=imx8mqevk DISTRO=fsl-imx-xwayland source fsl-setup-release.sh -b build

bitbake fsl-image-gui


u-boot
======

Clone the imx-mkimage repo:

```
git clone https://source.codeaurora.org/external/imx/imx-mkimage.git -b imx_4.9.51_imx8m_beta
```

and u-boot repo:

```
git clone https://source.codeaurora.org/external/imx/uboot-imx -b
imx_v2017.03_4.9.51_imx8_beta2
```

build u-boot:

```
make icoremx8m_defconfig

```

At the end copy:

```
cp -v spl/u-boot-spl.bin ../imx-mkimage/iMX8M/
cp -v u-boot-nodtb.bin ../imx-mkimage/iMX8M/
cp -v arch/arm/dts/icoremx8m.dtb ../imx-mkimage/iMX8M/
```

imx-mkimage
------

```
make SOC=iMX8M flash_spl_uboot

make SOC=iMX8M flash_hdmi_spl_uboot

sudo dd if=iMX8M/flash.bin of=/dev/sde  bs=1k seek=33
```

commandi da dare a u-boot
------
setenv ethaddr 02:42:2a:fe:9a:84
setenv ipaddr 192.168.2.64



Linux
======

git clone https://source.codeaurora.org/external/imx/linux-imx -b imx_4.9.51_imx8m_ga


LVDS [OK]
------

da cambiare il pin sotto che è associato al regolatore di boost


preso patch da varishite.

testato con il dcss presente nel file icoremx8. /opt/fsl-imx-wayland/4.9.51-mx8-beta/environment-setup-aarch64-poky-linuxm-lvds-dcss.dtsi

echo 13 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio13/direction
echo 1 > /sys/class/gpio/gpio13/value


HDMI [OK]
------

da capire se la periferica hdmi va clockata esternamente a 27 mhz o se si può
utilizzare un oscillatore esterno.


per hdmi va ricompilato u-boot
make SOC=iMX8M flash_hdmi_spl_uboot
sudo dd if=iMX8M/flash.bin of=/dev/sdc  bs=1k seek=33

setenv displayinfo 'video=mxcfb0:dev=hdmi,3840x2160M@60'

setenv displayinfo 'video=mxcfb0:dev=hdmi,1920x1080M@60'

setenv mmcargs 'setenv bootargs console=${console} root=${mmcroot} ${displayinfo}'

NAND Flash [OK]
------
```

flash_eraseall /dev/mtd0


ubiformat /dev/mtd0
ubiattach -p /dev/mtd0
ubimkvol /dev/ubi0 -N test  -m
mount -t ubifs ubi0:test /mnt
```

PCIe [KO]
------

GPU [OK]
------

Non importa il driver esterno
imx-gpu-viv                           1:6.2.4.p0.2-aarch64-r0                          
kernel-module-imx-gpu-viv                      :6.2.4.p0.2-r0

ifconfig eth0 192.168.2.64
mount -t nfs -o nfsvers=3,nolock 192.168.2.60:/nfs /mnt/
mount /dev/mmcblk0p1 /media
mv /mnt/icoremx8m.dtb  /media/
mv /mnt/Image  /media/

export KERNEL_SRC=~/_work_/engicam/mx8m/matteo_stuff/linux-imx/
ARCH="arm64" CROSS_COMPILE=aarch64-linux-gnu- make

modinfo -F vermagic

FEC [OK]
------

su u-boot:
setenv  ipaddr 192.168.2.66
setenv  ethaddr 88:44:22:22:44:66


WIFI [OK]
------
C134 e C139 da 22 pF 0402
R164 330K 0603

ritoccati i drive streng


REBOOT [KO]
------

SGTL5000 [OK]
------

gplay-1.0 LRMonoPhase4.wav


USB2 [OK]
------

Occhio al Jumper JMP2 è attaccato ad un deviatore per USB.

PIN sono 194 e 196

USB2_DN
USB2_DP

da abilitare il driver:

DesignWare USB3 DRD Core Support



MX8MQ_IOMUXC_GPIO1_IO09_GPIO1_IO9	0x19

metti a 0 e resetti
```

echo 2 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio2/direction
echo 0 > /sys/class/gpio/gpio2/value
echo 1 > /sys/class/gpio/gpio2/value
```

si muove il gpio ma non va uguale

oppure si può user il reset gpio


```

&iomuxc {
	icoremx8m {
        	pinctrl_usb2_reset: usb2grp {
			fsl,pins = <
                MX8MQ_IOMUXC_GPIO1_IO02_GPIO1_IO2 0x19
			>;
		};
    };        
};

/ {
    usb_hub_res:gpio-reset {
        compatible = "gpio-reset";
        pinctrl-names = "default";
        pinctrl-0 = <&pinctrl_usb2_reset>;
        reset-gpios = <&gpio1 2 GPIO_ACTIVE_HIGH>;
        initially-in-reset;
        reset-delay-us = <100000>;
        #reset-cells = <0>;
    };
};

&usb3_0 {
    resets = <&usb_hub_res>;
	status = "okay";
};
```


USB3 [OK]
------
```
mount /dev/sda1 /mnt
dd if=/dev/urandom  of=/run/media/sda1/dummy  bs=1M count=1024 conv=sync
```
```

**usb3
dd if=/dev/zero  of=/run/media/sda1/dummy  bs=1M count=1024
1024+0 records in
1024+0 records out
1073741824 bytes (1.1 GB, 1.0 GiB) copied, 68.8946 s, 15.6 MB/s

**usb2
dd if=/dev/zero  of=/run/media/sda1/dummy2  bs=1M count=1024
1024+0 records in
1024+0 records out
1073741824 bytes (1.1 GB, 1.0 GiB) copied, 57.128 s, 18.8 MB/s
```





NOTE
------

da implementare il gpio per l'overclock:
```

regulators {
  compatible = "simple-bus";
  #address-cells = <1>;
  #size-cells = <0>;

  reg_usdhc2_vmmc: usdhc2_vmmc {
    compatible = "regulator-fixed";
    regulator-name = "VSD_3V3";
    regulator-min-microvolt = <3300000>;
    regulator-max-microvolt = <3300000>;
    gpio = <&gpio2 19 GPIO_ACTIVE_HIGH>;
    enable-active-high;
  };
/*
  reg_gpio_dvfs: regulator-gpio {
    compatible = "regulator-gpio";
    pinctrl-names = "default";
    pinctrl-0 = <&pinctrl_dvfs>;
    regulator-min-microvolt = <900000>;
    regulator-max-microvolt = <1000000>;
    regulator-name = "gpio_dvfs";
    regulator-type = "voltage";
    gpios = <&gpio1 13 GPIO_ACTIVE_HIGH>;
    states = <900000 0x1 1000000 0x0>;
  };
*/        
};

&A53_0 {
	operating-points = <
		/* kHz    uV */
		1500000 1000000
		1300000 1000000
		1000000 900000
		800000  900000
	>;
	//dc-supply = <&reg_gpio_dvfs>;
};
```


cd /sys/devices/system/cpu/cpu0/cpufreq

echo performance > scaling_governor
cat scaling_cur_freq
echo powersave > scaling_governor
cat scaling_cur_freq
```

wget http://192.168.2.60:8000/Image
mount /dev/mmcblk0p1 /mnt/
mv Image  /mnt/

wget http://192.168.2.60:8000/icoremx8m.dtb
mount /dev/mmcblk0p1 /mnt/
mv icoremx8m.dtb  /mnt/
mv Image  /mnt/
REBOOT
```


dd if=/dev/urandom of=/dev/null bs=1M count=100


echo 13 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio13/direction
echo 1 > /sys/class/gpio/gpio13/value





ctouch 10 su iCoreMX8Mini


#ifdef C_TOUCH_10
    lvds_bridge: sn65dsi83@2c {
        compatible = "ti,sn65dsi83";
        reg = <0x2c>;
        ti,dsi-lanes = <2>;
        ti,lvds-format = <2>;
        ti,lvds-bpp = <24>;
        ti,width-mm = <149>;
        ti,height-mm = <93>;
		enable-gpios = <&gpio3 9  GPIO_ACTIVE_HIGH>;
        pinctrl-names = "default";
        pinctrl-0 = <&pinctrl_dsi_lvds_bridge>;
        status = "okay";

       display-timings {
            lvds {
                clock-frequency = <62500000>;
                hactive = <1280>;
                vactive = <800>;
                hback-porch = <6>;
                hfront-porch = <5>;
                vback-porch = <2>;
                vfront-porch = <3>;
                hsync-len = <2>;
                vsync-len = <1>;
                hsync-active = <0>;
                vsync-active = <0>;
                de-active = <1>;
                pixelclk-active = <0>;
            };
        };
#else
    lvds_bridge: sn65dsi83@2c {
        compatible = "ti,sn65dsi83";
        reg = <0x2c>;
        ti,dsi-lanes = <2>;
        ti,lvds-format = <2>;
        ti,lvds-bpp = <24>;
        ti,width-mm = <149>;
        ti,height-mm = <93>;
		enable-gpios = <&gpio3 9  GPIO_ACTIVE_HIGH>;
        pinctrl-names = "default";
        pinctrl-0 = <&pinctrl_dsi_lvds_bridge>;
        status = "okay";

       display-timings {
            lvds {
                clock-frequency = <76000000>;
                hactive = <1366>;
                vactive = <768>;
                hback-porch = <10>;
                hfront-porch = <10>;
                vback-porch = <10>;
                vfront-porch = <10>;
                hsync-len = <50>;
                vsync-len = <10>;
                hsync-active = <1>;
                vsync-active = <1>;
                de-active = <1>;
                pixelclk-active = <0>;
            };
        };
#endif
