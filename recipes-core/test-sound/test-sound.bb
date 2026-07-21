SUMMARY = "Basic script for mtd programming"
DESCRIPTION = "This package provides basic script for mtd programming"
LICENSE = "CLOSED"

inherit allarch

SRC_URI = "file://ring.wav \
           file://megamix_lr.wav \
	   file://playring.sh \
	   file://playleftright.sh \
	"

S = "${UNPACKDIR}"

do_install () {
	install -d ${D}/testfiles
	install -d ${D}${bindir}
	install -m 0755 ${UNPACKDIR}/ring.wav ${D}/testfiles/ring.wav
	install -m 0755 ${UNPACKDIR}/megamix_lr.wav  ${D}/testfiles/megamix_lr.wav
	install -m 0755 ${UNPACKDIR}/playring.sh ${D}${bindir}/playring.sh
	install -m 0755 ${UNPACKDIR}/playleftright.sh ${D}${bindir}/playleftright.sh
}

FILES:${PN} += "testfiles"

