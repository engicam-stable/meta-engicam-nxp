DESCRIPTION = "Engicam evaluation image for MicroGEA MX6ULL"
LICENSE = "MIT"

inherit core-image

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	ssh-server-dropbear \
	hwcodecs \
	"

PKG_DEBUG = "\
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
	imx6ull-lwb5plus \
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

IMAGE_INSTALL += " \
	brcm-patchram-plus \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	${PKG_DEBUG} \
"
