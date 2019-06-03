FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
		file://0001-add-icoremx8mm-support.patch\
		file://0002-fix-icoremx8mm-uboot.patch \
		file://0003-switch-to-1g-ram-support.patch \
		file://0004-fix-lpddr4_timing.patch \
		file://0005-emmc-running-icoremx8mm.patch \
		file://0006-add-icoremx8m-support.patch \
		file://0007-fix-u-boot-args-for-icoremx8m.patch \
"


#0008-fix-samsung-ddr-timing.patch


