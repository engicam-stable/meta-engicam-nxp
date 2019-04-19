DESCRIPTION = "Engicam image for hardware test"

LICENSE = "MIT"

inherit core-image

EXTRA_IMAGE_FEATURES = " debug-tweaks ssh-server-openssh tools-debug "


IMAGE_INSTALL_append = " \
	binutils \
	psplash \
	iproute2 \
	canutils \
	cantest \
	serialtools \
	devmem2 \
	i2c-tools \
	imx-kobs \
	test-sound \
	fbset \
	minicom \
	ethtool \
	dosfstools \
	e2fsprogs \
	fsl-alsa-plugins \
	v4l-utils \
	usbutils \
	cpufrequtils \
	wireless-tools \
    tslib \
	tslib-conf \
	tslib-tests \
	tslib-calibrate \
	evtest \
	alsa-utils \
	alsa-tools \
	alsa-state \
	fbset \
	packagegroup-fsl-gstreamer1.0-full \
	imx-test \
"

