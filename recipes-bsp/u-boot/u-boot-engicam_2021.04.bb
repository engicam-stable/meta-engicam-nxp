# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2021 NXP

DESCRIPTION = "i.MX U-Boot suppporting i.MX reference boards."
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native  dtc-native"

require recipes-bsp/u-boot/u-boot.inc
inherit fsl-u-boot-localversion

S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://192.168.2.254/yocto_honister/uboot-imx.git;protocol=http"
#UBOOT_SRC = "git:///home/mirko/u-boot-source/honister/git/uboot-imx/;protocol=file"
SRCBRANCH = "main"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH}"
SRCREV = "2c69d67acfaaefc8606e114509f7be9cf1f9078f"
LOCALVERSION = "-${SRCBRANCH}"
BOOT_TOOLS = "imx-boot-tools"

do_deploy:append:mx8m () {
    # Deploy u-boot-nodtb.bin and fsl-imx8mq-XX.dtb, to be packaged in boot binary by imx-boot
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${type}
                fi
            done
            unset  j
        done
        unset  i
    fi

}

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx6|mx8)"

UBOOT_NAME_mx6 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
