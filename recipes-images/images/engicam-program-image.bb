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
	package-management \
	splash \
	nfs-server \
	tools-debug \
	ssh-server-dropbear \
	tools-testapps \
	hwcodecs \
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
	serialtools \
	tslib \
	tslib-calibrate \
	tslib-conf \
	tslib-tests \
	usbutils \
	linux-firmware \
	nvme-cli \
	ldd \
	u-boot-fw-utils \	
"

IMAGE_INSTALL_append_mx8mm = " imx8-brcm "
IMAGE_INSTALL_append_mx8mp = " imx8-brcm "

IMAGE_INSTALL += " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${PKG_DEBUG} \
	engicam-emmc-tools \
"

IMAGE_INSTALL_append_mx6ull += "\
	engicam-mtd-script \
	imx-kobs \
	mtd-utils \
	mtd-utils-ubifs \
"
