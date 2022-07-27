# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native"

LINUX_VERSION ?= "5.4.70"
KERNEL_BRANCH ?= "5.4.70"
KERNEL_SRC ?= "git://github.com/engicam-stable/linux-engicam-nxp.git;protocol=http"
SRC_URI = "${KERNEL_SRC};branch=${KERNEL_BRANCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://defconfig"

SRCREV_default = "${AUTOREV}"
PV = "${KERNEL_BRANCH}+git${SRCPV}"

COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
