# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam evaluation image for MicroDev 3.0 IOT gateway"
LICENSE = "MIT"

inherit core-image

## Select Image Features
IMAGE_FEATURES += " \
	debug-tweaks \
	tools-profile \
	package-management \
	nfs-server \
	tools-debug \
	ssh-server-dropbear \
	hwcodecs \
	"

PKG_DEBUG = "\
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	ethtool \
	i2c-tools \
	iproute2 \
	minicom \
	e2fsprogs-resize2fs \
	serialtools \
	usbutils \
	imx6ull-brcm \
	imx6ull-lwb5plus \
	engicam-mtd-script \
	mtd-utils \
	mtd-utils-ubifs \
	imx-kobs \
"

PKG_IOT = "\
	ppp-dialin \
	ppp \
	hostapd \
	iptables \
	python-paho-mqtt \
	python-pyserial \
	python-pip \
	python-psutil \
	python-lxml \
	python-pycurl \
	python-pysnmp \
	python-pysqlite \
	mysql-python \
	nfs-utils \
"

IMAGE_INSTALL += " \
	brcm-patchram-plus \
	linux-firmware-bcm43430 \
	linux-firmware-sd8897 \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	${PKG_DEBUG} \
	${PKG_IOT} \
"
