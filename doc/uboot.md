
imx-mkimage Build Guide
======

imx-mkimage is used to combine input images and generate final boot image with appropriate IVT set.
Before building the boot image, need to prepare the input images first:

1. DDR PHY firmware images (Mandatory, used for all targets)
   Files:		lpddr4_pmu_train_imem.bin and lpddr4_pmu_train_dmem.bin
   Git:			ssh://git@sw-stash.freescale.net/imx/linux-firmware-imx.git
   Directory:   	firmware/ddr/synopsys

2. u-boot and SPL images (Mandatory, used for all targets)
   Files:		u-boot.bin and u-boot-spl.bin
   Git:			ssh://git@sw-stash.freescale.net/imx/uboot-imx.git
   Directory:		./u-boot.bin and spl/u-boot-spl.bin
   Build command:	make imx8mq_evk_defconfig; make

3. ATF image (Mandatory, used for all targets)
   File:		bl31.bin
   Git:			ssh://git@sw-stash.freescale.net/imx/arm-trusted-firmware.git
   Directory:		build/imx8mq/release
   Build command:	make PLAT=imx8mq

4. HDMI firmware image (flash_hdmi_spl_uboot and flash_hdmi_spl_uboot_tee)
   File:		hdmi_imx8m.bin
   Git:			ssh://git@sw-stash.freescale.net/imx/linux-firmware-imx.git
   Directory:		firmware/hdmi/cadence

5. Optee image (flash_hdmi_spl_uboot_tee and flash_spl_uboot_tee)
   File:		tee.bin
   Git:			ssh://git@sw-stash.freescale.net/imx/imx-optee-os.git


Buil u-boot out bitbake environment.
-------

Create your temporary build directory:
```
mkdir /media/matteo/ext4ml/mx8m_mm_yocto/temp_build
```

1. launch the bitbake environment with setup-environment command.

Es.
```
. setup-environment imx8mm-wayland/
```
and launch the devshell of imx-mkimage packet with command:

```
bitbake imx-mkimage -c devshell
```
the copy the git directory to your temporary build directory.

es.
```
cd ..
cp -rf git/  /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/imx-mkimage
exit
```

Now do the same with uboot source:

es.
```
bitbake u-boot-imx -c devshell
```
On new terminal:

```
cd ..
cp -rf git/ /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/u-boot-imx
cp -rf  deploy-u-boot-imx/imx-boot-tools/mkimage_uboot /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/imx-mkimage/iMX8M/
exit
```

Now copy the ddr training firmware into imx-mkimage/iMX8M

luanch the bitabke devshell on firmware packet
```
bitbake firmware-imx -c devshell

cp  ./firmware/ddr/synopsys/lpddr4_pmu_train_*  /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/imx-mkimage/iMX8M/

cp ./firmware/hdmi/cadence/signed_hdmi_imx8m.bin /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/imx-mkimage/iMX8M/
exit
```

Now copy the binary file from atf packet

```
bitbake imx-atf -c devshell
cp ../deploy-imx-atf/imx-boot-tools/bl31-imx8mm.bin  /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/imx-mkimage/iMX8M/bl31.bin
exit
```

2.
open a new terminal and enter on your temporary build folder.
```
cd /media/matteo/ext4ml/mx8m_mm_yocto/temp_build
```

3.
lanuch the toolchain setup environment
es.
```
. /opt/fsl-imx-wayland/4.9.51-mx8-beta/environment-setup-aarch64-poky-linux
```
and start configuring and building:
```
cd u-boot-imx/
make icore_mx8mm_defconfig
make
```
Now copy the files below to ../imx-mkimage/iMX8M/ folder with command:
```
cp -v spl/u-boot-spl.bin ../imx-mkimage/iMX8M/
cp -v u-boot-nodtb.bin ../imx-mkimage/iMX8M/
cp -v arch/arm/dts/icore-imx8mm.dtb ../imx-mkimage/iMX8M/
```
Now enter on imx-mkimage folder:

```
cd ../imx-mkimage/
```
and lauch the build command:

```
make SOC=iMX8MM flash_spl_uboot dtbs=icore-imx8mm.dtb
```
or if you need to use hdmi:

```
make SOC=iMX8MM flash_hdmi_spl_uboot  dtbs=icore-imx8mm.dtb
```
Now you can flash in on your sdcard:

```
sudo dd if=iMX8M/flash.bin of=/dev/sdf  bs=1k seek=33
```
