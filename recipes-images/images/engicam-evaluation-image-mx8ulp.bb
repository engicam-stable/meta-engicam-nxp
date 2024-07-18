# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image for imx8ulp 4GB eMMC"
LICENSE = "MIT"

inherit core-image

ROOTFS_POSTPROCESS_COMMAND:append = " fix_bcm43430;"

fix_bcm43430() { 
  cd ${IMAGE_ROOTFS}/lib/firmware/brcm
  ln -sf brcmfmac43430-sdio.bin brcmfmac43430-sdio.engi,imx8-icore.bin
}

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	ssh-server-dropbear \
	hwcodecs \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', \
		bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d), d)} \
"

ENGICAM_PKG = "\
	\
	alsa-utils \
	\
	brcm-patchram-plus \
	\
	cantest \
	canutils \
	\
	devmem2 \
	dosfstools \
	\
	e2fsprogs \
	e2fsprogs-resize2fs \
	engicam-emmc-tools \
	ethtool \
	evtest \
	\
	firmware-imx-hdmi \
	\
	i2c-tools \
	iproute2 \
	\
	ldd \
	libgpiod \
	libgpiod-tools \
	linux-firmware-bcm43430 \
	linux-firmware \
	\
	minicom \
	memtester \
	\
	parted \
	\
	serialtools \
	\
	usbutils \
	\
	zstd \
	\
	test-sound \
	stress-ng \
	iperf3 \
"

CORE_IMAGE_EXTRA_INSTALL += " \
	firmwared \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${ENGICAM_PKG} \
"

IMAGE_INSTALL += " \
	tzdata \
"

IMAGE_INSTALL:append:mx8-icore = "\
	imx8-brcm \
"

IMAGE_INSTALL:append:mx8ulp-microgea = "\
	imx8ulp-lwb5plus \
"
