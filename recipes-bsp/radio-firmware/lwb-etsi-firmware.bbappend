SUMMIT_URI_BASE:mx95-icore = "https://github.com/Ezurio/SonaIF-Release-Packages/releases/download/LRD-REL-${PV}"

SRC_URI:mx95-icore = "${SUMMIT_URI_BASE}/laird-${BPN}-${PV}.tar.bz2;name=${BPN}"
SRC_URI[lwb-etsi-firmware.md5sum] = "18de016cd6476bb0fca487384c249c43"
SRC_URI[lwb-etsi-firmware.sha256sum] = "ac545b0d096cf60a267e43e24abdf4a7f5f0e57e8a64151b1161f67a05234240"

NO_GENERIC_LICENSE[Ezurio] = "LICENSE"
NO_GENERIC_LICENSE[Cypress] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=53d3628b28a0bc3caea61587feade5f9"
RADIO_VERSION:mx95-icore := "11.39.0.18"