require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc

MACHINE_FEATURES += "pci wifi bluetooth"
MACHINE_FEATURES:append:use-nxp-bsp = " dpdk optee nxpwifi-all-sdio bcm43430 nxp8987-sdio"

UBOOT_DTB_NAME = "imx91-microgea.dtb"

IMX_DEFAULT_BOOTLOADER:use-nxp-bsp = "u-boot-imx"
IMX_DEFAULT_BOOTLOADER:use-mainline-bsp = "u-boot-fslc"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

SPL_BINARY = "spl/u-boot-spl.bin"

# Set ATF platform name
ATF_PLATFORM = "imx91"

IMXBOOT_TARGETS = "flash_singleboot"

IMX_BOOT_SOC_TARGET = "iMX91"
IMX_BOOT_SEEK = "32"

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

IMX_DEFAULT_BSP = "nxp"

MACHINE_FIRMWARE:append = " linux-firmware-bcm4373"
