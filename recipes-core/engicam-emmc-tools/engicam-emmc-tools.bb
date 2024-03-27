SUMMARY = "Tools for emmc programmig"
DESCRIPTION = "This package provides tools for emmc programmig"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/copyright;md5=7370e616173a3f5ee04da6bc4ee0ff3b"

inherit allarch

SRC_URI = "file://emmc_tools.sh \
	   file://copyright"

RDEPENDS:${PN} += "bash dosfstools e2fsprogs pv"

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/emmc_tools.sh ${D}${bindir}/emmc_tools.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_boot.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_dtb.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_fs.sh
  ln -sf emmc_tools.sh ${D}${bindir}/emmc_fs_ker.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_fs_ker_dtb.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_ker.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_ker_dtb.sh
	ln -sf emmc_tools.sh ${D}${bindir}/emmc_sdcard.sh
}
