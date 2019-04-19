FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
		file://0001-fix-dtb-for-icoremx8mm.patch \
"
