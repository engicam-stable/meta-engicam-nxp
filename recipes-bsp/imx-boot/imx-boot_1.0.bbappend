FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://eng_m33_image.bin"

# Use mx93a0-ahab-container.img for the a1 does not boot SPL
REV_OPTION:mx93-generic-bsp  = "REV=A0"

do_compile:prepend() {
    if ${IS_DXL}; then
        bbwarn "!!! Booting with an image for the wrong DXL Rev will PERMANENTLY DAMAGE YOUR BOARD !!!"
    fi
    if [ "${REV_OPTION}" = "ERROR_8DXL_REV_AMBIGUOUS" ]; then
        bbfatal "Machine ${MACHINE} cannot be used. Please select a machine with the correct Rev for your board."
    fi
    case ${SOC_FAMILY} in
    mx8)
        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_TCM_power_mode_switch_m40.bin \
                                                             ${BOOT_STAGING}/m4_image.bin
        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_TCM_power_mode_switch_m41.bin \
                                                             ${BOOT_STAGING}/m4_1_image.bin
        ;;
    mx8x)
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
        ;;
    mx8ulp)
        cp ${WORKDIR}/eng_m33_image.bin       ${BOOT_STAGING}/m33_image.bin
        ;;
    esac
}
