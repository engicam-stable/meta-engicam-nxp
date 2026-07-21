#
#@DESCRIPTION: brcm_patchram_plus network apps

PR = "r0"
LICENSE = "GPL-2.0-only"

S = "${UNPACKDIR}"

LIC_FILES_CHKSUM = "file://brcm_patchram_plus.c;md5=327c4eca9c6d733f8df7001d247c0806"
SRC_URI = "file://brcm_patchram_plus.c"

do_compile() {
             ${CC} ${CFLAGS} ${LDFLAGS} -o brcm_patchram_plus brcm_patchram_plus.c
}

do_install() {
             install -d ${D}${bindir}/
             install -m 0755 ${S}/brcm_patchram_plus ${D}${bindir}/
}

FILES_${PN} = "${bindir}/brcm_patchram_plus"
