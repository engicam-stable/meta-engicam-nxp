SUMMARY = "Cantest for cansend example on engicam iCoreM6"
DESCRIPTION = "This package provides basic fuction for cantest programming"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/copyright;md5=e96fda27b90c4f4429d691604b6e5932"
SECTION = "console/tools"
DEPENDS = "virtual/kernel"
PR = "r1"

SRC_URI = "file://can.h \
           file://cansend.c \
           file://lib.c \
           file://lib.h \
           file://Makefile \
           file://raw.h \
	   file://copyright"

do_compile () {
    cd ${WORKDIR}
    make 
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/cantest ${D}${bindir}/cantest
}

FILES:${PN} = "${bindir}/*"
INSANE_SKIP:${PN} = "ldflags"


