# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2022 NXP
# Released under the MIT license (see COPYING.MIT for the terms)
#
# SPDX-License-Identifier: MIT
#

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "eng_5.15.71"
LOCALVERSION = "-engicam"
KBRANCH = "${SRCBRANCH}"
SRC_URI = "git://git.engicam.com/external/linux-engicam-nxp.git;branch=${SRCBRANCH};protocol=https"
SRC_URI[sha256sum] = "e83cf5ea603e014ca7e1505465bb8c9cec822451c0a8e6668acc70ae87cf367b"
S = "${WORKDIR}/git"
#SRCREV = "${AUTOREV}"
SRCREV = "2fb3382c02c05f1b73a7242b88c879b4e5eff47f"

# PV is defined in the base in linux-imx.inc file and uses the LINUX_VERSION definition
# required by kernel-yocto.bbclass.
#
# LINUX_VERSION define should match to the kernel version referenced by SRC_URI and
# should be updated once patchlevel is merged.
LINUX_VERSION = "5.15.71"

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

DEFAULT_PREFERENCE = "1"

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY:mx6-nxp-bsp = "yes"
DO_CONFIG_V7_COPY:mx7-nxp-bsp = "yes"
DO_CONFIG_V7_COPY:mx8-nxp-bsp = "no"
DO_CONFIG_V7_COPY:mx9-nxp-bsp = "no"

# Add setting for LF Mainline build
#IMX_KERNEL_CONFIG_AARCH32 = "imx_v7_defconfig"
#IMX_KERNEL_CONFIG_AARCH64 ?= "imx_v8_defconfig"
KBUILD_DEFCONFIG ?= ""
KBUILD_DEFCONFIG:mx6-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH32}"
KBUILD_DEFCONFIG:mx7-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH32}"
KBUILD_DEFCONFIG:mx8-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH64}"
KBUILD_DEFCONFIG:mx9-nxp-bsp= "${IMX_KERNEL_CONFIG_AARCH64}"

# Use a verbatim copy of the defconfig from the linux-imx repo.
# IMPORTANT: This task effectively disables kernel config fragments
# since the config fragments applied in do_kernel_configme are replaced.
addtask copy_defconfig after do_kernel_configme before do_kernel_localversion
do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest IMX_KERNEL_CONFIG_AARCH32 to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/arch/arm/configs/${IMX_KERNEL_CONFIG_AARCH32} ${B}/.config
    else
        # copy latest IMX_KERNEL_CONFIG_AARCH64 to use for mx8
        mkdir -p ${B}
        cp ${S}/arch/arm64/configs/${IMX_KERNEL_CONFIG_AARCH64} ${B}/.config
    fi
}

do_kernel_configcheck[noexec] = "1"

KERNEL_VERSION_SANITY_SKIP="1"
COMPATIBLE_MACHINE = "(imx-nxp-bsp)"
