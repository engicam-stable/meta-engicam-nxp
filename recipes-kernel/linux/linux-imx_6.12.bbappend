FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://devtool-fragment.cfg \
    file://0001-added-imx95-smarcore-multimedia-4k.patch \
    file://0002-iMX95-Smarcore-removed-edma-disabled-on-EVB.patch \
    file://0003-added-support-for-imx95-icore-starterkit.patch \
    file://0004-imx95-smarcore-enabled-mipi_csi-fixed-sd.patch \
    file://0005-imx95-icore-starterkit-peripheral-issues-resolved.patch \
    file://0006-Fixed-WiFi-and-BT.patch \
    file://0007-gpio-gpiolib-add-gpio-export-of-capability.patch \
    file://0008-Input-edt-ft5x26-add-support.patch \
    file://0009-imx6ull-microgea-port-MicroGEA-MX6ULL-boards.patch \
    file://0010-regulator-pca9450-create-property-for-full-warm-rese.patch \
    file://0011-imx8mp-icore-.dts-port-i.Core-MX8M-Plus-boards.patch \
    file://0012-imx8mp-icore-fasteth-port-i.Core-MX8MP-Fast-Ethernet.patch \
    file://0013-arch-arm64-boot-dts-engicam-enable-video-on-MX8MP-CT.patch \
    file://0014-imx8mp-icore-2e-port-i.Core-MX8M-Plus-2GbE-boards.patch \
    file://0015-imx8mp-smarcore-xtouch2-port-SmarCore-MX8M-Plus-boar.patch \
    file://0016-drm-bridge-sn65dsi83-fix-probe-ordering-issue.patch \
    file://0017-Revert-drm-bridge-ti-sn65dsi83-Fix-enable-disable-fl.patch \
    file://0018-imx8mm-icore-port-i.Core-MX8M-Mini-boards.patch \
    file://0019-drivers-gpu-drm-panel-simple.c-add-Winstar-WF50-supp.patch \
    file://0020-imx8ulp-microgea-port-MicroGEA-MX8ULP-boards.patch \
    file://0021-drivers-net-phy-add-MXL8611X-ethernet-phy-driver.patch \
    file://0022-drivers-gpu-drm-panel-panel-simple-add-yes7-panel.patch \
    file://0023-imx93-icore-port-i.Core-MX93-boards.patch \
    file://0024-imx91-microgea-port-MicroGEA-MX91-boards.patch \
    file://0025-arch-arm64-boot-dts-engicam-imx91-microgea-starterki.patch \
    file://0026-added-support-imx95-smarcore-pico-board.patch \
    "
SRC_URI += " \
    file://kernel-config/config.cfg \
"

SRC_URI:append:mx95-icore = " file://kernel-config/mx95_icore_fragment.cfg"
SRC_URI:append:mx95-smarcore = " file://kernel-config/mx95_smarcore_fragment.cfg"
