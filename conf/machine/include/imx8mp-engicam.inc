MACHINEOVERRIDES =. "mx8mp:"

# FIXME: u-boot-imx should be converted to `binman` and then we can
# avoid this specific overrides and handle it in a generic way.
MACHINEOVERRIDES =. "${@bb.utils.contains('IMX_DEFAULT_BOOTLOADER', 'u-boot-imx', '', 'imx-boot-container:', d)}"

require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

MACHINE_FEATURES += "pci wifi bluetooth"

PREFERRED_PROVIDER_virtual/kernel:mx8-nxp-bsp = "linux-engicam"
PREFERRED_PROVIDER_linux-imx-mfgtool:mx8-nxp-bsp = ""
PREFERRED_PROVIDER_virtual/bootloader:mx8-nxp-bsp = "u-boot-engicam"
PREFERRED_PROVIDER_u-boot:mx8-nxp-bsp = "u-boot-engicam"

# NXP BSP can consume proprietary jailhouse and Marvell drivers
# OP-TEE is also applicable to NXP BSP, mainline BSP seems not to have
# a full support for it yet.
MACHINE_FEATURES:append:use-nxp-bsp = " optee jailhouse mrvl8997"

UBOOT_DTB_NAME ??= "${KERNEL_DEVICETREE_BASENAME}.dtb"

IMX_DEFAULT_BOOTLOADER:use-nxp-bsp = "u-boot-imx"

UBOOT_SUFFIX = "bin"

SPL_BINARY = "spl/u-boot-spl.bin"

ATF_PLATFORM = "imx8mp"

IMXBOOT_TARGETS = \
    "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', '${IMXBOOT_TARGETS_BASENAME}_flexspi', \
                                                  '${IMXBOOT_TARGETS_BASENAME}', d)}"

IMX_BOOT_SOC_TARGET = "iMX8MP"

SERIAL_CONSOLES = "115200;ttymxc1"

LOADADDR = ""
UBOOT_MAKE_TARGET = "all"
IMX_BOOT_SEEK = "32"

OPTEE_BIN_EXT = "8mp"
TEE_LOAD_ADDR = "0x56000000"

# Add additional firmware
MACHINE_FIRMWARE:append = " linux-firmware-ath10k"

KERNEL_IMAGETYPE:mx8-nxp-bsp = "Image"
