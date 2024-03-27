# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image

## Select Image Features
IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston"

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

PKG_DEBUG = "\
	alsa-utils \
	alsa-tools \
	alsa-state \
	bluez5 \
	brcm-patchram-plus \
	cantest \
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	e2fsprogs-resize2fs \
	engicam-mtd-script \
	ethtool \
	firmware-imx-hdmi \
	i2c-tools \
	imx-kobs \
	iperf3 \
	iproute2 \
	ldd \
	linux-firmware \
	minicom \
	mtd-utils \
	mtd-utils-ubifs \
	parted \
	serialtools \
	stress-ng \
	usbutils \
	zstd \
"

IMAGE_INSTALL += " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	${PKG_DEBUG} \
"
ROOTFS_POSTPROCESS_COMMAND:append = "fix_bcm43430; "

fix_bcm43430() {
  cd ${IMAGE_ROOTFS}/lib/firmware/brcm
  ln -sf brcmfmac43430-sdio.bin brcmfmac43430-sdio.fsl,imx6-icore.bin
}
