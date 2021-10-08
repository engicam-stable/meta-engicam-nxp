# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2021 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by Engicam"
DESCRIPTION = "Linux Kernel provided and supported by Engicam with focus on \
i.MX engicam Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."


LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

require recipes-kernel/linux/linux-imx.inc

# We need to pass it as param since kernel might support more then one
# machine, with different entry points
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "lzop-native bc-native"

KERNEL_BRANCH = "5.10.35"
LOCALVERSION = "-lts-5.10.y"

KERNEL_SRC ?= "git://github.com/engicam-stable/linux-engicam-nxp.git;protocol=http"

SRC_URI = "${KERNEL_SRC};branch=${KERNEL_BRANCH}"

SRCREV_default = "${AUTOREV}"

PV = "5.10.35"

S = "${WORKDIR}/git"

LINUX_VERSION = "5.10.35"

FILES_${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

DEFAULT_PREFERENCE = "1"

KBUILD_DEFCONFIG= "${IMX_KERNEL_CONFIG_AARCH64}"

# Use a verbatim copy of the defconfig from the linux-imx repo.
# IMPORTANT: This task effectively disables kernel config fragments
# since the config fragments applied in do_kernel_configme are replaced.
addtask copy_defconfig after do_kernel_configme before do_kernel_localversion
do_copy_defconfig () {
    install -d ${B}
    # copy latest IMX_KERNEL_CONFIG_AARCH64 to use for mx8
    mkdir -p ${B}
    cp ${S}/arch/arm64/configs/${IMX_KERNEL_CONFIG_AARCH64} ${B}/.config
}

COMPATIBLE_MACHINE = "(mx6|mx8)"
