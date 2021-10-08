
LICENSE = "CLOSED"


SRC_URI = "file://brcmfmac43430-sdio.fsl,imx6ull.txt"


do_install () {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.fsl,imx6ull.txt ${D}//lib/firmware/brcm/brcmfmac43430-sdio.fsl,imx6ull.txt
}

FILES_${PN} = "/lib/firmware/brcm/*"

