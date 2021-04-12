# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image
inherit populate_sdk_qt5

### WARNING: This image is NOT suitable for production use and is intended
###          to provide a way for users to reproduce the image used during
###          the validation process of Engicam SOM

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
"

IMAGE_INSTALL += " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	packagegroup-fsl-tools-audio \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	packagegroup-qt5-eng-qtcreator-debug \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${ERPC_COMPS} \
	${ISP_PKGS} \
	${QT5_IMAGE} \
	${PKG_DEBUG} \
"

IMAGE_INSTALL_append_mx6ull += "\
	engicam-mtd-script \
	imx-kobs \
	mtd-utils \
	mtd-utils-ubifs \
"

##IMAGE_INSTALL_append_mx8mp += "\
##	engicam-emmc-tools \
##"
##	imx-test \ 
