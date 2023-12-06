
LICENSE = "CLOSED"


SRC_URI += "file://brcmfmac43430-sdio.engi,imx8-icore.txt"

do_install:mx8-nxp-bsp() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.engi,imx8-icore.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.engi,imx8-icore.txt
}

FILES:${PN} = "/lib/firmware/brcm/*"

