meta-engicam-nxp
================


Based on NXP Yocto Scarthgap
----------------------------

To use the i.MX Mmanifest repository repo tool must be installed first.

```bash
mkdir ~/bin
curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
PATH=${PATH}:~/bin
```

Then download the yocto project BSP base on scarthgap

```bash
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://github.com/nxp-imx/imx-manifest -b imx-linux-scarthgap -m imx-6.6.52-2.2.0.xml 
repo sync
```

SOMs supported
--------------

- imx6dl-icore
- imx6qd-icore
- imx6ull-microgea
- imx8mm-icore
- imx8mp-icore
- imx8mp-icore-2e
- imx8mp-icore-fasteth
- imx8mp-smarcore
- imx8ulp-microgea
- imx91-microgea
- imx93-icore



Boards supported
----------------



|SOM                   |                BOARD                |
|----------------------|-------------------------------------|
|imx93-icore           |ctouch2                              |
|imx91-microgea        |microdev-rev3                        |
|                      |micro5                               |
|imx8mm-icore          |ctouch2                              |
|                      |starterkit-v2                        |
|imx8mp-icore-2e       |ctouch2                              |
|                      |starterkit-v2                        |
|imx8mp-icore-fasteth  |ctouch2                              |
|                      |starterkit-v2                        |
|imx8mp-icore          |ctouch2                              |
|                      |starterkit-v2                        |
|imx8ulp-microgea      |microdev-rev3                        |
|imx8mp-smarcore       |xtouch2                              |
|                      |starterkit-v2                        |
|imx6dl-icore          |starterkit-v2                        |
|imx6qd-icore          |starterkit-v2                        |
|imx6ull-microgea      |microdev-rev3                        |



Supported distros
-----------------

- fsl-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

- engicam-evaluation-image-mx6
- engicam-evaluation-image-mx6ull
- engicam-evaluation-image-mx8
- engicam-evaluation-image-mx93
- engicam-evaluation-image-mx91

First build
-----------

For imx8mp-icore:


```
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx91-microgea:

```
DISTRO=fsl-imx-xwayland MACHINE=imx91-microgea source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx91
```



# NOTE for imx91-microgea

Due to an Hardware issue on imx91-microgea it's impossible set the boot from sdcard.

There are 2 alterrnatives:

## Using uuu with starterkit 2.0

Enter into the image deploy folder and follow the next instructions

1 .download and build the last uuu version_

````
git clone --recursive https://github.com/nxp-imx/mfgtools.git
cd mfgtools
cmake .
make -j12
cp ./uuu/uuu ..
cd ..
````

2. Close these Jumpers on the board:

- nSD_BOOT
- B_M
- J_USB1

3. Launch the uuu command. Es:


````bash
sudo ./uuu -b emmc_all imx-boot-imx91-microgea-sd.bin-flash_singleboot core-image-weston-imx91-microgea.rootfs.wic.zst

sudo ./uuu -b emmc_all imx-boot-imx91-microgea-sd.bin-flash_singleboot core-image-minimal-imx91-microgea.rootfs.wic.zst

sudo ./uuu -b emmc_all imx-boot-imx91-microgea-sd.bin-flash_singleboot engicam-evaluation-image-mx91-imx91-microgea.rootfs.wic.zst

sudo ./uuu -b emmc imx-boot-imx91-microgea-sd.bin-flash_singleboot
````


## Start from eMMC card and set the u-boot variable for sdcard booting


__For microdev 2.0__

````bash
setenv mmcautodetect
saveenv
````

Reboot the board.

````bash
setenv mmcdev 2
setenv fdtfile imx91-microgea-microdev2.dtb
setenv mmcroot '/dev/mmcblk2p2 rootwait rw'
saveenv
````

__For microdev 3.0__

````bash
setenv mmcautodetect
saveenv
````

Reboot the board.

````bash
setenv mmcdev 2
setenv fdtfile imx91-microgea-microdev3.dtb
setenv mmcroot '/dev/mmcblk2p2 rootwait rw'
saveenv
````
