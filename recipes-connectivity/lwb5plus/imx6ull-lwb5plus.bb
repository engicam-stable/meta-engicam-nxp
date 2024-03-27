LICENSE = "CLOSED"

SRC_URI = "file://laird-lwb5plus-sdio-sa-firmware-10.4.0.10.tar.bz2"

do_install () {
	install -d ${D}/lib/
	cp -r ${WORKDIR}/lib/* ${D}/lib
	cd ${D}/lib/firmware/brcm
	ln -s brcmfmac4373-sa.txt brcmfmac4373-sdio.fsl,imx6ull.txt
	ln -s brcmfmac4373-sdio-prod_v13.10.246.261.bin brcmfmac4373-sdio.fsl,imx6ull.bin
	ln -s brcmfmac4373-clm-sa.clm_blob brcmfmac4373-sdio.fsl,imx6ull.clm_blob
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/brcm/*"
