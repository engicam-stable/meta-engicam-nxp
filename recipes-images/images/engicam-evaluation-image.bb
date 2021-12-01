# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image
inherit populate_sdk_qt5

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


HANTRO_PKGS = ""
HANTRO_PKGS_mx8mm = "imx-vpu-hantro-daemon"
HANTRO_PKGS_mx8mp = "imx-vpu-hantro-daemon"
HANTRO_PKGS_mx8mq = "imx-vpu-hantro-daemon"


ERPC_COMPS ?= ""
ERPC_COMPS_append_mx7ulp = "packagegroup-imx-erpc"

ISP_PKGS = ""
## ISP_PKGS_mx8mp = "packagegroup-imx-isp"

# Install fonts
QT5_FONTS = "ttf-dejavu-common ttf-dejavu-sans \
			ttf-dejavu-sans-mono ttf-dejavu-serif "

# Install qtquick3d
QT5_QTQUICK3D = "qtquick3d qtquick3d-dev"

QT5_IMAGE = " \
	${QT5_QTQUICK3D} \
	${QT5_FONTS} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxkbcommon', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins', '', d)}\
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-dev', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-qmlplugins', '', d)} \
	qtdeclarative-dev \
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdeclarative-qmlplugins', '', d)} \
	qtmqtt-dev \
	qtmultimedia-dev \
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtmultimedia-qmlplugins', '', d)} \
	qtserialport-dev \
	qtserialbus-dev \
	qtwebsockets-dev \
	${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebsockets-qmlplugins', '', d)} \
	qtquickcontrols2 \
	qtquickcontrols2-dev \
	qtquickcontrols-qmlplugins \
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
"

IMAGE_INSTALL += " \
	brcm-patchram-plus \
	imx8-brcm \
	linux-firmware-bcm43430 \
	linux-firmware-sd8897 \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	packagegroup-qt5-eng-qtcreator-debug \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${ERPC_COMPS} \
	${ISP_PKGS} \
	${QT5_IMAGE} \
	${PKG_DEBUG} \
	${HANTRO_PKGS} \
"
