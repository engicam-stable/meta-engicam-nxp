BRCM_DIR = "${D}${nonarch_base_libdir}/firmware/brcm"

do_install:append:mx6-nxp-bsp() {
  install -d ${BRCM_DIR}
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx6-icore.bin
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx6-icore.txt
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx6-icore.clm_blob
}


do_install:append:mx8() { 
  install -d ${BRCM_DIR}
  ln -sf ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.bin
  ln -sf ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.txt
  ln -sf ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx8-icore.clm_blob
}


do_install:append:mx93-nxp-bsp() { 
  install -d ${BRCM_DIR}
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.bin ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93.bin
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.txt ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93.txt
  ln -sf -r ${BRCM_DIR}/brcmfmac43430-sdio.clm_blob ${BRCM_DIR}/brcmfmac43430-sdio.engi,imx93.clm_blob
}
