LICENSE = "CLOSED"

SRC_URI = "\
			file://brcmfmac4373-sdio.fsl,imx6ull.bin \
			file://brcmfmac4373-sdio.fsl,imx6ull.txt \
			file://BCM4373A0-sdio-sa.hcd \
			"


do_install () {
	install -d ${D}/lib/firmware/brcm
	install -m 0755 ${WORKDIR}/brcmfmac4373-sdio.fsl,imx6ull.txt ${D}/lib/firmware/brcm/brcmfmac4373-sdio.fsl,imx6ull.txt
	install -m 0755 ${WORKDIR}/brcmfmac4373-sdio.fsl,imx6ull.bin ${D}/lib/firmware/brcm/brcmfmac4373-sdio.fsl,imx6ull.bin 
	install -m 0755 ${WORKDIR}/BCM4373A0-sdio-sa.hcd ${D}/lib/firmware/brcm/BCM4373A0-sdio-sa.hcd
}

FILES:${PN} = "/lib/*"
