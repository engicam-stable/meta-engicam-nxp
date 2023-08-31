# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image"
LICENSE = "MIT"

inherit core-image

ROOTFS_POSTPROCESS_COMMAND:append:mx8 = "fix_bcm43430;"

fix_bcm43430() { 
  cd ${IMAGE_ROOTFS}/lib/firmware/brcm
  ln -sf brcmfmac43430-sdio.bin brcmfmac43430-sdio.engi,imx8-icore.bin
}

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	tools-profile \
	splash \
	tools-debug \
	ssh-server-dropbear \
	tools-testapps \
	hwcodecs \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', \
		bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d), d)} \
"


G2D_SAMPLES = ""
G2D_SAMPLES:imxgpu2d = "imx-g2d-samples"

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
	\
	parted \
	\
	serialtools \
	\
	usbutils \
	\
	zstd \
	\
"

EXTRA_PACKAGE_INSTALL += " \
	packagegroup-fsl-tools-audio \
	packagegroup-fsl-tools-gpu \
	packagegroup-fsl-tools-gpu-external\ 
	packagegroup-fsl-tools-testapps \
	packagegroup-fsl-tools-benchmark \
	packagegroup-imx-isp \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
"


CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	firmwared \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
	${ENGICAM_PKG} \
	${EXTRA_PACKAGE_INSTALL} \
	${G2D_SAMPLES} \
"

IMAGE_INSTALL += " \
	tzdata \
"

IMAGE_INSTALL:append:mx8m = "\
	imx8-brcm \
"
