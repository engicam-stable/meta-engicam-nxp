FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://eng_m33_image.bin \
"

do_compile:prepend() {
    case ${SOC_FAMILY} in
    mx8ulp)
        cp ${UNPACKDIR}/eng_m33_image.bin ${BOOT_STAGING}/m33_image.bin
        ;;
    esac
}
