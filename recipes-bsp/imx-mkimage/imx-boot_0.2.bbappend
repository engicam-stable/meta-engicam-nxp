IMXBOOT_TARGETS_mx8mm = \
    "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', 'flash_flexspi', \
        bb.utils.contains('UBOOT_CONFIG', 'sd', 'flash_spl_uboot', \
                                                  'flash_multi_cores flash_dcd', d), d)}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx8mm = " \
		file://0001-fix-dtb-for-icoremx8mm.patch \
"
