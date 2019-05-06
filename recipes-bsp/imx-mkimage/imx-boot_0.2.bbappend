FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx8mm = " \
		file://0001-fix-dtb-for-icoremx8mm.patch \
"

SRC_URI_append_mx8mq = " \
		file://0001-fix-dtb-for-icoremx8m.patch \
"
