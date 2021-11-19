HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"


UBOOT_BRANCH ?= "2020.04"
UBOOT_SRC ?= "git://github.com/engicam-stable/u-boot-engicam-nxp.git;protocol=http"
SRC_URI = "${UBOOT_SRC};branch=${UBOOT_BRANCH}"
SRCREV_default = "${AUTOREV}"
PV = "${UBOOT_BRANCH}+git${SRCPV}"

S = "${WORKDIR}/git"
