SUMMARY = "iMx93 demo image Boards evaluation"

IMAGE_FEATURES += " \
	debug-tweaks \
	splash \
	ssh-server-dropbear \
	hwcodecs \
	tools-debug \
	tools-profile \
	tools-testapps \
"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-core-full-cmdline \
	packagegroup-imx-tools-audio \
	packagegroup-tools-bluetooth \	
	firmwared \
	alsa-utils \
	brcm-patchram-plus \
	cantest \
	canutils \
	devmem2 \
	dosfstools \
	e2fsprogs \
	e2fsprogs-resize2fs \
	engicam-emmc-tools \
	ethtool \
	evtest \
	i2c-tools \
	iperf3 \
	iproute2 \
	ldd \
	libgpiod \
	libgpiod-tools \
	minicom \
	parted \
	serialtools \
	stress-ng \
	test-sound \
	tzdata \
	usbutils \
	zstd \
	lvgl-demo-fb \
	iproute2 \ 
	rng-tools \ 
	ca-certificates \ 
	tzdata \ 
	htop \ 
	tcpdump \ 
	iw \ 
	imx91-lwb5plus \
	packagegroup-fsl-tools-audio \
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
"
