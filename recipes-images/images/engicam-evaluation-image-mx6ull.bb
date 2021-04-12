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
	package-management \
	splash \
	nfs-server \
	tools-debug \
	ssh-server-dropbear \
	tools-testapps \
	hwcodecs \
"


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
	engicam-mtd-script \
	imx-kobs \
	mtd-utils \
	mtd-utils-ubifs \
"

CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-tools-bluetooth \
	${PKG_DEBUG} \
  ${QT5_IMAGE} \
"
