# Copyright 2025 Engicam

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

ENGICAM_FIRMWARE_SRC ?= "git://github.com/murata-wireless/cyw-bt-patch.git;protocol=https"
SRCBRANCH_eng-firmware = "master"
SRC_URI += " \
    ${ENGICAM_FIRMWARE_SRC};branch=${SRCBRANCH_eng-firmware};destsuffix=eng-firmware;name=eng-firmware \
    file://brcmfmac4373-sdio.txt \
"

SRCREV_eng-firmware = "64ac86708253e12d7089cf75ef8dcc9b30594958"

BRCM_DIR = "${D}${nonarch_base_libdir}/firmware/brcm"

do_install:append () {
    install -d ${D}${sysconfdir}/firmware
    # Install Murata 4373 bluetooth firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2BC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_FCC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_JRL.hcd ${D}${sysconfdir}/firmware

    # Install 4373 wifi nvram file
    install -m 0644 ${UNPACKDIR}/brcmfmac4373-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm

    # Create symlinks for 4373
    install -d ${BRCM_DIR}
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.bin ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.txt ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.clm_blob ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.clm_blob

    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.clm_blob
}

do_install:append:mx6ull-microdev () {
    install -d ${D}${sysconfdir}/firmware
    # Install Murata 4373 bluetooth firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2BC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_FCC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_JRL.hcd ${D}${sysconfdir}/firmware

    # Install 4373 wifi nvram file
    install -m 0644 ${UNPACKDIR}/brcmfmac4373-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm

    # Create symlinks for 4373
    install -d ${BRCM_DIR}
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.bin ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.txt ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.clm_blob ${BRCM_DIR}/brcmfmac4373-sdio.fsl,imx6ull.clm_blob

    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.fsl,imx6ull.clm_blob
}

do_install:append:mx8ulp-microdev () {
    install -d ${D}${sysconfdir}/firmware
    # Install Murata 4373 bluetooth firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2BC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_FCC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_JRL.hcd ${D}${sysconfdir}/firmware

    # Install 4373 wifi nvram file
    install -m 0644 ${UNPACKDIR}/brcmfmac4373-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm

    # Create symlinks for 4373
    install -d ${BRCM_DIR}
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.bin ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx8ulp.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.txt ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx8ulp.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.clm_blob ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx8ulp.clm_blob

    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8ulp.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8ulp.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8ulp.clm_blob
}

do_install:append:imx91-microdev () {
    install -d ${D}${sysconfdir}/firmware
    # Install Murata 4373 bluetooth firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/BCM4373A0_001.001.025.0103.0156.JRL.2BC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_FCC.hcd ${D}${sysconfdir}/firmware
    install -m 0644 ${UNPACKDIR}/eng-firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_JRL.hcd ${D}${sysconfdir}/firmware

    # Install 4373 wifi nvram file
    install -m 0644 ${UNPACKDIR}/brcmfmac4373-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm

    # Create symlinks for 4373
    install -d ${BRCM_DIR}
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.bin ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx91.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.txt ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx91.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac4373-sdio.clm_blob ${BRCM_DIR}/brcmfmac4373-sdio.engi,imx91.clm_blob

    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91.clm_blob
}

do_install:append:imx8-icore () {
    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.clm_blob
}

do_install:append:imx91-icore () {
    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91-icore.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91-icore.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx91-icore.clm_blob
}

do_install:append:imx93-icore () {
    # Create symlinks for 43430
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93-icore.bin
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93-icore.txt
    ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93-icore.clm_blob
}

FILES:${PN}-bcm4373 += " \
       ${sysconfdir}/firmware/BCM4373A0_001.001.025.0103.0156.JRL.2AE.hcd \
       ${sysconfdir}/firmware/BCM4373A0_001.001.025.0103.0156.JRL.2BC.hcd \
       ${sysconfdir}/firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_FCC.hcd \
       ${sysconfdir}/firmware/CYW4373A0_001.001.025.0119.0000.2AE.USB_JRL.hcd \
       ${nonarch_base_libdir}/firmware/brcm/brcmfmac4373-sdio* \
"
