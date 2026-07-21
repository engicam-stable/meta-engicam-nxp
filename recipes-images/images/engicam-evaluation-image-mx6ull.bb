SUMMARY = "Engicam evaluation image for MicroGEA MX6ULL"

#IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs"
IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston"

LICENSE = "MIT"

inherit core-image

#CORE_IMAGE_BASE_INSTALL += "gtk+3-demo"
#CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

PKG_DEBUG = "\
        alsa-utils \
	brcm-patchram-plus \
        brcmfmac-fix \
	cantest \
	canutils \
	cryptsetup \
	devmem2 \
	dosfstools \
	e2fsprogs \
	e2fsprogs-resize2fs \
	engicam-mtd-script \
	ethtool \
	fsl-rc-local \
	i2c-tools \
	imx-kobs \
	iperf3 \
	iproute2 \
	ldd \
	libgpiod \
	libgpiod-tools \
	minicom \
	mtd-utils \
	mtd-utils-ubifs \
	parted \
	serialtools \
	stress-ng \
	usbutils \
	zstd \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
    packagegroup-tools-bluetooth \
    ${PKG_DEBUG} \
"
