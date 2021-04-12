HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRC_URI = "git://github.com/engicam-stable/u-boot-engicam_2019.04.git;protocol=https;branch=u-boot-engicam_2019.04_4.19.35"

SRCREV = "40c516e01a46c324acc1f1f0223cd5d30176350d"

S = "${WORKDIR}/git"
