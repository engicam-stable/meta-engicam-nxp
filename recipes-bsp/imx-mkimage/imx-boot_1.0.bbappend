IMXBOOT_TARGETS_mx8mm = \
    "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', 'flash_flexspi', \
        bb.utils.contains('UBOOT_CONFIG', 'sd', 'flash_spl_uboot', \
                                                  'flash_multi_cores flash_dcd', d), d)}"

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


