LICENSE = "CLOSED"

SRC_URI = "file://laird-lwb5plus-sdio-sa-firmware-10.4.0.10.tar.bz2"
#SRC_URI = "file://laird-lwb5plus-sdio-sa-firmware-11.39.0.18.tar.bz2"

do_install () {
	install -d ${D}/usr/lib/
	cp -r ${WORKDIR}/lib/* ${D}/usr/lib
	cd ${D}/usr/lib/firmware/brcm
	ln -s brcmfmac4373-sa.txt brcmfmac4373-sdio.eng,microdev3.0.txt
	ln -s brcmfmac4373-sdio-prod_v13.10.246.261.bin brcmfmac4373-sdio.eng,microdev3.0.bin
	ln -s brcmfmac4373-clm-sa.clm_blob brcmfmac4373-sdio.eng,microdev3.0.clm_blob
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/brcm/*"
FILES:${PN} = "/usr/lib"
