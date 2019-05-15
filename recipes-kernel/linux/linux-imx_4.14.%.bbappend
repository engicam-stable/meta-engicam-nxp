FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-add-sn65dsi83-driver-and-drm-fix.patch \
    file://0002-add-icore-support.patch \
    file://0003-add-icoremx8mm_defconfig.patch \
    file://0004-fix-drm-section.patch \
    file://0005-add-sgtl5000-support.patch \
    file://0006-fix-on-icoremx8mm-defconfig.patch \
    file://0007-remove-CAAM-from-defconfig.patch \
    file://0008-change-defconfig.patch \
    file://0009-add-iCoreMX8M-support.patch \
    file://0010-add-hdmi-dtb-imx8m.patch \
    file://0011-add-edt-ft5x26-driver.patch \
    file://0012-fix-starter-kit-2.0-support.patch \
    file://0013-fix-icoremx8m-support.patch \
    file://0014-fix-edimm-2.0-starterkit-lcd-timing.patch \
    file://0015-add-edimm-2.0-starter-kit-touch-controller-support.patch \
    file://0016-fix-hub-usb-reset.patch \
    file://0017-add-ctouch2-imx8mm-amp-10-dtb.patch \
    file://0018-remove-useless-i2c-touch-entry.patch \
    file://0019-remove-ov5640-entry.patch \
    file://0020-add-CONFIG_DRM_VIVANTE-flag-on-config.patch \
    file://defconfig \
    "
