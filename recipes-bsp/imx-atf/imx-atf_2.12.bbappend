FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-added-GPIO1-to-NS-word.patch \
    file://0002-plat-imx-imx8m-imx8mm-remove-UART4-from-M4.patch \
    "

