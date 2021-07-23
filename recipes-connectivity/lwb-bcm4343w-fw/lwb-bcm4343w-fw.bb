DESCRIPTION = "Firmware files for use with BCM4343w wifi"
S = "${WORKDIR}"

LICENSE = "CLOSED"

SRC_URI = "file://BCM4343A1_001.002.009.0038.0000_Generic_UART_37_4MHz_wlbga_ref_OTP.hcd"

do_install() {
	install -d ${D}/lib/firmware/brcm
	cp -rf ${WORKDIR}/BCM4343A1_001.002.009.0038.0000_Generic_UART_37_4MHz_wlbga_ref_OTP.hcd ${D}/lib/firmware/brcm/	
}

FILES_${PN} =  "/lib/firmware/brcm/*"
