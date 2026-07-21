MACHINEOVERRIDES =. "mx8mm:imx8-icore:"

require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

# Mainline BSP defaults to "generic" cortexa53 configuration,
# adjust it here to include crypto extension which enables
# inline NEON and FPU code generation
DEFAULTTUNE:use-mainline-bsp = "cortexa53-crypto"

MACHINE_FEATURES += "pci wifi bluetooth bcm43430 nxp8987-sdio"

IMX_DEFAULT_BOOTLOADER:use-nxp-bsp = "u-boot-imx"

UBOOT_SUFFIX = "bin"

SPL_BINARY = "spl/u-boot-spl.bin"

ATF_PLATFORM = "imx8mm"

IMXBOOT_TARGETS = "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', '${IMXBOOT_TARGETS_BASENAME}_flexspi', '${IMXBOOT_TARGETS_BASENAME}', d)}"

IMX_BOOT_SOC_TARGET = "iMX8MM"

SERIAL_CONSOLES = "115200;ttymxc1"

LOADADDR = ""
UBOOT_MAKE_TARGET = "all"
IMX_BOOT_SEEK = "33"

OPTEE_BIN_EXT = "8mm"
TEE_LOAD_ADDR = "0xbe000000"
