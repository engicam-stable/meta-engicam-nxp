FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx8mm = " \
		file://0001-fixed-dtb-for-imx8mm-icore.patch \
"

SRC_URI_append_mx8mp = " \
		file://0001-fixed-dtb-for-imx8mp-icore.patch \
"

SRC_URI_append_mx8mpsmarcore = " \
		file://0002-fixed-dtb-for-imx8mp-smarcore.patch \
"

SRC_URI_append_mx8qxp = " \
	file://0001-fixed-dtb-for-imx8x-icore.patch \
"

SRC_URI_append_mx8qxpsmarcore = " \
	file://0001-fixed-dtb-for-imx8x-smarcore.patch \
"
