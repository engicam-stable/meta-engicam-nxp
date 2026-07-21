require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc

MACHINE_FEATURES += "pci wifi bluetooth optee bcm43430"
MACHINE_FEATURES:append:use-nxp-bsp = " nxpwifi-all-pcie nxpwifi-all-sdio jailhouse dpdk xen"

IMX_DEFAULT_BOOTLOADER:use-nxp-bsp = "u-boot-imx"
IMX_DEFAULT_BOOTLOADER:use-mainline-bsp = "u-boot-fslc"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

SPL_BINARY = "spl/u-boot-spl.bin"

UBOOT_CONFIG ??= "sd"
UBOOT_CONFIG[sd] = "${UBOOT_CONFIG_BASENAME}_defconfig,sdcard"


ATF_PLATFORM = "imx95"

OEI_CORE   = "m33"
OEI_SOC    = "mx95"
OEI_BOARD  = "mx95lp5"

DDR_TYPE   = "lpddr5"


# imx-boot (flash.bin) targets based on UBOOT_CONFIG and IMXBOOT_VARIANT
IMXBOOT_TARGETS_SD = '${IMXBOOT_TARGETS_BASENAME}_all ${IMXBOOT_TARGETS_BASENAME}_a55'

IMXBOOT_TARGETS = " \
    ${@bb.utils.contains('UBOOT_CONFIG', 'fspi',   '${IMXBOOT_TARGETS_BASENAME}_a55_flexspi', \
       bb.utils.contains('UBOOT_CONFIG', 'sd-ecc', '${IMXBOOT_TARGETS_BASENAME}_all', \
                                                   '${IMXBOOT_TARGETS_SD}', d), d)} \
"

IMX_BOOT_SOC_TARGET = "iMX95"
IMX_BOOT_SEEK = "32"

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

IMX_DEFAULT_BSP = "nxp"
