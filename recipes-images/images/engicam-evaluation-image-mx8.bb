SUMMARY = "Wayland image with a terminal for Engicam Boards evaluation"

IMAGE_FEATURES += " \
	debug-tweaks \
	splash \
	package-management \
	ssh-server-dropbear \
	hwcodecs \
	tools-debug \
	tools-profile \
	tools-testapps \
	weston \
"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

ENGICAM_PKG = "\
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
	firmware-imx-hdmi \
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
    packagegroup-security-tpm2 \
	libtss2-tcti-device \
"

CORE_IMAGE_EXTRA_INSTALL += " \
	packagegroup-core-full-cmdline \
    	packagegroup-imx-tools-audio \
	packagegroup-tools-bluetooth \
	packagegroup-fsl-tools-gpu \
	packagegroup-fsl-tools-gpu-external\
	packagegroup-fsl-gstreamer1.0 \
	packagegroup-fsl-gstreamer1.0-full \
	firmwared \
	${ENGICAM_PKG} \
"

PACKAGE_IMX_TO_REMOVE = ""
PACKAGE_IMX_TO_REMOVE:imxgpu2d = "gtk+3-demo"
PACKAGE_IMX_TO_REMOVE:imxgpu3d = ""

CORE_IMAGE_EXTRA_INSTALL:remove = "${PACKAGE_IMX_TO_REMOVE}"
CORE_IMAGE_EXTRA_INSTALL:append:imx8ulp-microgea = "imx8ulp-lwb5plus"
