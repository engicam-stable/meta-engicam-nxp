# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image

### WARNING: This image is NOT suitable for production use and is intended
###          to provide a way for users to reproduce the image used during
###          the validation process of Engicam SOM

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	package-management \
	splash \
	ssh-server-dropbear \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
		bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"

PKG_DEBUG = "\
	brcm-patchram-plus \
	cantest \
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	ethtool \
	evtest \
	i2c-tools \
	iproute2 \
	minicom \
	parted \
	e2fsprogs-resize2fs \
	serialtools \
	tslib \
	tslib-calibrate \
	tslib-conf \
	tslib-tests \
	usbutils \
	linux-firmware \
	nvme-cli \
	ldd \
	bcm43430-nvram-config \
	lwb-bcm4343w-fw \
"

IMAGE_INSTALL += " \	
	packagegroup-tools-bluetooth \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${PKG_DEBUG} \
"

IMAGE_INSTALL_append_mx6ull += "\
	engicam-mtd-script \
	imx-kobs \
	mtd-utils \
	mtd-utils-ubifs \
"
