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
	cantest \
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	ethtool \
	i2c-tools \
	iproute2 \
	minicom \
	parted \
	e2fsprogs-resize2fs \
	serialtools \
	usbutils \
	ldd \
	engicam-mtd-script \
	mtd-utils \
	mtd-utils-ubifs \
	imx-kobs \
	alsa-utils \
	alsa-tools \
	alsa-state \
	lwb-bcm4343w-fw \
	imx6-brcm \
	bluez5 \
	zstd \
	stress-ng \
	iperf3 \
"

IMAGE_INSTALL += " \
	brcm-patchram-plus \
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
