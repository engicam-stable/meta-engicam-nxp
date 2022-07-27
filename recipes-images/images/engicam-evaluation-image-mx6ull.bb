# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	tools-profile \
	tools-sdk \
	package-management \
	splash \
	nfs-server \
	tools-debug \
	ssh-server-dropbear \
	tools-testapps \
	hwcodecs \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', \
	   bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
	                                                   '', d), d)} \
	"

PKG_DEBUG = "\
	alsa-utils \
	cantest \
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	engicam-emmc-tools \
	ethtool \
	evtest \
	i2c-tools \
	iproute2 \
	minicom \
	parted \
	e2fsprogs-resize2fs \
	serialtools \
	usbutils \
	linux-firmware \
	nvme-cli \
	ldd \
	fsl-rc-local \
	imx6ull-brcm \
	imx6ull-lwb5plus \
	engicam-mtd-script \
	mtd-utils \
	imx-kobs \
"

IMAGE_INSTALL += " \
	brcm-patchram-plus \
	linux-firmware-bcm43430 \
	linux-firmware-sd8897 \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${PKG_DEBUG} \
"
