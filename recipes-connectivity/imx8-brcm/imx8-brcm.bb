
LICENSE = "CLOSED"


SRC_URI += "file://brcmfmac43430-sdio.engi,icore-imx8mm.txt"
SRC_URI += "file://brcmfmac43430-sdio.engi,icore-imx8mp.txt"


do_install_mx8mm() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.engi,icore-imx8mm.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.engi,icore-imx8mm.txt
}

do_install_mx8mp() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.engi,icore-imx8mp.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.engi,icore-imx8mp.txt
}

FILES_${PN} = "/lib/firmware/brcm/*"

