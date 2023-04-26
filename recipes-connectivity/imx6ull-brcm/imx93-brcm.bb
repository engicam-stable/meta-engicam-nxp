
LICENSE = "CLOSED"


SRC_URI += "file://brcmfmac43430-sdio.fsl,imx6ull.txt"
SRC_URI += "file://brcmfmac43430-sdio.fsl,imx6ull.bin"


do_install () {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.fsl,imx6ull.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.fsl,imx93.txt
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.fsl,imx6ull.bin ${D}/lib/firmware/brcm//brcmfmac43430-sdio.fsl,imx93.bin
}

FILES:${PN} = "/lib/*"

