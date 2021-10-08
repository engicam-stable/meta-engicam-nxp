LICENSE = "CLOSED"

SRC_URI = "file://laird-lwb5plus-sdio-div-firmware-8.5.0.7.tar.bz2"

do_install () {
	install -d ${D}/lib/
	cp -r ${WORKDIR}/lib/* ${D}/lib
	cd ${D}/lib/firmware/brcm
	ln -s brcmfmac4373-div.txt brcmfmac4373-sdio.fsl,imx6ull.txt
}

FILES_${PN} = "/lib/*"
