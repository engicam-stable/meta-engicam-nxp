# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Engicam image for hardware test"
LICENSE = "MIT"

inherit core-image

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
ERPC_COMPS ?= ""
ERPC_COMPS_append_mx7ulp = "packagegroup-imx-erpc"

ISP_PKGS = ""
ISP_PKGS_mx8mp = "packagegroup-imx-isp"

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
    ldd \   
    minicom \
    serialtools \
    test-sound \
    tslib \
    tslib-calibrate \
    tslib-conf \
    tslib-tests \
    usbutils \    
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-audio \
    ${ERPC_COMPS} \
    ${ISP_PKGS} \
    ${PKG_DEBUG} \
"
