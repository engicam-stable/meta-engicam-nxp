FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx8mm = " \
		file://0001-mx8m-mini-remove-uart4-from-m4.patch \
"
