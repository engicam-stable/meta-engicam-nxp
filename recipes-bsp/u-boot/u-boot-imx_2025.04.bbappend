FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-added-mxl-8611x-PHY-driver.patch \
            file://0002-Added-nxp-pcal6416-entries-on-pca953x_gpio-driver.patch \
            file://0003-added-imx95-smarcore-support.patch \
            file://0004-added-missing-defines.patch \
            file://0005-added-support-for-imx95-icore.patch \
            file://0006-microgea-mx6ull-port-MicroGEA-MX6ULL-boards.patch \
            file://0007-include-configs-microgea-mx6ull.h-remove-cma-size-bo.patch \
            file://0008-imx8mp-icore-port-i.Core-MX8M-Plus-boards.patch \
            file://0009-imx8mp-icore-fasteth-port-i.Core-MX8MP-Fast-Ethernet.patch \
            file://0010-imx8mp-icore-2e-port-i.Core-MX8M-Plus-2GbE-boards.patch \
            file://0011-common-usb_onboard_hub.c-add-clock-management.patch \
            file://0012-drivers-clk-imx-clk-imx8mp.c-add-CLKOUT1-support.patch \
            file://0013-imx8mp-smarcore-port-SmarCore-MX8M-Plus-board.patch \
            file://0014-imx8mm-icore-port-i.Core-MX8M-Mini-boards.patch \
            file://0015-imx8ulp-microgea-port-MicroGEA-MX8ULP-boards.patch \
            file://0016-configs-fix-fastboot-flash-commmand.patch \
            file://0017-drivers-net-phy-add-MXL8611X-ethernet-phy-driver.patch \
            file://0018-imx93-icore-port-i.Core-MX93-boards.patch \
            file://0019-imx91-microgea-port-MicroGEA-MX91-boards.patch \
            file://0020-4GB-DDR-selection-for-iMX8MP-2e-and-Smarcore.patch \
            file://0021-imx8mp-Fixed-dram-init.patch \
            "
            