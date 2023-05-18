
LICENSE = "CLOSED"


SRC_URI += "file://brcmfmac43430-sdio.fsl,imx6-icore.txt"

do_install() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.fsl,imx6-icore.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.fsl,imx6-icore.txt
}

FILES:${PN} = "/lib/firmware/brcm/*"

