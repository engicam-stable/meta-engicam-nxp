DESCRIPTION = "Nvram support for Broadcom 43430 wifi/bt device"
SECTION = "kernel"

include broadcom-nvram-config.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

COMPATIBLE_MACHINE = "(imx6ull-microgea)"


